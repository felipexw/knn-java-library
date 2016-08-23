package com.github.felipexw.types;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class LabeledInstance extends Instance {
    private final String target;

    public LabeledInstance(double[][] features, String target) {
        super(features);
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
