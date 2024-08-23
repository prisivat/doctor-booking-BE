package com.example.Ticket.Booking.Exception;

public class PasswordIncorrectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PasswordIncorrectException(String exception) {
	    super(exception);
	  }
}
