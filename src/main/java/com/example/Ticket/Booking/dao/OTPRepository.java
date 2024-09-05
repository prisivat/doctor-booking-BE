package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.Locations;
import com.example.Ticket.Booking.model.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OTPRepository extends MongoRepository<OTP, String> {


    OTP findByOtpAndUserName(String otp, String userName);

    void deleteByCreatedAtBefore(LocalDateTime now);

    List<OTP> findByUserName(String userName);

    void deleteByUserName(String userName);
}
