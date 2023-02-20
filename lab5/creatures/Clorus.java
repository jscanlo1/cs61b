package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


/**
 * Implementation of a motile, predatory creature called a clorus
 *
 * @author Johnny Scanlon
 */
public class Clorus extends Creature {

    /**
     * RGB colours correspond to red, blue, green values respectively
     */
    private int r, g, b;


    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;

        energy = e;
    }

    public Clorus() {
        this(3);
    }


    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }


    /**
     * gain the attacked creatures energy.
     *
     * @param c
     */
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    /**
     * Clorus should lose 0.03 units of energy when moving. If you want
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy - 0.03;

    }


    /**
     * Cloruses gain 0.01 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += 0.01;

    }

    /**
     * Cloruses and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {
        Clorus newClorus = new Clorus(energy / 2);
        this.energy = this.energy / 2;
        return newClorus;
    }


    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1

        //Check for empty squares
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        boolean anyEmpty = false;
        boolean anyPlip = false;

        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Direction direction : neighbors.keySet()) {
            Occupant neighbor = neighbors.get(direction);

            if (neighbor.name().equals("empty")) {
                emptyNeighbors.add(direction);
                anyEmpty = true;
            }

            if (neighbor.name().equals("plip")) {
                plipNeighbors.add(direction);
                anyPlip = true;
            }

        }

        if (!anyEmpty) {
            return new Action(Action.ActionType.STAY);
        }

        //Rule 2
        //If there are empty squares and a Plip present, attack Plip.

        if (anyPlip) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors) );
        }

        //Rule 3
        //If energy >= 1, Clorus will replicate into an empty square

        if (energy > 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        //Rule 4
        //Move into any empty square

        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));


    }



    private Direction randomEntry(Deque<Direction> neighbors) {
        int arrayMaxIndex = neighbors.size() - 1;

        int randomIndex = (int) (Math.random() * (arrayMaxIndex + 1));

        for (int i = 0; i < randomIndex; i++) {
            neighbors.pop();
        }

        return neighbors.pop();
    }



}
