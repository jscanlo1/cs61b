package creatures;
import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2.5);
        assertEquals(2.5, c.energy(), 0.001);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(2.47, c.energy(), 0.001);
        c.move();
        assertEquals(2.44, c.energy(), 0.001);
        c.stay();
        assertEquals(2.45, c.energy(), 0.001);
        c.stay();
        assertEquals(2.46, c.energy(), 0.001);
    }

    @Test
    public void testReplicate() {
        Clorus c1 = new Clorus(2);
        Clorus c2 = c1.replicate();

        assertEquals(1, c1.energy(), 0.01);
        assertEquals(1, c2.energy(), 0.01);

        c1 = new Clorus(1);
        c2 = c1.replicate();

        assertEquals(0.5, c1.energy(), 0.01);
        assertEquals(0.5, c2.energy(), 0.01);

        c1 = new Clorus(0.5);
        c2 = c1.replicate();

        assertEquals(0.25, c1.energy(), 0.01);
        assertEquals(0.25, c2.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        //Tests for stay
        //First test check if avery square is impassable

        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        //Next test for stay examines presence of plips
        //Even tho plips are present
        // it should stay regardless as no square is empty
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded2 = new HashMap<Direction, Occupant>();
        surrounded2.put(Direction.TOP, new Plip());
        surrounded2.put(Direction.BOTTOM, new Impassible());
        surrounded2.put(Direction.LEFT, new Plip());
        surrounded2.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(surrounded2);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        //If Plip is present: attack!

        c = new Clorus(1.2);
        HashMap<Direction, Occupant> plipPresent = new HashMap<Direction, Occupant>();
        plipPresent.put(Direction.TOP, new Plip());
        plipPresent.put(Direction.BOTTOM, new Empty());
        plipPresent.put(Direction.LEFT, new Empty());
        plipPresent.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(plipPresent);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);






        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Energy < 1; stay.
        c = new Clorus(.99);

        actual = c.chooseAction(allEmpty);
        unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);

    }
}
