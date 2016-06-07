package unito;

import unito.api.Fixture;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contains test data
 * Created by bynull on 03.06.16.
 */
public abstract class AbstractFixture<T> implements Fixture<T> {
    private static final DataTransformer<Object> DEFAULT_TRANSFORMER = new DataTransformer<Object>() {
        @Override
        public Object transform(Object data) {
            return data;
        }
    };

    protected T data;
    protected final List<AbstractFixture<?>> dependencies = new ArrayList<>();
    protected DataTransformer<T> transformer = (DataTransformer<T>) DEFAULT_TRANSFORMER;

    @Override
    public void apply() {
        for (AbstractFixture<?> dependency : dependencies) {
            dependency.apply();
        }

        try {
            this.data = transformer.transform(initInternal());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    T initInternal() throws Exception {
        return init();
    }

    protected abstract T init() throws Exception;

    protected void dependsOn(AbstractFixture<?> dependency) {
        Objects.requireNonNull(dependency);
        dependencies.add(dependency);
    }

    protected void transformData(DataTransformer<T> transformer) {
        this.transformer = transformer;
    }

    @Override
    public T getData() {
        return data;
    }

    public interface DataTransformer<T> {
        T transform(T data);
    }
}
