package com.example.Ticket.Booking.Exception;

public class LocationInValidException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public LocationInValidException(String exception) {
        super(exception);
    }
}
