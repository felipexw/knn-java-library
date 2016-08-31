import com.github.felipexw.classifiers.neighbors.SimpleKNNClassifier;
import com.github.felipexw.core.Model;
import com.github.felipexw.core.Prediction;
import com.github.felipexw.core.extraction.DoubleFeatureExtractor;
import com.github.felipexw.core.extraction.FeatureExtractor;
import com.github.felipexw.evaluations.metrics.EuclidianSimilarityCalculator;
import com.github.felipexw.core.LabeledInstance;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by felipe.appio on 24/08/2016.
 */
/*
*/
public class SimpleKNNClassifierTest {
    static class TestModel extends Model{
        private List<Double> stringFeatures;

        public TestModel(FeatureExtractor featureExtractor, List<Double> stringFeatures) {
            super(featureExtractor);
            this.features = stringFeatures;
        }
    }


    private SimpleKNNClassifier classifier;

    @Before
    public void setUp() {
        classifier = new SimpleKNNClassifier(new EuclidianSimilarityCalculator(), new DoubleFeatureExtractor());
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
    public void it_should_predict_a_negative_label(){
        /*
        given a set of negative points:
           -    A(2,4); B(3,2)
        and a set of positive points:
           -    D(4,1); D(5,5)
        the algorithm must predict the label (which its positive or negative) for the point E(1,3)
        */

        String positiveLabel = new String("positive");
        String negativeLabel = new String("negative");
        FeatureExtractor featureExtractor =   new DoubleFeatureExtractor();

        TestModel t1 = new TestModel(featureExtractor, Arrays.asList(3d, 4d));
        LabeledInstance<Model> pointA = new LabeledInstance<Model>(negativeLabel, t1);

        TestModel t2 = new TestModel(featureExtractor, Arrays.asList(3d, 2d));
        LabeledInstance<Model> pointB = new LabeledInstance<Model>(negativeLabel, t2);

        TestModel t3 = new TestModel(featureExtractor, Arrays.asList(4d, 1d));
        LabeledInstance<Model> pointC = new LabeledInstance<Model>(positiveLabel, t3);

        TestModel t4 = new TestModel(featureExtractor, Arrays.asList(5d, 5d));
        LabeledInstance<Model> pointD = new LabeledInstance<Model>(positiveLabel, t4);

        TestModel predictingTest = new TestModel(featureExtractor, Arrays.asList(1d, 3d));
        LabeledInstance<Model> pointE = new LabeledInstance<Model>(negativeLabel,predictingTest);

        classifier.setK(5);
        classifier.train(Arrays.asList(pointA, pointB, pointC, pointD));
        Prediction predictedInstance = classifier.predict(pointE);

        Truth.assertThat(predictedInstance.getLabel())
                .isEqualTo(negativeLabel);
    }

}



