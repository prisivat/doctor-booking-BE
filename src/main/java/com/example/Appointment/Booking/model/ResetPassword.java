package com.example.Appointment.Booking.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ResetPassword {
    @NotEmpty(message = "Token is Mandatory")
    private String token;

    @NotEmpty(message = "New Password is Mandatory")
    private String newPassword;
}
