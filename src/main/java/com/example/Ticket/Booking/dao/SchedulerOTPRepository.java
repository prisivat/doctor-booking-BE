package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.SchedulerOTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SchedulerOTPRepository extends MongoRepository<SchedulerOTP, String> {
    SchedulerOTP findByOtpAndHospitalNameAndLocation(String otp, String hospitalName, String location);

    void deleteByOtpAndHospitalNameAndLocation(String otp, String hospitalName, String location);

    List<SchedulerOTP> findByHospitalNameAndLocation(String hospitalName, String location);

    void deleteByHospitalNameAndLocation(String hospitalName, String location);

    void deleteByCreatedAtBefore(LocalDateTime now);
}
