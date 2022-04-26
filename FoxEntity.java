public class FoxEntity implements IEntity {

    public Enums.EntityType Type;
    
    // VALUES
    private boolean isOpen;
    private Vector2 position;
    private int awarness = 2;
    private int speed = 1;
    private Node[] neighbours;

    //LOGIC VALUES
    private int movesLeftToDie = 3;

    //SETTERS && GETTERS
    public boolean GetIsOpen() { return isOpen; }
    public void SetIsOpen(boolean isOpen) { this.isOpen = isOpen; }

    public Vector2 GetPosition(){ return position; }
    public void SetPosition(Vector2 pos) { position = pos; }

    public int GetAwarness() { return awarness; } //might be uselles

    public int GetSpeed() { return speed; } //might be uselles
    
    public Node[] GetNeighbours() { return neighbours; }
    public void SetNeighbours(Node[] neigh) { neighbours = neigh; }


    //CTOR
    public FoxEntity(Enums.EntityType type, Vector2 position) {
        Type = type;
        this.position = position;
        isOpen = true;
    }

    public FoxEntity(Enums.EntityType type, Vector2 position, int awarness, int speed, int movesLeftToDie) {
        Type = type;
        this.position = position;
        isOpen = true;
        this.awarness = awarness;
        this.speed = speed;
        this.movesLeftToDie = movesLeftToDie;
    }

    //FUNC

    //Will return true if entity died while func
    public boolean DoMove()
    {
        movesLeftToDie--;

        //Fox should look for rabbit to eat, if one is sucessfully eaten movesLeftToDie should increase
        //
        //

        if(movesLeftToDie == 0) return true;
        return false;
    }
}
