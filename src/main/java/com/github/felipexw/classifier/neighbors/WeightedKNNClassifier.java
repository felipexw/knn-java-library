package com.github.felipexw.classifier.neighbors;

import com.github.felipexw.classifier.Classifier;
import com.github.felipexw.classifier.CrossValidateClassifier;
import com.github.felipexw.metrics.SimilarityCalculator;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;

import java.util.List;

/**
 * Created by felipe.appio on 25/08/2016.
 */
public class WeightedKNNClassifier extends SimpleKNNClassifier {
    private double weight;

    public WeightedKNNClassifier(SimilarityCalculator similarityCalculator) {
        super(similarityCalculator);
    }

    public WeightedKNNClassifier(SimilarityCalculator similarityCalculator, double weight) {
        super(similarityCalculator);
        this.weight = weight;
    }


}
