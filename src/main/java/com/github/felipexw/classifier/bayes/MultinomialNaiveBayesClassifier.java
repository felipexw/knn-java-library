package com.github.felipexw.classifier.bayes;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.classifier.CrossValidateClassifier;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import java.util.List;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public class MultinomialNaiveBayesClassifier  implements Classifier, CrossValidateClassifier{

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
}
