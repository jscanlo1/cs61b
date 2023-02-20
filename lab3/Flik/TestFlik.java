import static org.junit.Assert.*;
import org.junit.Test;

public class TestFlik {

    /**
     * Test Flik function for comparing to integers
     * Function should return true if integers are the same */
    @Test
    public void TestIsSameNumber(){

        //Test same values
        int a0 = 0, b0 = 0;
        boolean expected = true;
        assertEquals(expected, Flik.isSameNumber(a0,b0));


        //Test wrong values
        int a1 = -42, b1 = 0;
        boolean expected1 = false;
        assertEquals(expected1, Flik.isSameNumber(a1,b1));

        //Test more wrong values
        int a2 = 50, b2 = 7000;
        boolean expected2 = false;
        assertEquals(expected2, Flik.isSameNumber(a2,b2));

        //Test specific values
        int a3 = 128, b3 = 128;
        boolean expected3 = true;
        assertEquals(expected3, Flik.isSameNumber(a3,b3));

        //Test once more
        assertTrue("This is true", Flik.isSameNumber(128,128));



    }

}
