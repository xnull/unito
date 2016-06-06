package unito;

import unito.api.Action;
import unito.api.Fixture;
import unito.api.Spec;
import unito.api.Unito;

/**
 * Created by bynull on 03.06.16.
 */
public abstract class AbstractUnito<T, R> implements Unito<T, R> {
    protected Fixture<T> fixture;
    protected Action<T, R> action;
    protected Spec<T, R> spec;

    @Override
    public void fixture(Fixture<T> fixture) {
        this.fixture = fixture;
    }

    @Override
    public void action(Action<T, R> action) {
        this.action = action;
    }

    @Override
    public void spec(Spec<T, R> spec) {
        this.spec = spec;
    }

    @Override
    public void test() throws Exception {
        checkParameter("Fixture", fixture);
        checkParameter("Action", action);
        checkParameter("Spec", spec);

        T input = fixture.getData();
        R result = action.execute(fixture);
        spec.check(input, result);
    }

    private void checkParameter(String parameterName, Object parameter) {
        if (parameter == null) {
            throw new IllegalStateException(parameterName + " does't set");
        }
    }
}