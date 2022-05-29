package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Licbaza;

/**
 * This class represents Gimbus entity.
 * It's an entity which Podbus can change into after standing next to Gimbaza static entity.
 * It scares groups of Podbus entities up to 3 members. Higher amounts will scare Gimbus instead.
 * It is always going to be scared by either Student or Debil.
 * If two Gimbus entities stand next to eachother, they can either both become Patus entities; eliminate eachother; or nothing could happen.
 * Each of these events has 33% chance of occuring.
 * In order to become Licbus, Gimbus must stand next to Licbaza static entity.
 */
public class Gimbus extends Active_Entity {

    //VALUES
    private int fearlessTime = 10; //how many moves should Patus be immune to fear
    private int maxScaredMoves = 2; //indicates how many moves should entity last feared
    private int scaredMoves = 0; //how many moves to stopped being feared is left

    public static int amount =0;

    //SETTERS && GETTERS

    /**
     * This setter sets the scared moves if right conditions are met
     * @param val This parameter indicates the amount of scared moves to be set
     */
    public void SetScaredMoves(int val) { scaredMoves = val; }

    //CTOR

    /**
     * This constructor creates a new Gimbus entity
     * @param position This parameter sets the position of Gimbus
     * @param speedANDvision This parameter sets the speed and vision values of Gimbus
     */
    public Gimbus(Vector2 position, Vector2 speedANDvision) {
        super(position, speedANDvision);
    }

    /**
     * This constructor behaves the same as the previous one, except it takes fewer parameters
     * @param speedANDvision This parameter sets the speed and vision values of Gimbus
     */
    public Gimbus(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }

    
    //METHODS

    /**
     * This method defines the logic of Gimbus' movement.
     * It's main use is controlling the fear of Gimbus regarding the presence of certain entities in its neighbourhood.
     * It also makes it attracted to nearby Licbaza static entities.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Gimbus entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Gimbus entity
     */
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

    /**
     * This method defines possible changes in the Gimbus entity itself.
     * It changes to Licbus entity when stationed next to Licbaza.
     * When it meets another Gimbus entity, they both have 33% chance of either doing nothing, being removed from the grid or turning to Patus entities.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Gimbus entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Gimbus entity
     */
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
