/**
 * An interface for an instant in time for a {@link TimeDrivenSimulation}. We might be better off
 * using the JDK's {@link Temporal} or {@link Instant}, but those interfaces are more complex than
 * we really need right now. We could build classes later that satisfy both interfaces if we decide
 * to support them.
 */
public interface TimeUnitInstant {
    /**
     * Increment the current time unit by one.
     */
    void increment();

    /**
     * Compute the difference between two units of time. Difference is not a commutative operation;
     * the operation here is {@code this - other}.
     * 
     * @param other The instant to subtract from this one.
     * @return The difference between these instants as an integer.
     */
    default int difference(TimeUnitInstant other) {
        return asInteger() - other.asInteger();
    };

    /**
     * Return the current internal value of this instant of time as an integer.
     * 
     * @return The current time as an integer.
     */
    int asInteger();

    /**
     * Return an instance of {@link TimeUnitInstance} using this integer as a starting value.
     * 
     * @param value the value to use internally to represent this time instance.
     * @return an instance of {@link TimeUnitInstance}.
     */
    TimeUnitInstant fromInteger(int value);

    /**
     * Return a copy of the current time unit instant so that any changes to the underlying state of
     * *this* instant won't affect copies.
     * 
     * @return a copy of the current instant.
     */
    TimeUnitInstant instant();
}
