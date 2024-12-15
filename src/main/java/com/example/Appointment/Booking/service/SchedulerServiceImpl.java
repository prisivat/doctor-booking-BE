package com.example.Appointment.Booking.service;

import com.example.Appointment.Booking.Exception.*;
import com.example.Appointment.Booking.dao.HospitalDetailsRepository;
import com.example.Appointment.Booking.dao.SchedulerDetailRepositroy;
import com.example.Appointment.Booking.dao.SchedulerOTPRepository;
import com.example.Appointment.Booking.model.SchedulerDetails;
import com.example.Appointment.Booking.model.SchedulerOTP;
import com.example.Appointment.Booking.Exception.*;
import com.example.Appointment.Booking.model.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class SchedulerServiceImpl implements SchedulerService{

    @Autowired
    SchedulerDetailRepositroy schedulerDetailRepositroy;

    @Autowired
    HospitalDetailsRepository hospitalDetailsRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    SchedulerOTPRepository schedulerOTPRepository;

    @Autowired
    PasswordEncryptDecryptServiceImpl passwordEncryptDecryptService;


    @Override
    public void newScheduler(SchedulerDetails schedulerDetails) {

            SchedulerDetails schedulerDetails1 = schedulerDetailRepositroy.findByHospitalNameAndLocation(schedulerDetails.getHospitalName(), schedulerDetails.getLocation());

            if(Objects.isNull(schedulerDetails1)) {
                String hospitalEmail = hospitalDetailsRepository.findEmailByLocationAndHospitalName(schedulerDetails.getLocation(), (schedulerDetails.getHospitalName()));

                if (!Objects.isNull(hospitalEmail)) {
                    SchedulerOTP schedulerMailOtpDetails = new SchedulerOTP();
                    schedulerMailOtpDetails.setHospitalName(schedulerDetails.getHospitalName());
                    List<SchedulerOTP> userExist = schedulerOTPRepository.findByHospitalNameAndLocation(schedulerDetails.getHospitalName(), schedulerDetails.getLocation());
                    if (userExist.size() > 0) {
                        LocalDateTime now = LocalDateTime.now().minusMinutes(2);
                        schedulerOTPRepository.deleteByCreatedAtBefore(now);
                    }
                    ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
                    LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
                    schedulerMailOtpDetails.setCreatedAt(String.valueOf(localDateTime));
                    schedulerMailOtpDetails.setLocation(schedulerDetails.getLocation());
                    String otp = getOTP();
                    schedulerMailOtpDetails.setOtp(otp);
                    schedulerOTPRepository.save(schedulerMailOtpDetails);
                    try {
                        emailService.sendOTPEmailForScheduler(schedulerMailOtpDetails, otp, hospitalEmail);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                } else{
                     throw new HospitalDetailNotFoundException("Hospital not found");
                }
            } else{
                throw new SchedulerAlreadyExisitException("Scheduler already there for this hospital, Please Login");
            }


    }

    @Override
    public void verifyOtp(SchedulerOTP otp) {
        Optional<SchedulerOTP> validateOtp = Optional.ofNullable(schedulerOTPRepository.findByOtpAndHospitalNameAndLocation(otp.getOtp(), otp.getHospitalName(), otp.getLocation()));
        LocalDateTime now  =  LocalDateTime.now();

        if(!validateOtp.isPresent()){
            throw new UserNotFoundException("OTP is Invalid.");
        } else if(LocalDateTime.parse(validateOtp.get().getCreatedAt()).plusMinutes(2).isBefore(now)){
            LocalDateTime now1 = LocalDateTime.now().minusMinutes(2);
            schedulerOTPRepository.deleteByCreatedAtBefore(now1);
            throw new OTPExpiredException("OTP has Expired.");
        }
    }

    @Override
    public void schedulerSignIn(SchedulerDetails schedulerDetails) {
        String hospitalEmail = hospitalDetailsRepository.findEmailByLocationAndHospitalName(schedulerDetails.getLocation(), (schedulerDetails.getHospitalName()));
        schedulerDetails.setEmail(hospitalEmail);
        String upperCase = "(.*[A-Z].*)";
        String lowerCase = "(.*[a-z].*)";
        String numbers = "(.*[0-9].*)";
        String splChars = "(.*[@,#,$,%,&].*$)";
        String pswrd = schedulerDetails.getPassword();
        if (!pswrd.matches(upperCase) || !pswrd.matches(lowerCase) || !pswrd.matches(numbers) || !pswrd.matches(splChars) || pswrd.length()<8 || pswrd.contains(" ")){
            throw new PasswordInValidException("Password doesn't meet the requirements. Please try different one");
        }
        String password = passwordEncryptDecryptService.passwordSchedulerEncryption(schedulerDetails);
        schedulerDetails.setPassword(password);
        schedulerDetailRepositroy.save(schedulerDetails);
    }

    @Override
    public void schedulerLogin(SchedulerDetails schedulerDetails) {
        String hospitalEmail = hospitalDetailsRepository.findEmailByLocationAndHospitalName(schedulerDetails.getLocation(), (schedulerDetails.getHospitalName()));
        SchedulerDetails schedulerDetails1 = schedulerDetailRepositroy.findByHospitalNameAndLocation(schedulerDetails.getHospitalName(), schedulerDetails.getLocation());
        if(Objects.isNull(schedulerDetails1)){
            throw new SchedulerNotAvailableException("Scheduler not found");
        } else{
            String password = passwordEncryptDecryptService.passwordSchedulerDecryption(schedulerDetails1.getPassword());
            if(!password.equals(schedulerDetails.getPassword())) {
                throw new PasswordIncorrectException("Password is incorrect. Please give correct password or reset your password by clicking forgot password");
            }
            List<SchedulerOTP> userExist = schedulerOTPRepository.findByHospitalNameAndLocation(schedulerDetails.getHospitalName(), schedulerDetails.getLocation());
            if (userExist.size() > 0) {
                LocalDateTime now = LocalDateTime.now().minusMinutes(2);
                schedulerOTPRepository.deleteByCreatedAtBefore(now);
            }
            SchedulerOTP schedulerMailOtpDetails = new SchedulerOTP();
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
            LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
            schedulerMailOtpDetails.setCreatedAt(String.valueOf(localDateTime));
            schedulerMailOtpDetails.setLocation(schedulerDetails.getLocation());
            String otp = getOTP();
            schedulerMailOtpDetails.setOtp(otp);
            schedulerOTPRepository.save(schedulerMailOtpDetails);
            try {
                emailService.sendOTPEmailForScheduler(schedulerMailOtpDetails, otp, hospitalEmail);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getOTP(){
        String chars = "0123456789";
        StringBuilder stringBuilder;
        Random random1 = new Random();

        stringBuilder = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            int index = random1.nextInt(chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }
}
