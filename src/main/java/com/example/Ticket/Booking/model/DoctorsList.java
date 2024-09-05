package com.example.Ticket.Booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DoctorsList {

    List<DocNameAndAvblTime> docNameAndAvblTime = new ArrayList<>(); ;
}
