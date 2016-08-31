package com.github.felipexw.classifiers;

import com.github.felipexw.types.Instance;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.List;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public interface Predictable {

    PredictedInstance predict(Instance instance);

    List<PredictedInstance> predict(List<Instance> instance);

}
