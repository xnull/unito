package unito;

import unito.api.Fixture;

import java.util.List;

/**
 * Contains test data
 * Created by bynull on 03.06.16.
 */
public abstract class AbstractFixture<T> implements Fixture<T> {
    protected T data;

    public AbstractFixture() {
        dependsOn();
        setup();
    }

    public abstract void setup();

    public abstract List<? extends AbstractFixture<?>> dependsOn();

    @Override
    public T getData() {
        return data;
    }
}
