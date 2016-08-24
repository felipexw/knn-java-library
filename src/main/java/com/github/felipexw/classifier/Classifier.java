package com.github.felipexw.classifier;

import com.github.felipexw.types.Instance;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public abstract class Classifier {


    public abstract void train(List<LabeledTrainingInstance> instances);

    public abstract PredictedInstance predict(Instance instance);

}
