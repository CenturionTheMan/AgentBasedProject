package main.ActiveSubclass;
import java.util.List;

import main.*;

public class Gimbus extends Active_Entity {

    public Gimbus(Vector2 position, int speed, int visionRange, Entity[] neighbours) {
        super(position, speed, visionRange,neighbours);
    }


    @Override
    protected Vector2 Logic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        return null;
    }
}
