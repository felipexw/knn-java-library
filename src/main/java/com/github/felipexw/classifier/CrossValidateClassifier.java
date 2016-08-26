package com.github.felipexw.classifier;

import com.github.felipexw.classifier.Model;
import com.github.felipexw.types.LabeledTrainingInstance;

import java.util.List;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public interface CrossValidateClassifier extends Model {

    void train(java.util.List<LabeledTrainingInstance> instances, int k);
}
