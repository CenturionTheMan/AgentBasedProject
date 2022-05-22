package main.ActiveSubclass;
import java.util.List;

import main.*;

public class Podbus extends Active_Entity{

    private int scaredMoves = 1;

    public Podbus(Vector2 position, Vector2 speedANDvision, List<Entity> neighbours) {
        super(position, speedANDvision, neighbours);
    }


    @Override
    protected Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {

        if(scaredMoves > 0)
        {
            scaredMoves--;
            Vector2 t = GridMap.FindFreePositionInNeighbourhood(GetPosition());
            t.SubtractVector(GetPosition());
            t = new Vector2(2, 1);
            return t;
        }



        return null;
    }
    
    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        if(scaredMoves > 0) return false;



        return false;
    }
}
