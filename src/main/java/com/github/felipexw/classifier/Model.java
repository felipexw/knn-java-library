package com.github.felipexw.classifier;

import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public interface Model {

     PredictedInstance predict(LabeledTrainingInstance instance);
}
