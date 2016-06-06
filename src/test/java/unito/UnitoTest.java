package unito;

import org.junit.Test;
import unito.api.Action;
import unito.api.Fixture;
import unito.api.Spec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by bynull on 03.06.16.
 */
public class UnitoTest {

    @Test
    public void test() {
        new UnitoBox().test();
    }

    /**
     * Summ of elements in the list
     */
    class UnitoBox extends AbstractUnito<List<Integer>, Integer> {

        @Override
        public void configure() {
            fixture = new TestFixture();
            action = new TestAction();
            spec = new TestSpec();
        }

        class TestFixture extends AbstractFixture<List<Integer>> {

            @Override
            public void setup() {
                System.out.println("Init a fixture");
                data = Arrays.asList(123, 456);
            }

            @Override
            public List<? extends AbstractFixture<?>> dependsOn() {
                return Collections.emptyList();
            }
        }

        class TestAction implements Action<List<Integer>, Integer> {

            @Override
            public Integer execute(Fixture<List<Integer>> fixture) {
                System.out.println("Execute business logic");
                return new Calculator().sum(fixture.getData());
            }
        }

        class TestSpec implements Spec<List<Integer>, Integer> {

            @Override
            public void check(List<Integer> input, Integer result) {
                System.out.println("Check the result");
                assertEquals(Integer.valueOf(579), result);
            }
        }
    }

    public static class Calculator {

        public Integer sum(List<Integer> elements) {
            System.out.println("Calculate sum of elements");
            int result = 0;
            for (Integer element : elements) {
                result += element;
            }
            return result;
        }
    }
}
