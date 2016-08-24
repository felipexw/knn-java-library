import com.github.felipexw.metrics.Distance;
import com.github.felipexw.metrics.EuclidianDistance;
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
}
