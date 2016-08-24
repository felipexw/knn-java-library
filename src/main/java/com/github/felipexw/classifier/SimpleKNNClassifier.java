package com.github.felipexw.classifier;

import com.github.felipexw.metrics.Distance;
import com.github.felipexw.metrics.EuclidianDistance;
import com.github.felipexw.types.Instance;
import com.github.felipexw.types.TrainingInstance;

import java.util.Collections;
import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class SimpleKNNClassifier extends Classifier {
    private short k;
    private double[][] features;
    private Distance distance;

    public SimpleKNNClassifier() {
        distance = new EuclidianDistance();
    }

    @Override
    public void train(List<TrainingInstance> instances) {
//        Collections.sort(instances, (Instance i1, Instance i2) -> i1.getDescription().compareTo(i2.getDescription()));
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
    public double predict(Instance instance) {
        return 0;
    }

    public double[][] getFeatures() {
        return features;
    }


}
