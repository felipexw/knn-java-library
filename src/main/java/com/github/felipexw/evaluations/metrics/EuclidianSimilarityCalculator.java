package com.github.felipexw.evaluations.metrics;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class EuclidianSimilarityCalculator implements SimilarityCalculator {

    @Override
    public double calculate(List<Double> a, List<Double> b) {
        if (a == null || a.isEmpty() || (a.size()!= b.size()))
            throw new IllegalArgumentException("The params can't be null or empty.");
        double distance = 0;

        for (int i = 0; i < a.size(); i++) {
            double difference = b.get(i) - a.get(i);
            distance += Math.pow(difference, 2);
        }
        return Math.round(Math.sqrt(distance));
    }
}
