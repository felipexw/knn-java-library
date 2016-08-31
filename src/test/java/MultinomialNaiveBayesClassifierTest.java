import com.github.felipexw.classifiers.bayes.MultinomialNaiveBayesClassifier;
import com.github.felipexw.classifiers.bayes.NaiveBayes;
import com.github.felipexw.types.LabeledInstance;
import com.github.felipexw.types.LabeledTrainingInstance;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.*;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public class MultinomialNaiveBayesClassifierTest {

    private NaiveBayes naiveBayesClassifier;

    @Before
    public void setUp() {
        naiveBayesClassifier = new MultinomialNaiveBayesClassifier();
    }

    @Test
    public void it_should_calculate_a_priori_probs() {
        String negativeLabel = "negative";
        String positiveLabel = "positive";

        List<LabeledTrainingInstance> training =
                Arrays.asList(new LabeledTrainingInstance(new double[]{2}, negativeLabel),
                        new LabeledTrainingInstance(new double[]{2}, negativeLabel),
                        new LabeledTrainingInstance(new double[]{2}, positiveLabel));

        Map<String, Double> probs = naiveBayesClassifier.getPrioriProbs();

        assertThat(probs.get(negativeLabel))
                .isEqualTo(2);
        assertThat(probs.get(positiveLabel))
                .isEqualTo(1);
    }

    @Test
    public void it_should_calculate_the_posterior_probabilities() {
        /**
         * given the following sentences:
         *  1) 'windows sucks' (negative)
         *  2) 'amd sucs' (negative)
         *  3) 'intel its good' (positive)
         *  4) 'linux its good' (positive
         * it should calculate de posterior probabilities
         */

        String negativeLabel = "negative";
        String positiveLabel = "positive";

        double[] featuresA = {"windows".hashCode(), "sucks".hashCode()};
        double[] featuresB = {"amd".hashCode(), "sucks".hashCode()};
        double[] featuresC = {"intel".hashCode(), "its".hashCode(), "good".hashCode()};
        double[] featuresD = {"linux".hashCode(), "its".hashCode(), "good".hashCode()};

        LabeledTrainingInstance intance1 = new LabeledTrainingInstance(featuresA, negativeLabel);
        LabeledTrainingInstance intance2 = new LabeledTrainingInstance(featuresB, negativeLabel);
        LabeledTrainingInstance intance3 = new LabeledTrainingInstance(featuresC, positiveLabel);
        LabeledTrainingInstance intance4 = new LabeledTrainingInstance(featuresD, positiveLabel);

        naiveBayesClassifier.train(Arrays.asList(intance1, intance2, intance3, intance4));
        Map<String, List<LabeledInstance>> probs = naiveBayesClassifier.getPosterioriProbs();;

        /*for(Double key: probs.keySet()){



        }*/

        List<LabeledInstance> is = probs.get(String.valueOf(Double.parseDouble(String.valueOf("its".hashCode()))));
        for(LabeledInstance instance: is){
            System.out.println(instance.getCount());
        }

        assertThat(probs.size())
                .isEqualTo(7);

    }


}
