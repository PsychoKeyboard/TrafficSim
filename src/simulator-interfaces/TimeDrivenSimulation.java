/**
 * {@code TimeDrivenSimulation} is an interface describing the behaviour necessary for an external
 * main driver class to interact with a time-driven simulation. The primary behaviour for a
 * time-driven simulation is to be able to get the current state of the simulation, and to
 * {@link TimeDrivenSimulation#step()} --- change the state of all objects currently in the
 * simulation, then increment the time state by one unit.
 */
public interface TimeDrivenSimulation {
    /**
     * Increment the current time by one unit, then change the state of all objects currently in the
     * system.
     */
    void step();

    /**
     * Get the current time of the simulation as a {@link TimeUnitInstant}.
     * 
     * @return the current {@link TimeUnitInstant} for this simulation. This should **always**
     *         return a new instance of {@link TimeUnitInstant}.
     */
    TimeUnitInstant currentTime();

    /**
     * Get the maximum time that this simulation should stop at.
     * 
     * @return the maximum time for the simulation.
     */
    TimeUnitInstant maxTime();

    /**
     * Print the current state of the simulation.
     */
    void printState();

    /**
     * Print a summary report at the end of the simulation.
     */
    void printReport();

    /**
     * Provide some settings for the simulation before it starts.
     * 
     * @param settings the settings for the simulation.
     */
    void applySettings(TimeDrivenSimulationSettings settings);

    /**
     * Settings for a {@link TimeDrivenSimulation}. This is only used as a super-type for specific
     * settings implementations that will be passed to your {@link TimeDrivenSimulation}.
     */
    public interface TimeDrivenSimulationSettings {
        /**
         * How long should this simulation run for?
         * 
         * @param maxTime the maximum time this simulation should run until.
         */
        void setMaxTimeInstant(TimeUnitInstant maxTime);

        /**
         * How long should this simulation run for?
         * 
         * @return the maximum time this simulation should run until.
         */
        TimeUnitInstant getMaxTimeInstant();
    }
}
