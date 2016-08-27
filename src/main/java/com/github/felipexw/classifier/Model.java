package com.github.felipexw.classifier;

import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.List;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public interface Model {

    PredictedInstance predict(LabeledTrainingInstance instance);

    List<PredictedInstance> predict(List<LabeledTrainingInstance> instance);

}
