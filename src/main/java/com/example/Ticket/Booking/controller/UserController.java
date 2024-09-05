package com.example.Ticket.Booking.controller;

import com.example.Ticket.Booking.Exception.*;
import com.example.Ticket.Booking.dao.ForgotPasswordReqRepository;
import com.example.Ticket.Booking.dao.OTPRepository;
import com.example.Ticket.Booking.model.Login;
import com.example.Ticket.Booking.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Ticket.Booking.service.EmailService;
import com.example.Ticket.Booking.service.PasswordEncryptDecryptServiceImpl;
import com.example.Ticket.Booking.service.UserService;

import com.example.Ticket.Booking.model.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordEncryptDecryptServiceImpl passwordEncryptDecryptService;

    @Autowired
    ForgotPasswordReqRepository forgotPasswordReqRepository;

    @Autowired
    OTPRepository otpRepository;



    @PostMapping(value = "/sign-up")
    public ResponseEntity<String> newUser(@Valid @RequestBody User user){
        try {
            userService.newUser(user);
            emailService.sendEmail(user);
        } catch (EmailAndPhoneNumberAlreadyExistsException e){
            return new ResponseEntity<>("EmailId "+user.getEmail()+" is already registered with us. Please try to LogIn",HttpStatus.NOT_FOUND);
        }catch (PasswordInValidException e){
            return new ResponseEntity<>("Password doesn't meet the requirements. Please try different one",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>("Please enter Valid Email ID", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User added successfully.",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@Valid @RequestBody Login lo) throws Exception {
        User user;
        try {
            OTP otp = new OTP();
             user = userService.userLogin(lo, otp);
             emailService.sendOTPEmail(user, otp.getOtp());

        }
        catch (UserNotFoundException e){
            return new ResponseEntity<>("Please Provide a Valid User name",HttpStatus.NOT_FOUND);
        }catch (PasswordIncorrectException e){
            return new ResponseEntity<>("Password is incorrect. Please give correct password or reset your password by clicking forgot password",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("OTP sent Successfully",HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@Valid @RequestBody OTP otp) throws Exception{
        try {
            userService.verifyOtp(otp);
            LocalDateTime now = LocalDateTime.now().minusMinutes(2);
            otpRepository.deleteByCreatedAtBefore(now);
        } catch(UserNotFoundException e){
            return new ResponseEntity<>("OTP is Invalid.",HttpStatus.NOT_FOUND);
        } catch (OTPExpiredException e){
            return new ResponseEntity<>("OTP has Expired.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
    @PostMapping("/forgot-username")
    public ResponseEntity<String> forgotUserName(@Valid @RequestBody ForgotRequest forgotRequest) throws Exception{
        try {
            User user = userService.forgotUserName(forgotRequest);
            emailService.sendUserName(forgotRequest,user,"forgotUserName");
        }
        catch(UserNotFoundException e){
            return new ResponseEntity<>("Email Id "+forgotRequest.getEmail()+" not found, Please provide the valid email",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User Name has been sent successfully", HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotRequest) throws Exception{
        try {
            forgotRequest = userService.forgotPassword(forgotRequest);
            emailService.sendPassword(forgotRequest);
        }
        catch(UserNotFoundException e){
            return new ResponseEntity<>("Email Id "+forgotRequest.getEmail()+" not found, Please provide the valid email.",HttpStatus.NOT_FOUND);
        }
        catch (PasswordInValidException e){
            return new ResponseEntity<>("User Name "+forgotRequest.getUserName()+" not found, Please provide the valid user name.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Password Reset Link has been sent successfully", HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPassword resetPassword) throws Exception{
        try {
            String msg = userService.resetPassword(resetPassword);
        }
       catch (UserNotFoundException e){
            return new ResponseEntity<>("Token is Invalid.",HttpStatus.NOT_FOUND);
        } catch (PasswordInValidException e){
            return new ResponseEntity<>("Password doesn't meet the requirements. Please try different one",HttpStatus.BAD_REQUEST);
        } catch (OTPExpiredException e) {
            return new ResponseEntity<>("Token has expired", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Password Reset successfully", HttpStatus.OK);
    }

    @PostMapping("/change-username")
    public ResponseEntity<String> changeUserName(@Valid @RequestBody ChangeUserName changeUserName) throws Exception {
        try{
            userService.changeUserName(changeUserName);
        }catch (UserNameAlreadyExistException e){
            return new ResponseEntity<>("UserName Already Exist, Please try with different one.", HttpStatus.BAD_REQUEST);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>("Please provide valid userName.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User name changed successfully", HttpStatus.OK);
    }



    }