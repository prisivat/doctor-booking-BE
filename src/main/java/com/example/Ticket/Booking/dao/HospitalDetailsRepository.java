package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.HospitalDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HospitalDetailsRepository extends MongoRepository<HospitalDetails, String> {

    List<HospitalDetails> findByLocationInOrderByLocationAsc(List<String> location);

    HospitalDetails findByLocationOrderByLocationAsc(String location);



    HospitalDetails findByLocation(String location);
}
