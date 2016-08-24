import com.github.felipexw.metrics.DistanceCalculator;
import com.github.felipexw.metrics.EuclidianDistanceCalculator;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by felipe.appio on 24/08/2016.
 */
public class EuclidianDistanceCalculatorCalculatorTest {
    private DistanceCalculator distanceCalculator;


    @Before
    public void setUp() {
        distanceCalculator = new EuclidianDistanceCalculator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldRaiseAnExceptionWithNullArgs() {
        distanceCalculator.calculate(null, null);
    }

    @Test
    public void it_should_calculate_the_distance_between_5_And_2(){
        double found = distanceCalculator.calculate(new double[]{3}, new double[]{5});
        double expected = Math.round(2.449489742783178);
        Truth.assertThat(found).isEqualTo(expected);
    }




}
