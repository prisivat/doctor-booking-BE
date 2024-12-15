package com.example.Appointment.Booking.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "HospitalDetails")
public class HospitalDetails {

    private String location;

    private List<HospitalDtl> hospitalDetails = new ArrayList<>();

    public HospitalDetails() {
    }


}
