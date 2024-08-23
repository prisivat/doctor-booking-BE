package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.AppointmentDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentDetailsRepository extends MongoRepository<AppointmentDetails, String> {

    List<AppointmentDetails> findByUserName(String userName);

    List<AppointmentDetails> findByUserNameAndDateAfter(String userName, String date);
}
