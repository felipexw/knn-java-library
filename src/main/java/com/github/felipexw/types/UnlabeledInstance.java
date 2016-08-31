package com.github.felipexw.types;

/**
 * Created by felipe.appio on 30/08/2016.
 */
public class UnlabeledInstance implements Instance{

    private double[] features;

    public UnlabeledInstance(double[] features) {
        this.features = features;
    }

    public double[] getFeatures() {
        return features;
    }
}
