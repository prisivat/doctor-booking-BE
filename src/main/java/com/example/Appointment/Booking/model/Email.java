package com.example.Appointment.Booking.model;

import lombok.Data;

@Data
public class Email {
	private String toEmail;
	private String subject;
	private String emailMessage;
	@Override
	public String toString() {
		return String.format("Email{To=%s, Subject=%s, Message=%s}", getToEmail(), getSubject(), getEmailMessage());
	}
}
