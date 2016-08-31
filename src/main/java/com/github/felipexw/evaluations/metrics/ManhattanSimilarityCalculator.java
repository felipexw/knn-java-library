package com.github.felipexw.evaluations.metrics;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class ManhattanSimilarityCalculator implements SimilarityCalculator {
    @Override
    public double calculate(double[] a, double[] b) {
        if (a == null || a.length == 0)
            throw new IllegalArgumentException("The params can't be null or empty.");
        double distance = 0;

        for (short i = 0; i < a.length; i++) {
            double difference = Math.abs(b[i] - a[i]);
            distance += difference;
        }
        return distance;
    }
}
