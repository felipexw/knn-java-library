package com.github.felipexw.classifier.neighbors;

import com.github.felipexw.types.LabeledTrainingInstance;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class Neighbor {
    private final LabeledTrainingInstance instance;
    private final double distance;

    public Neighbor(LabeledTrainingInstance instance, double distance) {
        this.instance = instance;
        this.distance = distance;
    }

    public LabeledTrainingInstance getInstance() {
        return instance;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Neighbor)) return false;

        Neighbor neighbor = (Neighbor) o;

        return instance.equals(neighbor.instance);
    }

    @Override
    public int hashCode() {
        return instance.hashCode();
    }

    @Override
    public String toString() {
        return "Neighbor{" +
                "instance=" + instance +
                ", distance=" + distance +
                '}';
    }
}
