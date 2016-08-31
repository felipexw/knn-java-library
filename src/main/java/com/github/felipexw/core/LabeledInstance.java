package com.github.felipexw.core;

import java.util.Objects;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class LabeledInstance<L extends Label, F extends Model> extends Instance<String> {
    protected L label;
    private int count;

    public LabeledInstance(L label) {
        this.label = label;
    }

    public L getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return label.toString();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
