public class OffByN implements CharacterComparator{
    private int gap;
    public OffByN(int N){
        gap = N;
    }
    public boolean equalChars(char x, char y){
        if(x - y == gap || y - x == gap){
            return true;
        } else{
            return false;
        }
    }
}
