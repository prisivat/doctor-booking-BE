package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    Optional<User> findByPassword(String password);

    User findByUserName(String userName);

    User findByEmail(String email);

    User findByPhoneNumberAndEmail(String phoneNumber, String email);

}
