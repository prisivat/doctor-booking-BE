package com.easyMed.EasyMed.service;

import ai.djl.Model;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.nn.AbstractBlock;
import ai.djl.nn.core.Linear;
import ai.djl.training.DefaultTrainingConfig;
import ai.djl.training.GradientCollector;
import ai.djl.training.Trainer;
import ai.djl.training.dataset.ArrayDataset;
import ai.djl.training.dataset.Batch;
import ai.djl.training.dataset.Dataset;
import ai.djl.training.loss.Loss;
import ai.djl.translate.TranslateException;
import com.easyMed.EasyMed.dao.TrainingDataRepository;
import com.easyMed.EasyMed.model.CustomTextDataset;
import com.easyMed.EasyMed.model.TrainingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ai.djl.ndarray.types.Shape;



import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
public class TrainingService {

    @Autowired
    private TrainingDataRepository trainingDataRepository;

    public String trainModel() {
        // Fetch data from MongoDB
        List<TrainingData> trainingDataList = trainingDataRepository.findAll();

        // Prepare input and output for training
        List<String> inputTexts = new ArrayList<>();
        List<String> outputLabels = new ArrayList<>();
        for (TrainingData data : trainingDataList) {
            inputTexts.add(data.getInputText());
            outputLabels.add(data.getOutputLabel());
        }

        // Create Text Dataset
        CustomTextDataset dataset = new CustomTextDataset.Builder()
                .setTexts(inputTexts)
                .setLabels(outputLabels)
                .build();

        // Initialize Model
        try (Model model = Model.newInstance("basic-model");
             NDManager manager = NDManager.newBaseManager()) {

            // Define training configuration with loss function
            DefaultTrainingConfig config = new DefaultTrainingConfig(Loss.softmaxCrossEntropyLoss());

            // Create a trainer for the model
            Trainer trainer = model.newTrainer(config);

                Shape shape = new Shape(1, 1); // Example shape for a single data point with one feature

                // Initialize the trainer with input shape
                trainer.initialize((Shape) manager.create(new Shape(1, 1))); // Modify shape as per your data

                // Load a dataset

                // Iterate through the dataset
                for (Batch batch : trainer.iterateDataset(dataset)) {
                    try (GradientCollector collector = trainer.newGradientCollector()) {
                        // Perform training steps here...
                    }
                    batch.close();
                }

        }
        catch (IOException | TranslateException e) {
            e.printStackTrace();
            return "Error training the model: " + e.getMessage();
        }

        return "Trained";
    }

//    private class MLP extends AbstractBlock {
//        public MLP(int inputSize, int hiddenSize, int outputSize) {
//            addChildBlock("dense1", Linear.builder().setUnits(hiddenSize).build());
//            addChildBlock("dense2", Linear.builder().setUnits(outputSize).build());
//        }
//    }
}
