package main.ActiveSubclass;
import java.util.List;
import java.util.Random;

import main.*;
import main.StaticSubclass.Egzamin;

/**
 * This class represents Debil entity.
 * It is a special entity which Student entity can become randomly every round (10%).
 * Debil scares every active entity, except other Debil entities or Student entities.
 * It behaves similarly to Student, except it cannot consume Piwo.
 * In order to become Student again, Debil needs to stand next to Egzamin.
 * This action turns Debil back to Student and removes Egzamin.
 * It can alternatively become Student every new round with 10% chance of this happening.
 */
public class Debil extends Active_Entity{

    //VALUES
    public static int amount =0;


    //CONSTRUCTORS

    /**
     * This constructor creates a new Debil entity.
     * @param position This parameter sets the position of Debil
     * @param speedANDvision This parameter sets the speed and vision values of Debil
     */
    public Debil(Vector2 position, Vector2 speedANDvision) {
        super(position, speedANDvision);
    }

    /**
     * This constructor behaves the same as the previous one, except it takes fewer parameters
     * @param speedANDvision This parameter sets the speed and vision values of Debil
     */
    public Debil(Vector2 speedANDvision)
    {
        super(speedANDvision);
    }
    
    
    //METHODS

    /**
     * This method defines the logic of Debil's movement.
     * It's main use is to attract it towards Egzamin entities.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Debil entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Debil entity
     */
    @Override
    public Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh) {
        Vector2 dir = GetMovementVectorToStaticEntity(staticNeigh, Egzamin.class);
        return dir;
    }

    /**
     * This method defines possible changes in the Debil entity itself.
     * One possible change for the Debil is to turn back to Student every round, with 10% chance.
     * Second possible change is when Debil stands next to Egzamin. This automatically turns it to Student.
     * @param grid This parameter represents map used for the simulation
     * @param activeNeigh This parameter lists ActiveSubclass neighbours visible to the Debil entity
     * @param staticNeigh This parameter lists StaticSubclass neighbours visible to the Debil entity
     */
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
