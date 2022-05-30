package main.StaticSubclass;
import main.*;

/**
 * This class represents a Piwo entity.
 * It has 20% of being dropped by Patus entities.
 * Its use is to be consumed by Student entities.
 */
public class Piwo extends Static_Entity {

    public static int amount = 0;

    /**
     * This method defines the position of Piwo
     * @param position This parameter yields the position
     */
    public Piwo(Vector2 position) {
        super(position);
    }
    
}
