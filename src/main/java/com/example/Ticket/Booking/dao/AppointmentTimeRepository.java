package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.AppointmentTime;
import com.example.Ticket.Booking.model.ForgotPasswordRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentTimeRepository extends MongoRepository<AppointmentTime, String> {

    List<AppointmentTime> findByLocationAndHospitalNameAndSpecialistAndDocNameAndDate(String location, String hospitalName, String specialist, String docName, String date);

}
