package main;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to program entities capable of moving on the map / grid
 */
public abstract class Active_Entity extends Entity { //klasa abstrakcyjna

    //=======================================================================VALUES
    private int speed;  //amount of squares can unit travel in round
    private int visionRange;    //range of awarness
    private int movesLeft =0;   //moves left in given round

    //=====================================================================GETTERS && SETTERS

    /**
     * This getter returns the speed value of an entity
     */
    public int GetSpeed() { return speed; }

    /**
     * This setter sets a given speed to an entity
     * @param speed This parameter yields the speed value to be given
     */
    public void SetSpeed(int speed) { this.speed = speed; }

    /**
     * This getter returns the range of an entity's vision
     */
    public int GetVisionRange() { return visionRange; }

    /**
     * This setter sets a given amount of vision range for a certain entity
     * @param visionRange This parameter yields the vision range value to be given
     */
    public void SetVisionRange(int visionRange) { this.visionRange = visionRange; }

    /**
     * This setter adds a given number of moves to an entity
     * @param amount This parameter yields the number of moves to be given
     */
    public void AddMoves(int amount) { movesLeft += amount; }

    //==============================================================================================CTOR

    /**
     * This constructor is used to create a new active entity
     * @param position This parameter sets the position of an entity
     * @param speedANDvision This parameter sets the speed and vision values of an entity
     */
    public Active_Entity(Vector2 position, Vector2 speedANDvision) {
        super(position);
        this.speed = speedANDvision.x;
        this.visionRange = speedANDvision.y;
    }

    /**
     * This constructor is used to create a new active entity
     * @param position This parameter sets the position of an entity
     * @param speed This parameter sets the speed of an entity
     * @param visionRange This parameter sets the vision value of an entity
     */
    public Active_Entity(Vector2 position,int speed, int visionRange) {
        super(position);
        this.speed = speed;
        this.visionRange = visionRange;
    }

    /**
     * This constructor is used to create a new active entity
     * @param speedANDvision This parameter sets the speed and vision values of an entity
     */
    public Active_Entity(Vector2 speedANDvision) 
    {
        speed = speedANDvision.x;
        visionRange = speedANDvision.y;
    }
    public Active_Entity() 
    {
    }

    
    //=======================================================================================METHODS

    /**
     * This method initialises an entity's movement.
     * Returns true if any of end condition (of simulation) is meet
     * @param grid This parameter represents a double array with nodes used for simulation
     * @param forcedDir This parameter gives direction. If not null, direction given by it is being used as movement vector
     */
    public final boolean DoMove(Node[][] grid, Vector2 forcedDir) //HUB used for moving entities
    {
        if(IsOpen()) movesLeft = speed;
    
        if(movesLeft <= 0)return false;
        else {
            movesLeft--;
            DoMove(grid, forcedDir);
        }

        List<Active_Entity> activeNeigh = GetActiveNeighbours(grid);
        List<Static_Entity> staticNeigh = GetStaticNeighbours(grid);

        Vector2 move =(forcedDir == null)? MovementLogic(grid,activeNeigh,staticNeigh) : forcedDir; //do MovementLogic and get movement vec
        if(move == null) return false;
        

        //change position
        ChangePosition(Vector2.AddVectors(GetPosition(), move), grid); 

        boolean isEndConMeet = StatusChangeLogic(grid, activeNeigh, staticNeigh); //Change status if needed
  
        return isEndConMeet;
    }

    /**
     * This method returns local movement vector which indicates direction to which will entity go in given round
     * @param grid This parameter represents a double array with nodes used for simulation
     * @param activeNeigh This parameter lists active neighbours visible to an entity
     * @param staticNeigh This parameter lists static neighbours visible to an entity
     */
    protected abstract Vector2 MovementLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh); //Get MovementVector to change unit position

    /**
     * This method returns true if any of end conditions is met
     * @param grid This parameter represents a double array with nodes used for simulation
     * @param activeNeigh This parameter lists active neighbours visible to an entity
     * @param staticNeigh This parameter lists static neighbours visible to an entity
     */
    protected abstract boolean StatusChangeLogic(Node[][] grid, List<Active_Entity> activeNeigh, List<Static_Entity> staticNeigh); //Check status if needed; should return true if entity = Student and Piwo was consumed in turn

    /**
     * This method returns active entities in vision range
     * @param grid This parameter represents a double array with nodes used for simulation
     */
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

    /**
     * This method returns static entities in vision range
     * @param grid This parameter represents a double array with nodes used for simulation
     */
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

    /**
     * This method returns true if there is a given static entity in any of surrounding nodes
     * @param staticNeigh This parameter lists static neighbours visible to an entity
     * @param target This parameter represents a subclass which extends from Static_Entity class
     */
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

    /**
     * This method returns a local vector to given static entity.
     * Will return a random local vector if no object of chosen subclass is in vision range
     * @param staticNeigh This parameter lists static neighbours visible to an entity
     * @param targetClass This parameter represents a subclass which extends from Static_Entity class
     */
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
