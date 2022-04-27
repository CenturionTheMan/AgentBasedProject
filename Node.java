
public class Node {
    
    //VALUES
    private TerrainType terrainType;
    private Entity occupant;

    //SETTERS && GETTERS
    public TerrainType GetTerrainType(){ return terrainType; }
    public void SetTerrainType(TerrainType type){ terrainType = type; }

    public Entity GetOccupant(){ return occupant; }
    public void SetOccupant(Entity occupant){ this.occupant = occupant; }


    public Node(Entity occupant) {
        terrainType = TerrainType.BASIC;
        this.occupant = occupant;
    }
}
