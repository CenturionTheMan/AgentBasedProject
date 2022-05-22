package main;

public class Entity {

    //VALUES
    private Vector2 position;
    private boolean isOpen = true;  //says wether unit is able to move in given round
    private boolean hasMoved = false;

    //GETTERS && SETTERS
    public Vector2 GetPosition() { return position; }
    public void SetPosition(Vector2 pos) { position = pos; }

    public boolean IsOpen() { 
        if(isOpen) 
        {
            hasMoved = true;
            isOpen = false;
            return true;
        }
        else
            return false;
    }
    public void SetToOpen() { isOpen = true; hasMoved = false; }

    public boolean HasMoved()
    {
        if(hasMoved == true)
        {
            hasMoved = false;
            return true;
        }
        else
        {
            return false;
        }
    }


    //CTOR
    public Entity(Vector2 position) {
        this.position = position;
        isOpen = true;
    }

    public Entity()
    {
    }

    public Entity(Entity copy)
    {
        this.position = copy.position;
    }




    //METHODS

    //Changes position of entitiy 
    public void ChangePosition(Vector2 pos, Node[][] grid)
    {
        
        if(grid[pos.x][pos.y].GetOccupant() != null) 
        {
            //System.err.println("ERROR: Entity at position " + GetPosition().toString() +" tried to move to occupied node at pos " + pos.toString());
            return;
        }
        grid[GetPosition().x][GetPosition().y].SetOccupant(null);
        grid[pos.x][pos.y].SetOccupant(this);
        SetPosition(pos);
    }
}
