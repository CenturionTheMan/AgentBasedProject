package main.ActiveSubclass;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import main.*;
import main.StaticSubclass.Podbaza;

public class Podbus extends Active_Entity{

    private int scaredMoves = 0;

    public Podbus(Vector2 position, Vector2 speedANDvision, List<Entity> neighbours) {
        super(position, speedANDvision, neighbours);
    }
    public Podbus(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }


    @Override
    protected Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {

        Vector2 dir = new Vector2(0,0);

        if(scaredMoves > 0)
        {
            dir = GridMap.FindFreePositionInNeighbourhood(GetPosition());
            dir = dir.SubtractVector(GetPosition());
            scaredMoves--;
            return dir;
        }
        else
        {
            //KIEDY COS TO SIE BOJA!!!!! --->>> DOKONCZ
        }

        Vector2 baza = null;
        if(staticNeigh.size() > 0)
        {
            List<Vector2> schoolsPos = new ArrayList<Vector2>();
            for (Static_Entity static_Entity : staticNeigh) {
                if(static_Entity instanceof Podbaza)
                {
                    schoolsPos.add(static_Entity.GetPosition());
                }
            }

            baza = GridMap.GetTheClosestPointToTargetFromPoints(schoolsPos, GetPosition());
        }

        if(baza == null)
        {
            dir = GridMap.FindFreePositionInNeighbourhood(GetPosition()).SubtractVector(GetPosition());
        }
        else
        {
            Node target = GridMap.GetClosestToPointNodeInUnitSquare(GetPosition(), baza);
            if(target != null) dir = target.GetPosition().SubtractVector(GetPosition());
        }

        if(activeNeigh.size() > 0)
        {
            for (Active_Entity a : activeNeigh) {
                if(a instanceof Podbus && GetPosition().SubtractVector(a.GetPosition()).GetLenght()<2)
                {
                    Podbus p = (Podbus)a;
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

        if(staticNeigh.size() > 0)
        {
            for (Static_Entity s : staticNeigh) {
                if(s instanceof Podbaza && GetPosition().SubtractVector(s.GetPosition()).GetLenght()<2)
                {
                    GridMap.PlaceUnitOnMap(GetPosition(), new Gimbus(Simulation.GetGimbus_speedANDvision()));
                }
            }
        }

        return false;
    }
}
