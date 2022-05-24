package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Piwo;

public class Student extends Active_Entity{

    //CTOR
    public Student(Vector2 position, Vector2 speedANDvision, List<Entity> neighbours) {
        super(position, speedANDvision, neighbours);
    }
    public Student(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }


    //METHODS
    @Override
    protected Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        Vector2 dir = GetMovementVectorToStaticEntity(staticNeigh, Piwo.class);
        
        return dir;
    }
    
    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        if(new Random().nextInt(10) == 0)
        {
            GridMap.PlaceUnitOnMap(GetPosition(), new Debil(Simulation.GetDebil_speedANDvision()));
            return false;
        }

        if(IsStaticEntityInNeighborhood(staticNeigh, Piwo.class))
        {
            return true;
        }
        else
            return false;
    }
}
