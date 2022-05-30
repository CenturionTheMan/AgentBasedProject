package main;

import main.ActiveSubclass.Debil;
import main.ActiveSubclass.Gimbus;
import main.ActiveSubclass.Licbus;
import main.ActiveSubclass.Patus;
import main.ActiveSubclass.Podbus;
import main.ActiveSubclass.Student;
import main.StaticSubclass.Egzamin;
import main.StaticSubclass.Piwo;

/**
 * This class represents all entities, so the things which are placed on the GridMap
 */
public class Entity {

    //VALUES
    private Vector2 position;
    private boolean isOpen = true;  //says wether unit moves in round should be set to speed value

    //GETTERS && SETTERS

    /**
     * This getter is used to return a position value of an entity
     */
    public Vector2 GetPosition() { return position; }

    /**
     * This setter is used to set a certain position to an entity
     * @param pos This parameter represents the position
     */
    public void SetPosition(Vector2 pos) { position = pos; }

    /**
     * This boolean returns true or false statements regarding the entity's status of being opened or not
     */
    public boolean IsOpen() { 
        if(isOpen) 
        {
            isOpen = false;
            return true;
        }
        else
            return false;
    }

    /**
     * This sets the entity's status of being opened to true
     */
    public void SetToOpen() { isOpen = true; }


    //CTOR

    /**
     * This constructor creates a new entity
     * @param position This parameter represents the entity's position
     */
    public Entity(Vector2 position) {
        this.position = position;
        isOpen = true;
    }

    /**
     * This constructor creates a new entity
     */
    public Entity()
    {
    }

    /**
     * This constructor creates a new entity
     * @param copy This parameter is used to copy
     */
    public Entity(Entity copy)
    {
        this.position = copy.position;
    }




    //METHODS

    /**
     * This method changes position of an entity
     * @param pos This parameter represents position to which will entity go
     * @param grid This parameter represents a double array with nodes used for simulation
     */
    public void ChangePosition(Vector2 pos, Node[][] grid)
    {
        if(grid[pos.x][pos.y].GetOccupant() == null || (grid[pos.x][pos.y].GetOccupant() instanceof Piwo) || (grid[pos.x][pos.y].GetOccupant() instanceof Egzamin)) 
        {
            // if(grid[pos.x][pos.y].GetOccupant() instanceof Piwo) Piwo.amount--;
            // if(grid[pos.x][pos.y].GetOccupant() instanceof Egzamin) Egzamin.amount--;

            grid[GetPosition().x][GetPosition().y].SetOccupant(null);
            ChangeAmountOfGivenSubclass(grid[pos.x][pos.y].GetOccupant(),-1); //????
            grid[pos.x][pos.y].SetOccupant(this);
            SetPosition(pos);            
        }
    }

    /**
     * This method removes an entity from grid
     * @param grid This parameter represents a double array with nodes used for simulation
     */
    public void RemoveFromGrid(Node[][] grid)
    {
        ChangeAmountOfGivenSubclass(this, -1);
        grid[GetPosition().x][GetPosition().y].SetOccupant(null);
    }

    /**
     * This method changes the amount of given subclass by a given amount
     * @param ent This parameter represents an object which class amount should be changed
     * @param change This parameter represents changes which should be applied to a current amount
     */
    public static void ChangeAmountOfGivenSubclass(Entity ent, int change)
    {
        if(ent == null) return;

        if(ent instanceof Debil) Debil.amount += change;
        else if(ent instanceof Gimbus) Gimbus.amount += change;
        else if(ent instanceof Licbus) Licbus.amount += change;
        else if(ent instanceof Patus) Patus.amount += change;
        else if(ent instanceof Podbus) Podbus.amount += change;
        else if(ent instanceof Piwo) Piwo.amount += change;
        else if(ent instanceof Egzamin) Egzamin.amount += change;
        else if(ent instanceof Student) Student.amount += change;
    }

    /**
     * This method changes amount of all subclasses to 0
     */
    public static void ResetAmountOfAllSubclasses()
    {
        Debil.amount =0;
        Gimbus.amount =0;
        Licbus.amount =0;
        Patus.amount =0;
        Podbus.amount =0;
        Piwo.amount =0;
        Egzamin.amount =0;
        Student.amount=0;
    }
}
