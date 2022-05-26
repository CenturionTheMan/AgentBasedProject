package main;

import main.ActiveSubclass.Debil;
import main.ActiveSubclass.Gimbus;
import main.ActiveSubclass.Licbus;
import main.ActiveSubclass.Patus;
import main.ActiveSubclass.Podbus;
import main.ActiveSubclass.Student;
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
            ChangeAmountOfGivenSubclass(grid[pos.x][pos.y].GetOccupant(),-1);
            grid[pos.x][pos.y].SetOccupant(this);
            SetPosition(pos);            
        }
    }

    //*Removes this unit from grid
    //Node[][] grid - double array with nodes used for simulation
    public void RemoveFromGrid(Node[][] grid)
    {
        ChangeAmountOfGivenSubclass(this, -1);
        grid[GetPosition().x][GetPosition().y].SetOccupant(null);
    }

    //*Changes amount of given subclass by given amount
    //Entity ent - object which class amount should be changed
    //int change - change which shold be applied to current amount
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

    //*Changes amount of all subclasses to 0
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
