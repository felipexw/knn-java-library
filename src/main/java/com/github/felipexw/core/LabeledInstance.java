package com.github.felipexw.core;

import java.util.List;
import java.util.Objects;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class LabeledInstance<F extends Model> extends Instance<F> {
    protected String label;
    private int count;

    public LabeledInstance(String label, F model) {
        super(model);
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }


    public void setCount(int count) {
        this.count = count;
    }


    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabeledInstance<?> that = (LabeledInstance<?>) o;

        return label.equals(that.label);

    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }
}
