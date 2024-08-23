package com.example.Ticket.Booking.service;

import com.example.Ticket.Booking.model.AppointmentDetails;
import com.example.Ticket.Booking.model.AppointmentTime;
import com.example.Ticket.Booking.model.BookingHistory;
import com.itextpdf.text.DocumentException;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface AppointmentDetailsService {

    void bookAppointment(AppointmentDetails appointmentDetails) throws MessagingException, TemplateException, IOException, DocumentException;

    List<String> getBookedTimings(AppointmentTime appointmentTime);

    List<BookingHistory> getBookingHistory(String userName);

    List<BookingHistory> getUpcomingAppointment(String userName);
}
