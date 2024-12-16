package com.example.Appointment.Booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class HospitalDtl {
    private String name;
    private List<Specialist> specialist = new ArrayList<>(); ;
}
