package com.example.Ticket.Booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Specalist {
    private String spclName;
    private List<DoctorsList> doctorsList = new ArrayList<>(); ;
}
