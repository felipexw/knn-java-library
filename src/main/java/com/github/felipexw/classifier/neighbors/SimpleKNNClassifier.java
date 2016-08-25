package com.github.felipexw.classifier.neighbors;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.metrics.SimilarityCalculator;
import com.github.felipexw.types.Instance;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class SimpleKNNClassifier extends Classifier {
    private int k;
    private byte kFolds;
    private Map<Neighbor, List<Neighbor>> features;
    private List<LabeledTrainingInstance> instances;
    private SimilarityCalculator similarityCalculator;

    public SimpleKNNClassifier(SimilarityCalculator similarityCalculator) {
        this.similarityCalculator = similarityCalculator;
        k = 5;
        kFolds = 10;
    }

    @Override
    public void train(List<LabeledTrainingInstance> instances) {
        if (instances == null || instances.isEmpty())
            throw new IllegalArgumentException("Instances for training can't be null or empty.");

        this.instances = instances;
        this.features = new HashMap<>();

        calculateFeatureSimilarities();
    }

    private void calculateFeatureSimilarities() {
        for(int i=0; i < instances.size(); i++){
            LabeledTrainingInstance instance = instances.get(i);
            Neighbor neighbor = new Neighbor(instance, 0d);

            List<Neighbor> neighbors = new ArrayList<>();
            for(int j =0; j < instances.size()-1; j++){
                if (i != j){
                    LabeledTrainingInstance neighborInstance = instances.get(j);
                    double similarity = similarityCalculator.calculate(instance.getFeatures(), neighborInstance.getFeatures());
                    Neighbor neighborRoot = new Neighbor(neighborInstance, similarity);
                    neighbors.add(neighborRoot);
                }
            }

            features.put(neighbor, neighbors);
        }
    }

    public List<Neighbor> similarNeighbors(Neighbor neighbor) {
        if (neighbor == null || neighbor.getInstance() == null)
            throw new IllegalArgumentException("Neighboor can't be invalid");

        return null;
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


    public void setkFolds(byte kFolds) {
        this.kFolds = kFolds;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }

}