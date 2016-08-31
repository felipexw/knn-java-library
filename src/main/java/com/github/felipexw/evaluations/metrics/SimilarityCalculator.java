package com.github.felipexw.evaluations.metrics;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public interface SimilarityCalculator {

    double calculate(List<Double> a, List<Double> b);


}
