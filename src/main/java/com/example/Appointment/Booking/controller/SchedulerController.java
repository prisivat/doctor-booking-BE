package com.example.Appointment.Booking.controller;

import com.example.Appointment.Booking.Exception.*;
import com.example.Appointment.Booking.dao.SchedulerOTPRepository;
import com.example.Appointment.Booking.model.SchedulerDetails;
import com.example.Appointment.Booking.model.SchedulerOTP;
import com.example.Appointment.Booking.service.EmailService;
import com.example.Appointment.Booking.service.SchedulerService;
import com.example.Appointment.Booking.Exception.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/scheduler")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SchedulerController {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    EmailService emailService;

    @Autowired
    SchedulerOTPRepository schedulerOTPRepository;

    @PostMapping(value = "/register-scheduler")
    public ResponseEntity<String> newScheduler(@Valid @RequestBody SchedulerDetails schedulerDetails) {
        try {
            schedulerService.newScheduler(schedulerDetails);
        } catch (SchedulerAlreadyExisitException e) {
            return new ResponseEntity<>("Scheduler already there for this hospital, Please Login", HttpStatus.BAD_REQUEST);
        } catch (HospitalDetailNotFoundException e) {
            return new ResponseEntity<>("Hospital detail not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("OTP sent Successfully.", HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@Valid @RequestBody SchedulerOTP otp) throws Exception {
        try {
            schedulerService.verifyOtp(otp);
            LocalDateTime now = LocalDateTime.now().minusMinutes(2);
            schedulerOTPRepository.deleteByOtpAndHospitalNameAndLocation(otp.getOtp(), otp.getHospitalName(), otp.getLocation());
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("OTP is Invalid.", HttpStatus.NOT_FOUND);
        } catch (OTPExpiredException e) {
            return new ResponseEntity<>("OTP has Expired.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    @PostMapping("/save-scheduler")
    public ResponseEntity<String> signIn(@Valid @RequestBody SchedulerDetails schedulerDetails) throws Exception {
        try {
            schedulerService.schedulerSignIn(schedulerDetails);
        } catch (PasswordInValidException e) {
            return new ResponseEntity<>("Password doesn't meet the requirements. Please try different one.", HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>("Registered successful", HttpStatus.OK);

    }

    @PostMapping("/scheduler-login")
    public ResponseEntity<String> login(@Valid @RequestBody SchedulerDetails schedulerDetails) throws Exception {
        try {
            String resp = schedulerService.schedulerLogin(schedulerDetails);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (PasswordIncorrectException e) {
            throw new PasswordIncorrectException("Password is incorrect. Please give correct password or reset your password by clicking forgot password");
        }

    }






}
