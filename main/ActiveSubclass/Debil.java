package main.ActiveSubclass;
import java.util.List;

import main.*;

public class Debil extends Active_Entity{

    public Debil(Vector2 position, int speed, int visionRange, Entity[] neighbours) {
        super(position, speed, visionRange, neighbours);
    }
    
    @Override
    public void Logic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
    }
}
