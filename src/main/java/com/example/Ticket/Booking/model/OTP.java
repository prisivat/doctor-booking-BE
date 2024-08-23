package com.example.Ticket.Booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "otp")
@Data
public class OTP {

    @NotEmpty(message = "OTP is mandatory")
    private String otp;

    private String userName;

    private LocalDateTime createdAt;


}
