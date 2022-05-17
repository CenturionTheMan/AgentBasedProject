package main;

public class Entity {

    //VALUES
    private Vector2 position;
    private boolean isOpen = true;  //says wether unit is able to move in given round

    //GETTERS && SETTERS
    public Vector2 GetPosition() { return position; }
    public void SetPosition(Vector2 pos) { position = pos; }

    public boolean IsOpen() { return isOpen; }
    public void SetToOpen() { isOpen = true; }

    //CTOR
    public Entity(Vector2 position) {
        this.position = position;
    }

    public Entity()
    {
        
    }
}
