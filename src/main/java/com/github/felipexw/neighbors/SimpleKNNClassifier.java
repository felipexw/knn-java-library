package com.github.felipexw.neighbors;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.metrics.SimilarityCalculator;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.*;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class SimpleKNNClassifier extends Classifier {
    private int k;
    private double[][] features;
    private List<LabeledTrainingInstance> instances;
    private SimilarityCalculator similarityCalculator;

    public SimpleKNNClassifier(SimilarityCalculator similarityCalculator) {
        this.similarityCalculator = similarityCalculator;
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
            double distance = similarityCalculator.calculate(labeledInstance.getFeatures(), trainingInstance.getFeatures());

            Neighboor neighboor = new Neighboor(trainingInstance, distance);
            neighboorses.add(neighboor);
        }

        neighboorses = getKNearestNeighbors(neighboorses);
        return vote(neighboorses);
    }

    public PredictedInstance vote(List<Neighboor> neighboors) {
        Map<String, Integer> votes = new HashMap<>();

        for (Neighboor neighboor : neighboors) {
            LabeledInstance instance = neighboor.getInstance();
            if (!votes.containsKey(instance.getLabel()))
                votes.put(instance.getLabel(), 1);

            else {
                Integer count = votes.get(instance.getLabel());
                votes.put(instance.getLabel(), count + 1);
            }

        }

        String mostVotedLabel = getMostVotedLabel(votes);
        int nearestNeighboorIndex = getIndexOfNearestNeighboorVoted(mostVotedLabel, neighboors);
        Neighboor neighboor = neighboors.get(nearestNeighboorIndex);


        return new PredictedInstance(mostVotedLabel, neighboor.getDistance()/100);
    }

    private String getMostVotedLabel(Map<String, Integer> votes) {
        Set<String> keys = votes.keySet();
        int count = Integer.MIN_VALUE;

        String label = "";

        for(String key: keys){
            if (votes.get(key)  > count){
                count =  votes.get(key);
                label = key;
            }
        }

        return label;
    }

    private int getIndexOfNearestNeighboorVoted(String label, List<Neighboor> neighboors) {
        double distance = Integer.MAX_VALUE;
        int index =0;

        for (int i =0; i < neighboors.size(); i++) {
            Neighboor neighboor = neighboors.get(i);
            LabeledInstance instance = neighboor.getInstance();
            if (instance.getLabel().equalsIgnoreCase(label) && neighboor.getDistance() < distance){
                distance = neighboor.getDistance();
                index = i;
            }
        }

        return index;
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