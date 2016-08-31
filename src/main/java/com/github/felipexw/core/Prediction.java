package com.github.felipexw.core;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class Prediction{
    private final String label;
    private final double score;
    private int count;

    public String getLabel() {
        return label;
    }

    public Prediction(String label, double score) {
        this.label = label;
        this.score = score;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "label='" + label + '\'' +
                ", score=" + score +
                ", count=" + count +
                '}';
    }
}
