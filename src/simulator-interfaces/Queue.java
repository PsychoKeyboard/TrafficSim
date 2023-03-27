/**
 * {@link Queue} is a simple interface defining a queue. While there might be more operations on
 * other queue types, this one very simply lets us ask how many things are currently in the queue,
 * put things onto the front of the queue, and take things off the back of the queue.
 * 
 * This queue has elements of type {@link QueueItem}.
 */
public interface Queue {
    /**
     * How many elements are currently in the queue.
     * 
     * @return How many elements are currently in the queue.
     */
    int size();

    /**
     * Put a new element onto the beginning of the queue.
     * 
     * @param thing The item to put onto the queue.
     */
    void enqueue(QueueItem thing);

    /**
     * Take an element off the end of the queue.
     * 
     * @return The element that's currently at the end of the queue.
     */
    QueueItem dequeue();

    /**
     * Look at an item on the queue without removing it from the queue.
     * 
     * @param index the location you want to peek at.
     * @return The element that's currently at the specified index, or {@code null}, if the index is
     *         out of bounds (negative or past the end of this queue).
     */
    QueueItem peek(int index);

    /**
     * A convenience method to peek at the end of the queue.
     * 
     * @return the element that's currently at the end of the queue, or {@code 
     *         null} if the queue is currently empty.
     */
    QueueItem peekAtEnd();

    /**
     * {@link QueueItem} is an interface that must be implemented for an item to be in the queue.
     * This does not specify any behaviour.
     */
    public interface QueueItem {

    }
}
