package com.example.Ticket.Booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
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
