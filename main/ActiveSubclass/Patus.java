package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Egzamin;
import main.StaticSubclass.Piwo;

/**
 * This class represents Patus entity.
 * It's a special entity which two Gimbus entities can turn into after meeting each other (33% chance).
 * It scares groups of Podbus entities up to 3 members. Higher amounts will scare Patus instead.
 * It is always going to be scared by either Student or Debil.
 * Patus entities have 20% chance of leaving a Piwo entity behind them every round.
 * In order to become Licbus, Podbus entity needs to consume Egzamin.
 */
public class Patus extends Active_Entity{

    //VALUES
    private int fearlessTime = 10; //how many moves should Patus be immune to fear
    private int maxScaredMoves = 2; //how many moves should patus stay feard
    private int scaredMoves = 0; //how many moves to stopped being feard is left

    public static int amount =0;

    //SETTERS && GETTERS

    /**
     * This setter sets the scared moves if right conditions are met
     * @param val This parameter indicates the amount of scared moves to be set
     */
    public void SetScaredMoves(int val) { scaredMoves = val; }

    //CTOR

    /**
     * This constructor creates a new Patus entity
     * @param position This parameter sets the position of Patus
     * @param speedANDvision This parameter sets the speed and vision values of Patus
     */
    public Patus(Vector2 position, Vector2 speedANDvision) {
        super(position, speedANDvision);
    }

    /**
     * This constructor behaves the same as the previous one, except it takes fewer parameters
     * @param speedANDvision This parameter sets the speed and vision values of Patus
     */
    public Patus(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }


    //METHODS

    /**
     * This method defines the logic of Patus' movement.
     * It's main use is controlling the fear of Patus regarding the presence of certain entities in its neighbourhood.
     * It also makes it attracted to nearby Egzamin static entities.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Patus entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Patus entity
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


        Vector2 dir = GetMovementVectorToStaticEntity(staticNeigh, Egzamin.class);
        return dir;
    }

    /**
     * This method defines possible changes in the Patus entity itself.
     * Patus entity has 20% chance of dropping a Piwo entity behind it every round.
     * It changes to Licbus entity when stationed next to Egzamin.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Patus entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Patus entity
     */
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
