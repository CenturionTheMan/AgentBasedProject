package main;

import java.util.ArrayList;
import java.util.List;

public abstract class Active_Entity extends Entity {

    //VALUES
    private Entity[] neighbours;
    private int speed;
    private int visionRange;


    //GETTERS && SETTERS
    public Entity[] GetNeighbours() { return neighbours; }
    public void SetNeighbours(Entity[] neighbours) { this.neighbours = neighbours; }

    public int GetSpeed() { return speed; }
    public void SetSpeed(int speed) { this.speed = speed; }

    public int GetVisionRange() { return visionRange; }
    public void SetVisionRange(int visionRange) { this.visionRange = visionRange; }


    //CTOR
    public Active_Entity(Vector2 position,int speed, int visionRange, Entity[] neighbours) {
        super(position);
        this.neighbours = neighbours;
        this.speed = speed;
        this.visionRange = visionRange;
    }

    
    //do not touch
    public void DoMove(Node[][] grid)
    {
        Vector2 move = Logic(grid,GetActiveNeighbours(grid),GetStaticNeighbours(grid));
        if(move == null) return;


        grid[GetPosition().x + move.x][GetPosition().y + move.y].SetOccupant(this); //NWM CZY NIE ROBI Z KAZDEGO OCCUPANTA KLASY ACTIVE_ENTITY -> raczej nie
        grid[GetPosition().x][GetPosition().y].SetOccupant(null);
        SetPosition(Vector2.AddVectors(GetPosition(), move));
    }

    protected abstract Vector2 Logic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh); //TO OVERWRITE

    private List<Active_Entity> GetActiveNeighbours(Node[][] grid)
    {
        List<Active_Entity> arr = new ArrayList<Active_Entity>();
        
        for (int x = -visionRange; x < visionRange +1; x++) {
            for (int y = -visionRange; y < visionRange +1; y++) {
                
                Vector2 inRange = new Vector2(x, y);
                Vector2 relativePos = Vector2.AddVectors(GetPosition(), inRange);
                
                if(relativePos.Compare(GetPosition())) continue;
                if(relativePos.x <0 || relativePos.x > grid.length) continue;
                if(relativePos.y <0 || relativePos.y > grid[x].length) continue;

                if(grid[relativePos.x][relativePos.y].GetOccupant() == null) continue;
                if(!(grid[relativePos.x][relativePos.y].GetOccupant() instanceof Active_Entity)) continue;

                arr.add((Active_Entity)grid[relativePos.x][relativePos.y].GetOccupant());
            }
        }

        return arr;
    }

    private List<Static_Entity> GetStaticNeighbours(Node[][] grid)
    {
        List<Static_Entity> arr = new ArrayList<Static_Entity>();
        
        for (int x = -visionRange; x < visionRange +1; x++) {
            for (int y = -visionRange; y < visionRange +1; y++) {
                
                Vector2 inRange = new Vector2(x, y);
                Vector2 relativePos = Vector2.AddVectors(GetPosition(), inRange);
                
                if(relativePos.Compare(GetPosition())) continue;
                if(relativePos.x <0 || relativePos.x > grid.length) continue;
                if(relativePos.y <0 || relativePos.y > grid[x].length) continue;

                if(grid[relativePos.x][relativePos.y].GetOccupant() == null) continue;
                if(!(grid[relativePos.x][relativePos.y].GetOccupant() instanceof Static_Entity)) continue;

                arr.add((Static_Entity)grid[relativePos.x][relativePos.y].GetOccupant());
            }
        }

        return arr;
    }
}
