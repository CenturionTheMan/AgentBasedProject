package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Egzamin;
import main.StaticSubclass.Uczelnia;

/**
 * This class represents Licbus entity.
 * It's an entity which Gimbus can change into after standing next to Licbaza or Patus, after consuming Egzamin.
 * It is always going to be scared by either Student or Debil.
 * Licbus entities have 20% chance of leaving an Egzamin entity behind every round.
 * In order to become Student, it needs to place itself next to Uczelnia static entity.
 */
public class Licbus extends Active_Entity{

    //VALUES
    private int maxScaredMoves = 2;//indicates how many moves should Licbus last feared
    private int scaredMoves = 0; //how many moves to stopped being feard is left

    public static int amount =0;

    //CTOR

    /**
     * This constructor creates a new Licbus entity
     * @param position This parameter sets the position of Licbus
     * @param speedANDvision This parameter sets the speed and vision values of Licbus
     */
    public Licbus(Vector2 position, Vector2 speedANDvision) {
        super(position, speedANDvision);
    }

    /**
     * This constructor behaves the same as the previous one, except it takes fewer parameters
     * @param speedANDvision This parameter sets the speed and vision values of Licbus
     */
    public Licbus(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }


    //METHODS

    /**
     * This method defines the logic of Licbus' movement.
     * Its main usage is to either set scared moves for the entity when right conditions are met or attract it to nearby Uczelnia static entities.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Licbus entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Licbus entity
     */
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

    /**
     * This method defines possible changes in the Licbus entity itself.
     * Licbus entity has 20% chance of dropping an Egzamin entity behind it every round.
     * It changes to Student entity when stationed next to Uczelnia.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Licbus entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Licbus entity
     */
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
