package com.example.Ticket.Booking.controller;

import com.example.Ticket.Booking.model.*;
import com.example.Ticket.Booking.service.EmailService;
import com.example.Ticket.Booking.service.SchedulerHospitalTimingService;
import com.itextpdf.text.DocumentException;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class SchedulerHospitalTimingController {

    @Autowired
    SchedulerHospitalTimingService schedulerHospitalTimingService;

    @Autowired
    EmailService emailService;

    @PostMapping(value = "/booking")
    public ResponseEntity<String> bookAppointment(@Valid @RequestBody AppointmentDetails appointmentDetails) {
        try {
            schedulerHospitalTimingService.bookAppointment(appointmentDetails);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>("Appointment has been booked successfully and details sent through registered mail", HttpStatus.OK);

    }

    @PostMapping(value = "/booked/time")
    public ResponseEntity<List<String>> getBookedTime(@Valid @RequestBody SchedulerHospitalTiming schedulerHospitalTiming) {
        List<String> bookedTimings = new ArrayList<>();
        try {
            bookedTimings = schedulerHospitalTimingService.getBookedTimings(schedulerHospitalTiming);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(bookedTimings, HttpStatus.OK);

    }


    @PostMapping(value = "/booking-history")
    public ResponseEntity<List<BookingHistory>> getBookingHistory(@RequestBody String userName){
        List<BookingHistory> bookingHistories = schedulerHospitalTimingService.getBookingHistory(userName);
        return new ResponseEntity<>(bookingHistories, HttpStatus.OK);
    }

    @PostMapping(value = "/upcoming-appointments")
    public ResponseEntity<List<BookingHistory>> getUpcomingAppointments(@RequestBody String userName){
        List<BookingHistory> bookingHistories = schedulerHospitalTimingService.getUpcomingAppointment(userName);
        return new ResponseEntity<>(bookingHistories, HttpStatus.OK);
    }

    /**
     *
     * @param schedulerDetails
     * @return get appointment booked - hospital and location, date for home page of scheduler
     */
    @PostMapping(value = "/scheduler-appointments")
    public ResponseEntity<List<SchedulerHospitalTiming>> getSchedulerAppointmentDtls(@RequestBody SchedulerGetTiming schedulerDetails){
        List<SchedulerHospitalTiming> schedulerHospitalTimings = schedulerHospitalTimingService.getSchedulerAppointDtl(schedulerDetails);
        return new ResponseEntity<>(schedulerHospitalTimings, HttpStatus.OK);
    }

    /**
     *
     * @param schedulerDetails
     * @return get Total No of appointment booked - hospital and location, for home page of scheduler
     */
    @PostMapping(value = "/scheduler-total-appointments")
    public ResponseEntity<List<SchedulerHospitalTiming>> getTotalAppointDtls(@RequestBody SchedulerGetTiming schedulerDetails){
        List<SchedulerHospitalTiming> schedulerHospitalTimings = schedulerHospitalTimingService.getTotalAppointmentDtls(schedulerDetails);
        return new ResponseEntity<>(schedulerHospitalTimings, HttpStatus.OK);
    }

    /**
     *
     * @param bookingId
     * @return get patient details for booking Id filter
     */
    @GetMapping(value = "/scheduler-appointments/{bookingId}")
    public ResponseEntity<SchedulerHospitalTiming> getTotalAppointDtls(@PathVariable String bookingId){
        SchedulerHospitalTiming schedulerHospitalTimings = schedulerHospitalTimingService.getPatientDtlsByBookingId(bookingId);
        return new ResponseEntity<>(schedulerHospitalTimings, HttpStatus.OK);
    }

    /**
     * Cancel Booking
     */
    @PostMapping(value = "/cancel-booking")
    public ResponseEntity<String> cancelBooking(@RequestBody SchedulerHospitalTiming schedulerHospitalTiming) throws MessagingException {
        SchedulerHospitalTiming message = schedulerHospitalTimingService.cancelBooking(schedulerHospitalTiming.getBookingId(), schedulerHospitalTiming.getPatientName());

        return new ResponseEntity<>("Cancelled Successfully", HttpStatus.OK);
    }

    /**
     * Rescheduling Booking
     */
    @PostMapping(value = "/reschedule-booking")
    public ResponseEntity<String> reschedulingBooking(@RequestBody SchedulerHospitalTiming schedulerHospitalTiming) throws MessagingException, TemplateException, DocumentException, IOException {
        SchedulerHospitalTiming schedulerHospitalTiming1 = schedulerHospitalTimingService.reschedulingBooking(schedulerHospitalTiming);

        return new ResponseEntity<>("Cancelled Successfully", HttpStatus.OK);

    }



}