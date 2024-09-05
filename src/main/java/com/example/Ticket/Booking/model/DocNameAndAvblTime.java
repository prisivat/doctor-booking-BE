package com.example.Ticket.Booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DocNameAndAvblTime {

    private String docName;
    private Integer cost;
    private List<String> availableTime = new ArrayList<>(); ;
}
