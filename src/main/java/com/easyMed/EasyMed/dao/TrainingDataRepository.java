package com.easyMed.EasyMed.dao;

import com.easyMed.EasyMed.model.TrainingData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingDataRepository extends MongoRepository<TrainingData, String> {
}
