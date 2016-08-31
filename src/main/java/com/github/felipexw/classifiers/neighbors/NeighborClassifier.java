package com.github.felipexw.classifiers.neighbors;

import com.github.felipexw.metrics.SimilarityCalculator;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by felipe.appio on 31/08/2016.
 */
public abstract class NeighborClassifier {
    protected int k;
    protected Map<Neighbor, List<Neighbor>> features;
    protected List<LabeledInstance> instances;
    protected SimilarityCalculator similarityCalculator;

    public abstract PredictedInstance vote(List<Neighbor> neighbors);

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
