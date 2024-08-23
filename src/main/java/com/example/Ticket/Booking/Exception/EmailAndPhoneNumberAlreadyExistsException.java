package com.example.Ticket.Booking.Exception;

public class EmailAndPhoneNumberAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAndPhoneNumberAlreadyExistsException(String exception) {
        super(exception);
    }
}