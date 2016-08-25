package com.github.felipexw.neighbors;

import com.github.felipexw.types.LabeledTrainingInstance;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class Neighboor {
    private final LabeledTrainingInstance instance;
    private final double distance;

    public Neighboor(LabeledTrainingInstance instance, double distance) {
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
        if (!(o instanceof Neighboor)) return false;

        Neighboor neighboor = (Neighboor) o;

        if (Double.compare(neighboor.distance, distance) != 0) return false;
        return instance.equals(neighboor.instance);

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
