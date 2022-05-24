package main;

public class Node {
    
    //==================================================================VALUES
    private Entity occupant;
    private Vector2 position;

    
    //================================================================SETTERS && GETTERS
    public Entity GetOccupant(){ return occupant; }
    public void SetOccupant(Entity occupant){ this.occupant = occupant; }

    public Vector2 GetPosition(){ return position; }
    public void SetPosition(Vector2 pos){ position = pos; }


    //=================================================================CTOR
    public Node(Entity occupant, Vector2 position) {
        this.occupant = occupant;
        this.position = position;
    }
    public Node(Vector2 pos)
    {
        position = pos;
        occupant = null;
    }
    public Node(Node node)
    {
        occupant = node.occupant;
        position = node.position;
    }
    public Node()
    {
    }
}
