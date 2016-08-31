package com.github.felipexw.core;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public abstract class Instance<F extends Model> {
    protected final F model;

    public Instance(F model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}
