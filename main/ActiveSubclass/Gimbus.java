package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Licbaza;

public class Gimbus extends Active_Entity {

    private int fearlessTime = 10;
    private int maxScaredMoves = 2;
    private int scaredMoves = 0;

    public void SetScaredMoves(int val) { scaredMoves = val; }


    public Gimbus(Vector2 position, Vector2 speedANDvision, List<Entity> neighbours) {
        super(position, speedANDvision, neighbours);
    }
    public Gimbus(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }


    @Override
    protected Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        if(fearlessTime <= 0)
        {
            if(activeNeigh != null && activeNeigh.size() > 0)
            {
                for (Active_Entity a : activeNeigh) {
                    if(a instanceof Podbus)
                    {
                        if(((Podbus)a).GetGroupSize() >= 3)
                        {
                            SetScaredMoves(maxScaredMoves);
                        }
                    }
                    else if(a instanceof Student || a instanceof Debil) SetScaredMoves(maxScaredMoves);
                }
            }
        }
        if(scaredMoves > 0)
        {
            scaredMoves --;
            Vector2 pos = GridMap.GetFreePositionInNeighbourhood(GetPosition());
            return pos.SubtractVector(GetPosition());
        }
        
        Vector2 dir = GetMovementVectorToStaticEntity(staticNeigh,Licbaza.class);
        return dir;
    }

    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        if(scaredMoves > 0) return false;

        if(IsStaticEntityInNeighborhood(staticNeigh, Licbaza.class))
            GridMap.PlaceUnitOnMap(GetPosition(), new Licbus(Simulation.GetLicbus_speedANDvision()));

        if(activeNeigh != null && activeNeigh.size() > 0)
        {
            for (Active_Entity a : activeNeigh) {
                if(a instanceof Gimbus)
                {
                    Gimbus gim =(Gimbus)a;
                    int ran = new Random().nextInt(3);

                    if(ran == 0)
                    {
                        RemoveFromGrid(grid);
                        gim.RemoveFromGrid(grid);
                        return false;
                    }
                    else if(ran == 1)
                    {
                        GridMap.PlaceUnitOnMap(GetPosition(), new Patus(Simulation.GetPatus_speedANDvision()));
                        GridMap.PlaceUnitOnMap(gim.GetPosition(), new Patus(Simulation.GetPatus_speedANDvision()));
                        return false;
                    }
                }
            }
        }
        
        return false;
    }
}
