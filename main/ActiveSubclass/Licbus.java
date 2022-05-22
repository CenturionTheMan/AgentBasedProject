package main.ActiveSubclass;
import java.util.List;

import main.*;

public class Licbus extends Active_Entity{

    public Licbus(Vector2 position, Vector2 speedANDvision, List<Entity> neighbours) {
        super(position, speedANDvision, neighbours);
    }
    public Licbus(Vector2 speedANDvision)
    {
        super(speedANDvision);
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
