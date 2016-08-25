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

        if (Double.compare(neighbor.distance, distance) != 0) return false;
        return instance.equals(neighbor.instance);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = instance.hashCode();
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
