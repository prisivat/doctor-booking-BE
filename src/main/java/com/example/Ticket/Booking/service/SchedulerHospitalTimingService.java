package com.example.Ticket.Booking.service;

import com.example.Ticket.Booking.model.*;
import com.itextpdf.text.DocumentException;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface SchedulerHospitalTimingService {

    void bookAppointment(AppointmentDetails appointmentDetails) throws MessagingException, TemplateException, IOException, DocumentException;

    List<String> getBookedTimings(SchedulerHospitalTiming appointmentTime);

    List<BookingHistory> getBookingHistory(String userName);

    List<BookingHistory> getUpcomingAppointment(String userName);

    List<SchedulerHospitalTiming> getSchedulerAppointDtl(SchedulerGetTiming schedulerDetails);

    List<SchedulerHospitalTiming> getTotalAppointmentDtls(SchedulerGetTiming schedulerDetails);

    SchedulerHospitalTiming getPatientDtlsByBookingId(String bookingId);

    SchedulerHospitalTiming cancelBooking(String bookingId, String patientName) throws MessagingException;

    SchedulerHospitalTiming reschedulingBooking(SchedulerHospitalTiming schedulerHospitalTiming) throws MessagingException, TemplateException, IOException, DocumentException;
}
