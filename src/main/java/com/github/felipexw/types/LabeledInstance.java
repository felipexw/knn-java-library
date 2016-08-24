package com.github.felipexw.types;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class LabeledInstance extends Instance {
    private final String label;

    public LabeledInstance(String target) {
        super(target );
        this.label = target;
    }

    public String getLabel() {
        return label;
    }
}
