package com.github.felipexw.neighbors;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.metrics.DistanceCalculator;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.*;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class SimpleKNNClassifier extends Classifier {
    private int k;
    private double[][] features;
    private List<LabeledTrainingInstance>  instances;
    private DistanceCalculator distanceCalculator;

    public SimpleKNNClassifier(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
        k = 10;
    }

    @Override
    public void train(List<LabeledTrainingInstance> instances) {
        if (instances == null || instances.isEmpty())
            throw new IllegalArgumentException("Instances for training can't be null or empty.");

        this.instances  = instances;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }

    @Override
    public PredictedInstance predict(LabeledTrainingInstance labeledInstance) {
        if (labeledInstance == null)
            throw new IllegalArgumentException("Instance to predict can't be null");

        List<Neighboor> neighboorses = new ArrayList<>();
        for(short i=0; i  < instances.size(); i++){
            LabeledTrainingInstance trainingInstance = instances.get(i);
            double distance  = distanceCalculator.calculate(labeledInstance.getFeatures(), trainingInstance.getFeatures());

            Neighboor neighboor = new Neighboor(trainingInstance, distance);
            neighboorses.add(neighboor);
        }

        neighboorses = getKNearestNeighbors(neighboorses);

        return new PredictedInstance("", 0d);
    }

    private String getLabelByMajorityVote(double[] knnVotes){
        int max = Integer.MIN_VALUE;

        return null;
    }

    public List<Neighboor> getKNearestNeighbors(List<Neighboor> neighboors){
        Collections.sort(neighboors, (  nei1,   nei2) -> {
            if (nei2.getDistance() > nei1.getDistance())
                return -1;
            return 0;
        });

        if (neighboors.size() > k)
            return neighboors.subList(0, k);

        return neighboors;
    }

    public double[][] getFeatures() {
        return features;
    }


}