package com.github.felipexw.classifiers;

import com.github.felipexw.types.LabeledInstance;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public interface Classifier extends Predictable {

     void train(List<LabeledInstance> instances);

}
