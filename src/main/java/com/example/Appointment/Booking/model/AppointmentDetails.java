package com.example.Appointment.Booking.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "AppoinmentDetails")
public class AppointmentDetails {

    private String userName;

    private transient String schedulerEmail;

    private String docName;

    private String hospitalName;

    private String specialist;

    private String location;

    @NotEmpty(message = "Patient Name is mandatory")
    private String patientName;

    @NotEmpty(message = "Age is mandatory")
    private String age;

    @NotEmpty(message = "Gender is mandatory")
    private String gender;

    @NotEmpty(message = "Phone Number must not be blank")
    @Size(min = 10, max = 10, message = "Phone Number must be 10 digits")
    private String phoneNumber;

    @NotEmpty(message = "Date must not be blank")
    private String date;//yyyy-MM-dd

    @NotEmpty(message = "Time must not be blank")
    private String time;//HH:mm

    private String bookingId;

}
