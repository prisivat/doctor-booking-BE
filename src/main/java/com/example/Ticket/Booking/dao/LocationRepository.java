package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.Locations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Locations, String> {

}
