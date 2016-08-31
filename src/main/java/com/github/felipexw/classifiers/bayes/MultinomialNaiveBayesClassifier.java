package com.github.felipexw.classifiers.bayes;

import com.github.felipexw.types.Instance;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.ArrayList;
import java.util.Arrays;
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
    public PredictedInstance predict(Instance instance) {
        return null;
    }

    @Override
    public List<PredictedInstance> predict(List<Instance> instances) {
        List<PredictedInstance> predictions = new ArrayList<>();

        instances.forEach((instance) -> {
            predictions.add(predict(instance));
        });

        return predictions;
    }


    @Override
    public void calculateProbs(List<LabeledInstance> instanceList) {
        if (instanceList == null || instanceList.isEmpty())
            throw new IllegalArgumentException("Args can't be null");

        for (LabeledInstance<String, Double> instance : instanceList) {
            String key = instance.getLabel();
            if (!labels.containsKey(key)) {
                labels.put(instance.getLabel(), 1);
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

    private void countFromLabels(List<LabeledInstance<String, Double>> instances, LabeledInstance<String, Double> instance) {
        for (LabeledInstance<String, Double> featuresInstance : instances) {
            if (featuresInstance.getLabel().equalsIgnoreCase(instance.getLabel())) {
                featuresInstance.setCount(featuresInstance.getCount() + 1);
            } else {
                instance.setCount(1);
                instances.add(instance);
            }
        }
    }
}
