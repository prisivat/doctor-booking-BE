package com.example.Appointment.Booking.dao;

import com.example.Appointment.Booking.model.AppointmentDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDetailsRepository extends MongoRepository<AppointmentDetails, String> {

    List<AppointmentDetails> findByUserName(String userName);

    List<AppointmentDetails> findByUserNameAndDateGreaterThanEqual(String userName, String date);

    void deleteByUserName(String userName);

    void deleteByBookingIdAndPatientName(String bookingId, String patientName);

    AppointmentDetails findByBookingIdAndPatientName(String bookingId, String patientName);
}
