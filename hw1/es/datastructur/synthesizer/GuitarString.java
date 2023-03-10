package es.datastructur.synthesizer;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;


    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int) Math.round(((double) SR) / frequency ));
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        //Deque All
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }

        //Fill with noise
        while (!buffer.isFull()) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        if (buffer.isEmpty()) {
            return;
        }

        double sample1 = buffer.dequeue();
        double sample2 = buffer.peek();
        double newSample = DECAY * 0.5 * (sample1 + sample2);

        buffer.enqueue(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        if (buffer.isEmpty()){
            return 0;
        }
        return buffer.peek();
    }
}
