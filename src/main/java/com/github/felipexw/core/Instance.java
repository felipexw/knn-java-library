package com.github.felipexw.core;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public abstract class Instance<F> {
    protected  List<F> features;

    public abstract  List<F> getFeatures();
}
