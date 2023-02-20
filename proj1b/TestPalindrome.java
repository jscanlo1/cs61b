import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {


        assertTrue(palindrome.isPalindrome("otto"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("isfgfsi"));

        assertFalse(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("asdfgh"));
    }

    @Test
    public void testIsPalindromeOffByOne() {

        OffByOne obo = new OffByOne();

        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("elf", obo));

        assertFalse(palindrome.isPalindrome("", obo));
        assertFalse(palindrome.isPalindrome("cat", obo));
        assertFalse(palindrome.isPalindrome("asdfgh", obo));
    }
}