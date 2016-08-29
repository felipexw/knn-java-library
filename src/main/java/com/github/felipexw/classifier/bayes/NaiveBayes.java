package com.github.felipexw.classifier.bayes;

import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.LabeledTrainingInstance;
import java.util.List;
import java.util.Map;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public abstract class NaiveBayes {
  protected Map<Double, List<LabeledInstance>> features;
  protected Map<String, Double> prioriProbs;
  protected Map<String, Integer> labels;

  public abstract void calculatePrioriProbs(List<LabeledTrainingInstance> instanceList);

  public Map<String, Double> getPrioriProbs() {
    return prioriProbs;
  }

  public abstract void calculatePosterioriProbability(LabeledTrainingInstance instances);
}
