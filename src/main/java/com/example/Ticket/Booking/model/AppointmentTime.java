package com.example.Ticket.Booking.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "AppoinmentTime")
public class AppointmentTime {

    private String docName;

    private String hospitalName;

    private String specialist;

    private String location;

    private String date;

    private String time;

}
