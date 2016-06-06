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
        try {
            dependsOn();
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void init() throws Exception;

    public abstract List<? extends AbstractFixture<?>> dependsOn() throws Exception;

    @Override
    public T getData() {
        return data;
    }
}
