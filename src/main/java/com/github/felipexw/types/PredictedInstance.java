package com.github.felipexw.types;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class PredictedInstance implements Instance{
    private final String label;
    private final double score;

    public String getLabel() {
        return label;
    }

    public PredictedInstance(String label, double score) {
        this.label = label;
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
