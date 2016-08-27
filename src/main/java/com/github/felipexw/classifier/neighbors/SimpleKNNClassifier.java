package com.github.felipexw.classifier.neighbors;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.classifier.CrossValidateClassifier;
import com.github.felipexw.evaluations.EvaluatorMetric;
import com.github.felipexw.metrics.SimilarityCalculator;
import com.github.felipexw.types.Instance;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.sql.Array;
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

        public Map<Neighbor, List<Neighbor>> getFeatures() {
        return ImmutableMap.copyOf(features);
    }

    @Override
    public void train(List<LabeledTrainingInstance> instances) {
        setUpForTraining(instances);
        calculateFeatureSimilarities();
    }

    @Override
    public void train(List<LabeledTrainingInstance> instances, int k) {
        setUpForTraining(instances);
        instances = getInstancesthatMaximizeAccuracy();
        calculateFeatureSimilarities();
    }

    private List<LabeledTrainingInstance> getInstancesthatMaximizeAccuracy() {
        Map<Integer, double[]> kFoldedFeatures = new HashMap<>();

        List<List<LabeledTrainingInstance>> partitionedInstances = Lists.partition(instances, instances.size()/k);
        int testIndex = 0;

        double[] accuraciesAndInstanceTestIndex = new double[k];

        do{
            features = new HashMap<>();
            List<LabeledTrainingInstance> testIntances = new ArrayList<>();

            for (int i = 0; i < partitionedInstances.size(); i++) {
                for (List<LabeledTrainingInstance> labeled : partitionedInstances) {
                    for (LabeledTrainingInstance instance : labeled) {
                        if (i != testIndex) {
                            Neighbor neighbor = new Neighbor(instance, -1d);
                            List<Neighbor> neighbors = getNeighborsWithDistanceFromARootNeighboor(neighbor, k);
                            features.put(neighbor, neighbors);
                        }
                        else{
                            testIntances.add(instance);
                        }
                    }
                }
            }

            List<LabeledTrainingInstance> instances = getInstancesFromTrainedNeighbors();
            List<PredictedInstance> predictedInstanceList = predict(testIntances);

            double accuracy = EvaluatorMetric.accuracy(partitionedInstances.get(testIndex), predictedInstanceList);
            accuraciesAndInstanceTestIndex[testIndex] = accuracy;

            testIndex++;
        }while(testIndex < accuraciesAndInstanceTestIndex.length-1);

        testIndex = getTestIndexWithGreaterAccuracy(accuraciesAndInstanceTestIndex);
        return getTrainingLabeledInstances(partitionedInstances, testIndex);
    }


    private List<LabeledTrainingInstance> getTrainingLabeledInstances(List<List<LabeledTrainingInstance>> instances, int testIndex) {
        List<LabeledTrainingInstance> trainingInstances = new ArrayList<>();

        for (int i = 0; i < instances.size(); i++) {
            if (i != testIndex) {
                for (LabeledTrainingInstance instance : instances.get(testIndex))
                    trainingInstances.add(instance);
            }
        }

        return trainingInstances;
    }


    private int getTestIndexWithGreaterAccuracy(double[] accuracies) {
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

    private List<LabeledTrainingInstance> getInstancesFromTrainedNeighbors() {
        Set<Neighbor> keys = features.keySet();

        List<LabeledTrainingInstance> result = new ArrayList<>();
        for (Neighbor key : keys) {
            List<Neighbor> neighboors = features.get(key);
            for (Neighbor n : neighboors) {
                result.add(n.getInstance());
            }
        }
        return result;
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

    @Override
    public List<PredictedInstance> predict(List<LabeledTrainingInstance> instances) {
        if (instances == null || instances.isEmpty())
            throw new IllegalArgumentException("instanances can't be empty or null");

        List<PredictedInstance> predictedInstanceList = new ArrayList<>();
        for(LabeledTrainingInstance instance: instances)
            predictedInstanceList.add(predict(instance));

        return predictedInstanceList;
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