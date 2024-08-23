package com.example.Ticket.Booking.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Login {
    @NotEmpty(message = "userName is mandatory")
    private String userName;

    @NotEmpty(message = "Password is mandatory")
    private String password;
    
}
