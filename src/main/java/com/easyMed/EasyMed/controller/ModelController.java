package com.easyMed.EasyMed.controller;

import com.easyMed.EasyMed.service.InferenceService;
import com.easyMed.EasyMed.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/model")
public class ModelController {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private InferenceService inferenceService;

    @PostMapping("/train")
    public String trainModel() {
        return trainingService.trainModel();
    }

    @PostMapping("/predict")
    public String predict(@RequestBody String inputText) {
        return inferenceService.predict(inputText);
    }
}
