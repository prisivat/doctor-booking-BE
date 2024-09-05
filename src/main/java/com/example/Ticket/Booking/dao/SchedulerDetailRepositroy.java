package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.SchedulerDetails;
import com.example.Ticket.Booking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SchedulerDetailRepositroy extends MongoRepository<SchedulerDetails, String> {

    SchedulerDetails findByHospitalNameAndLocation(String hospitalNAme, String location);


}
