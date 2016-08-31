package com.github.felipexw.core;

import java.util.List;
import java.util.Objects;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class LabeledInstance<L extends Label, F extends Model> extends Instance<F> {
    protected L label;
    private int count;

    public LabeledInstance(List<F> features,L label) {
        this.label = label;
        this.features = features;
    }

    public L getLabel() {
        return this.label;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public List<F> getFeatures() {

        return null;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabeledInstance<?, ?> that = (LabeledInstance<?, ?>) o;

        return label.equals(that.label);

    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }
}
