package com.github.felipexw.core.extraction;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by felipe.appio on 31/08/2016.
 */
public class DoubleFeatureExtractor implements FeatureExtractor<Double>{

    @Override
    public List<Double> extract(List<Object> source) {
        return Arrays.asList(new Double(1d), new Double(2d), new Double(3d), new Double(4d));
    }
}