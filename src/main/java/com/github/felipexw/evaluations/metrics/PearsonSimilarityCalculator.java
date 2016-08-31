package com.github.felipexw.evaluations.metrics;

import java.util.List;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class PearsonSimilarityCalculator implements SimilarityCalculator {

    /**
     *
     * @param a feature vector from datapoint 1
     * @param b feature vector from datapoint 2
     * @return the pearon correlation among them.
     *          the correlation will be among -1 and 1 (the more close to 1, the better):
     *              * 1 means the datapoints are perfect correlated.
     *              * -1 means the datapoints inversaly proportional. In other words, while 1 datapoint increase, the other decrease.
     *              * 0  means the datapoints doesn't have a correlation among them.
     */
    @Override
    public double calculate(List<Double> a, List<Double> b) {
        if (a == null || a.size() == 0)
            throw new IllegalArgumentException("The params can't be null or empty.");
        double distance = 0;

        double avgFeaturesA  = getAvearageFromFeatures(a);
        double avgFeaturesB  = getAvearageFromFeatures(b);
        double aVariance= 0;
        double bVariance = 0;

        for (short i = 0; i < a.size(); i++) {
            aVariance += a.get(i) * avgFeaturesA;
            bVariance += b.get(i) * avgFeaturesB;
        }

        double numerator = aVariance * bVariance;
        double denominator = Math.sqrt((Math.pow(aVariance, 2)) * (Math.pow(bVariance, 2)));

        return numerator/denominator;
    }

    private double getAvearageFromFeatures(List<Double> features){
        double average = 0;

        for(int i =0; i < features.size(); i++)
            average += features.get(i);

        return average/features.size();
    }
}
