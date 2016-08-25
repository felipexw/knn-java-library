package com.github.felipexw.metrics;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class PearsonSimilarityCalculator implements SimilarityCalculator {

    @Override
    public double calculate(double[] a, double[] b) {
        if (a == null || a.length == 0)
            throw new IllegalArgumentException("The params can't be null or empty.");
        double distance = 0;

        double avgFeaturesA  = getAvearageFromFeatures(a);
        double avgFeaturesB  = getAvearageFromFeatures(b);
        double aVariance= 0;
        double bVariance = 0;

        for (short i = 0; i < a.length; i++) {
            aVariance += a[i] * avgFeaturesA;
            bVariance += b[i] * avgFeaturesB;
        }

        double numerator = aVariance * bVariance;
        double denominator = Math.sqrt((Math.pow(aVariance, 2)) * (Math.pow(bVariance, 2)));

        return numerator/denominator;
    }

    private double getAvearageFromFeatures(double[] features){
        double average = 0;

        for(int i =0; i < features.length; i++)
            average += features[i];

        return average/features.length;
    }
}
