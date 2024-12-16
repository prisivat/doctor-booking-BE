package com.example.Appointment.Booking.controller;

import com.example.Appointment.Booking.dao.LocationRepository;
import com.example.Appointment.Booking.model.*;
import com.example.Appointment.Booking.service.HospitalDetailsService;
import com.example.Appointment.Booking.model.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hospital")
@CrossOrigin(origins = "http://localhost:3000")
public class HospitalController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    HospitalDetailsService hospitalDetailsService;

    @GetMapping(value = "/hospitalDetails")
    public ResponseEntity<List<HospitalDetails>> fetchHsptlDetails() {

        List<HospitalDetails> hospitalDtls = hospitalDetailsService.getHsptlDtls();
        return new ResponseEntity<>(hospitalDtls, HttpStatus.OK);
    }
    @GetMapping(value = "/locations")
    public ResponseEntity<List<String>> getLocations() {
        List<Locations> locations = locationRepository.findAll();
        List<String> locList = locations.get(0).getLocation().stream().sorted().collect(Collectors.toList());
        return new ResponseEntity<>(locList, HttpStatus.OK);
    }

    @GetMapping(value="/filterDtls/{location}")
    public ResponseEntity<FilterDetails> getFltrDtlsByLoc(@PathVariable String location){
        FilterDetails filterDetails = hospitalDetailsService.getFltrDtlsByLocation(location);
        return new ResponseEntity<>(filterDetails, HttpStatus.OK);
    }

    @PostMapping(value = "/filteredDtls")
    public ResponseEntity<HospitalDetails> getFilteredHospitalDetails(@Valid @RequestBody FilterDetails filterDetails){
        HospitalDetails hospitalDetailsList = hospitalDetailsService.getHospitalDetailsByFilter(filterDetails);
        return new ResponseEntity<>(hospitalDetailsList, HttpStatus.OK);
    }

    @PostMapping(value = "/specialistName")
    public ResponseEntity <Set<String>> getSpecialistDetails(@Valid @RequestBody String location){
        Set<String> specialistDtls = hospitalDetailsService.getListOfSpclName(location);

        return new ResponseEntity<>(specialistDtls, HttpStatus.OK);
    }

    @PostMapping(value = "/specialist")
    public ResponseEntity<List<HospitalDtlBySpclty>> fetchListOfSpecialist(@Valid @RequestBody SpecialistName spclList){
        List<HospitalDtlBySpclty> listOfHoDtls = hospitalDetailsService.getSpecialistDetails(spclList);
        return new ResponseEntity<>(listOfHoDtls, HttpStatus.OK);

    }


}
