import java.util.ArrayList;
import java.util.List;

/**
 * A suite of tests for exercising a {@link Queue}.
 */
public class QueueTest {
    private final Queue q;

    public QueueTest(Queue q) {
        this.q = q;
    }

    /**
     * A new queue should be empty by default!
     * 
     * @return true if the queue is empty (pass), false otherwise (fail)
     */
    @UnitTests.UnitTest
    public boolean testEmptyQueue() {
        return q.size() == 0;
    }

    /**
     * Add one thing to the queue, there should be one thing in the queue.
     * 
     * @return true for pass, false for fail
     */
    @UnitTests.UnitTest
    public boolean testSingleItemSize() {
        CountingThing x = new CountingThing();

        q.enqueue(x);

        return q.size() == 1;
    }

    /**
     * Add one thing ot the queue, we should be able to dequeue the same thing.
     * 
     * @return true for pass, false for fail
     */
    @UnitTests.UnitTest
    public boolean testAddRemoveSingle() {
        CountingThing x = new CountingThing();

        q.enqueue(x);
        Queue.QueueItem y = q.dequeue();
        return x.equals(y);
    }

    /**
     * Add one thing ot the queue and remove it, the queue should be empty.
     * 
     * @return true for pass, false for fail
     */
    @UnitTests.UnitTest
    public boolean testAddRemoveThenEmpty() {
        q.enqueue(new CountingThing());
        q.dequeue();
        return q.size() == 0;
    }

    /**
     * We should be able to add a bunch of things and then get them back in the same order that we
     * inserted them in.
     * 
     * @return true for pass, false for fail
     */
    @UnitTests.UnitTest
    public boolean testAddRemoveOrdered() {
        final List<CountingThing> things = new ArrayList<>();
        final int TOTAL_THINGS = 100;
        boolean inOrder = true;

        for (int i = 0; i < TOTAL_THINGS; i++) {
            things.add(new CountingThing());
        }

        for (final CountingThing thing : things) {
            q.enqueue(thing);
        }

        for (int i = 0; i < TOTAL_THINGS && inOrder; i++) {
            CountingThing got = (CountingThing) q.dequeue();
            inOrder &= got.equals(things.get(i));
            if (!inOrder) {
                System.err.printf("\tExpected %d, got %d\n", things.get(i).id, got.id);
            }
        }

        // if everything was in order, we should have an empty queue
        return inOrder && q.size() == 0;
    }

    /**
     * Peeking at any element in an empty list should return null.
     * 
     * @return true for pass, false for fail.
     */
    @UnitTests.UnitTest
    public boolean testPeekEmpty() {
        boolean allNull = true;
        final int TOTAL_CHECKS = 10000000;

        for (int i = 0; i < TOTAL_CHECKS && allNull; i++) {
            allNull &= q.peek(i) == null;
            if (!allNull) {
                System.err.printf("Expected null at %d, got %s\n", i, q.peek(i));
            }
        }

        return allNull;
    }

    /**
     * The element at the end of the list should be the one just added; peeking at it should not
     * change the size of the queue.
     * 
     * @return true for pass, false for fail.
     */
    @UnitTests.UnitTest
    public boolean testPeekAtEnd() {
        final CountingThing t = new CountingThing();
        q.enqueue(t);
        return q.peekAtEnd().equals(t) && q.size() == 1;
    }

    /**
     * We should be able to peek at ordered items without removing anything.
     * 
     * @return true for pass, false for fail
     */
    @UnitTests.UnitTest
    public boolean testPeekSpecificItem() {
        final List<CountingThing> things = new ArrayList<>();
        final int TOTAL_THINGS = 100;
        boolean inOrder = true;

        for (int i = 0; i < TOTAL_THINGS; i++) {
            things.add(new CountingThing());
        }

        for (final CountingThing thing : things) {
            q.enqueue(thing);
        }

        for (int i = TOTAL_THINGS - 1; i >= 0 && inOrder; i--) {
            CountingThing got = (CountingThing) q.peek(i);
            inOrder &= got.equals(things.get(TOTAL_THINGS - 1 - i));
            if (!inOrder) {
                System.err.printf("\tExpected %d, got %d\n", things.get(TOTAL_THINGS - 1 - i).id,
                        got.id);
            }
        }

        return inOrder && q.size() == TOTAL_THINGS;
    }

    /**
     * A simple counting class to test implementations of {@link Queue}. You should not (and can't)
     * use this class outside of this test.
     */
    private static class CountingThing implements Queue.QueueItem {
        private static int counter = 0;
        private final int id;

        public CountingThing() {
            this.id = counter++;
        }

        public boolean equals(Object other) {
            boolean equals = false;

            if (other instanceof CountingThing) {
                final CountingThing otherThing = (CountingThing) other;
                equals = this.id == otherThing.id;
            }

            return equals;
        }
    }
}
