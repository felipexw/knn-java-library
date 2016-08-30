package com.github.felipexw.classifiers;

import com.github.felipexw.types.LabeledTrainingInstance;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public interface Classifier extends Model {

     void train(List<LabeledTrainingInstance> instances);

}
