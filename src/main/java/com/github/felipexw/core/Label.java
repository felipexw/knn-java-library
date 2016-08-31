package com.github.felipexw.core;

/**
 * Created by felipe.appio on 31/08/2016.
 */
public class Label<T> {
    private final T label;

    public T getLabel() {
        return label;
    }

    public Label(T label) {
        this.label = label;
    }
}
