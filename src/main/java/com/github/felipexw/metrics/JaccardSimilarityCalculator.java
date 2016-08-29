package com.github.felipexw.metrics;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class JaccardSimilarityCalculator implements SimilarityCalculator {
    @Override
    public double calculate(double[] a, double[] b) {
        if (a == null || b == null)
            throw new IllegalArgumentException("args can't be invalid");

        return 0;
    }
}
