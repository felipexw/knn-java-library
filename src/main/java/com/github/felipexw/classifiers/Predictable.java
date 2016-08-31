package com.github.felipexw.classifiers;

import com.github.felipexw.types.Instance;
import com.github.felipexw.types.Prediction;

import java.util.List;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public interface Predictable {

    Prediction predict(Instance instance);

    List<Prediction> predict(List<Instance> instance);

}
