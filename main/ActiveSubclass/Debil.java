package main.ActiveSubclass;
import java.util.List;

import main.*;

public class Debil extends Active_Entity{

    public Debil(Vector2 position, Vector2 speedANDvision, Entity[] neighbours) {
        super(position, speedANDvision, neighbours);
    }
    
    
    @Override
    public Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        return null;
    }


    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        return false;
    }
}
