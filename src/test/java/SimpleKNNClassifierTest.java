import com.github.felipexw.classifier.SimpleKNNClassifier;
import com.github.felipexw.types.Instance;
import com.github.felipexw.types.TrainingInstance;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class SimpleKNNClassifierTest {
    private SimpleKNNClassifier classifier;

    @Before
    public void setUp() {
        classifier = new SimpleKNNClassifier();
    }

    @Test
    public void it_should_calculate_the_distance_between_two_points() {
        TrainingInstance p1 = new TrainingInstance(new double[]{1, 2}, "P1");
        TrainingInstance p2 = new TrainingInstance(new double[]{3, 2}, "P2");
        TrainingInstance p3 = new TrainingInstance(new double[]{1, 2}, "P3");
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

}
