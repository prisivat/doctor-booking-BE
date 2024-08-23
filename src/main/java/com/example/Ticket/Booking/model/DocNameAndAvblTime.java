package com.example.Ticket.Booking.model;

import lombok.Data;

import java.util.List;

@Data
public class DocNameAndAvblTime {

    private String docName;
    private List<String> availableTime;
}
