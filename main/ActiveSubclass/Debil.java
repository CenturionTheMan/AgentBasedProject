package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Egzamin;

public class Debil extends Active_Entity{

    //VALUES
    public static int amount =0;

    //CTOR
    public Debil(Vector2 position, Vector2 speedANDvision) {
        super(position, speedANDvision);
    }
    public Debil(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }
    
    
    //METHODS
    @Override
    public Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        Vector2 dir = GetMovementVectorToStaticEntity(staticNeigh, Egzamin.class);
        return dir;
    }


    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        if(new Random().nextInt(10) == 0)
        {
            GridMap.PlaceUnitOnMap(GetPosition(), new Student(Simulation.GetStudent_speedANDvision()));
            return false;
        }

        if(IsStaticEntityInNeighborhood(staticNeigh, Egzamin.class))
        {
            if(staticNeigh != null && staticNeigh.size() > 0)
            {
                for (Static_Entity k : staticNeigh) {
                    if(k instanceof Egzamin && k.GetPosition().SubtractVector(GetPosition()).GetLenght() < 2)
                    {
                        k.RemoveFromGrid(grid);
                        break;
                    }
                }
            }
            

            GridMap.PlaceUnitOnMap(GetPosition(), new Student(Simulation.GetStudent_speedANDvision()));
        }

        return false;
    }
}
