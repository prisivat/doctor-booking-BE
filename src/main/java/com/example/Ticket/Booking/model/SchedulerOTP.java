package com.example.Ticket.Booking.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "otpScheduler")
@Data
public class SchedulerOTP {


    private String otp;
    private String hospitalName;
    private String location;
    private String createdAt;


}
