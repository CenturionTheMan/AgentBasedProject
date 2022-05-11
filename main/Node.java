package main;

public class Node {
    
    //VALUES
    private Entity occupant;
    private Vector2 position;

    
    //SETTERS && GETTERS
    public Entity GetOccupant(){ return occupant; }
    public void SetOccupant(Entity occupant){ this.occupant = occupant; }

    public Vector2 GetPosition(){ return position; }
    public void SetPosition(Vector2 pos){ position = pos; }


    //CTOR
    public Node(Entity occupant) {
        this.occupant = occupant;
    }
}
