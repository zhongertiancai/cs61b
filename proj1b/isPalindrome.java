import org.junit.Test;
import static org.junit.Assert.*;

public class isPalindrome {
    @Test
    public void isPalindrome(){
        Palindrome p = new Palindrome();
        assertFalse(p.isPalindrome("add"));
        assertFalse(p.isPalindrome("ball"));
        assertTrue(p.isPalindrome("a"));
        assertTrue(p.isPalindrome(""));
        assertTrue(p.isPalindrome("abba"));
        assertTrue(p.isPalindrome("abcba"));
        OffByOne obo = new OffByOne();
        assertTrue(p.isPalindrome("a",obo));
        assertTrue(p.isPalindrome("ab",obo));
    }

}
