package com.github.felipexw.evaluations;

import com.github.felipexw.classifier.neighbors.Neighbor;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.List;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public class EvaluatorMetric {

    public static double accuracy(List<LabeledTrainingInstance> a, List<LabeledTrainingInstance> b) {
        if ((a == null || a.size() <= 1) || (b == null || b.size() <= 1))
            throw new IllegalArgumentException("The args can't be invalid.");

        double accuracy = 0d;

        for (int i = 0; i < a.size(); i++) {
            LabeledTrainingInstance realInstance = a.get(i);
            LabeledTrainingInstance predictedInstance = b.get(i);

            if (realInstance.getLabel().equalsIgnoreCase(predictedInstance.getLabel()))
                accuracy++;
        }

        return accuracy / a.size();
    }

    public static double fMeasure(double[] a, double[] b) {
        if ((a == null || a.length == 1) || (b == null || b.length == 1))
            throw new IllegalArgumentException("The args can't be invalid.");
        return 0d;
    }

    public static double precision(double[] a, double[] b) {
        if ((a == null || a.length == 1) || (b == null || b.length == 1))
            throw new IllegalArgumentException("The args can't be invalid.");
        return 0d;
    }

    public static double recall(double[] a, double[] b) {
        if ((a == null || a.length == 1) || (b == null || b.length == 1))
            throw new IllegalArgumentException("The args can't be invalid.");
        return 0d;
    }

    public static double test(){

        return 0;
    }
}
