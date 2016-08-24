package com.github.felipexw.neighbors;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.metrics.DistanceCalculator;
import com.github.felipexw.types.Instance;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class SimpleKNNClassifier extends Classifier {
    private int k;
    private double[][] features;
    private List<LabeledTrainingInstance> instances;
    private DistanceCalculator distanceCalculator;

    public SimpleKNNClassifier(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
        k = 10;
    }

    @Override
    public void train(List<LabeledTrainingInstance> instances) {
        if (instances == null || instances.isEmpty())
            throw new IllegalArgumentException("Instances for training can't be null or empty.");

        this.instances = instances;
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
        for (short i = 0; i < instances.size(); i++) {
            LabeledTrainingInstance trainingInstance = instances.get(i);
            double distance = distanceCalculator.calculate(labeledInstance.getFeatures(), trainingInstance.getFeatures());

            Neighboor neighboor = new Neighboor(trainingInstance, distance);
            neighboorses.add(neighboor);
        }

        neighboorses = getKNearestNeighbors(neighboorses);
        return getLabelByMajorityVote(neighboorses);
    }

    private PredictedInstance getLabelByMajorityVote(List<Neighboor> neighboors) {
        Map<String, Integer> votes = new HashMap<>();

        for (Neighboor neighboor : neighboors) {
            LabeledInstance instance = neighboor.getInstance();
            if (!votes.containsKey(instance.getLabel()))
                votes.put(instance.getLabel(), 1);

            else{
                Integer count = votes.get(instance.getLabel());
                votes.put(instance.getLabel(), count+1);
            }
        }
        return getMajorLabel(votes);
    }

    private PredictedInstance getMajorLabel(Map<String, Integer> votes){
        Integer min = Integer.MAX_VALUE;
        Set<String> labels = votes.keySet();
        String votedLabel = null;

        for(String label: labels){
            Integer vote = votes.get(label);
            if (min > vote){
                min= vote;
                votedLabel = label;
            }

        }
        return new PredictedInstance(votedLabel, 1/min);
    }

    public List<Neighboor> getKNearestNeighbors(List<Neighboor> neighboors) {
        Collections.sort(neighboors, (nei1, nei2) -> {
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