/**
 * A suite of tests for exercising an instance of {@link Vehicle}.
 */
public class VehicleTest {
    private final Vehicle v;

    public VehicleTest(final Vehicle v) {
        this.v = v;
    }

    /**
     * We shouldn't be moving when we're first created.
     */
    @UnitTests.UnitTest
    public boolean testInitialSpeed() {
        return v.currentSpeed() == 0;
    }

    /**
     * Increasing our speed should increase our speed.
     * 
     * @return
     */
    @UnitTests.UnitTest
    public boolean testIncreaseSpeed() {
        v.increaseSpeed(10);
        return v.currentSpeed() == 10;
    }

    /**
     * We shouldn't be able to slow down below zero.
     * 
     * @return
     */
    @UnitTests.UnitTest
    public boolean testDecreaseSpeedBelowZero() {
        v.decreaseSpeed(10);
        return v.currentSpeed() == 0;
    }

    /**
     * A vehicle would crash into itself if it tried to move (moving would cause it to move past
     * itself, we can't pass on this one dimensional plane).
     * 
     * @return
     */
    @UnitTests.UnitTest
    public boolean testCrashInto() {
        Vehicle other = TimeDrivenSimulationMain.vehicle();
        // move `other` to be just in front of `v`
        other.increaseSpeed(5);
        other.move();

        // try to increase the speed of this vehicle and move it
        // past `other`, we should indicate that we will crash.
        v.increaseSpeed(10);
        return v.willCrashWithOnMove(other);
    }

    @UnitTests.UnitTest
    public boolean testMove() {
        v.increaseSpeed(10);
        v.move();
        return v.getPosition() == 10;
    }
}
