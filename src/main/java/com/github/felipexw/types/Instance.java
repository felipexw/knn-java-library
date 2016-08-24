package com.github.felipexw.types;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public abstract class Instance {
    private final String description;

    public Instance(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instance instance = (Instance) o;

        return description.equals(instance.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public String toString() {
        return "description: " + description;
    }
}
