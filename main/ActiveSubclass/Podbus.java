package main.ActiveSubclass;
import java.util.ArrayList;
import java.util.List;

import main.*;
import main.StaticSubclass.Gimbaza;

public class Podbus extends Active_Entity{

    //VALUES
    private int maxScaredMoves = 2; //indicates how many moves should entity last feared
    private int scaredMoves = 0; //how moves to stopped being feard is left
    private List<Podbus> group; //list of Posbus'es in surranding

    public static int amount =0;

    //GETTERS && SETTERS
    public void SetScaredMoves(int val) { scaredMoves = val; }

    //CTOR
    public Podbus(Vector2 position, Vector2 speedANDvision) {
        super(position, speedANDvision);
    }
    public Podbus(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }

    //METHODS

    //*Returns size of List<Podbus> group in Podbus class
    public int GetGroupSize() { 
        if(group == null) return 0;   
        return group.size(); 
    }


    @Override
    protected Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {

        Vector2 dir = new Vector2(0,0);

        boolean isThread = false;

        if(activeNeigh != null && activeNeigh.size() >0)
        {
            for (Active_Entity a : activeNeigh) {
                if((a instanceof Gimbus || a instanceof Student || a instanceof Debil || a instanceof Patus) && a.GetPosition().SubtractVector(GetPosition()).GetLenght()<2) isThread = true;
            }
        }

        if(isThread && GetGroupSize() < 3)
        {
            scaredMoves = maxScaredMoves;

            if(group != null && group.size() > 0)
            {
                group.forEach(k -> k.SetScaredMoves(maxScaredMoves) );
            }
        }

        if(scaredMoves > 0)
        {            
            AddMoves(Simulation.GetPodbus_speedANDvision().x);

            dir = GridMap.GetFreePositionInNeighbourhood(GetPosition());
            dir = dir.SubtractVector(GetPosition());
            scaredMoves--;
            return dir;
        }


        dir = GetMovementVectorToStaticEntity(staticNeigh,Gimbaza.class);
        if(dir.Compare(new Vector2(0, 0))) return dir;

        group = new ArrayList<Podbus>();
        if(activeNeigh.size() > 0)
        {
            for (Active_Entity a : activeNeigh) {
                if(a instanceof Podbus && GetPosition().SubtractVector(a.GetPosition()).GetLenght()<2)
                {
                    Podbus p = (Podbus)a;
                    group.add(p);
                    if(GridMap.IsOnGrid(dir.AddVector(p.GetPosition())))
                    {
                        p.DoMove(grid,dir);
                    }
                }
            }
        }

        return dir;
    }
    
    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        if(scaredMoves > 0) return false;

        if(IsStaticEntityInNeighborhood(staticNeigh, Gimbaza.class))
            GridMap.PlaceUnitOnMap(GetPosition(), new Gimbus(Simulation.GetGimbus_speedANDvision()));
        return false;
    }
}
