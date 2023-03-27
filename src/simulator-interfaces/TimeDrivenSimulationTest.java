/**
 * Runs a series of basic tests on implementations of the {@link TimeDrivenSimulation} interface.
 */
public class TimeDrivenSimulationTest {
    private final TimeDrivenSimulation tds;

    public TimeDrivenSimulationTest(final TimeDrivenSimulation tds) {
        this.tds = tds;
    }

    /**
     * Calling {@link TimeDrivenSimulation#step()} once should change the current time in the
     * simulation by 1.
     * 
     * @return True for pass, false for fail.
     */
    @UnitTests.UnitTest
    public boolean testStepChangesTimeOnce() {
        TimeUnitInstant initialInstant, nextInstant;

        initialInstant = tds.currentTime();
        tds.step();
        nextInstant = tds.currentTime();

        // 1 unit of time should have elapsed between the first instant and the
        // second instant.
        return nextInstant.difference(initialInstant) == 1;
    }

    /**
     * Calling {@link TimeDrivenSimulation#step()} $n$ times should change the current time in the
     * simulation by $n$.
     * 
     * @return True for pass, false for fail.
     */
    @UnitTests.UnitTest
    public boolean testStepChangesTimeN() {
        TimeUnitInstant initialInstant, nextInstant;
        int n = 1000;

        initialInstant = tds.currentTime();
        for (int i = 0; i < n; i++) {
            tds.step();
        }
        nextInstant = tds.currentTime();
        return nextInstant.difference(initialInstant) == n;
    }

    /**
     * Verifying minimally that applying the super-type of our settings applies to the intance of
     * {@link TimeDrivenSimulation}.
     * 
     * @return true for pass, false for fail.
     */
    @UnitTests.UnitTest
    public boolean testApplySettings() {
        TimeDrivenSimulation.TimeDrivenSimulationSettings settings =
                TimeDrivenSimulationMain.settings();
        TimeUnitInstant maxTime = TimeDrivenSimulationMain.timeUnitInstant().fromInteger(5);

        settings.setMaxTimeInstant(maxTime);
        tds.applySettings(settings);

        return tds.maxTime().equals(settings.getMaxTimeInstant());
    }
}
