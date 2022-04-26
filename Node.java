
public class Node {
    
    //VALUES
    private Enums.TerrainType terrainType;
    private IEntity occupant;

    //SETTERS && GETTERS
    public Enums.TerrainType GetTerrainType(){ if(terrainType == null)terrainType = Enums.TerrainType.BASIC; return terrainType; }
    public void SetTerrainType(Enums.TerrainType type){ terrainType = type; }

    public IEntity GetOccupant(){ return occupant; }
    public void SetOccupant(IEntity occupant){ this.occupant = occupant; }


    //CTOR
    public Node(Enums.TerrainType type, IEntity occupant) {
        this.terrainType = type;
        this.occupant = occupant;
    }

    public Node(IEntity occupant) {
        terrainType = Enums.TerrainType.BASIC;
        this.occupant = occupant;
    }
}
