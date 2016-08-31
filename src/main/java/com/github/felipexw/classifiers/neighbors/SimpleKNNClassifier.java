package com.github.felipexw.classifiers.neighbors;

import com.github.felipexw.core.*;
import com.github.felipexw.core.extraction.DoubleFeatureExtractor;
import com.github.felipexw.core.extraction.FeatureExtractor;
import com.github.felipexw.evaluations.EvaluatorMetric;
import com.github.felipexw.evaluations.metrics.SimilarityCalculator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class SimpleKNNClassifier extends KNNClassifier {


    public SimpleKNNClassifier(SimilarityCalculator similarityCalculator, FeatureExtractor featureExtractor) {
        this.similarityCalculator = similarityCalculator;
        k = 5;
    }

    public Map<Neighbor, List<Neighbor>> getFeatures() {
        return ImmutableMap.copyOf(features);
    }

    @Override
    public void train(List<LabeledInstance> instances) {
        setUpForTraining(instances);
        calculateFeatureSimilarities();
    }

    @Override
    public void train(List<LabeledInstance> instances, int k) {
        setUpForTraining(instances);
        instances = getInstancesthatMaximizeAccuracy();
        calculateFeatureSimilarities();
    }

    protected List<LabeledInstance> getInstancesthatMaximizeAccuracy() {
        List<List<LabeledInstance>> partitionedInstances = Lists.partition(instances, instances.size() / k);
        int testIndex = 0;

        double[] accuraciesAndInstanceTestIndex = new double[k];

        do {
            features = new HashMap<>();
            List<Instance> testIntances = new ArrayList<>();

            for (int i = 0; i < partitionedInstances.size(); i++) {
                for (List<LabeledInstance> labeled : partitionedInstances) {
                    for (LabeledInstance instance : labeled) {
                        if (i != testIndex) {
                            Neighbor neighbor = new Neighbor(instance, -1d, featureExtractor);
                            List<Neighbor> neighbors = getNeighborsWithDistanceFromARootNeighboor(neighbor, k);
                            features.put(neighbor, neighbors);
                        } else
                            testIntances.add(instance);
                    }
                }
            }

            List<LabeledInstance> instances = getInstancesFromTrainedNeighbors();
            List<Prediction> predictionList = predict(testIntances);

            double accuracy = EvaluatorMetric.accuracy(partitionedInstances.get(testIndex), predictionList);
            accuraciesAndInstanceTestIndex[testIndex] = accuracy;

            testIndex++;
        } while (testIndex < accuraciesAndInstanceTestIndex.length - 1);

        testIndex = getTestIndexWithGreaterAccuracy(accuraciesAndInstanceTestIndex);
        return getTrainingLabeledInstances(partitionedInstances, testIndex);
    }


    protected List<LabeledInstance> getTrainingLabeledInstances(List<List<LabeledInstance>> instances, int testIndex) {
        List<LabeledInstance> trainingInstances = new ArrayList<>();

        for (int i = 0; i < instances.size(); i++) {
            if (i != testIndex) {
                for (LabeledInstance instance : instances.get(testIndex))
                    trainingInstances.add(instance);
            }
        }

        return trainingInstances;
    }


    protected int getTestIndexWithGreaterAccuracy(double[] accuracies) {
        double max = Integer.MIN_VALUE;
        int index = 0;

        for (byte i = 0; i < accuracies.length; i++) {
            if (accuracies[i] > max) {
                max = accuracies[i];
                index = i;
            }
        }

        return index;
    }

    private List<LabeledInstance> getInstancesFromTrainedNeighbors() {
        Set<Neighbor> keys = features.keySet();

        List<LabeledInstance> result = new ArrayList<>();
        for (Neighbor key : keys) {
            List<Neighbor> neighboors = features.get(key);
            for (Neighbor n : neighboors) {
                result.add(n.getInstance());
            }
        }
        return result;
    }


    protected void setUpForTraining(List<LabeledInstance> instances) {
        if (instances == null || instances.isEmpty())
            throw new IllegalArgumentException("Instances for training can't be null or empty.");

        this.instances = instances;
        this.features = new HashMap<>();
    }

    protected void calculateFeatureSimilarities() {
        for (int i = 0; i < instances.size(); i++) {
            LabeledInstance instance = instances.get(i);
            Neighbor neighbor = new Neighbor(instance, -1d, featureExtractor);

            List<Neighbor> neighbors = getNeighborsWithDistanceFromARootNeighboor(neighbor, this.k);
            features.put(neighbor, neighbors);
        }
    }

    protected List<Neighbor> getNeighborsWithDistanceFromARootNeighboor(Neighbor neighbor, int threshold) {
        Neighbor nei = new Neighbor(null, 0d, featureExtractor);
        List<Neighbor> neighbors = new ArrayList<>();

        LabeledInstance instance = neighbor.getInstance();

        for (int j = -1; j < instances.size() - 1; j++) {
            LabeledInstance neighborInstance = instances.get(j + 1);
            double similarity = similarityCalculator.calculate(instance.getFeatures(), neighborInstance.getFeatures());
            Neighbor neighborRoot = new Neighbor(neighborInstance, similarity, new DoubleFeatureExtractor());
            neighbors.add(neighborRoot);

            if (neighbors.size() == threshold)
                return neighbors;
        }

        return neighbors;

    }

    public List<Neighbor> similarNeighbors(LabeledInstance trainingInstance, int k) {
        if (trainingInstance == null)
            throw new IllegalArgumentException("Neighboor can't be invalid");

        if (features.containsKey(trainingInstance))
            features.get(trainingInstance);

        Neighbor neighbor1 = new Neighbor(trainingInstance, -1d, featureExtractor);
        return getNeighborsWithDistanceFromARootNeighboor(neighbor1, k);
    }

    @Override
    public Prediction predict(Instance labeledInstance) {
        if (labeledInstance == null)
            throw new IllegalArgumentException("Instance to predict can't be null");

        List<Neighbor> neighbors = getKNearestNeighbors(getAllNeighbors(labeledInstance));
        return vote(neighbors);
    }

    @Override
    public List<Prediction> predict(List<Instance> instances) {
        if (instances == null || instances.isEmpty())
            throw new IllegalArgumentException("instanances can't be empty or null");

        List<Prediction> predictionList = new ArrayList<>();
        for (Instance instance : instances)
            predictionList.add(predict(instance));

        return predictionList;
    }

    protected List<Neighbor> getAllNeighbors(Instance labeledInstance) {
        throw new UnsupportedOperationException("Continue the refactoring.");
        /*
        List<Neighbor> neighborses = new ArrayList<>();
        for (short i = 0; i < instances.size(); i++) {
            LabeledInstance trainingInstance = instances.get(i);
            double distance = similarityCalculator.calculate(labeledInstance.getFeatures(), trainingInstance.getFeatures());

            Neighbor neighbor = new Neighbor(trainingInstance, distance);
            neighborses.add(neighbor);
        }

        return neighborses;
        */
    }

    @Override
    public Prediction vote(List<Neighbor> neighbors) {
        Map<String, Integer> votes = new HashMap<>();

        for (Neighbor neighbor : neighbors) {
            LabeledInstance<Label, Model> instance = neighbor.getInstance();
            if (!votes.containsKey(instance.getLabel()))
                votes.put(instance.getLabel().toString(), 1);

            else {
                Integer count = votes.get(instance.getLabel());
                votes.put(instance.getLabel().toString(), count + 1);
            }

        }

        String mostVotedLabel = getMostVotedLabel(votes);
        int nearestNeighborIndex = getIndexOfNearestNeighboorVoted(mostVotedLabel, neighbors);
        Neighbor neighbor = neighbors.get(nearestNeighborIndex);


        return new Prediction(mostVotedLabel, neighbor.getDistance() / 100);
    }

    protected String getMostVotedLabel(Map<String, Integer> votes) {
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

    protected int getIndexOfNearestNeighboorVoted(String label, List<Neighbor> neighbors) {
        double distance = Integer.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < neighbors.size(); i++) {
            Neighbor neighbor = neighbors.get(i);
            LabeledInstance<Label, Model> instance = neighbor.getInstance();
            if (instance.getLabel().toString().equalsIgnoreCase(label) && neighbor.getDistance() < distance) {
                distance = neighbor.getDistance();
                index = i;
            }
        }

        return index;
    }

}