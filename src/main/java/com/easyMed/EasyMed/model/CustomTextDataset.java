package com.easyMed.EasyMed.model;

import ai.djl.ndarray.NDList;
import ai.djl.training.dataset.Batch;
import ai.djl.translate.Batchifier;
import ai.djl.translate.TranslateException;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.training.dataset.Dataset;
import ai.djl.translate.TranslatorOptions;
import ai.djl.util.Progress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class CustomTextDataset implements Dataset {

    private List<String> texts;
    private List<String> labels;

    public CustomTextDataset(List<String> texts, List<String> labels) {
        this.texts = texts;
        this.labels = labels;
    }

    public CustomTextDataset(Builder builder) {
    }

    @Override
    public Iterable<Batch> getData(NDManager manager) {
        List<Batch> batches = new ArrayList<>();
        for (int i = 0; i < texts.size(); i++) {
            // Create NDList for data and labels
            NDList data = new NDList(manager.create(texts.get(i).getBytes()));
            NDList label = new NDList(manager.create(labels.get(i).getBytes()));

            // Add a Batch with all required parameters
            batches.add(new Batch(
                    manager,
                    data,
                    label,
                    1, // Batch size is 1 since we are iterating item by item
                    Batchifier.STACK, // Default batchifier for data
                    Batchifier.STACK, // Default batchifier for labels
                    i, // Current progress (e.g., index in the loop)
                    texts.size(), // Total progress (total number of samples)
                    List.of(i) // Indices (in this case, the current index as a singleton list)
            ));
        }
        return batches;
    }


    @Override
    public Iterable<Batch> getData(NDManager manager, ExecutorService executorService) throws IOException, TranslateException {
        return Dataset.super.getData(manager, executorService);
    }

    @Override
    public void prepare() throws IOException, TranslateException {
        Dataset.super.prepare();
    }

    @Override
    public void prepare(Progress progress) throws IOException, TranslateException {

    }
    @Override
    public TranslatorOptions matchingTranslatorOptions() {
        return Dataset.super.matchingTranslatorOptions();
    }
//    @Override
    public Batchifier getBatchifier() {
        return Batchifier.STACK;
    }

    public static class Builder {
        private List<String> texts = new ArrayList<>();
        private List<String> labels = new ArrayList<>();
        private Batchifier batchifier = Batchifier.STACK;

        public Builder setTexts(List<String> texts) {
            this.texts = texts;
            return this;
        }

        public Builder setLabels(List<String> labels) {
            this.labels = labels;
            return this;
        }

        public Builder setBatchifier(Batchifier batchifier) {
            this.batchifier = batchifier;
            return this;
        }

        public CustomTextDataset build() {
            return new CustomTextDataset(this);
        }
    }


}
