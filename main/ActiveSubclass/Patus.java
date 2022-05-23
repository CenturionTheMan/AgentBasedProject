package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Egzamin;
import main.StaticSubclass.Piwo;

public class Patus extends Active_Entity{

    private int fearlessTime = 10;
    private int maxScaredMoves = 2;
    private int scaredMoves = 0;

    public void SetScaredMoves(int val) { scaredMoves = val; }


    public Patus(Vector2 position, Vector2 speedANDvision, List<Entity> neighbours) {
        super(position, speedANDvision, neighbours);
    }
    public Patus(Vector2 speedANDvision)
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


        Vector2 dir = GetMovementVectorToStaticEntity(staticNeigh, Egzamin.class);
        return dir;
    }
    
    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        if(new Random().nextInt(5) == 0)
        {
            Vector2 temp = GridMap.GetFreePositionInNeighbourhood(GetPosition());
            GridMap.PlaceUnitOnMap(temp, new Piwo(null));
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
            

            GridMap.PlaceUnitOnMap(GetPosition(), new Licbus(Simulation.GetLicbus_speedANDvision()));
        }

        return false;
    }
}
