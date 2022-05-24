package main;

import main.StaticSubclass.Egzamin;
import main.StaticSubclass.Piwo;

public class Entity {

    //VALUES
    private Vector2 position;
    private boolean isOpen = true;  //says wether unit moves in round should be set to speed value

    //GETTERS && SETTERS
    public Vector2 GetPosition() { return position; }
    public void SetPosition(Vector2 pos) { position = pos; }

    public boolean IsOpen() { 
        if(isOpen) 
        {
            isOpen = false;
            return true;
        }
        else
            return false;
    }
    public void SetToOpen() { isOpen = true; }


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

    //*Changes position of entitiy
    //Node[][] grid - double array with nodes used for simulation
    //Vector2 pos - position to which will entity go
    public void ChangePosition(Vector2 pos, Node[][] grid)
    {
        if(grid[pos.x][pos.y].GetOccupant() == null || (grid[pos.x][pos.y].GetOccupant() instanceof Piwo) || (grid[pos.x][pos.y].GetOccupant() instanceof Egzamin)) 
        {
            grid[GetPosition().x][GetPosition().y].SetOccupant(null);
            grid[pos.x][pos.y].SetOccupant(this);
            SetPosition(pos);
        }
    }

    //*Removes this unit from grid
    //Node[][] grid - double array with nodes used for simulation
    public void RemoveFromGrid(Node[][] grid)
    {
        grid[GetPosition().x][GetPosition().y].SetOccupant(null);
    }
}
