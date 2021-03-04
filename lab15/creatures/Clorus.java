package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;
import java.util.Random;
import huglife.Creature;

public class Clorus extends Creature{
    private double energy;
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }
    public Clorus() {
        super("clorus");
        energy = 1;
    }
    public double energy() {
        return energy;
    }
    public Color color(){
        return new Color(34, 0, 231);
    }
    public void attack(Creature c) {
        energy += c.energy();
    }
    public void move() {
        energy = energy - 0.03;
    }
    public void stay() {
        energy = energy - 0.01;
    }
    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        List<Direction> food = getNeighborsOfType(neighbors, "plip");
        if (food.size() > 0) {
            Direction moveDir = HugLifeUtils.randomEntry(food);
            return new Action(Action.ActionType.ATTACK, moveDir);
        }
        if (energy > 1) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        }
        Direction moveDir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, moveDir);
    }
}
