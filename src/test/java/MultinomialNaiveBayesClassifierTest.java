import com.github.felipexw.classifiers.bayes.MultinomialNaiveBayesClassifier;
import com.github.felipexw.classifiers.bayes.NaiveBayes;
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
        Arrays.asList(new LabeledTrainingInstance(new double[] {2}, negativeLabel),
            new LabeledTrainingInstance(new double[] {2}, negativeLabel),
            new LabeledTrainingInstance(new double[] {2}, positiveLabel));

    Map<String, Double> probs = naiveBayesClassifier.getPrioriProbs();

    assertThat(probs.get(negativeLabel))
        .isEqualTo(2);
    assertThat(probs.get(positiveLabel))
        .isEqualTo(1);
  }


}
