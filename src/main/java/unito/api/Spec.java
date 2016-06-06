package unito.api;

/**
 * Checks the result of the operation
 * Created by bynull on 03.06.16.
 */
public interface Spec<T, R> {
    void check(T input, R result) throws Exception;
}