package com.github.felipexw.core.extraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by felipe.appio on 31/08/2016.
 */
public class DoubleFeatureExtractor implements FeatureExtractor<Double> {

    @Override
    public List<Double> extract(List<Object> source) {
        List<Double> target = new ArrayList<>();
        source.forEach(s -> target.add(Double.parseDouble(String.valueOf(s))));
        return target;
    }
}