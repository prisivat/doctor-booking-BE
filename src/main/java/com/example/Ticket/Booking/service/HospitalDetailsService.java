package com.example.Ticket.Booking.service;

import com.example.Ticket.Booking.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HospitalDetailsService {
    List<HospitalDetails> getHsptlDtls();

    Set<String> getListOfSpclName(String location);

    List<HospitalDtlBySpclty> getSpecalistDetails(SpecalistName spclList);

    FilterDetails getFltrDtlsByLocation(String location);

    HospitalDetails getHospitalDetailsByFilter(FilterDetails filterDetails);
}
