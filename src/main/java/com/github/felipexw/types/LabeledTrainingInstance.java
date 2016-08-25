package com.github.felipexw.types;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class LabeledTrainingInstance extends LabeledInstance{
    private final double[] features;

    public LabeledTrainingInstance(double[] features, String label) {
        super(label);
        this.features = features;
    }

    private void validateData(){
        /*
            TODO:
            implement a method to validate the features.
         */
    }

    public double[] getFeatures() {
        return features;
    }
}
