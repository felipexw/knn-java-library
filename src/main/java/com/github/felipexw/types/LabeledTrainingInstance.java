package com.github.felipexw.types;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class TrainingInstance extends Instance{
    private final double[] features;

    public TrainingInstance(double[] features, String description) {
        super(description);
        this.features = features;
    }

    public double[] getFeatures() {
        return features;
    }
}
