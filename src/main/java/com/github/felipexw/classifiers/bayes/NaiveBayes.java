package com.github.felipexw.classifiers.bayes;

import com.github.felipexw.classifiers.Classifier;
import com.github.felipexw.classifiers.CrossValidation;
import com.github.felipexw.types.LabeledInstance;

import java.util.List;
import java.util.Map;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public abstract class NaiveBayes implements Classifier, CrossValidation {
  protected Map<String, List<LabeledInstance>> posterioriProbs;
  protected Map<String, Double> prioriProbs;
  protected Map<String, Integer> labels;

  public abstract void calculateProbs(List<LabeledInstance> instanceList);

  public Map<String, Double> getPrioriProbs() {
    return prioriProbs;
  }

  public Map<String, List<LabeledInstance>> getPosterioriProbs() {
    return posterioriProbs;
  }

  public abstract void calculatePosterioriProbability(LabeledInstance instances);
}
