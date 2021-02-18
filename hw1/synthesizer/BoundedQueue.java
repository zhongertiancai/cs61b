package synthesizer;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();
    int fillCount();
    void enqueue(T x);
    boolean isEmpty();
    boolean isFull();
    T dequeue();
    T peek();
}