package com.example.Ticket.Booking.service;

import com.example.Ticket.Booking.model.HospitalDetails;
import com.example.Ticket.Booking.model.HospitalDtl;
import com.example.Ticket.Booking.model.HospitalDtlBySpclty;
import com.example.Ticket.Booking.model.SpecalistName;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HospitalDetailsService {
    Map<String,List<HospitalDtl>> getHsptlDtls(List<String> location);

    Map<String, Set<String>> getListOfSpclName(List<String> location);

    List<HospitalDtlBySpclty> getSpecalistDetails(SpecalistName spclList);

}
