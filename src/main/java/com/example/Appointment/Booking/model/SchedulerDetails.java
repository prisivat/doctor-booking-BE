package com.example.Appointment.Booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "SchedulerDetails")
public class SchedulerDetails {
    @Id
    private String schedulerUserName;
    private String password;
    private String hospitalName;
    private String email;
    private String location;
}

