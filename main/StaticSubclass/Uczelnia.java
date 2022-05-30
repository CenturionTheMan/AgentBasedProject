package main.StaticSubclass;
import main.*;

/**
 * This class represents an Uczelnia entity.
 * Its use is to allow Licbus entities turn into Student entities
 */
public class Uczelnia extends Static_Entity {
    /**
     * This method defines the position of Uczelnia
     * @param position This parameter yields the position
     */
    public Uczelnia(Vector2 position) {
        super(position);
    }
    
}
