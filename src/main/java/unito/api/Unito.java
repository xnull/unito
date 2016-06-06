package unito.api;

/**
 * Created by bynull on 03.06.16.
 */
public interface Unito<T, R> {
    void fixture(Fixture<T> fixture);

    void action(Action<T, R> action);

    void spec(Spec<T, R> spec);

    void test() throws Exception;
}