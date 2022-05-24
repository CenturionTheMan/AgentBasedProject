package main;

import java.util.ArrayList;
import java.util.List;

public abstract class Active_Entity extends Entity { //klasa abstrakcyjna

    //VALUES
    private List<Entity> neighbours;    //entities in neighbourhood
    private int speed;  //amount of squares can unit travel in round
    private int visionRange;    //range of awarness
    private int movesLeft =0;   //moves left in given round

    //GETTERS && SETTERS
    public List<Entity> GetNeighbours() { return neighbours; }
    public void SetNeighbours(List<Entity> neighbours) { this.neighbours = neighbours; }

    public int GetSpeed() { return speed; }
    public void SetSpeed(int speed) { this.speed = speed; }

    public int GetVisionRange() { return visionRange; }
    public void SetVisionRange(int visionRange) { this.visionRange = visionRange; }

    public void AddMoves(int amount) { movesLeft += amount; }

    //CTOR
    public Active_Entity(Vector2 position, Vector2 speedANDvision, List<Entity> neighbours) {
        super(position);
        this.neighbours = neighbours;
        this.speed = speedANDvision.x;
        this.visionRange = speedANDvision.y;
    }

    public Active_Entity(Vector2 position,int speed, int visionRange, List<Entity> neighbours) {
        super(position);
        this.neighbours = neighbours;
        this.speed = speed;
        this.visionRange = visionRange;
    }
    public Active_Entity(Vector2 speedANDvision) 
    {
        speed = speedANDvision.x;
        visionRange = speedANDvision.y;
    }
    public Active_Entity() 
    {
    }

    
    //METHODS
    public final boolean DoMove(Node[][] grid, Vector2 forcedDir) //HUB used for moving entities
    {
        if(IsOpen()) movesLeft = speed;
    
        if(movesLeft <= 0)return false;
        else
            movesLeft--;

        List<Active_Entity> activeNeigh = GetActiveNeighbours(grid);
        List<Static_Entity> staticNeigh = GetStaticNeighbours(grid);

        Vector2 move =(forcedDir == null)? MovementLogic(grid,activeNeigh,staticNeigh) : forcedDir; //do MovementLogic and get movement vec
        if(move == null) return false;
        

        //change position
        ChangePosition(Vector2.AddVectors(GetPosition(), move), grid); 

        boolean isEndConMeet = StatusChangeLogic(grid, activeNeigh, staticNeigh); //Change status if needed

        //GUI.PrintGrid(grid, 1000); //TEMP EACH MOVE GUI UPDATE

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


    protected boolean IsStaticEntityInNeighborhood(List<Static_Entity> staticNeigh ,Class<? extends Static_Entity> target)
    {
        if(staticNeigh != null && staticNeigh.size() > 0)
        {
            for (Static_Entity s : staticNeigh) {
                if(target.isInstance(s))
                {
                    if(GetPosition().SubtractVector(s.GetPosition()).GetLenght() < 2) return true;
                }
            }
        }
        return false;
    }

    //RETURNS LOCAL VECTOR TO CHOOSEN STATIC ENTITY OR RANDOM VECTOR TO FREE NODE IF NO CHOOSEN STATIC ENT IN NEIGHBORHOOD
    protected Vector2 GetMovementVectorToStaticEntity(List<Static_Entity>staticNeigh, Class<? extends Static_Entity> targetClass)
    {
        Vector2 baza = null;
        Vector2 dir = new Vector2(0, 0);
        if(staticNeigh != null && staticNeigh.size() > 0)
        {
            List<Vector2> schoolsPos = new ArrayList<Vector2>();
            for (Static_Entity static_Entity : staticNeigh) {
                if(targetClass.isInstance(static_Entity))
                {
                    schoolsPos.add(static_Entity.GetPosition());
                }
            }

            baza = GridMap.GetTheClosestPointToTargetFromPoints(schoolsPos, GetPosition());
        }
        
        if(baza == null)
        {
            dir = GridMap.GetFreePositionInNeighbourhood(GetPosition()).SubtractVector(GetPosition());
        }
        else
        {
            Node target = GridMap.GetClosestToPointNodeInUnitSquare(GetPosition(), baza);
            if(target != null) dir = target.GetPosition().SubtractVector(GetPosition());
        }

        return dir;
    }

}
