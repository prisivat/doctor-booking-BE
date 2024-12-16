package com.example.Appointment.Booking.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SpecialistName {
    private String location;
    private Set<String> specialistName;
}
