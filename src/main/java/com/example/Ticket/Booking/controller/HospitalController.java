package com.example.Ticket.Booking.controller;

import com.example.Ticket.Booking.Exception.EmailAndPhoneNumberAlreadyExistsException;
import com.example.Ticket.Booking.Exception.LocationInValidException;
import com.example.Ticket.Booking.Exception.PasswordInValidException;
import com.example.Ticket.Booking.Exception.PasswordIncorrectException;
import com.example.Ticket.Booking.dao.HospitalDetailsRepository;
import com.example.Ticket.Booking.dao.LocationRepository;
import com.example.Ticket.Booking.model.*;
import com.example.Ticket.Booking.service.HospitalDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @GetMapping(value = "/locations")
    public ResponseEntity<List<String>> getLocations() {
        List<Locations> locations = locationRepository.findAll();
        List<String> locList = locations.getFirst().getLocation().stream().sorted().collect(Collectors.toList());
        return new ResponseEntity<>(locList, HttpStatus.OK);
    }

    @PostMapping(value = "/hospitalDetails")
    public ResponseEntity<Map<String, List<HospitalDtl>>> fetchHsptlDetails(@Valid @RequestBody List<String> location) {

        Map<String,List<HospitalDtl>> hospitalDtls = hospitalDetailsService.getHsptlDtls(location);



        return new ResponseEntity<>(hospitalDtls, HttpStatus.OK);
    }

    @PostMapping(value = "/specialistName")
    public ResponseEntity<Map<String, Set<String>>> getSpecalistDetails(@Valid @RequestBody List<String> location){
        Map<String, Set<String>> specialistDtls = hospitalDetailsService.getListOfSpclName(location);

        return new ResponseEntity<>(specialistDtls, HttpStatus.OK);
    }

    @PostMapping(value = "/specialist")
    public ResponseEntity<List<HospitalDtlBySpclty>> fetchListOfSpecialist(@Valid @RequestBody SpecalistName spclList){
        List<HospitalDtlBySpclty> listOfHoDtls = hospitalDetailsService.getSpecalistDetails(spclList);
        return new ResponseEntity<>(listOfHoDtls, HttpStatus.OK);

    }


}
