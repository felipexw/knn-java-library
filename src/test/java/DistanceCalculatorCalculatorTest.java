import com.github.felipexw.metrics.DistanceCalculator;
import com.github.felipexw.metrics.EuclidianDistanceCalculator;
import com.github.felipexw.metrics.ManhattanDistanceCalculator;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by felipe.appio on 24/08/2016.
 */
public class DistanceCalculatorCalculatorTest {


    @Test(expected = IllegalArgumentException.class)
    public void itShouldRaiseAnExceptionWithNullArgs() {
        DistanceCalculator distanceCalculator = new EuclidianDistanceCalculator();
        distanceCalculator.calculate(null, null);
    }

    @Test
    public void it_should_calculate_the_euclidian_distance_between_5_And_2(){
        DistanceCalculator distanceCalculator = new EuclidianDistanceCalculator();
        double found = distanceCalculator.calculate(new double[]{3}, new double[]{5});
        double expected = Math.round(2.449489742783178);
        Truth.assertThat(found).isEqualTo(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_calculate_the_manhattan_distance_with_empty_point_it_should_raise_an_exception(){
        DistanceCalculator distanceCalculator = new ManhattanDistanceCalculator();
        distanceCalculator.calculate(null, null);
    }

    @Test
    public void it_should_calculate_the_mahattan_distance_between_5_And_2(){
        DistanceCalculator distanceCalculator = new ManhattanDistanceCalculator();
        double found = distanceCalculator.calculate(new double[]{3,2, 50}, new double[]{5,2, 10});
        double expected = 42d;

        Truth.assertThat(found).isEqualTo(expected);
    }



}
