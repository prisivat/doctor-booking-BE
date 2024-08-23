package com.example.Ticket.Booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "HospitalDetails")
public class HospitalDetails {


    private String location;
    private List<HospitalDtl> hospitalDetails;

}
