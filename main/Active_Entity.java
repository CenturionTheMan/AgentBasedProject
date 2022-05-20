package main;

import java.util.ArrayList;
import java.util.List;

public abstract class Active_Entity extends Entity { //klasa abstrakcyjna

    //VALUES
    private Entity[] neighbours;    //entities in neighbourhood
    private int speed;  //amount of squares can unit travel in round
    private int visionRange;    //range of awarness


    //GETTERS && SETTERS
    public Entity[] GetNeighbours() { return neighbours; }
    public void SetNeighbours(Entity[] neighbours) { this.neighbours = neighbours; }

    public int GetSpeed() { return speed; }
    public void SetSpeed(int speed) { this.speed = speed; }

    public int GetVisionRange() { return visionRange; }
    public void SetVisionRange(int visionRange) { this.visionRange = visionRange; }


    //CTOR
    public Active_Entity(Vector2 position, Vector2 speedANDvision, Entity[] neighbours) {
        super(position);
        this.neighbours = neighbours;
        this.speed = speedANDvision.x;
        this.visionRange = speedANDvision.y;
    }

    public Active_Entity(Vector2 position,int speed, int visionRange, Entity[] neighbours) {
        super(position);
        this.neighbours = neighbours;
        this.speed = speed;
        this.visionRange = visionRange;
    }

    
    //METHODS
    public final boolean DoMove(Node[][] grid) //HUB used for moving entities
    {
        if(!IsOpen()) return false; //if entity moved in round -> prevent from moving again

        List<Active_Entity> activeNeigh = GetActiveNeighbours(grid);
        List<Static_Entity> staticNeigh = GetStaticNeighbours(grid);

        Vector2 move = MovementLogic(grid,activeNeigh,staticNeigh); //do MovementLogic and get movement vec
        if(move == null) return false;

        int swap = move.x;
        move.x = -move.y;
        move.y = swap;
        
        //change position
        grid[GetPosition().x + move.x][GetPosition().y + move.y].SetOccupant(this);
        grid[GetPosition().x][GetPosition().y].SetOccupant(null);
        SetPosition(Vector2.AddVectors(GetPosition(), move));
        //

        boolean isEndConMeet = StatusChangeLogic(grid, activeNeigh, staticNeigh); //Change status if needed
        return isEndConMeet;
    }

    protected abstract Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh); //Get MovementVector to change unit position
    
    protected abstract boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh); //Check status if needed; should return true if enticy = student and piwo was eatn in turn

    //GET ACTIVE NEIGHBOURS BASED ON VISION RANGE
    private List<Active_Entity> GetActiveNeighbours(Node[][] grid)
    {
        List<Active_Entity> arr = new ArrayList<Active_Entity>();   
        List<Node> nodes = GridMap.GetNeighbourNodes(GetPosition(), visionRange);
        
        for (Node node : nodes) {
            if(node.GetOccupant() instanceof Active_Entity)
            {
                arr.add((Active_Entity)node.GetOccupant());
            }
        }

        return arr;
    }

    //GET STATIC NEIGHBOURS BASED ON VISION RANGE
    private List<Static_Entity> GetStaticNeighbours(Node[][] grid)
    {
        List<Static_Entity> arr = new ArrayList<Static_Entity>();
        List<Node> nodes = GridMap.GetNeighbourNodes(GetPosition(), visionRange);
        
        for (Node node : nodes) {
            if(node.GetOccupant() instanceof Static_Entity)
            {
                arr.add((Static_Entity)node.GetOccupant());
            }
        }
        

        return arr;
    }
}
