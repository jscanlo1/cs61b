package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void capacityTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertEquals(10, arb.capacity());



    }

    @Test
    public void fillCountTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertEquals(0, arb.fillCount());

        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(3, arb.fillCount());

        arb.dequeue();
        assertEquals(2, arb.fillCount());

    }

    /**
     * This test will examine enqueuing and dequeuing
     * It will also test peak
     */
    @Test
    public void queueTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(1, (int) arb.peek());

        int frontItem = arb.dequeue();
        assertEquals(1, frontItem);
        assertEquals(2, (int) arb.peek());

        arb.enqueue(4);
        arb.enqueue(5);

        arb.dequeue();
        frontItem = arb.dequeue();
        assertEquals(3, frontItem);




    }
}
