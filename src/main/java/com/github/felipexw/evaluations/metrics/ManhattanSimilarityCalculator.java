package com.github.felipexw.evaluations.metrics;

import java.util.List;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class ManhattanSimilarityCalculator implements SimilarityCalculator {
    @Override
    public double calculate(List<Double> a, List<Double> b) {
        if (a == null || a.size() == 0)
            throw new IllegalArgumentException("The params can't be null or empty.");
        double distance = 0;

        for (short i = 0; i < a.size(); i++) {
            double difference = Math.abs(b.get(i) - a.get(i));
            distance += difference;
        }
        return distance;
    }
}
