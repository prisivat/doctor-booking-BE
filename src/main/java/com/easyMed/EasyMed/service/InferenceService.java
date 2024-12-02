package com.easyMed.EasyMed.service;


import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
public class InferenceService {

    public String predict(String inputText) {
        try (Model model = Model.newInstance("trained-model", String.valueOf(Paths.get("models/")))) {
            // Load model predictor
            Predictor<String, String> predictor = model.newPredictor(new CustomTranslator());

            // Perform prediction
            return predictor.predict(inputText);
        } catch (TranslateException e) {
            e.printStackTrace();
            return "Error during inference: " + e.getMessage();
        }
    }

    private class CustomTranslator implements Translator<String, String> {
//        @Override
//        public String processOutput(TranslatorContext ctx, NDList output) {
//            return output.toString(); // Map output to labels
//        }

        @Override
        public NDList processInput(TranslatorContext ctx, String input) {
            NDManager manager = ctx.getNDManager();
            return (NDList) manager.create(input.hashCode()); // Example input processing
        }

        @Override
        public String processOutput(TranslatorContext translatorContext, NDList ndList) throws Exception {
            return ndList.toString();
        }
    }
}
