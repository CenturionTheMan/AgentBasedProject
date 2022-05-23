package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Egzamin;
import main.StaticSubclass.Licbaza;
import main.StaticSubclass.Uczelnia;

public class Licbus extends Active_Entity{

    private int maxScaredMoves = 2;
    private int scaredMoves = 0;

    public Licbus(Vector2 position, Vector2 speedANDvision, List<Entity> neighbours) {
        super(position, speedANDvision, neighbours);
    }
    public Licbus(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }


    @Override
    protected Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        Vector2 dir = null;

        if(activeNeigh != null && activeNeigh.size() >0)
        {
            for (Active_Entity a : activeNeigh) {
                if(a instanceof Student || a instanceof Debil) scaredMoves = maxScaredMoves;
            }
        }

        if(scaredMoves > 0)
        {
            dir = GridMap.GetFreePositionInNeighbourhood(GetPosition());
            dir = dir.SubtractVector(GetPosition());
            scaredMoves--;
            return dir;
        }


        dir = GetMovementVectorToStaticEntity(staticNeigh, Uczelnia.class);
        return dir;
    }
    
    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        if(scaredMoves > 0) return false;

        if(new Random().nextInt(5)==0)
        {
            Vector2 temp = GridMap.GetFreePositionInNeighbourhood(GetPosition());
            GridMap.PlaceUnitOnMap(temp, new Egzamin(null));
        }

        if(IsStaticEntityInNeighborhood(staticNeigh, Uczelnia.class))
        {
            GridMap.PlaceUnitOnMap(GetPosition(), new Student(Simulation.GetStudent_speedANDvision()));
            return false;
        }

        return false;
    }
}
