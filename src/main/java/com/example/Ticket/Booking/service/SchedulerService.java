package com.example.Ticket.Booking.service;

import com.example.Ticket.Booking.model.OTP;
import com.example.Ticket.Booking.model.SchedulerDetails;
import com.example.Ticket.Booking.model.SchedulerOTP;
import jakarta.mail.MessagingException;

public interface SchedulerService {

    void newScheduler(SchedulerDetails schedulerDetails) ;

    void verifyOtp(SchedulerOTP otp);

    void schedulerSignIn(SchedulerDetails schedulerDetails);

    void schedulerLogin(SchedulerDetails schedulerDetails);
}
