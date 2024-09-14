package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.AppointmentTime;
import com.example.Ticket.Booking.model.SchedulerHospitalTiming;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulerHospitalTimingRepository extends MongoRepository<SchedulerHospitalTiming, String> {

    List<SchedulerHospitalTiming> findByLocationAndHospitalNameAndSpecialistAndDocNameAndDate(String location, String hospitalName, String specialist, String docName, String date);

    List<SchedulerHospitalTiming> findByLocationAndHospitalNameAndDateGreaterThanEqual(String location, String hospitalName, String date);

    List<SchedulerHospitalTiming> findByLocationAndHospitalNameAndDate(String location, String hospitalName, String date);

    SchedulerHospitalTiming findByBookingId(String bookingId);

    void deleteByBookingIdAndPatientName(String bookingId, String patientName);

    SchedulerHospitalTiming findByBookingIdAndPatientName(String bookingId, String patientName);
}
