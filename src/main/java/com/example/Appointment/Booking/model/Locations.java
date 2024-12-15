package com.example.Appointment.Booking.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "Location")
public class Locations {
    List<String> location;
}
