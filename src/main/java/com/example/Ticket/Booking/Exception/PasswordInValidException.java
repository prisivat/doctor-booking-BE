package com.example.Ticket.Booking.Exception;

public class PasswordInValidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PasswordInValidException(String exception) {
        super(exception);
    }
}
