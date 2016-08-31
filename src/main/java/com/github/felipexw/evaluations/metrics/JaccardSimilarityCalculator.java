package com.github.felipexw.evaluations.metrics;

import java.util.List;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class JaccardSimilarityCalculator implements SimilarityCalculator {
    @Override
    public double calculate(List<Double> a, List<Double> b) {
        if (a == null || b == null || a.size() != b.size())
            throw new IllegalArgumentException("args can't be invalid");
        double similarity = 0d;
        for(int i =0; i < a.size(); i++){
            similarity += Math.abs(a.get(i) - b.get(i));
        }
        return similarity;
    }
}
