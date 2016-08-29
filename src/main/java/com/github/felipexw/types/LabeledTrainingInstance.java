package com.github.felipexw.types;

import java.util.Arrays;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class LabeledTrainingInstance extends LabeledInstance{
    private final double[] features;

    public LabeledTrainingInstance(double[] features, String label) {
        super(label);
        this.features = features;
    }

    public double[] getFeatures() {
        return features;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LabeledTrainingInstance)) return false;

        LabeledTrainingInstance that = (LabeledTrainingInstance) o;

        return Arrays.equals(features, that.features);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(features);
    }

    @Override
    public String toString() {
        return "LabeledTrainingInstance{" +
                "features=" + Arrays.toString(features) +
                '}';
    }
}
