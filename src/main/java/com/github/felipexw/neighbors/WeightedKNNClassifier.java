package com.github.felipexw.neighbors;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.List;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class WeightedKNNClassifier extends Classifier{

    @Override
    public void train(List<LabeledTrainingInstance> instances) {

    }

    @Override
    public PredictedInstance predict(LabeledTrainingInstance instance) {
        return null;
    }
}
