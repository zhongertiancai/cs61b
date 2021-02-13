public class LinkedListDeque<T>{
    private static int size;
    private class Deque<T>{
        public T item;
        public Deque<T> prev;
        public Deque<T> next;
    }
    private int flag = 1;
    private Deque<T> sentinel = new Deque<>();
    private Deque<T> shadowsentinel = sentinel;
    public LinkedListDeque(){

        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    public void addFirst(T item){
        Deque<T> ptr = new Deque<>();
        ptr.item = item;
        sentinel.next.prev = ptr;
        ptr.next = sentinel.next;
        ptr.prev = sentinel;
        sentinel.next = ptr;
        size++;
    }
    public void addLast(T item){
        Deque<T> ptr = new Deque<>();
        ptr.item = item;
        sentinel.prev.next = ptr;
        ptr.prev = sentinel.prev;
        ptr.next = sentinel;
        sentinel.prev = ptr;
        size++;
    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }
    public int size() {
        return size;
    }
    public void printDeque(){
        Deque<T> ptr = sentinel.next;
        while(ptr != sentinel){
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }
    public T removeFirst(){
        if(isEmpty() == true){
            return null;
        }
        Deque<T> ptr = sentinel.next;
        sentinel.next = ptr.next;
        ptr.next.prev = sentinel;
        size--;
        return ptr.item;
    }
    public T removeLast(){
        if(isEmpty() == true){
            return null;
        }
        Deque<T> ptr = sentinel.prev;
        sentinel.prev = ptr.prev;
        ptr.prev.next = sentinel;
        size--;
        return ptr.item;
    }
    public T get(int index){
        Deque<T> ptr = sentinel.next;
        for(int i = 0;i < index; i++){
            if(ptr == sentinel){
                return null;
            }
            ptr = ptr.next;
        }
        return ptr.item;
    }
    public T getRecursive(int index){
        if(flag==1){
            shadowsentinel=sentinel;
            flag=0;
        }
        if(index >= size){
            return null;
        }
        if (index == 0) {
            T result = shadowsentinel.next.item;
            shadowsentinel = sentinel;
            flag=1;
            return result;
        }else{
            shadowsentinel = shadowsentinel.next;
            return getRecursive(index - 1);
        }
    }
}
