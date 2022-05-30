package main.StaticSubclass;
import main.*;

/**
 * This class represents an Egzamin entity.
 * This entity has 20% chance of being dropped by a Licbus entity.
 * It is used for converting Patus entities to Licbus entities and Debil entities to Student entities.
 */
public class Egzamin extends Static_Entity{

    public static int amount = 0;

    /**
     * This method defines the position of Egzamin.
     * @param position This parameter yields the position
     */
    public Egzamin(Vector2 position) {
        super(position);
    }
    
}
