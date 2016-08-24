package com.github.felipexw.classifier;

import com.github.felipexw.types.Instance;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class SimpleKNNClassifier extends Classifier {
    private short k;
    private long features[][];

    @Override
    public void train(List<Instance> instances) {

    }

    @Override
    public double predict(Instance instance) {
        return 0;
    }
}
