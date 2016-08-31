import com.github.felipexw.classifiers.neighbors.KNNClassifier;
import com.github.felipexw.classifiers.neighbors.Neighbor;
import com.github.felipexw.classifiers.neighbors.SimpleKNNClassifier;
import com.github.felipexw.core.Label;
import com.github.felipexw.core.Model;
import com.github.felipexw.core.Prediction;
import com.github.felipexw.core.extraction.DoubleFeatureExtractor;
import com.github.felipexw.core.extraction.FeatureExtractor;
import com.github.felipexw.evaluations.metrics.EuclidianSimilarityCalculator;
import com.github.felipexw.core.LabeledInstance;
import com.google.common.truth.Truth;
import javafx.scene.control.Labeled;
import org.junit.Before;
import org.junit.Test;
import com.google.common.truth.Truth.*;

import java.sql.Array;
import java.util.*;

/**
 * Created by felipe.appio on 24/08/2016.
 */
/*
*/
public class SimpleKNNClassifierTest {
    static class TestModel extends Model{
        private List<String> stringFeatures;

        public TestModel(FeatureExtractor featureExtractor, List<String> stringFeatures) {
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

        Label positiveLabel = new Label("positive");
        Label negativeLabel = new Label("negative");
        FeatureExtractor featureExtractor =   new DoubleFeatureExtractor();

        TestModel t1 = new TestModel(featureExtractor, Arrays.asList("3.0", "4.0"));
        LabeledInstance<Label, Model> pointA = new LabeledInstance<Label, Model>(Arrays.asList(t1), negativeLabel);

        TestModel t2 = new TestModel(featureExtractor, Arrays.asList("3.0", "2.0"));
        LabeledInstance<Label, Model> pointB = new LabeledInstance<Label, Model>(Arrays.asList(t2), negativeLabel);

        TestModel t3 = new TestModel(featureExtractor, Arrays.asList("4.0", "1.0"));
        LabeledInstance<Label, Model> pointC = new LabeledInstance<Label, Model>(Arrays.asList(t3), negativeLabel);

        TestModel t4 = new TestModel(featureExtractor, Arrays.asList("5.0", "5.0"));
        LabeledInstance<Label, Model> pointD = new LabeledInstance<Label, Model>(Arrays.asList(t2), negativeLabel);

        TestModel predictingTest = new TestModel(featureExtractor, Arrays.asList("1.0", "3.0"));
        LabeledInstance<Label, Model> pointE = new LabeledInstance<Label, Model>(Arrays.asList(predictingTest), negativeLabel);

        classifier.setK(5);
        classifier.train(Arrays.asList(pointA, pointB, pointC, pointD));
        Prediction predictedInstance = classifier.predict(pointE);

        Truth.assertThat(predictedInstance.getLabel())
                .isEqualTo(negativeLabel);
    }

}



