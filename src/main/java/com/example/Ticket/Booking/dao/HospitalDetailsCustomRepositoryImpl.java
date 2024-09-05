package com.example.Ticket.Booking.dao;

import com.example.Ticket.Booking.model.HospitalDetails;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class HospitalDetailsCustomRepositoryImpl implements HospitalDetailsCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public HospitalDetails findByFilters(String location, Set<String> spclName, List<String> hospitalNames, Double maxCost) {
        List<AggregationOperation> operations = new ArrayList<>();

        // Match the location
        operations.add(match(Criteria.byExample(new Document("location", location))));

        // Unwind the hospitalDetails array
        operations.add(unwind("hospitalDetails"));

        // Optional match on hospital name
            operations.add(match(Criteria.byExample(new Document("hospitalDetails.name", new Document("$in", hospitalNames)))));


        // Unwind the specalist array within hospitalDetails
        operations.add(unwind("hospitalDetails.specalist"));

        // Optional match on specialist name
            operations.add(match(Criteria.byExample(new Document("hospitalDetails.specalist.spclName", new Document("$in", spclName)))));

        // Unwind the doctorsList array within specalist
        operations.add(unwind("hospitalDetails.specalist.doctorsList"));

        // Unwind the docNameAndAvblTime array within doctorsList
        operations.add(unwind("hospitalDetails.specalist.doctorsList.docNameAndAvblTime"));

        // Optional match on cost
        operations.add(match(Criteria.byExample(new Document("hospitalDetails.specalist.doctorsList.docNameAndAvblTime.cost", new Document("$lte", maxCost)))));

        // Group the data back into the original structure
        operations.add(group("_id")
                .first("location").as("location")
                .push(new Document("hospitalDetails", "$hospitalDetails")).as("hospitalDetails")
        );

        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<HospitalDetails> results = mongoTemplate.aggregate(aggregation, "hospitalDetails", HospitalDetails.class);

        return results.getUniqueMappedResult();
    }

//    @Override
//    public HospitalDetails findByLocationAndHospitalName(String location, List<String> hospitalName) {
//        MatchOperation match = Aggregation.match(Criteria.where("location").is(location));
//
//        // Filter hospitalDetails array to include only elements that match the name
//        ProjectionOperation project = Aggregation.project("location")
//                .and(ArrayOperators.Filter.filter("hospitalDetails")
//                        .as("hospital")
//                        .by(ArrayOperators.In.arrayOf("hospital.name").containsValue(hospitalName))
//                ).as("hospitalDetails");
//
//        // Build the aggregation pipeline
//        Aggregation aggregation = Aggregation.newAggregation(match, project);
//
//        // Execute the aggregation
//        AggregationResults<HospitalDetails> results = mongoTemplate.aggregate(aggregation, "hospitalDetails", HospitalDetails.class);
//        return results.getUniqueMappedResult();
//    }
}
