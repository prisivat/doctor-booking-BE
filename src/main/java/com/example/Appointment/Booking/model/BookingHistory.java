package com.example.Appointment.Booking.model;

import lombok.Data;

@Data
public class BookingHistory {
    private String docName;

    private String hospitalName;

    private String specialist;

    private String location;

    private String patientName;

    private String age;

    private String gender;

    private String phoneNumber;

    private String dateAndTime;

    private String bookingId;
}
