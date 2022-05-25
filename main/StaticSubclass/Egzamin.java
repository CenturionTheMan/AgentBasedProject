package main.StaticSubclass;
import main.*;

public class Egzamin extends Static_Entity{

    public static int amount = 0;

    public Egzamin(Vector2 position) {
        super(position);
        amount++;
    }
    
}
