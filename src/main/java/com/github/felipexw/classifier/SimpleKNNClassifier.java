package com.github.felipexw.classifier;

import com.github.felipexw.metrics.Distance;
import com.github.felipexw.types.Instance;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class SimpleKNNClassifier extends Classifier {
    private short k;
    private double[][] features;
    private Distance distance;

    public SimpleKNNClassifier(Distance distance) {
        this.distance = distance;
    }

    @Override
    public void train(List<LabeledTrainingInstance> instances) {
        features = new double[instances.size()][instances.size()];

        for (short i = 0; i < features.length; i++) {
            for (short j = -1; j < features.length - 1; j++) {
                double[] dataPoint1 = instances.get(i).getFeatures();
                double[] dataPoint2 = instances.get(j + 1).getFeatures();
                features[i][j+1] = distance.calculate(dataPoint1, dataPoint2);
                System.out.println("Distance between: " + instances.get(i) + " and " + instances.get(j+1));
            }
        }
    }

    @Override
    public PredictedInstance predict(Instance instance) {
        if (instance == null)
            throw new IllegalArgumentException("Instance to predict can't be null");
        return new PredictedInstance("", 0d);
    }

    private List<Double> getKNearestNeighbors(){

        for(short i =0; i < features.length; i++){

        }
        return null;
    }

    public double[][] getFeatures() {
        return features;
    }


}
