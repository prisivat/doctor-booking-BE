package com.example.Appointment.Booking.dao;

import com.example.Appointment.Booking.model.SchedulerDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SchedulerDetailRepositroy extends MongoRepository<SchedulerDetails, String> {

    SchedulerDetails findByHospitalNameAndLocation(String hospitalName, String location);


}
