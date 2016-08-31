package com.github.felipexw.evaluations.metrics;

import java.util.List;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class CosineSimilarityCalculator implements SimilarityCalculator{
    @Override
    public double calculate(List<Double> a, List<Double> b) {
        if (a == null || a.isEmpty() || (a.size()!= b.size()))
            throw new IllegalArgumentException("The params can't be null or empty.");

        double numerator = 0d;
        double denominator = 0d;
        double aFeatures = 0d;
        double bFeatures = 0d;

        for(int i =0; i < a.size(); i++){
            numerator += a.get(i) * b.get(i);
            denominator += (Math.pow(a.get(i), 2)) * (Math.pow(a.get(i), 2));
        }

//        denominator = (Math.sqrt(aFeatures)) * (Math.sqrt(bFeatures));

        return numerator/denominator;
    }
}
