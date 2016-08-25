import com.github.felipexw.classifier.neighbors.Neighboor;
import com.github.felipexw.classifier.neighbors.SimpleKNNClassifier;
import com.github.felipexw.metrics.EuclidianSimilarityCalculator;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class SimpleKNNClassifierTest {
    private SimpleKNNClassifier classifier;

    @Before
    public void setUp() {
        classifier = new SimpleKNNClassifier(new EuclidianSimilarityCalculator());
    }

//    @Test
//    public void it_should_calculate_the_distance_between_two_points() {
//        LabeledTrainingInstance p1 = new LabeledTrainingInstance(new double[]{1, 2}, "P1");
//        LabeledTrainingInstance p2 = new LabeledTrainingInstance(new double[]{3, 2}, "P2");
//        LabeledTrainingInstance p3 = new LabeledTrainingInstance(new double[]{1, 2}, "P3");
//        double[][] expected = new double[][]{
//                {0, 2, 0},
//                {2, 0, 2},
//                {0, 2, 0}
//        };
//
//        classifier.train(Arrays.asList(p1, p2, p3));
//        double[][] found = classifier.getFeatures();
//
//        for (int i = 0; i < found.length; i++)
//            Truth.assertThat(found[i]).isEqualTo(expected[i], 0.02);
//
//    }

    @Test(expected = IllegalArgumentException.class)
    public void when_predict_method_its_called_with_invalid_args_it_should_raise_an_exception_(){
        classifier.predict(null);
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
    public void it_should_calculate_the_2_nearest_neighboors(){
        LabeledTrainingInstance dummy1 = new LabeledTrainingInstance(null, "Car");
        Neighboor neighboor1  = new Neighboor(dummy1, 12d);

        LabeledTrainingInstance dummy2 = new LabeledTrainingInstance(null, "Car");
        Neighboor neighboor2  = new Neighboor(dummy1, 15d);

        LabeledTrainingInstance dummy3 = new LabeledTrainingInstance(null, "Car");
        Neighboor neighboor3  = new Neighboor(dummy1, 30d);

        LabeledTrainingInstance dummy4 = new LabeledTrainingInstance(null, "Boat");
        Neighboor neighboor4  = new Neighboor(dummy1, 50d);

        LabeledTrainingInstance dummy5 = new LabeledTrainingInstance(null, "Boat");
        Neighboor neighboor5  = new Neighboor(dummy1, 150d);

        LabeledTrainingInstance dummy6 = new LabeledTrainingInstance(null, "Boat");
        Neighboor neighboor6  = new Neighboor(dummy1, 170d);

        List<Neighboor> neighboors = Arrays.asList(neighboor1, neighboor2, neighboor3, neighboor4, neighboor5, neighboor6);
        classifier.setK(2);

        List<Neighboor> found = classifier.getKNearestNeighbors(neighboors);
        List<Neighboor> expected = Arrays.asList(neighboor1, neighboor2);

        Truth.assertThat(found)
                .containsAllIn(expected);
    }

    @Test
    public void it_should_get_the_most_voted_label(){
        LabeledTrainingInstance dummy1 = new LabeledTrainingInstance(null, "Car");
        Neighboor neighboor1  = new Neighboor(dummy1, 12d);

        LabeledTrainingInstance dummy2 = new LabeledTrainingInstance(null, "Car");
        Neighboor neighboor2  = new Neighboor(dummy1, 15d);

        LabeledTrainingInstance dummy3 = new LabeledTrainingInstance(null, "Car");
        Neighboor neighboor3  = new Neighboor(dummy1, 30d);

        LabeledTrainingInstance dummy4 = new LabeledTrainingInstance(null, "Boat");
        Neighboor neighboor4  = new Neighboor(dummy1, 50d);

        LabeledTrainingInstance dummy5 = new LabeledTrainingInstance(null, "Boat");
        Neighboor neighboor5  = new Neighboor(dummy1, 150d);

        LabeledTrainingInstance dummy6 = new LabeledTrainingInstance(null, "Boat");
        Neighboor neighboor6  = new Neighboor(dummy1, 170d);

        List<Neighboor> neighboors = Arrays.asList(neighboor1, neighboor2, neighboor3, neighboor4, neighboor5, neighboor6);
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


}
