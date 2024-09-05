package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.HospitalDetails;
import jdk.jfr.Registered;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface HospitalDetailsCustomRepository {
    HospitalDetails findByFilters(String location, Set<String> spclName, List<String> hospitalNames, Double maxCost);

//    HospitalDetails findByLocationAndHospitalName(String location, List<String > hospitalName);
}
