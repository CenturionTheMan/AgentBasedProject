package main;

public class Entity {

    //VALUES
    private Vector2 position;
    private boolean isOpen = true;

    //GETTERS && SETTERS
    public Vector2 GetPosition() { return position; }
    public void SetPosition(Vector2 pos) { position = pos; }

    public boolean GetIsOpen() { return isOpen; }
    public void SetIsOpen(boolean isOpen) { this.isOpen = isOpen; }

    //CTOR
    public Entity(Vector2 position) {
        this.position = position;
    }

    public Entity()
    {
        
    }
}
