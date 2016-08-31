package com.github.felipexw.metrics;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class CosineSimilarityCalculator implements SimilarityCalculator{
    @Override
    public double calculate(double[] a, double[] b) {
        if (a == null || b == null || (a.length != b.length))
            throw new IllegalArgumentException("args can't be invalid");

        double numerator = 0d;
        double denominator = 0d;
        double aFeatures = 0d;
        double bFeatures = 0d;

        for(int i =0; i < a.length; i++){
            numerator += a[i] * b[i];
            denominator += (Math.pow(a[i], 2)) * (Math.pow(b[i], 2));
        }

//        denominator = (Math.sqrt(aFeatures)) * (Math.sqrt(bFeatures));

        return numerator/denominator;
    }
}
