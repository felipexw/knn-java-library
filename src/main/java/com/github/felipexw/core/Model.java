package com.github.felipexw.core;

import com.github.felipexw.core.extraction.FeatureExtractor;
import com.github.felipexw.core.extraction.FeatureVector;

import java.util.List;

/**
 * Created by felipe.appio on 31/08/2016.
 */
public class Model<T> implements FeatureVector {

    protected FeatureExtractor featureExtractor;
    protected List<T> features;

    public Model(FeatureExtractor featureExtractor) {
        this.featureExtractor = featureExtractor;
    }

    @Override
    public List<T> getData() {
        if (features == null)
            features = featureExtractor.extract(features);
        return features;
    }

}
