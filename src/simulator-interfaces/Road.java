/**
 * An interface for an ordered sequence of {@link Vehicle}s.
 */
public interface Road {
    /**
     * Get the {@link Vehicle} at the specified position on the road.
     * 
     * @param position the position on the road to check for a {@link Vehicle}.
     * @return The {@link Vehicle} at that space on the road, or {@code null} if there is no
     *         {@link Vehicle} in that position on the road.
     */
    Vehicle getVehicleAt(int position);

    /**
     * Add a {@link Vehicle} to the beginning of this road at position 0.
     * 
     * @param v the {@link Vehicle} to add.
     */
    void addVehicle(Vehicle v);
}
