package com.github.felipexw.classifiers.bayes;

import com.github.felipexw.classifiers.Classifier;
import com.github.felipexw.classifiers.CrossValidateClassifier;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public class MultinomialNaiveBayesClassifier extends NaiveBayes
    implements Classifier, CrossValidateClassifier {

  private void init() {
    prioriProbs = new HashMap<>();
    posterioriProbs = new HashMap<>();
    labels = new HashMap<>();
  }

  @Override public void train(List<LabeledTrainingInstance> instances) {

  }

  @Override public PredictedInstance predict(LabeledTrainingInstance instance) {
    return null;
  }

  @Override public List<PredictedInstance> predict(List<LabeledTrainingInstance> instances) {
    List<PredictedInstance> predictions = new ArrayList<>();

    instances.forEach((instance) -> {
      predictions.add(predict(instance));
    });

    return predictions;
  }

  @Override public void train(List<LabeledTrainingInstance> instances, int k) {

  }

  @Override
  public void calculatePrioriProbs(List<LabeledTrainingInstance> instanceList) {
    for (LabeledTrainingInstance instance : instanceList) {
      if (!labels.containsKey(instance.getLabel())) {
        labels.put(instance.getLabel(), 1);
      }

      calculatePosterioriProbability(instance);
    }
  }

  @Override
  public void calculatePosterioriProbability(LabeledTrainingInstance instance) {
    double[] features = instance.getFeatures();

    for (int i = 0; i < features.length; i++) {
      double key = features[i];

      if (!this.posterioriProbs.containsKey(key)) {
        List<LabeledInstance> instances = Arrays.asList(new LabeledInstance(instance.getLabel()));
        this.posterioriProbs.put(key, instances);
      } else {
        countFromLabels(this.posterioriProbs.get(key), instance);
      }
    }
  }

  private void countFromLabels(List<LabeledInstance> instances, LabeledInstance instance) {
    for (LabeledInstance featuresInstance : instances) {
      if (featuresInstance.getLabel().equalsIgnoreCase(instance.getLabel())) {
        featuresInstance.setCount(featuresInstance.getCount() + 1);
      } else {
        instance.setCount(1);
        instances.add(instance);
      }
    }
  }
}
