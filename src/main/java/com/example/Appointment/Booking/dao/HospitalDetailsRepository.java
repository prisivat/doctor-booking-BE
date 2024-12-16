package com.example.Appointment.Booking.dao;

import com.example.Appointment.Booking.model.HospitalDetails;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface HospitalDetailsRepository extends MongoRepository<HospitalDetails, String>, HospitalDetailsCustomRepository{

    @Aggregation(pipeline = {
            "{ $match: { 'location': ?0 } }",
            "{ $project: { " +
                    "'location': 1, " +
                    "'hospitalDetails': { $map: { " +
                    "input: { $filter: { " +
                    "input: '$hospitalDetails', " +
                    "as: 'hospital', " +
                    "cond: { $and: [ " +
                    "{ $in: ['$$hospital.name', ?2] } " +
                    "] } " +
                    "} }, " +
                    "as: 'hospital', " +
                    "in: { " +
                    "'name': '$$hospital.name', " +
                    "'specialist': { $map: { " +
                    "input: { $filter: { " +
                    "input: '$$hospital.specialist', " +
                    "as: 'spec', " +
                    "cond: { $and: [ " +
                    "{ $in: ['$$spec.spclName', ?1] }, " +
                    "{ $lte: [ " +
                    "{ $min: { $map: { " +
                    "input: '$$spec.doctorsList', " +
                    "as: 'doctor', " +
                    "in: { $min: '$$doctor.docNameAndAvblTime.cost' } " +
                    "} } }, ?3 ] } " +
                    "] } " +
                    "} }, " +
                    "as: 'spec', " +
                    "in: { " +
                    "'spclName': '$$spec.spclName', " +
                    "'doctorsList': '$$spec.doctorsList' " +
                    "} " +
                    "} } " +
                    "} " +
                    "} } } }"
    })
    HospitalDetails findByLocationAndSpecialistAndHospitalNameAndCost(String location, Set<String> spclName, List<String> name, Integer cost );

    @Aggregation(pipeline = {
            "{ $match: { 'location': ?0 } }",
            "{ $project: { " +
                    "'location': 1, " +
                    "'hospitalDetails': { $map: { " +
                    "input: { $filter: { " +
                    "input: '$hospitalDetails', " +
                    "as: 'hospital', " +
                    "cond: { $in: ['$$hospital.name', ?1] } " +
                    "} }, " +
                    "as: 'hospital', " +
                    "in: { " +
                    "'name': '$$hospital.name', " +
                    "'specialist': { $filter: { " +
                    "input: '$$hospital.specialist', " +
                    "as: 'spec', " +
                    "cond: { $in: ['$$spec.spclName', ?2] } " +
                    "} } " +
                    "} " +
                    "} } } }"
    })
    HospitalDetails findByLocationAndHospitalNameAndSpecialist(String location, List<String> name , Set<String> spclName);//working


    @Aggregation(pipeline = {
            "{ $match: { 'location': ?0 } }",  // Match by location
            "{ $project: { " +
                    "'location': 1, " +
                    "'hospitalDetails': { $filter: { " +
                    "input: '$hospitalDetails', " +
                    "as: 'hospital', " +
                    "cond: { " +
                    "{ $gt: [ " +
                    "{ $size: { $filter: { " +
                    "input: '$$hospital.specialist', " +
                    "as: 'spec', " +
                    "cond: { $in: ['$$spec.spclName', ?1] } " + // Filter by specialist names
                    "} } }, 0] }, " +
                    "{ $lte: [ " +
                    "{ $min: { $map: { " +
                    "input: { $reduce: { " +
                    "input: '$$hospital.specialist', " +
                    "initialValue: [], " +
                    "in: { $concatArrays: ['$$value', '$$this.doctorsList.docNameAndAvblTime'] } " +
                    "} }, " +
                    "as: 'docDetails', " +
                    "in: '$$docDetails.cost' " +
                    "} } }, ?2 ] } " + // Compare minimum cost to a threshold
                    "} " +
                    "} " +
                    "} } " +
                    "} }"
    })
    HospitalDetails findByLocationAndSpecialistAndCost(String location, Set<String> spclName,  Integer cost );


    @Aggregation(pipeline = {
            "{ $match: { 'location': ?0 } }",
            "{ $project: { " +
                    "'location': 1, " +
                    "'hospitalDetails': { $filter: { " +
                    "input: '$hospitalDetails', " +
                    "as: 'hospital', " +
                    "cond: { $and: [ " +
                    "{ $in: ['$$hospital.name', ?1] }, " +
                    "{ $gt: [ " +
                    "{ $size: { $filter: { " +
                    "input: '$$hospital.specialist', " +
                    "as: 'spec', " +
                    "cond: { $and: [ " +
                    "{ $in: ['$$spec.spclName', ?3] }, " +
                    "{ $gt: [ " +
                    "{ $size: { $filter: { " +
                    "input: '$$spec.doctorsList', " +
                    "as: 'doctor', " +
                    "cond: { $gte: [ " +
                    "{ $min: '$$doctor.docNameAndAvblTime.cost' }, ?2 ] " +
                    "} " +
                    "} } }, 0] } " +
                    "] " +
                    "} " +
                    "} } }, 0] }, " +
                    "] } " +
                    "} } } }"
    })
    HospitalDetails findByLocationAndHospitalNameAndCost(String location, List<String> name, Integer cost );

//    @Query("{'location': ?0, 'hospitalDetails': { $elemMatch: { 'name': { $in: ?1 } } } }")
//    @Aggregation(pipeline = {
//            "{ $match: { 'location': ?0 } }",
//            "{ $project: { " +
//                    "'location': 1, " +
//                    "'hospitalDetails': { $filter: { " +
//                    "input: '$hospitalDetails', " +
//                    "as: 'hospital', " +
//                    "cond: { $and: [ " +
//                    "{ $in: ['$$hospital.specialist.spclName', ?1] }, " +
//                    "{ $in: ['$$hospital.name', ?2] }, " +
//                    "{ $lte: [ { $min: '$$hospital.specialist.doctorsList.docNameAndAvblTime.cost' }, ?3 ] } " +
//                    "] } " +
//                    "} } } }"
//    })
//    HospitalDetails findByLocationAndHospitalDetailsNameIn(String location, List<String> names);

    @Aggregation(pipeline = {
            "{ $match: { 'location': ?0 } }",
            "{ $project: { " +
                    "'location': 1, " +
                    "'hospitalDetails': { $map: { " +
                    "input: '$hospitalDetails', " +
                    "as: 'hospital', " +
                    "in: { " +
                    "'name': '$$hospital.name', " +
                    "'specialist': { $filter: { " +
                    "input: '$$hospital.specialist', " +
                    "as: 'spec', " +
                    "cond: { $in: ['$$spec.spclName', ?1] } " +
                    "} } " +
                    "} " +
                    "} } " +
                    "} }"
    })
    HospitalDetails findByLocationAndSpcName(String location, Set<String> spclName);//working

    @Aggregation(pipeline = {
            "{ $match: { 'location': ?0 } }",
            "{ $project: { " +
                    "'location': 1, " +
                    "'hospitalDetails': { $filter: { " +
                    "input: '$hospitalDetails', " +
                    "as: 'hospital', " +
                    "cond: { $gte: [ { $min: '$$hospital.specialist.doctorsList.docNameAndAvblTime.cost' }, ?1 ] } " +
                    "} } } }"
    })
    HospitalDetails findByLocationAndCost(String  location, Integer cost);

    HospitalDetails findByLocation(String location);


//    @Query("{'hospitalDetails.name': { $in: ?0}, 'location': ?1 }")
//    HospitalDetails findByHospitalNameAndLocation( List<String> name , String location);

    @Aggregation(pipeline = {
            "{ $match: { 'location': ?0 } }",
            "{ $project: { 'location': 1, 'hospitalDetails': { $filter: { input: '$hospitalDetails', as: 'hospital', cond: { $in: ['$$hospital.name', ?1] } } } } }"
    })
    HospitalDetails findByLocationAndHospitalName(String location,List<String> name );//working

    @Aggregation(pipeline = {
            "{ $match: { 'location': ?0 } }",
            "{ $project: { 'hospitalDetails': { $filter: { input: '$hospitalDetails', as: 'hospital', cond: { $eq: ['$$hospital.name', ?1] } } } } }",
            "{ $project: { 'email': { $cond: { if: { $gt: [{ $size: '$hospitalDetails' }, 0] }, then: { $arrayElemAt: ['$hospitalDetails.email', 0] }, else: null } } } }"
    })
    String findEmailByLocationAndHospitalName(String location, String hospitalName);


}
