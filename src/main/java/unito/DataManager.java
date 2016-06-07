package unito;

/**
 * Actions (Post processing) after taking generated data, for example: to save the data into a database.
 * It has same interface as Fixture and actually it's a fixture, but in contrast to a fixture it's needed to
 * send data to an external system or make some actions, not just generate stubs or fill a pojo.
 * It depends on a fixture and it takes data from the fixture.
 *
 * @param <T> generated data
 */
public abstract class DataManager<T> extends AbstractFixture<T> {
    protected AbstractFixture<T> fixture;

    @Override
    public void apply() {
        fixture.apply();
        super.apply();
    }

    @Override
    T initInternal() throws Exception {
        if (fixture == null) {
            throw new IllegalStateException("Can't init the data manager. Please set a fixture in initialization block of the class");
        }
        return super.initInternal();
    }

    /**
     * The method makes some actions with the data and returns the result of these actions or just same data
     *
     * @return the data
     * @throws Exception an exception
     */
    @Override
    protected abstract T init() throws Exception;
}