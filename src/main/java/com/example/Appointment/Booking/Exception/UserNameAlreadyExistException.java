package com.example.Appointment.Booking.Exception;

public class UserNameAlreadyExistException extends RuntimeException{


    private static final long serialVersionUID = 1L;

    public UserNameAlreadyExistException(String exception) {
        super(exception);
    }
}
