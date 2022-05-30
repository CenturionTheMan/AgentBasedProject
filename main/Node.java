package main;

/**
 * This class represents a single node on which an entity can be placed
 */
public class Node {
    
    //==================================================================VALUES
    private Entity occupant;
    private Vector2 position;

    
    //================================================================SETTERS && GETTERS

    /**
     * This getter is used to return an information about what entity is placed on the node
     */
    public Entity GetOccupant(){ return occupant; }

    /**
     * This setter sets a chosen entity on the node
     * @param occupant This parameter represents the type of entity
     */
    public void SetOccupant(Entity occupant){ this.occupant = occupant; }

    /**
     * This getter gets the position of a node
     */
    public Vector2 GetPosition(){ return position; }

    /**
     * This setter sets the position of a node
     * @param pos This parameter represents the position value
     */
    public void SetPosition(Vector2 pos){ position = pos; }


    //=================================================================CTOR

    /**
     * This constructor creates a node
     * @param occupant This parameter represents what entity is placed on the node
     * @param position This parameter represents the position of the node on the gridMap
     */
    public Node(Entity occupant, Vector2 position) {
        this.occupant = occupant;
        this.position = position;
    }

    /**
     * This constructor creates a node
     * @param pos This parameter represents the position of the node on the gridMap
     */
    public Node(Vector2 pos)
    {
        position = pos;
        occupant = null;
    }

    /**
     * This constructor creates a node
     * @param node This parameter represents the node itself
     */
    public Node(Node node)
    {
        occupant = node.occupant;
        position = node.position;
    }

    /**
     * This constructor creates a node
     */
    public Node()
    {
    }
}
