import com.github.felipexw.evaluations.EvaluatorMetric;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public class EvaluatorMetricTest {

    private EvaluatorMetric evalutor;

    @Before
    public void setUp(){
        evalutor = new EvaluatorMetric();
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_accuracy_evaluator_its_called_with_null_args_it_should_raise_an_exception(){
        evalutor.accuracy(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_accuracy_evaluator_its_called_with_empty_args_it_should_raise_an_exception(){
        evalutor.accuracy(new double[1], new double[1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_precision_evaluator_its_called_with_null_args_it_should_raise_an_exception(){
        evalutor.precision(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_precision_evaluator_its_called_with_empty_args_it_should_raise_an_exception(){
        evalutor.precision(new double[1], new double[1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_recall_evaluator_its_called_with_null_args_it_should_raise_an_exception(){
        evalutor.recall(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_recall_evaluator_its_called_with_empty_args_it_should_raise_an_exception(){
        evalutor.recall(new double[1], new double[1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_fMeasure_evaluator_its_called_with_null_args_it_should_raise_an_exception(){
        evalutor.fMeasure(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_fMeasure_evaluator_its_called_with_empty_args_it_should_raise_an_exception(){
        evalutor.fMeasure(new double[1], new double[1]);
    }
}
