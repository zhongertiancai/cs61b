package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;
    public Iterator<T> iterator() {
        return new interator<T>();
    }

    private class interator<T> implements Iterator<T> {
        private int ptr;
        interator() {
            ptr = first;
        }
        @Override
        public boolean hasNext() {
            if (ptr != last) {
                return true;
            } else {
                return false;
            }
        }
        @Override
        public T next() {
            T returnitem = (T) rb[ptr];
            ptr++;
            return returnitem;
        }
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == capacity) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public boolean isEmpty() {
        if (fillCount == 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean isFull() {
        if (capacity()  == fillCount()) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        fillCount--;
        T result = rb[first];
        first = (first + 1) % capacity;
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            return null;
        }
        return rb[first];
    }
}
