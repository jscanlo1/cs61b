package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;

        rb = (T[]) new Object[capacity];
    }

    /**
     * Helper function to help increment to boundaries
     * Increments index by 1, or wraps around to zero
     * @return new index for first or last
     */
    private int incrementIndex(int index) {
        if (index == capacity() - 1) {
            return 0;
        }

        return index + 1;
    }


    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;
        last = incrementIndex(last);
        fillCount++;

        return;
    }

    /**
     * Dequeue the oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     * @return dequeued item
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        T dequeueItem = rb[first];
        first = incrementIndex(first);
        fillCount--;

        return dequeueItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     * @return the oldest item
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T>{
        private int pos;
        public ArrayRingBufferIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < capacity();
        }
        @Override
        public T next() {
            T nextItem = rb[pos];
            pos++;
            return nextItem;
        }
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;

        if(o.fillCount() != this.fillCount()) {
            return false;
        }

        Iterator<T> otherIter = o.iterator();
        Iterator<T> thisIter = this.iterator();

        while(otherIter.hasNext() && thisIter.hasNext()) {
            if(!otherIter.next().equals(thisIter.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Function to return the max capacity of the buffer
     * @return capacity of buffer
     */
    @Override
    public int capacity() {
        return rb.length;
    }


    @Override
    public int fillCount() {
        return fillCount;
    }
    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}

