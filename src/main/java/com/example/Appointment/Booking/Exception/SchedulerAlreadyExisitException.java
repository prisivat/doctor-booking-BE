package com.example.Appointment.Booking.Exception;

public class SchedulerAlreadyExisitException extends RuntimeException{


    private static final long serialVersionUID = 1L;

    public SchedulerAlreadyExisitException(String exception) {
        super(exception);
    }
}
