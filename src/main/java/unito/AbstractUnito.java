package unito;

import unito.api.Action;
import unito.api.Fixture;
import unito.api.Spec;
import unito.api.Unito;

/**
 * Created by bynull on 03.06.16.
 */
abstract class AbstractUnito<T, R> implements Unito {
    protected Fixture<T> fixture;
    protected Action<T, R> action;
    protected Spec<T, R> spec;

    public AbstractUnito() {
        configure();
    }

    public abstract void configure();

    @Override
    public void test() {
        T input = fixture.getData();
        R result = action.execute(fixture);
        spec.check(input, result);
    }

    @Override
    public Fixture fixture() {
        return fixture;
    }

    @Override
    public Action action() {
        return action;
    }

    @Override
    public Spec spec() {
        return spec;
    }
}