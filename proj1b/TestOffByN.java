import static org.junit.Assert.*;
import org.junit.Test;

public class TestOffByN {
    @Test
    public void testEqualChars(){
        OffByN offBy5 = new OffByN(5);

        //Test true values
        assertTrue(offBy5.equalChars('a','f'));
        assertTrue(offBy5.equalChars('G', 'L'));

        //Test false values
        assertFalse(offBy5.equalChars('d','g'));
        assertFalse(offBy5.equalChars('A','f'));
    }
}
