package com.github.felipexw.classifier;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public abstract class Classifier {
    protected double precision;
    protected double recall;
    protected double fMeasure;
    protected double accuracy;

    abstract void train();

    abstract double predict();

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getfMeasure() {
        return fMeasure;
    }

    public void setfMeasure(double fMeasure) {
        this.fMeasure = fMeasure;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
