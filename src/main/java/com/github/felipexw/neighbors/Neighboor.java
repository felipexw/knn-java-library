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
}
