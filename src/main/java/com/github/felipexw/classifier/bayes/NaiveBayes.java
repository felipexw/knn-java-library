package com.github.felipexw.classifier.bayes;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.classifier.CrossValidateClassifier;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.LabeledTrainingInstance;
import java.util.List;
import java.util.Map;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public abstract class NaiveBayes implements Classifier, CrossValidateClassifier {
  protected Map<String, List<LabeledInstance>> posterioriProbs;
  protected Map<String, Double> prioriProbs;
  protected Map<String, Integer> labels;

  public abstract void calculateProbs(List<LabeledTrainingInstance> instanceList);

  public Map<String, Double> getPrioriProbs() {
    return prioriProbs;
  }

  public Map<String, List<LabeledInstance>> getPosterioriProbs() {
    return posterioriProbs;
  }

  public abstract void calculatePosterioriProbability(LabeledTrainingInstance instances);
}
