package main;

import main.ActiveSubclass.*;
import main.StaticSubclass.*;

public class Simulation {
    
    //VALUES
    private Vector2 gridSize;

    private Vector2 Debil_speedANDvision;
    private Vector2 Gimbus_speedANDvision;
    private Vector2 Licbus_speedANDvision;
    private Vector2 Patus_speedANDvision;
    private Vector2 Podbus_speedANDvision;
    private Vector2 Student_speedANDvision;

    private boolean isRunning = false;


    //SETTERS && GETTERS
    public void SetGridSize(Vector2 size) { gridSize = size; }

    public void SetDebil_speedANDvision(Vector2 speedANDvision) { Debil_speedANDvision = speedANDvision; }
    public void SetGimbus_speedANDvision(Vector2 speedANDvision) { Gimbus_speedANDvision = speedANDvision; }
    public void SetLicbus_speedANDvision(Vector2 speedANDvision) { Licbus_speedANDvision = speedANDvision; }
    public void SetPatus_speedANDvision(Vector2 speedANDvision) { Patus_speedANDvision = speedANDvision; }
    public void SetPodbus_speedANDvision(Vector2 speedANDvision) { Podbus_speedANDvision = speedANDvision; }
    public void SetStudent_speedANDvision(Vector2 speedANDvision) { Student_speedANDvision = speedANDvision; }

    public boolean IsRunning() { return isRunning; }


    //CTOR
    public Simulation(/*sth to handle gui: GUI class/pointer to func in gui class/delegates -> sth have to be choosen */) {
        
        //BASE VALUES
        SetGridSize(new Vector2(10, 10));
        
        SetDebil_speedANDvision(new Vector2(1, 1));
        SetGimbus_speedANDvision(new Vector2(1, 1));
        SetLicbus_speedANDvision(new Vector2(1, 1));
        SetPatus_speedANDvision(new Vector2(1, 1));
        SetPodbus_speedANDvision(new Vector2(1, 1));
        SetStudent_speedANDvision(new Vector2(1, 1));
    }

    public void RunSimulation()
    {
        if(isRunning)
        {
            //end previus simulation and create new one
        }

        Node[][] grid = new Node[gridSize.x][gridSize.y]; // tu albo publiczne, zależnie od potrzeb GUI
        
        //For each node in grid
            //spawn entities with some probability
            //setup neighboures for each entity

        isRunning = true;
        Update(); //Run Update in new thread (prob)

        //Testing(grid);
    }

    //TEMP FUNC
    private void Testing(Node[][] grid)
    {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Node(new Vector2(i, j));
            }
        }

        grid[1][1].SetOccupant(new Patus(new Vector2(1, 1), 1, 1, null));
        Patus t = (Patus)grid[1][1].GetOccupant();
        t.DoMove(grid);
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].GetOccupant() == null) System.out.print("-");
                else
                {
                    System.out.print("x");
                    //System.out.println(grid[i][j].GetOccupant());
                }
            }
            System.out.println();
        }
    }


    private void Update()
    {
        //while (true)

            //For each node in grid
                //entity = node.occupant
                
                //jakis check dla tej szkoły co zmienia miejsce

                //if(entity == null || !(entity instanceof Active_Entity)) continue
                //Active_Entity active = (Active_Entity)entity; 
               
                //Active_Entity.DoMove();

                //GUI UPDATE
            //

            //For each node in grid
                //if(node.occupant != null)
                    //node.occupant.SetIsOpen(true);
            //
            
            
            //if(END CONDITION) break;
        //

        //Some sumarize after sim end??

        isRunning = false;
    }
}
