package com.example.Ticket.Booking.model;

import lombok.Data;

import java.util.List;

@Data
public class HospitalDtl {
    private String name;
    private List<Specalist> specalist;
}
