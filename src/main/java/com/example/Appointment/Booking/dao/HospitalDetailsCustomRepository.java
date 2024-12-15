package com.example.Appointment.Booking.dao;

import com.example.Appointment.Booking.model.HospitalDetails;

import java.util.List;
import java.util.Set;

public interface HospitalDetailsCustomRepository {
    HospitalDetails findByFilters(String location, Set<String> spclName, List<String> hospitalNames, Double maxCost);

//    HospitalDetails findByLocationAndHospitalName(String location, List<String > hospitalName);
}
