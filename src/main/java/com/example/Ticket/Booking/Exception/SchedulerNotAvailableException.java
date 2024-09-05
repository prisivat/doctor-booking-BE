package com.example.Ticket.Booking.Exception;

public class SchedulerNotAvailableException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SchedulerNotAvailableException(String exception) {
        super(exception);
    }
}