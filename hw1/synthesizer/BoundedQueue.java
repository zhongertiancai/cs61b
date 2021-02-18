package synthesizer;

import  java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
    int capacity();
    int fillCount();
    void enqueue(T x);
    T dequeue();
    T peek();


    default  boolean isEmpty(){
        if (capacity() == 0){
            return true;
        }else{
            return false;
        }
    }
    default boolean isFull(){
        if (capacity() == fillCount()){
            return true;
        }else {
            return false;
        }
    }
}
