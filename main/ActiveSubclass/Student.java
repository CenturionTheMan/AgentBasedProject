package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Piwo;

/**
 * This class represents Student entity.
 * It's an entity which Licbus can change into after standing next to Uczelnia or Debil, after either consuming Egzamin or at the start of a new round (10% chance).
 * Student entity has 10% chance of turning into Debil each round.
 * Student scares every active entity, except other Student entities or Debil entities.
 * Every Student will wander randomly until they find Piwo entity, which attracts them. Consuming it finishes the simulation.
 */
public class Student extends Active_Entity{

    //VALUES
    public static int amount =0;

    //CTOR

    /**
     * This constructor creates a new Student entity
     * @param position This parameter sets the position of Student
     * @param speedANDvision This parameter sets the speed and vision values of Student
     */
    public Student(Vector2 position, Vector2 speedANDvision) {
        super(position, speedANDvision);
    }

    /**
     * This constructor behaves the same as the previous one, except it takes fewer parameters
     * @param speedANDvision This parameter sets the speed and vision values of Student
     */
    public Student(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }


    //METHODS

    /**
     * This method defines the logic of Student's movement.
     * It's main use is defining the attraction of Student towards Piwo entities.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Student entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Student entity
     */
    @Override
    protected Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        Vector2 dir = GetMovementVectorToStaticEntity(staticNeigh, Piwo.class);
        
        return dir;
    }

    /**
     * This method defines possible changes in the Student entity itself.
     * It can change to Debil entity every round with 10% chance.
     * This method also yields whether Student is currently standing next to Piwo entity.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Student entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Student entity
     */
    @Override
    protected boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        
        if(new Random().nextInt(10) == 0)
        {
            GridMap.PlaceUnitOnMap(GetPosition(), new Debil(Simulation.GetDebil_speedANDvision()));
            return false;
        }

        if(IsStaticEntityInNeighborhood(staticNeigh, Piwo.class))
        {
            return true;
        }
        else
            return false;
    }
}
