package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.ForgotPasswordRequest;
import com.example.Ticket.Booking.model.ForgotRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ForgotPasswordReqRepository  extends MongoRepository<ForgotPasswordRequest, String> {

    ForgotPasswordRequest findByToken(String token);
    void deleteByToken(String token);

    void deleteByCreatedAtBefore(LocalDateTime now);
}
