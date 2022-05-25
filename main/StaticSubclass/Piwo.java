package main.StaticSubclass;
import main.*;

public class Piwo extends Static_Entity {

    public static int amount = 0;

    public Piwo(Vector2 position) {
        super(position);
        amount++;
    }
    
}
