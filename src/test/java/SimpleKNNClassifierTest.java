import com.github.felipexw.neighbors.Neighboor;
import com.github.felipexw.neighbors.SimpleKNNClassifier;
import com.github.felipexw.metrics.EuclidianDistanceCalculator;
import com.github.felipexw.types.Instance;
import com.github.felipexw.types.LabeledTrainingInstance;
import com.github.felipexw.types.PredictedInstance;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class SimpleKNNClassifierTest {
    private SimpleKNNClassifier classifier;

    @Before
    public void setUp() {
        classifier = new SimpleKNNClassifier(new EuclidianDistanceCalculator());
    }

    @Test
    public void it_should_calculate_the_distance_between_two_points() {
        LabeledTrainingInstance p1 = new LabeledTrainingInstance(new double[]{1, 2}, "P1");
        LabeledTrainingInstance p2 = new LabeledTrainingInstance(new double[]{3, 2}, "P2");
        LabeledTrainingInstance p3 = new LabeledTrainingInstance(new double[]{1, 2}, "P3");
        double[][] expected = new double[][]{
                {0, 2, 0},
                {2, 0, 2},
                {0, 2, 0}
        };

        classifier.train(Arrays.asList(p1, p2, p3));
        double[][] found = classifier.getFeatures();

        for (int i = 0; i < found.length; i++)
            Truth.assertThat(found[i]).isEqualTo(expected[i], 0.02);

    }

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

        List<Neighboor> kNearestNeighbors = classifier.getKNearestNeighbors(neighboors);
        PredictedInstance predictedExpected = new PredictedInstance("Car", 12d/100);
        PredictedInstance predictedFound = classifier.getLabelByMajorityVote(kNearestNeighbors);

//        Truth.assertThat(predictedFound)
//                .isEqualTo(predictedExpected);

        Truth.assertThat("Car")
                .isEqualTo(predictedFound.getLabel());
    }

}
