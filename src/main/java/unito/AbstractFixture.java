package unito;

import unito.api.Fixture;

/**
 * Contains test data
 * Created by bynull on 03.06.16.
 */
public abstract class AbstractFixture<T> implements Fixture<T> {
    protected T data;

    public AbstractFixture() {
        setup();
    }

    public abstract void setup();

    @Override
    public T getData() {
        return data;
    }
}
