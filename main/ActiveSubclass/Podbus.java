package main.ActiveSubclass;
import java.util.ArrayList;
import java.util.List;

import main.*;
import main.StaticSubclass.Gimbaza;

/**
 * This class represents Podbus entity.
 * It's the entity lowest in hierarchy, it cannot be created by other entities doing something.
 * It is capable of grouping up with other Podbus entities, making them travel together as a single entity.
 * Groups of Podbus entities up to 3 members will be scared by nearby Gimbus and Patus entities.
 * Groups of Podbus entities over 3 members will scare nearby Gimbus and Patus entities.
 * It is always going to be scared by either Student or Debil entities.
 * In order to become Gimbus, Patus entity needs to stand next to Gimbaza static entity.
 */
public class Podbus extends Active_Entity{

    //VALUES
    private int maxScaredMoves = 2; //indicates how many moves should entity last feared
    private int scaredMoves = 0; //how many moves to stopped being feard is left
    private List<Podbus> group; //list of Posbus'es in surranding

    public static int amount =0;

    //GETTERS && SETTERS

    /**
     * This setter sets the scared moves if right conditions are met
     * @param val This parameter indicates the amount of scared moves to be set
     */
    public void SetScaredMoves(int val) { scaredMoves = val; }

    //CTOR

    /**
     * This constructor creates a new Podbus entity
     * @param position This parameter sets the position of Podbus
     * @param speedANDvision This parameter sets the speed and vision values of Podbus
     */
    public Podbus(Vector2 position, Vector2 speedANDvision) {
        super(position, speedANDvision);
    }

    /**
     * This constructor behaves the same as the previous one, except it takes fewer parameters
     * @param speedANDvision This parameter sets the speed and vision values of Podbus
     */
    public Podbus(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }

    //METHODS

    /**
     * This method returns size of List<Podbus> group in Podbus class
     */
    public int GetGroupSize() { 
        if(group == null) return 0;   
        return group.size(); 
    }

    /**
     * This method defines the logic of Podbus' movement.
     * It's main use is controlling the fear of Podbus regarding the presence of certain entities in its neighbourhood.
     * It also defines how Podbus entities can group up and how they are attracted to nearby Gimbaza static entities.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Podbus entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Podbus entity
     */
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

    /**
     * This method defines possible changes in the Podbus entity itself.
     * It changes to Gimbus entity when stationed next to Gimbaza static entity.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Podbus entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Podbus entity
     */
    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        if(scaredMoves > 0) return false;

        if(IsStaticEntityInNeighborhood(staticNeigh, Gimbaza.class))
            GridMap.PlaceUnitOnMap(GetPosition(), new Gimbus(Simulation.GetGimbus_speedANDvision()));
        return false;
    }
}
