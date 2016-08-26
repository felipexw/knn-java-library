package com.github.felipexw.classifier.neighbors;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.classifier.CrossValidateClassifier;
import com.github.felipexw.metrics.SimilarityCalculator;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import com.google.common.collect.ImmutableMap;

import java.util.*;
import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class SimpleKNNClassifier implements Classifier, CrossValidateClassifier {
    private int k;
    private Map<Neighbor, List<Neighbor>> features;
    private List<LabeledTrainingInstance> instances;
    private SimilarityCalculator similarityCalculator;

    public SimpleKNNClassifier(SimilarityCalculator similarityCalculator) {
        this.similarityCalculator = similarityCalculator;
        k = 5;
    }

    @Override
    public void train(List<LabeledTrainingInstance> instances) {
        setUpForTraining(instances);
        calculateFeatureSimilarities();
    }

    public Map<Neighbor, List<Neighbor>> getFeatures() {
        return ImmutableMap.copyOf(features);
    }

    @Override
    public void train(List<LabeledTrainingInstance> instances, int k) {
    }

    private void setUpForTraining(List<LabeledTrainingInstance> instances) {
        if (instances == null || instances.isEmpty())
            throw new IllegalArgumentException("Instances for training can't be null or empty.");

        this.instances = instances;
        this.features = new HashMap<>();
    }

    private void calculateFeatureSimilarities() {
        for (int i = 0; i < instances.size(); i++) {
            LabeledTrainingInstance instance = instances.get(i);
            Neighbor neighbor = new Neighbor(instance, -1d);

            List<Neighbor> neighbors = getNeighborsWithDistanceFromARootNeighboor(neighbor, this.k);
            features.put(neighbor, neighbors);
        }
    }

    private List<Neighbor> getNeighborsWithDistanceFromARootNeighboor(Neighbor neighbor, int threshold) {
        List<Neighbor> neighbors = new ArrayList<>();
        LabeledTrainingInstance instance = neighbor.getInstance();

        for (int j = -1; j < instances.size() - 1; j++) {
            LabeledTrainingInstance neighborInstance = instances.get(j + 1);
            double similarity = similarityCalculator.calculate(instance.getFeatures(), neighborInstance.getFeatures());
            Neighbor neighborRoot = new Neighbor(neighborInstance, similarity);
            neighbors.add(neighborRoot);
            if (neighbors.size() == threshold)
                return neighbors;
        }

        return neighbors;
    }

    public List<Neighbor> similarNeighbors(LabeledTrainingInstance trainingInstance, int k) {
        if (trainingInstance == null)
            throw new IllegalArgumentException("Neighboor can't be invalid");

        if (features.containsKey(trainingInstance))
            features.get(trainingInstance);

        Neighbor neighbor1 = new Neighbor(trainingInstance, -1d);
        return getNeighborsWithDistanceFromARootNeighboor(neighbor1, k);
    }

    @Override
    public PredictedInstance predict(LabeledTrainingInstance labeledInstance) {
        if (labeledInstance == null)
            throw new IllegalArgumentException("Instance to predict can't be null");

        List<Neighbor> neighbors = getKNearestNeighbors(getAllNeighbors(labeledInstance));
        return vote(neighbors);
    }

    private List<Neighbor> getAllNeighbors(LabeledTrainingInstance labeledInstance) {
        List<Neighbor> neighborses = new ArrayList<>();
        for (short i = 0; i < instances.size(); i++) {
            LabeledTrainingInstance trainingInstance = instances.get(i);
            double distance = similarityCalculator.calculate(labeledInstance.getFeatures(), trainingInstance.getFeatures());

            Neighbor neighbor = new Neighbor(trainingInstance, distance);
            neighborses.add(neighbor);
        }

        return neighborses;
    }


    public PredictedInstance vote(List<Neighbor> neighbors) {
        Map<String, Integer> votes = new HashMap<>();

        for (Neighbor neighbor : neighbors) {
            LabeledInstance instance = neighbor.getInstance();
            if (!votes.containsKey(instance.getLabel()))
                votes.put(instance.getLabel(), 1);

            else {
                Integer count = votes.get(instance.getLabel());
                votes.put(instance.getLabel(), count + 1);
            }

        }

        String mostVotedLabel = getMostVotedLabel(votes);
        int nearestNeighborIndex = getIndexOfNearestNeighboorVoted(mostVotedLabel, neighbors);
        Neighbor neighbor = neighbors.get(nearestNeighborIndex);


        return new PredictedInstance(mostVotedLabel, neighbor.getDistance() / 100);
    }

    private String getMostVotedLabel(Map<String, Integer> votes) {
        Set<String> keys = votes.keySet();
        int count = Integer.MIN_VALUE;

        String label = "";

        for (String key : keys) {
            if (votes.get(key) > count) {
                count = votes.get(key);
                label = key;
            }
        }

        return label;
    }

    private int getIndexOfNearestNeighboorVoted(String label, List<Neighbor> neighbors) {
        double distance = Integer.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < neighbors.size(); i++) {
            Neighbor neighbor = neighbors.get(i);
            LabeledInstance instance = neighbor.getInstance();
            if (instance.getLabel().equalsIgnoreCase(label) && neighbor.getDistance() < distance) {
                distance = neighbor.getDistance();
                index = i;
            }
        }

        return index;
    }


    private List<Neighbor> getKNearestNeighbors(List<Neighbor> neighbors) {
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