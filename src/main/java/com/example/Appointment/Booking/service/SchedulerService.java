package com.example.Appointment.Booking.service;

import com.example.Appointment.Booking.model.SchedulerDetails;
import com.example.Appointment.Booking.model.SchedulerOTP;

public interface SchedulerService {

    void newScheduler(SchedulerDetails schedulerDetails) ;

    void verifyOtp(SchedulerOTP otp);

    void schedulerSignIn(SchedulerDetails schedulerDetails);

    String schedulerLogin(SchedulerDetails schedulerDetails);
}
