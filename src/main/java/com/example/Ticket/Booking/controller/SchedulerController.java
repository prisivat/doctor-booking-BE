package com.example.Ticket.Booking.controller;

import com.example.Ticket.Booking.Exception.*;
import com.example.Ticket.Booking.dao.SchedulerOTPRepository;
import com.example.Ticket.Booking.model.OTP;
import com.example.Ticket.Booking.model.SchedulerDetails;
import com.example.Ticket.Booking.model.SchedulerOTP;
import com.example.Ticket.Booking.model.User;
import com.example.Ticket.Booking.service.EmailService;
import com.example.Ticket.Booking.service.SchedulerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/scheduler")
@CrossOrigin(origins = "http://localhost:3000")
public class SchedulerController {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    EmailService emailService;

    @Autowired
    SchedulerOTPRepository schedulerOTPRepository;

    @PostMapping(value = "/register-scheduler")
    public ResponseEntity<String> newScheduler(@Valid @RequestBody SchedulerDetails schedulerDetails){
        try {
            schedulerService.newScheduler(schedulerDetails);
        } catch (SchedulerAlreadyExisitException e){
            return new ResponseEntity<>("Scheduler already there for this hospital, Please Login", HttpStatus.BAD_REQUEST);
        } catch (HospitalDetailNotFoundException e){
            return new ResponseEntity<>("Hospital detail not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("OTP sent Successfully.",HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@Valid @RequestBody SchedulerOTP otp) throws Exception{
        try {
            schedulerService.verifyOtp(otp);
            LocalDateTime now = LocalDateTime.now().minusMinutes(2);
            schedulerOTPRepository.deleteByOtpAndHospitalNameAndLocation(otp.getOtp(), otp.getHospitalName(), otp.getLocation());
        } catch(UserNotFoundException e){
            return new ResponseEntity<>("OTP is Invalid.",HttpStatus.NOT_FOUND);
        } catch (OTPExpiredException e){
            return new ResponseEntity<>("OTP has Expired.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    @PostMapping("/save-scheduler")
    public ResponseEntity<String> signIn(@Valid @RequestBody SchedulerDetails schedulerDetails) throws Exception{
try {
    schedulerService.schedulerSignIn(schedulerDetails);
}catch (PasswordInValidException e){
    return new ResponseEntity<>("Password doesn't meet the requirements. Please try different one.",HttpStatus.NOT_FOUND);

} catch (Exception e){
    return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);

}
        return new ResponseEntity<>("Registered successful", HttpStatus.OK);

    }

    @PostMapping("/scheduler-login")
    public ResponseEntity<String> login(@Valid @RequestBody SchedulerDetails schedulerDetails) throws Exception{
try {
    schedulerService.schedulerLogin(schedulerDetails);
    return new ResponseEntity<>("Login successful", HttpStatus.OK);
} catch (PasswordIncorrectException e){
    throw new PasswordIncorrectException("Password is incorrect. Please give correct password or reset your password by clicking forgot password");
}

    }

}

// hospital name and location -> send email -> generate opt  -> ui ask otp ->
//validate otp -> ui enter username(non editable) -> password-> to be send userName,password, hospital, location
