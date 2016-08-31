package com.github.felipexw.core;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public interface CrossValidation extends Predictable {

    void train(java.util.List<LabeledInstance> instances, int k);
}
