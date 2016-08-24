import com.github.felipexw.metrics.Distance;
import com.github.felipexw.metrics.EuclidianDistance;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by felipe.appio on 24/08/2016.
 */
public class EuclidianDistanceTest {
    private Distance distance;


    @Before
    public void setUp() {
        distance = new EuclidianDistance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldRaiseAnExceptionWithNullArgs() {
        distance.calculate(null, null);
    }

    @Test
    public void it_should_calculate_the_distance_between_5_And_2(){
        double found = distance.calculate(new double[]{3}, new double[]{5});
        double expected = Math.round(2.449489742783178);
        Truth.assertThat(found).isEqualTo(expected);
    }

}
