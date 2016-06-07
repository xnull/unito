package unito;

import org.junit.Test;
import unito.api.Action;
import unito.api.Fixture;
import unito.api.Spec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by bynull on 03.06.16.
 */
public class UnitoTest {

    @Test
    public void testWithFixture() throws Exception {
        new AbstractUnito<List<Integer>, Integer>() {{
            fixture(new TestFixture() {{
                dependsOn(new LowerLevelFixture() {{
                    //we can customize the behavior of the fixture here
                }});

                //Transform generated test data
                transformData(new DataTransformer<List<Integer>>() {
                    @Override
                    public List<Integer> transform(List<Integer> data) {
                        data.add(111);
                        return data;
                    }
                });
            }});

            action(new TestAction() {{
                //we can modify business logic here
            }});

            spec(new TestSpec() {{
                //Additional checks if needed, or override default checks
            }});

            test();
        }};
    }

    @Test
    public void testWithDataManager() throws Exception {
        final TestFixture testFixture = new TestFixture() {{
            dependsOn(new LowerLevelFixture() {{
                //we can customize the behavior of the fixture here
            }});

            //Transform generated test data
            transformData(new DataTransformer<List<Integer>>() {
                @Override
                public List<Integer> transform(List<Integer> data) {
                    data.add(111);
                    return data;
                }
            });
        }};


        new AbstractUnito<List<Integer>, Integer>() {{
            fixture(new TestDataManager(){{
                fixture = testFixture;
            }});

            action(new TestAction() {{
                //we can modify business logic here
            }});

            spec(new TestSpec() {{
                //Additional checks if needed, or override default checks
            }});

            test();
        }};
    }

    static class TestFixture extends AbstractFixture<List<Integer>> {

        @Override
        public List<Integer> init() throws Exception {
            System.out.println("Init a fixture");
            return new ArrayList<Integer>() {{
                addAll(Arrays.asList(123, 456));
            }};
        }
    }

    static class LowerLevelFixture extends AbstractFixture<String> {

        @Override
        protected String init() throws Exception {
            System.out.println("Dependency fixture!!!");
            return null;
        }
    }

    static class TestDataManager extends DataManager<List<Integer>> {
        private final DataStorage dataStorage = new DataStorage();

        @Override
        protected List<Integer> init() throws Exception {
            System.out.println("Add an element to the data storage");
            for (Integer el : fixture.getData()) {
                dataStorage.add(el.toString());
            }
            return fixture.getData();
        }
    }

    static class TestAction implements Action<List<Integer>, Integer> {
        private final Calculator calc = new Calculator();

        @Override
        public Integer execute(Fixture<List<Integer>> fixture) throws Exception {
            System.out.println("Execute business logic");
            return calc.sum(fixture.getData());
        }
    }

    static class TestSpec implements Spec<List<Integer>, Integer> {

        @Override
        public void check(List<Integer> input, Integer result) {
            System.out.println("Check the result");
            assertEquals(Integer.valueOf(123 + 456 + 111), result);
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

    public static class DataStorage {
        private final List<String> storage = new ArrayList<>();

        public void add(String element) {
            storage.add(element);
        }
    }
}
