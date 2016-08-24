package com.github.felipexw.types;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public abstract class LabeledInstance implements Instance {
    protected final String label;

    public LabeledInstance(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
