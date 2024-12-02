package com.easyMed.EasyMed.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TrainingData")
public class TrainingData {

    @Id
    private String id;
    private String inputText; // Input for the model
    private String outputLabel; // Expected output label

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getInputText() {
        return inputText;
    }
    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
    public String getOutputLabel() {
        return outputLabel;
    }
    public void setOutputLabel(String outputLabel) {
        this.outputLabel = outputLabel;
    }
}
