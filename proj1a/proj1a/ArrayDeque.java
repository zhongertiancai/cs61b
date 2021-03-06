public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst = 0;
    private int nextLast = 0;
    public ArrayDeque(){
        items =(T[])new Object[8];
        size = 0;
    }
    public int size(){
        return size;
    }
    private void resizing(int capacity){
        T[] a =(T[]) new Object[capacity];
        System.arraycopy(items ,(nextFirst + 1)%items.length  ,a, 1,items.length - nextFirst -1);
        System.arraycopy(items , 0, a ,items.length - nextFirst +1 , (nextLast + 1)%items.length );
        items = a;
        nextFirst = 0;
        nextLast = size + 1;
    }
    public void addFirst(T item){

        if((nextFirst - 1 ==nextLast)||(nextFirst - 1 + items.length == nextLast)){
            resizing(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst --;
        if (nextFirst < 0){
            nextFirst += items.length;
        }
        size++;
    }
    public void addLast(T item){
        if((nextLast + 1)% items.length == nextFirst){
            resizing(items.length * 2);
        }
        items[nextLast] = item;
        nextLast  = (nextLast + 1) % items.length;
        size++;
    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }
    public void printDeque() {
        int i = (nextFirst+1)%items.length;
        while (i != nextLast) {
            System.out.print(items[i] + " ");
            i=(i+1)%items.length;
        }
    }
    public T removeFirst(){
        nextFirst = (nextFirst + 1) % items.length;
        T result = items[nextFirst];
        size--;
        if (items.length / size>=4){
            resizing(items.length / 2);
        }
        return result;
    }
    public T removeLast(){
        nextLast--;
        if(nextLast < 0){
            nextLast += items.length;
        }
        T result = items[nextLast];
        size--;
        if (items.length / size>=4){
            resizing(items.length / 2);
        }
        return result;
    }
    public T get(int index){
        if (index >= size){
            return null;
        }else{
            return items[(nextFirst + index + 1)% items.length];
        }
    }
}
