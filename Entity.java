/**
 * IEntity
 */
public class Entity {

    // public EntityType GetType();
    // public void SetType(EntityType type);

    // public Vector2 GetPosition();
    // public void SetPosition(Vector2 pos);

    // public boolean GetIsOpen();
    // public void SetIsOpen(boolean isOpen);

    // public Node[] GetNeighbours();
    // public void SetNeighbours(Node[] neigh);

    // public boolean DoMove();

    private EntityType type;
    private Vector2 position;
    private boolean isOpen;
    private Node[] neighbours; 


    //GETTERS && SETTERS
    public EntityType GetType() { return type; }
    public void SetType(EntityType type) { this.type = type; }

    public Vector2 GetPosition() { return position; }
    public void SetPosition(Vector2 pos) { position = pos; }

    public boolean GetIsOpen() { return isOpen; }
    public void SetIsOpen(boolean isOpen) { this.isOpen = isOpen; }

    public Node[] GetNeighbours() { return neighbours; }
    public void SetNeighbours(Node[] neigh) { neighbours = neigh; }

    public Entity(EntityType type, Vector2 position) {
        this.type = type;
        this.position = position;
    }

    //FUNC
    public boolean DoMove() {
        //returns true if unit dies while func
        return false;
    }
}