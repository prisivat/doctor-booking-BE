package com.example.Appointment.Booking.Exception;

public class HospitalDetailNotFoundException  extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public HospitalDetailNotFoundException(String exception) {
        super(exception);
    }
}