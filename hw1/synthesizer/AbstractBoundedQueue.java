package synthesizer;

public abstract class AbstractBoundedQueue <T> implements BoundedQueue<T>{
    protected int fillCount;
    protected int capacity;
    public int capacity(){
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }
    public boolean isEmpty(){
        if (capacity() == 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean isFull(){
        if (capacity() - 1 == fillCount()){
            return true;
        }else {
            return false;
        }
    }
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);
}