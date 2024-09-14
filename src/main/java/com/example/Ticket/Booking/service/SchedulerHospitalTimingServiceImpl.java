package com.example.Ticket.Booking.service;

import com.example.Ticket.Booking.dao.*;
import com.example.Ticket.Booking.model.*;
import com.itextpdf.text.DocumentException;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SchedulerHospitalTimingServiceImpl implements SchedulerHospitalTimingService {

    @Autowired
    AppointmentDetailsRepository appointmentDetailsRepository;

    @Autowired
    SchedulerHospitalTimingRepository schedulerHospitalTimingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    HospitalDetailsRepository hospitalDetailsRepository;
    @Override
    public void bookAppointment(AppointmentDetails appointmentDetails) throws MessagingException, TemplateException, IOException, DocumentException {
        User user = userRepository.findByUserName(appointmentDetails.getUserName());
        setBookingId(appointmentDetails);
        appointmentDetailsRepository.save(appointmentDetails);// this is Patient DB
        SchedulerHospitalTiming schedulerHospitalTiming = new SchedulerHospitalTiming();
        schedulerHospitalTiming.setLocation(appointmentDetails.getLocation());
        schedulerHospitalTiming.setHospitalName(appointmentDetails.getHospitalName());
        schedulerHospitalTiming.setSpecialist(appointmentDetails.getSpecialist());
        schedulerHospitalTiming.setDocName(appointmentDetails.getDocName());
        schedulerHospitalTiming.setDate(appointmentDetails.getDate());
        schedulerHospitalTiming.setTime(appointmentDetails.getTime());
        schedulerHospitalTiming.setPatientName(appointmentDetails.getPatientName());
        schedulerHospitalTiming.setAge(appointmentDetails.getAge());
        schedulerHospitalTiming.setGender(appointmentDetails.getGender());
        schedulerHospitalTiming.setBookingId(appointmentDetails.getBookingId());
        schedulerHospitalTiming.setPhoneNumber(appointmentDetails.getPhoneNumber());
        schedulerHospitalTiming.setUserName(appointmentDetails.getUserName());

        schedulerHospitalTimingRepository.save(schedulerHospitalTiming);// scheduler DB
        emailService.sentAppointmentDtlsToPatient(appointmentDetails,user);
        emailService.sentAppointmentDtlsToHospital(appointmentDetails);

    }

    @Override
    public List<String> getBookedTimings(SchedulerHospitalTiming appointmentTime) {
        List<SchedulerHospitalTiming> appointmentTimeList = schedulerHospitalTimingRepository.findByLocationAndHospitalNameAndSpecialistAndDocNameAndDate(
                appointmentTime.getLocation(), appointmentTime.getHospitalName(), appointmentTime.getSpecialist(),
                appointmentTime.getDocName(), appointmentTime.getDate());
        List<String> bookedTimings = appointmentTimeList.stream().map(SchedulerHospitalTiming::getTime).collect(Collectors.toList());

        return bookedTimings;
    }

    @Override
    public List<BookingHistory> getBookingHistory(String userName) {
        List<AppointmentDetails> appointmentDetails = appointmentDetailsRepository.findByUserName(userName);
        List<BookingHistory> bookingHistories = new ArrayList<>();
        appointmentDetails.stream().forEach(appoinment -> {
            BookingHistory bookingHistory = new BookingHistory();
            BeanUtils.copyProperties(appoinment, bookingHistory);
            bookingHistory.setDateAndTime(appoinment.getDate()+ " " + appoinment.getTime());
            bookingHistories.add(bookingHistory);
        });
        return bookingHistories;
    }

    @Override
    public List<BookingHistory> getUpcomingAppointment(String userName) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String bookingDate = simpleDateFormat.format(date);
        List<AppointmentDetails> appointmentDetails = appointmentDetailsRepository.findByUserNameAndDateGreaterThanEqual(userName, bookingDate);
        List<BookingHistory> bookingHistories = new ArrayList<>();
        appointmentDetails.stream().forEach(appoinment -> {
            BookingHistory bookingHistory = new BookingHistory();
            BeanUtils.copyProperties(appoinment, bookingHistory);
            bookingHistory.setDateAndTime(appoinment.getDate()+ " " + appoinment.getTime());
            bookingHistories.add(bookingHistory);
        });


        return bookingHistories;
    }

    @Override
    public List<SchedulerHospitalTiming> getSchedulerAppointDtl(SchedulerGetTiming schedulerDetails) {
        List<SchedulerHospitalTiming> schedulerHospitalTimings = schedulerHospitalTimingRepository.findByLocationAndHospitalNameAndDate(schedulerDetails.getLocation(), schedulerDetails.getHospitalName(), schedulerDetails.getDate());
        return schedulerHospitalTimings;
    }

    @Override
    public List<SchedulerHospitalTiming> getTotalAppointmentDtls(SchedulerGetTiming schedulerDetails) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String bookingDate = simpleDateFormat.format(date);
        List<SchedulerHospitalTiming> schedulerHospitalTimings = schedulerHospitalTimingRepository.findByLocationAndHospitalNameAndDateGreaterThanEqual(schedulerDetails.getLocation(), schedulerDetails.getHospitalName(), bookingDate);
        return schedulerHospitalTimings;
    }

    @Override
    public SchedulerHospitalTiming getPatientDtlsByBookingId(String bookingId) {
        SchedulerHospitalTiming schedulerHospitalTiming = schedulerHospitalTimingRepository.findByBookingId(bookingId);
        return schedulerHospitalTiming;
    }

    @Override
    public SchedulerHospitalTiming cancelBooking(String bookingId, String patientName) throws MessagingException {
        SchedulerHospitalTiming schedulerHospitalTiming = schedulerHospitalTimingRepository.findByBookingIdAndPatientName(bookingId, patientName);
        String email = hospitalDetailsRepository.findEmailByLocationAndHospitalName(schedulerHospitalTiming.getLocation(), schedulerHospitalTiming.getHospitalName());
        User user = userRepository.findByUserName(schedulerHospitalTiming.getUserName());
        schedulerHospitalTimingRepository.deleteByBookingIdAndPatientName(bookingId, patientName);
        appointmentDetailsRepository.deleteByBookingIdAndPatientName(bookingId, patientName);
        emailService.sendCancelledMailToPatient(schedulerHospitalTiming, user);
        emailService.sendCancelledMailToHospital(schedulerHospitalTiming, email);

        return schedulerHospitalTiming;
    }

    @Override
    public SchedulerHospitalTiming reschedulingBooking(SchedulerHospitalTiming schedulerHospitalTiming) throws MessagingException, TemplateException, IOException, DocumentException {

        AppointmentDetails appointmentDetails = appointmentDetailsRepository.findByBookingIdAndPatientName(schedulerHospitalTiming.getBookingId(), schedulerHospitalTiming.getPatientName());
        appointmentDetails.setDate(schedulerHospitalTiming.getDate());
        appointmentDetails.setTime(schedulerHospitalTiming.getTime());
        setBookingId(appointmentDetails);
        appointmentDetailsRepository.save(appointmentDetails);
        SchedulerHospitalTiming schedulerHospitalTiming1 = schedulerHospitalTimingRepository.findByBookingIdAndPatientName(schedulerHospitalTiming.getBookingId(), schedulerHospitalTiming.getPatientName());
        schedulerHospitalTiming1.setDate(schedulerHospitalTiming.getDate());
        schedulerHospitalTiming1.setTime(schedulerHospitalTiming.getTime());
        schedulerHospitalTiming1.setBookingId(appointmentDetails.getBookingId());
        schedulerHospitalTimingRepository.save(schedulerHospitalTiming1);
        User user = userRepository.findByUserName(schedulerHospitalTiming1.getUserName());
        schedulerHospitalTimingRepository.deleteByBookingIdAndPatientName(schedulerHospitalTiming.getBookingId(), schedulerHospitalTiming.getPatientName());
        appointmentDetailsRepository.deleteByBookingIdAndPatientName(schedulerHospitalTiming.getBookingId(), schedulerHospitalTiming.getPatientName());

        emailService.sentRescheduleAppointmentDtlsToHospital(appointmentDetails);
        emailService.sentRescheduleAppointmentDtlsToPatient(appointmentDetails, user);
        return null;
    }

    private void setBookingId(AppointmentDetails appointmentDetails) {
        String chars = "0123456789";
        StringBuilder stringBuilder;
        Random random1 = new Random();

        stringBuilder = new StringBuilder();
        for (int j = 0; j < 9; j++) {
            int index = random1.nextInt(chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        appointmentDetails.setBookingId(stringBuilder.toString());

    }


}
