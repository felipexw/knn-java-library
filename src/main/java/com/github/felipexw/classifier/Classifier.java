package com.github.felipexw.classifier;

import com.github.felipexw.types.Instance;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public abstract class Classifier {


    public abstract void train(List<Instance> instances);

    public abstract double predict(Instance instance);

}
