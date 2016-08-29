package com.github.felipexw.classifier.bayes;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.classifier.CrossValidateClassifier;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public class MultinomialNaiveBayesClassifier extends NaiveBayes
    implements Classifier, CrossValidateClassifier {

  @Override public void train(List<LabeledTrainingInstance> instances) {

  }

  @Override public PredictedInstance predict(LabeledTrainingInstance instance) {
    return null;
  }

  @Override public List<PredictedInstance> predict(List<LabeledTrainingInstance> instance) {
    return null;
  }

  @Override public void train(List<LabeledTrainingInstance> instances, int k) {

  }

  @Override
  public Map<String, Integer> calculateAPrioriProbs(List<LabeledTrainingInstance> instanceList) {
    Map<String, Integer> probs = new HashMap<>();

    for (LabeledTrainingInstance instance : instanceList) {
      if (!probs.containsKey(instance.getLabel())) {
        probs.put(instance.getLabel(), 1);
      } else {
        probs.put(instance.getLabel(), probs.get(instance.getLabel()) + 1);
      }
    }

    return probs;
  }
}
