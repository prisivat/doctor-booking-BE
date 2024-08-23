package com.example.Ticket.Booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;

import java.util.UUID;


@Data
@Document(collection = "SignIn")
public class User {
    @Id
    @JsonIgnore
    private String userName = UUID.randomUUID().toString().substring(0, 8);
    @NotEmpty(message = "First Name is mandatory")
    private String firstName;
    @NotEmpty(message = "Last Name is mandatory")
    private String lastName;
    @NotEmpty(message = "Phone Number must not be blank")
    @Size(min = 10, max = 10, message = "Phone Number must be 10 digits")
    private String phoneNumber;
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "EmailId is mandatory")
    private String email;
    @NotEmpty(message = "Password is mandatory")
    private String password;
}