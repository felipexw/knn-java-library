package com.github.felipexw.core;

import com.github.felipexw.core.extraction.FeatureExtractor;
import com.github.felipexw.core.extraction.FeatureVector;

import java.util.List;

/**
 * Created by felipe.appio on 31/08/2016.
 */
public class Model implements FeatureVector {

    protected FeatureExtractor featureExtractor;
    protected List features;

    public Model(FeatureExtractor featureExtractor) {
        this.featureExtractor = featureExtractor;
    }

    @Override
    public List getData() {
        return  featureExtractor.extract(features);
    }

}
