package UnionFind;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class UnionFindTest {

    /**
     * Test that the constructor sets up the correct array
     */
    @Test
    public void TestUnionFindConstructor() {
        UnionFind newSet = new UnionFind(6);
        int[] expected = new int[]{-1, -1, -1, -1, -1, -1};
        int[] actual = newSet.vertices;

        assertArrayEquals(expected,actual);
    }

    /**
     * Test Size of
     */
    @Test
    public void TestSizeOf() {

    }

    /**
     * Test Find Parent
     */
    @Test
    public void TestParent(){

    }


    /**
     * Test Connected
     */
    @Test
    public void TestConnected(){

    }

    /**
     * Test union
     */
    @Test
    public void TestUnion(){
        UnionFind newSet = new UnionFind(6);
        newSet.union(1,2);
        int[] expected = new int[]{-1, -2, 1, -1, -1, -1};
        int[] actual = newSet.vertices;

        assertArrayEquals(expected,actual);

        newSet.union(2,5);
        int[] expected2 = new int[]{-1, -3, 1, -1, -1, 1};
        int[] actual2 = newSet.vertices;

        assertArrayEquals(expected2,actual2);

        newSet.union(0,4);
        int[] expected3 = new int[]{-2, -3, 1, -1, 0, 1};
        int[] actual3 = newSet.vertices;

        assertArrayEquals(expected3,actual3);

        newSet.union(0,3);
        int[] expected4 = new int[]{-3, -3, 1, 0, 0, 1};
        int[] actual4 = newSet.vertices;

        assertArrayEquals(expected4, actual4);

        newSet.union(3,5);
        int[] expected5 = new int[]{-6, 0, 1, 0, 0, 1};
        int[] actual5 = newSet.vertices;

        assertArrayEquals(expected5,actual5);



    }

    /**
     * Test find
     */
    @Test
    public void TestFind(){

    }

}
