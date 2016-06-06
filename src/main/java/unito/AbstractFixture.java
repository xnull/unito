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
    protected final T data;
    protected final List<AbstractFixture<?>> dependencies = new ArrayList<>();
    protected DataTransformer<T> transformer;

    protected AbstractFixture() {
        try {
            this.data = transformer.transform(init());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract T init() throws Exception;

    protected void dependsOn(AbstractFixture<?> dependency) {
        Objects.requireNonNull(dependency);
        dependencies.add(dependency);
    }

    protected void transformData(DataTransformer<T> transformer){
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
