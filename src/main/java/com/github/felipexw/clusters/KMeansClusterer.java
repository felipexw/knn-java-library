package com.github.felipexw.clusters;

import com.github.felipexw.metrics.SimilarityCalculator;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import com.github.felipexw.types.UnlabeledInstance;

import java.util.List;

/**
 * Created by felipe.appio on 30/08/2016.
 */
public class KMeansClusterer implements KMeansCluster {
    private int k;
    private int maxIterations;
    private SimilarityCalculator similarityCalculator;

    public KMeansClusterer(SimilarityCalculator similarityCalculator) {
        this.similarityCalculator = similarityCalculator;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    @Override
    public void cluster(List<UnlabeledInstance> instanceList) {
        if (instanceList == null || instanceList.isEmpty())
            throw new IllegalArgumentException("args can't be invalid");

        for(UnlabeledInstance instance: instanceList){
            double[] features = instance.getFeatures();

        }

    }
}