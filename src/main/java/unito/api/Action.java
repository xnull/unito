package unito.api;

/**
 * Represents business logic and executes domain actions
 * Created by bynull on 03.06.16.
 */
public interface Action<T, R> {
    R execute(Fixture<T> fixture) throws Exception;
}