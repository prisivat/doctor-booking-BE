package com.example.Appointment.Booking.service;

import com.example.Appointment.Booking.model.FilterDetails;
import com.example.Appointment.Booking.model.HospitalDetails;
import com.example.Appointment.Booking.model.HospitalDtlBySpclty;
import com.example.Appointment.Booking.model.SpecalistName;
import com.example.Appointment.Booking.model.*;

import java.util.List;
import java.util.Set;

public interface HospitalDetailsService {
    List<HospitalDetails> getHsptlDtls();

    Set<String> getListOfSpclName(String location);

    List<HospitalDtlBySpclty> getSpecalistDetails(SpecalistName spclList);

    FilterDetails getFltrDtlsByLocation(String location);

    HospitalDetails getHospitalDetailsByFilter(FilterDetails filterDetails);
}
