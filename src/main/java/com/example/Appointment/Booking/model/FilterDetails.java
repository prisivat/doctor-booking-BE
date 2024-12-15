package com.example.Appointment.Booking.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class FilterDetails {
    private String location;
    private List<String> hospitalName;
    private Set<String> specialist;
    private Integer cost;
}
