/**
 * IEntity
 */
public interface IEntity {

    public Enums.EntityType type = Enums.EntityType.NULL;
    
    public Vector2 GetPosition();
    public void SetPosition(Vector2 pos);

    public boolean GetIsOpen();
    public void SetIsOpen(boolean isOpen);

    public int GetAwarness();
    public int GetSpeed();

    public Node[] GetNeighbours();
    public void SetNeighbours(Node[] neigh);

    public boolean DoMove();
}