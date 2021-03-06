package com.github.felipexw.classifiers.neighbors;

import com.github.felipexw.core.LabeledInstance;
import com.github.felipexw.core.Model;
import com.github.felipexw.core.extraction.FeatureExtractor;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class Neighbor extends  Model {
    private LabeledInstance instance;
    private double distance;

    public Neighbor(LabeledInstance instance, double distance, FeatureExtractor featureExtractor) {
        super(featureExtractor);
        this.instance = instance;
        this.distance = distance;
    }

    public LabeledInstance getInstance() {
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
