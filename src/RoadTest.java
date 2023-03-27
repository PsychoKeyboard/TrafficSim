/**
 * A suite of tests for exercising an instance of {@link Road}.
 */
public class RoadTest {
    private final Road r;

    public RoadTest(Road implementation) {
        this.r = implementation;
    }

    /**
     * Verify that we can get a specific vehicle at a specific location. This test exercises both
     * {@link Road#addVehicle(Vehicle)} and {@link Road#getVehicleAt(int)}
     * 
     * @return true for pass, false for fail.
     */
    @UnitTests.UnitTest
    public boolean testVehicleAt() {
        Vehicle v = new VehicleFake() {
            public int getPosition() {
                return 5;
            }
        };

        r.addVehicle(v);
        return r.getVehicleAt(5).equals(v);
    }

    /**
     * Verify that attempting to get a {@link Vehicle} at a place on the road that doesn't have a
     * {@link Vehicle} returns {@code null}. The empty {@link Road} has no {@link Vehicle}s, so just
     * try to get the first one.
     * 
     * @return true for pass, false for fail.
     */
    @UnitTests.UnitTest
    public boolean testVehicleAtEmpty() {
        return r.getVehicleAt(0) == null;
    }

    /**
     * A fake implementation of {@link Vehicle} that's used to test the implementation of
     * {@link Road}. All methods on this implementation throw an exception. This class should not
     * (and cannot) be used outside of the enclosing test suite.
     */
    private static class VehicleFake implements Vehicle {
        public void increaseSpeed(int by) {
            throw new UnsupportedOperationException("Unimplemented method 'increaseSpeed'");
        }

        public void decreaseSpeed(int by) {
            throw new UnsupportedOperationException("Unimplemented method 'decreaseSpeed'");
        }

        public int currentSpeed() {
            throw new UnsupportedOperationException("Unimplemented method 'currentSpeed'");
        }

        public int getPosition() {
            throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
        }

        public boolean willCrashWithOnMove(Vehicle other) {
            throw new UnsupportedOperationException("Unimplemented method 'willCrashWithOnMove'");
        }

        public void move() {
            throw new UnsupportedOperationException("Unimplemented method 'move'");
        }

        public boolean nervous() {
            throw new UnsupportedOperationException("Unimplemented method 'slowsDown'");
        }

        public char asChar() {
            throw new UnsupportedOperationException("Unimplemented method 'asChar'");
        }
    }
}
