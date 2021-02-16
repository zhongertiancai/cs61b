import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void MyAutoGrader(){

        for(int j = 0; j < 10 ;j++) {
            String s = new String();
            StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
            ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
            for (int i = 0; i < 50; i += 1) {
                double numberBetweenZeroAndOne = StdRandom.uniform();
                if (numberBetweenZeroAndOne < 0.25) {
                    s = s.concat("addLast("+i+")\n");
                    sad1.addLast(i);
                    sad2.addLast(i);
                } else if (numberBetweenZeroAndOne < 0.5){
                    s = s.concat("addLast("+i+")\n");
                    sad1.addFirst(i);
                    sad2.addFirst(i);
                }else if (numberBetweenZeroAndOne < 0.75){
                    s = s.concat("removeFirst()\n");
                    assertEquals(s,sad1.removeFirst(),sad2.removeFirst());
                }else {
                    s = s.concat("removeLast()\n");
                    assertEquals(s,sad1.removeLast(),sad2.removeLast());
                }
            }
        }

    }
}
