package com.github.felipexw.classifiers.bayes;

import com.github.felipexw.core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public class MultinomialNaiveBayesClassifier extends NaiveBayes {

    private void init() {
        prioriProbs = new HashMap<>();
        posterioriProbs = new HashMap<>();
        labels = new HashMap<>();
    }

    public MultinomialNaiveBayesClassifier() {
        init();
    }

    @Override
    public void train(List<LabeledInstance> instances) {
        calculateProbs(instances);

    }

    @Override
    public void train(List<LabeledInstance> instances, int k) {

    }

    @Override
    public Prediction predict(Instance instance) {
        return null;
    }

    @Override
    public List<Prediction> predict(List<Instance> instances) {
        List<Prediction> predictions = new ArrayList<>();

        instances.forEach((instance) -> {
            predictions.add(predict(instance));
        });

        return predictions;
    }


    @Override
    public void calculateProbs(List<LabeledInstance> instanceList) {
        if (instanceList == null || instanceList.isEmpty())
            throw new IllegalArgumentException("Args can't be null");

        for (LabeledInstance<Label, Model> instance : instanceList) {
            String key = instance.getLabel().toString();
            if (!labels.containsKey(key)) {
                labels.put(instance.getLabel().toString(), 1);
            }
            else{
                labels.put(key, labels.get(key)+1);
            }

            calculatePosterioriProbability(instance);
        }
    }

    @Override
    public void calculatePosterioriProbability(LabeledInstance instance) {
        throw new UnsupportedOperationException("Continue the implementation");
        /**
         double[] features = instance.getFeatures();

         for (int i = 0; i < features.length; i++) {
         double key = features[i];

         if (!this.posterioriProbs.containsKey(key)) {
         List<LabeledInstance> instances = Arrays.asList(new LabeledInstance(instance.getLabel()));
         this.posterioriProbs.put(String.valueOf(key), instances);
         } else {
         countFromLabels(this.posterioriProbs.get(key), instance);
         }
         }
         */
    }

    private void countFromLabels(List<LabeledInstance<Label, Model>> instances, LabeledInstance<Label, Model> instance) {
        for (LabeledInstance<Label, Model> featuresInstance : instances) {
            if (featuresInstance.getLabel().toString().equalsIgnoreCase(instance.getLabel().toString())) {
                featuresInstance.setCount(featuresInstance.getCount() + 1);
            } else {
                instance.setCount(1);
                instances.add(instance);
            }
        }
    }
}
