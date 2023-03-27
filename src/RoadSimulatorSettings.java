/**
 * A concrete implementation of {@link TimeDrivenSimulation.TimeDrivenSimulationSettings}.
 */
public interface RoadSimulatorSettings extends TimeDrivenSimulation.TimeDrivenSimulationSettings {

    /**
     * Set the length of the road.
     * 
     * @param roadLength the road's length.
     */
    void setRoadLength(int roadLength);

    /**
     * Get the road length.
     * 
     * @return the road length.
     */
    int getRoadLength();

    /**
     * Set the speed limit.
     * 
     * @param limit the speed limit.
     */
    void setSpeedLimit(int limit);

    /**
     * Get the speed limit.
     * 
     * @return the speed limit.
     */
    int getSpeedLimit();

}
