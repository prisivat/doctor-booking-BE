package com.example.Appointment.Booking.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SchedulerHospitalTiming")
@Data
public class SchedulerHospitalTiming {

    private String userName;

    private String docName;

    private String hospitalName;

    private String specialist;

    private String location;

    private String patientName;

    private String age;

    private String gender;

    private String phoneNumber;

    @NotEmpty(message = "Date must not be blank")
    private String date;//yyyy-MM-dd

    private String time;//HH:mm

    private String bookingId;
}
