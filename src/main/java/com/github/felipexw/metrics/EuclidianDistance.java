package com.github.felipexw.metrics;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class EuclidianDistance implements Distance {
    @Override
    public double calculate(double[] a, double[] b) {
        if (a == null || a.length == 0)
            throw new IllegalArgumentException("The params can't be null or empty.");
        double distance = 0;

        for (short i = 0; i < a.length; i++) {
            double difference = b[i] - a[i];
            distance += Math.pow(difference, 2);
        }
        return Math.round(Math.sqrt(distance));
    }
}
