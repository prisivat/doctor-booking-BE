package com.example.Appointment.Booking.dao;

import com.example.Appointment.Booking.model.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OTPRepository extends MongoRepository<OTP, String> {


    OTP findByOtpAndUserName(String otp, String userName);

    void deleteByCreatedAtBefore(LocalDateTime now);

    List<OTP> findByUserName(String userName);

    void deleteByUserName(String userName);
}
