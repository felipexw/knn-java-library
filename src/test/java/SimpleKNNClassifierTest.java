import com.github.felipexw.classifiers.neighbors.Neighbor;
import com.github.felipexw.classifiers.neighbors.SimpleKNNClassifier;
import com.github.felipexw.metrics.EuclidianSimilarityCalculator;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class SimpleKNNClassifierTest {
    private SimpleKNNClassifier classifier;

    @Before
    public void setUp() {
        classifier = new SimpleKNNClassifier(new EuclidianSimilarityCalculator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_train_method_its_called_with_null_args_it_should_raise_an_exception_(){
        classifier.train(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_train_method_its_called_with_empty_args_it_should_raise_an_exception_(){
        classifier.train(Arrays.asList());
    }



    @Test
    public void it_should_get_the_most_voted_label(){
        LabeledTrainingInstance dummy1 = new LabeledTrainingInstance(null, "Car");
        Neighbor neighbor1 = new Neighbor(dummy1, 12d);

        LabeledTrainingInstance dummy2 = new LabeledTrainingInstance(null, "Car");
        Neighbor neighbor2 = new Neighbor(dummy1, 15d);

        LabeledTrainingInstance dummy3 = new LabeledTrainingInstance(null, "Car");
        Neighbor neighbor3 = new Neighbor(dummy1, 30d);

        LabeledTrainingInstance dummy4 = new LabeledTrainingInstance(null, "Boat");
        Neighbor neighbor4 = new Neighbor(dummy1, 50d);

        LabeledTrainingInstance dummy5 = new LabeledTrainingInstance(null, "Boat");
        Neighbor neighbor5 = new Neighbor(dummy1, 150d);

        LabeledTrainingInstance dummy6 = new LabeledTrainingInstance(null, "Boat");
        Neighbor neighbor6 = new Neighbor(dummy1, 170d);

        List<Neighbor> neighbors = Arrays.asList(neighbor1, neighbor2, neighbor3, neighbor4, neighbor5, neighbor6);
        classifier.setK(2);


        PredictedInstance predictedExpected = new PredictedInstance("Car", 12d/100);
//        PredictedInstance predictedFound = classifier.vote(kNearestNeighbors);

//        Truth.assertThat(predictedFound)
//                .isEqualTo(predictedExpected);

//        Truth.assertThat("Car")
//                .isEqualTo(predictedFound.getLabel());
    }


    @Test
    public void it_should_predict_a_negative_label(){
        /*
        given a set of negative points:
           -    A(2,4); B(3,2)
        and a set of positive points:
           -    D(4,1); D(5,5)
        the algorithm must predict the label (which its positive or negative) for the point E(1,3)
         */
        String positiveLabel = "positive";
        String negativeLabel = "negative";

        LabeledTrainingInstance pointA = new LabeledTrainingInstance(new double[]{2d, 4d}, negativeLabel);
        LabeledTrainingInstance pointB = new LabeledTrainingInstance(new double[]{3d, 2d}, negativeLabel);

        LabeledTrainingInstance pointC = new LabeledTrainingInstance(new double[]{4d, 1d}, positiveLabel);
        LabeledTrainingInstance pointD = new LabeledTrainingInstance(new double[]{5d, 5d}, positiveLabel);

        LabeledTrainingInstance pointE = new LabeledTrainingInstance(new double[]{1d, 3d}, "");

        classifier.setK(5);
        classifier.train(Arrays.asList(pointA, pointB, pointC, pointD));
        PredictedInstance predictedInstance = classifier.predict(pointE);

        Truth.assertThat(predictedInstance.getLabel())
                .isEqualTo(negativeLabel);
    }

    @Test
    public void it_should_predict_a_positive_label(){
        /*
        given a set of negative points:
           -    A(2,4); B(3,2)
        and a set of positive points:
           -    D(4,1); D(5,5)
        the algorithm must predict the label (which its positive or negative) for the point E(1,3)
         */
        String positiveLabel = "positive";
        String negativeLabel = "negative";

        LabeledTrainingInstance pointA = new LabeledTrainingInstance(new double[]{2d, 4d}, negativeLabel);
        LabeledTrainingInstance pointB = new LabeledTrainingInstance(new double[]{3d, 2d}, negativeLabel);

        LabeledTrainingInstance pointC = new LabeledTrainingInstance(new double[]{4d, 1d}, positiveLabel);
        LabeledTrainingInstance pointD = new LabeledTrainingInstance(new double[]{5d, 5d}, positiveLabel);

        LabeledTrainingInstance pointE = new LabeledTrainingInstance(new double[]{7d, 7d}, "");

        classifier.setK(3);
        classifier.train(Arrays.asList(pointA, pointB, pointC, pointD));
        PredictedInstance predictedInstance = classifier.predict(pointE);

        Truth.assertThat(predictedInstance.getLabel())
                .isEqualTo(negativeLabel);

        Truth.assertThat(predictedInstance.getScore())
                .isEqualTo((double)6/100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_similarNeighbors_its_called_with_null_neighbors_args_it_should_raise_an_exception(){
        classifier.similarNeighbors(null, 10);
    }

    @Test
    public void when_train_its_called_it_should_calculate_the_distance_between_the_neighbors(){
        /*
        given a set of negative points:
           -    A(2,4); B(3,2)
        and a set of positive points:
           -    D(4,1); D(5,5)
        the algorithm must predict the label (which its positive or negative) for the point E(1,3)
         */
        String positiveLabel = "positive";
        String negativeLabel = "negative";

        LabeledTrainingInstance pointA = new LabeledTrainingInstance(new double[]{2d, 4d}, negativeLabel);
        LabeledTrainingInstance pointB = new LabeledTrainingInstance(new double[]{3d, 2d}, negativeLabel);

        LabeledTrainingInstance pointC = new LabeledTrainingInstance(new double[]{4d, 1d}, positiveLabel);
        LabeledTrainingInstance pointD = new LabeledTrainingInstance(new double[]{5d, 5d}, positiveLabel);

        LabeledTrainingInstance pointE = new LabeledTrainingInstance(new double[]{7, 7d}, "");

        classifier.setK(2);
        classifier.train(Arrays.asList(pointA, pointB, pointC, pointD));
        List<Neighbor> similarNeighbors = classifier.similarNeighbors(pointE, 2);

        Neighbor n1 = new Neighbor(new LabeledTrainingInstance(pointA.getFeatures(), "teste"), 0d);
        Neighbor n2 = new Neighbor(new LabeledTrainingInstance(pointB.getFeatures(), "teste"), 0d);

        Truth.assertThat(similarNeighbors)
                .containsAllIn(Arrays.asList(n1, n2));
    }

    @Test
    public void when_trained_with_k_fold_it_should_predict_a_positive_label(){
        /*
        given a set of negative points:
           -    A(2,4); B(3,2); C(4,4)
        and a set of positive points:
           -    D(4,1); E(5,5); F(6,3)
        the algorithm must predict the label (which its positive or negative) for the point G(10,7)
         */
        String positiveLabel = "positive";
        String negativeLabel = "negative";

        LabeledTrainingInstance pointA = new LabeledTrainingInstance(new double[]{2d, 4d}, negativeLabel);
        LabeledTrainingInstance pointB = new LabeledTrainingInstance(new double[]{3d, 2d}, negativeLabel);
        LabeledTrainingInstance pointC = new LabeledTrainingInstance(new double[]{4, 4d}, negativeLabel);

        LabeledTrainingInstance pointD = new LabeledTrainingInstance(new double[]{4d, 1d}, positiveLabel);
        LabeledTrainingInstance pointE = new LabeledTrainingInstance(new double[]{5d, 5d}, positiveLabel);
        LabeledTrainingInstance pointF = new LabeledTrainingInstance(new double[]{6d, 3d}, positiveLabel);

        classifier.setK(3);
        classifier.train(Arrays.asList(pointA, pointB, pointC, pointD, pointE, pointF), 3);

        double scoreExpected = Math.sqrt(29)/100;
        PredictedInstance predictedInstance = new PredictedInstance(positiveLabel, scoreExpected);

        PredictedInstance predictedInstance1 = classifier.predict(pointF);
        Truth.assertThat(predictedInstance.getLabel())
                .isEqualTo(positiveLabel);
        Truth.assertThat(predictedInstance.getScore())
                .isEqualTo(scoreExpected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void it_should_raise_an_exception_when_predict_list_method_its_called(){
        classifier.predict(Collections.emptyList());
    }

}
