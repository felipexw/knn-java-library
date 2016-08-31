package com.github.felipexw.evaluations;

import com.github.felipexw.core.LabeledInstance;
import com.github.felipexw.core.Prediction;

import java.util.List;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public class EvaluatorMetric {

    public static double accuracy(List<LabeledInstance> expectedList, List<Prediction> predictedList) {
        if ((expectedList == null || expectedList.size() <= 1) || (predictedList == null || predictedList.size() <= 1))
            throw new IllegalArgumentException("The args can't be invalid.");

        double accuracy = 0d;

        for (int i = 0; i < expectedList.size(); i++) {
            LabeledInstance realInstance = expectedList.get(i);
            Prediction prediction = predictedList.get(i);

            if (realInstance.getLabel().equals(prediction.getLabel()))
                accuracy++;
        }

        return accuracy / expectedList.size();
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
