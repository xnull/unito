package unito.api;

/**
 * Created by bynull on 03.06.16.
 */
public interface Unito<T, R> {
    Fixture<T> fixture();

    Action<T, R> action();

    Spec<T, R> spec();

    void test();
}