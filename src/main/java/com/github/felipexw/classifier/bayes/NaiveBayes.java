package com.github.felipexw.classifier.bayes;

import com.github.felipexw.types.LabeledTrainingInstance;
import java.util.List;
import java.util.Map;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public abstract class NaiveBayes {
  public abstract Map<String, Integer> calculateAPrioriProbs  (List<LabeledTrainingInstance> instanceList);
}
