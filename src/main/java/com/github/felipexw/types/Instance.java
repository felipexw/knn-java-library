package com.github.felipexw.types;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class Instance {
    private final double[][] features;

    public Instance(double[][] features) {
        this.features = features;
    }

    public double[][] getFeatures() {
        return features;
    }
}
