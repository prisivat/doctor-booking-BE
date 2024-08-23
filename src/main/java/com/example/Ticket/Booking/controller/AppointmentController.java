package com.example.Ticket.Booking.controller;

import com.example.Ticket.Booking.model.*;
import com.example.Ticket.Booking.service.AppointmentDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    @Autowired
    AppointmentDetailsService appointmentDetailsService;

    @PostMapping(value = "/booking")
    public ResponseEntity<String> bookAppointment(@Valid @RequestBody AppointmentDetails appointmentDetails) {
        try {
            appointmentDetailsService.bookAppointment(appointmentDetails);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>("Appointment has been booked successfully and details sent through registered mail", HttpStatus.OK);

    }

    @PostMapping(value = "/booked/time")
    public ResponseEntity<List<String>> getBookedTime(@Valid @RequestBody AppointmentTime appointmentTime) {
        List<String> bookedTimings = new ArrayList<>();
        try {
            bookedTimings = appointmentDetailsService.getBookedTimings(appointmentTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(bookedTimings, HttpStatus.OK);

    }


    @PostMapping(value = "/booking-history")
    public ResponseEntity<List<BookingHistory>> getBookingHistory(@RequestBody String userName){
        List<BookingHistory> bookingHistories = appointmentDetailsService.getBookingHistory(userName);
        return new ResponseEntity<>(bookingHistories, HttpStatus.OK);
    }

    @PostMapping(value = "/upcoming-appointments")
    public ResponseEntity<List<BookingHistory>> getUpcomingAppointments(@RequestBody String userName){
        List<BookingHistory> bookingHistories = appointmentDetailsService.getUpcomingAppointment(userName);
        return new ResponseEntity<>(bookingHistories, HttpStatus.OK);
    }

}
