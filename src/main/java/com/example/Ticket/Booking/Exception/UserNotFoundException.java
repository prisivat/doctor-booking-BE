package com.example.Ticket.Booking.Exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String exception) {
	    super(exception);
	  }
}