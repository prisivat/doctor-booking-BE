package com.example.Ticket.Booking.model;

import lombok.Data;

import java.util.List;

@Data
public class Specalist {
    private String spclName;
    private List<DoctorsList> doctorsList;
}
