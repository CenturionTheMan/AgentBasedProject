package main.ActiveSubclass;
import java.util.List;

import main.*;

public class Licbus extends Active_Entity{

    public Licbus(Vector2 position, int speed, int visionRange, Entity[] neighbours) {
        super(position, speed, visionRange, neighbours);
    }


    @Override
    protected Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        return null;
    }
    
    @Override
    protected void StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
    }
}
