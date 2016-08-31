import com.github.felipexw.evaluations.metrics.*;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * Created by felipe.appio on 24/08/2016.
 */
public class SimilarityCalculatorCalculatorTest {


    @Test(expected = IllegalArgumentException.class)
    public void itShouldRaiseAnExceptionWithNullArgs() {
        SimilarityCalculator similarityCalculator = new EuclidianSimilarityCalculator();
        similarityCalculator.calculate(null, null);
    }

    @Test
    public void it_should_calculate_the_euclidian_distance_between_5_And_2() {
        SimilarityCalculator similarityCalculator = new EuclidianSimilarityCalculator();
        double found = similarityCalculator.calculate(Arrays.asList(0d, 1d), Arrays.asList(0d));
        double expected = Math.round(2.449489742783178);
        Truth.assertThat(found).isEqualTo(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_calculate_the_pearson_correlation_with_empty_point_it_should_raise_an_exception() {
        SimilarityCalculator similarityCalculator = new PearsonSimilarityCalculator();
        similarityCalculator.calculate(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_calculate_the_manhattan_distance_with_empty_point_it_should_raise_an_exception() {
        SimilarityCalculator similarityCalculator = new ManhattanSimilarityCalculator();
        similarityCalculator.calculate(null, null);
    }

    @Test
    public void it_should_calculate_the_mahattan_distance_between_5_And_2() {
        SimilarityCalculator similarityCalculator = new ManhattanSimilarityCalculator();
        double found = similarityCalculator.calculate(Arrays.asList(3d, 2d, 50d), Arrays.asList(5d, 2d, 10d));
        double expected = 42d;

        Truth.assertThat(found).isEqualTo(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_calculate_the_jaccard_distance_with_invalid_args_it_should_raise_an_exception() {
        SimilarityCalculator similarityCalculator = new JaccardSimilarityCalculator();
        similarityCalculator.calculate(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_calculate_the_cosine_distance_with_invalid_args_it_should_raise_an_exception() {
        SimilarityCalculator similarityCalculator = new CosineSimilarityCalculator();
        similarityCalculator.calculate(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_calculate_the_cosine_distance_with_different_args_dimensions_it_should_raise_an_exception() {
        SimilarityCalculator similarityCalculator = new CosineSimilarityCalculator();
        similarityCalculator.calculate(Arrays.asList(1d), Arrays.asList(2d, 3d));
    }

    @Test()
    public void when_calculate_the_jaccard_distance_between_two_feature_vectors() {
        /**
         * A(2,2); B(2,2)
         */
        SimilarityCalculator similarityCalculator = new JaccardSimilarityCalculator();
        List<Double> a = Arrays.asList(2d, 2d);
        List<Double> b = Arrays.asList(2d, 2d);
        double expectedSimilarity = 0d;
        double similarity = similarityCalculator.calculate(a,b);

        Truth.assertThat(similarity)
                .isEqualTo(expectedSimilarity);
    }

    @Test()
    public void when_calculate_the_cosine_distance_between_two_feature_vectors() {
        /**
         * A(2,2); B(2,2)
         */
        SimilarityCalculator similarityCalculator = new JaccardSimilarityCalculator();
        List<Double> a = Arrays.asList(2d, 2d);
        List<Double> b = Arrays.asList(2d, 2d);

        double similarity = similarityCalculator.calculate(a, b);
        double expectedSimilarity = 0d;

        Truth.assertThat(similarity)
                .isEqualTo(expectedSimilarity);
    }

}
