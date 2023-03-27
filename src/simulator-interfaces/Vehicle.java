/**
 * {@link Vehicle} is the specific type of thing that we're simulating with our traffic simulator.
 */
public interface Vehicle extends Queue.QueueItem {
    /**
     * Increase the current speed of the {@link Vehicle} by the specified amount.
     * 
     * @param by the amount to increase speed by.
     */
    void increaseSpeed(int by);

    /**
     * Decrease the current speed of the {@link Vehicle} by the specified amount. {@link Vehicle}
     * speed should always be 0 or more (never negative).
     * 
     * @param by the amount to decrease speed by.
     */
    void decreaseSpeed(int by);

    /**
     * The current speed the {@link Vehicle} is moving at. A newly constructed instance of
     * {@link Vehicle} should not be moving (its speed should be 0).
     * 
     * @return the current speed.
     */
    int currentSpeed();

    /**
     * Where the {@link Vehicle} currently is.
     * 
     * @return the current position of the {@link Vehicle}
     */
    int getPosition();

    /**
     * Whether or not the {@link Vehicle} will crash into the other {@link Vehicle} if it were to
     * move.
     * 
     * @param other the other {@link Vehicle}.
     * @return true if they collide, false if they don't.
     */
    boolean willCrashWithOnMove(Vehicle other);

    /**
     * Move the {@link Vehicle} by its current velocity.
     */
    void move();

    /**
     * Given the current state of this {@link Vehicle}, should it slow down? A {@link Vehicle}
     * should slow down if it's too close to the {@link Vehicle} in front of it (it will crash!) or
     * if its behaviour says that it will slow down.
     */
    boolean nervous();

    /**
     * Get a character-based representation of this {@link Vehicle} so that we can show it on
     * screen.
     * 
     * @return the character to represent this vehicle.
     */
    char asChar();
}

