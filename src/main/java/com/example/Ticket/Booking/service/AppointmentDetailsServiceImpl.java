package com.example.Ticket.Booking.service;

import com.example.Ticket.Booking.dao.AppointmentDetailsRepository;
import com.example.Ticket.Booking.dao.AppointmentTimeRepository;
import com.example.Ticket.Booking.dao.UserRepository;
import com.example.Ticket.Booking.model.AppointmentDetails;
import com.example.Ticket.Booking.model.AppointmentTime;
import com.example.Ticket.Booking.model.BookingHistory;
import com.example.Ticket.Booking.model.User;
import com.itextpdf.text.DocumentException;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AppointmentDetailsServiceImpl implements AppointmentDetailsService{

    @Autowired
    AppointmentDetailsRepository appointmentDetailsRepository;

    @Autowired
    AppointmentTimeRepository appointmentTimeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;
    @Override
    public void bookAppointment(AppointmentDetails appointmentDetails) throws MessagingException, TemplateException, IOException, DocumentException {
        User user = userRepository.findByUserName(appointmentDetails.getUserName());
        setBookingId(appointmentDetails);
        appointmentDetailsRepository.save(appointmentDetails);
        AppointmentTime appointmentTime = new AppointmentTime();
        appointmentTime.setLocation(appointmentDetails.getLocation());
        appointmentTime.setHospitalName(appointmentDetails.getHospitalName());
        appointmentTime.setSpecialist(appointmentDetails.getSpecialist());
        appointmentTime.setDocName(appointmentDetails.getDocName());
        appointmentTime.setDate(appointmentDetails.getDate());
        appointmentTime.setTime(appointmentDetails.getTime());
        appointmentTimeRepository.save(appointmentTime);
        emailService.sentAppointmentDtlsToPatient(appointmentDetails,user);
        emailService.sentAppointmentDtlsToHospital(appointmentDetails);

    }

    @Override
    public List<String> getBookedTimings(AppointmentTime appointmentTime) {
        List<AppointmentTime> appointmentTimeList = appointmentTimeRepository.findByLocationAndHospitalNameAndSpecialistAndDocNameAndDate(
                appointmentTime.getLocation(), appointmentTime.getHospitalName(), appointmentTime.getSpecialist(),
                appointmentTime.getDocName(), appointmentTime.getDate());
        List<String> bookedTimings = appointmentTimeList.stream().map(AppointmentTime::getTime).collect(Collectors.toList());

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
        List<AppointmentDetails> appointmentDetails = appointmentDetailsRepository.findByUserNameAndDateAfter(userName, bookingDate);
        List<BookingHistory> bookingHistories = new ArrayList<>();
        appointmentDetails.stream().forEach(appoinment -> {
            BookingHistory bookingHistory = new BookingHistory();
            BeanUtils.copyProperties(appoinment, bookingHistory);
            bookingHistory.setDateAndTime(appoinment.getDate()+ " " + appoinment.getTime());
            bookingHistories.add(bookingHistory);
        });


        return bookingHistories;
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
