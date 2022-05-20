package main.ActiveSubclass;
import java.util.List;

import main.*;

public class Gimbus extends Active_Entity {

    public Gimbus(Vector2 position, Vector2 speedANDvision, Entity[] neighbours) {
        super(position, speedANDvision, neighbours);
    }


    @Override
    protected Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        return null;
    }

    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        return false;
    }
}
