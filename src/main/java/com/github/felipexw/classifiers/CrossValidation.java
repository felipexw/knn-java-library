package com.github.felipexw.classifiers;

import com.github.felipexw.types.LabeledTrainingInstance;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public interface CrossValidateClassifier extends Model {

    void train(java.util.List<LabeledTrainingInstance> instances, int k);
}
