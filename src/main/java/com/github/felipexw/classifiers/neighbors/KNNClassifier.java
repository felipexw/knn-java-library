package com.github.felipexw.classifiers.neighbors;

import com.github.felipexw.classifiers.Classifier;
import com.github.felipexw.core.CrossValidation;
import com.github.felipexw.evaluations.metrics.SimilarityCalculator;
import com.github.felipexw.core.LabeledInstance;
import com.github.felipexw.core.Prediction;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by felipe.appio on 31/08/2016.
 */
public abstract class KNNClassifier implements Classifier, CrossValidation {
    protected int k;
    protected Map<Neighbor, List<Neighbor>> features;
    protected List<LabeledInstance> instances;
    protected SimilarityCalculator similarityCalculator;

    public abstract Prediction vote(List<Neighbor> neighbors);

    protected List<Neighbor> getKNearestNeighbors(List<Neighbor> neighbors) {
        Collections.sort(neighbors, (nei1, nei2) -> {
            if (nei2.getDistance() > nei1.getDistance())
                return -1;
            return 0;
        });

        if (neighbors.size() > k)
            return neighbors.subList(0, k);

        return neighbors;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }
}
