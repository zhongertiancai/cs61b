package synthesizer;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();
    int fillCount();
    void enqueue(T x);

    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

    default boolean isFull() {
        if (capacity()  == fillCount()) {
            return true;
        } else {
            return false;
        }
    }
    T dequeue();
    T peek();
}