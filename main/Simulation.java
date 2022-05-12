package main;

import main.ActiveSubclass.*;
import main.StaticSubclass.*;

public class Simulation {
    
    private Node[][] grid;


    public Simulation(/*setup values, sth to handle gui: GUI class/pointer to func in gui class/delegates -> sth have to be choosen */) {

        // Entity base = new Gimbaza(new Vector2(0, 0));
        // Gimbus temp = new Gimbus(new Vector2(0, 0), 0, 0, null);
        
        //Setup grid 
            //set size
        
        //For each node in grid
            //spawn entities with some probability
            //setup neighboures for each entity

        //Run Update in new thread (prob)


        //Testing();
    }

    //TEMP FUNC
    private void Testing()
    {
        grid = new Node[2][2];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Node(new Vector2(i, j));
            }
        }

        grid[0][0].SetOccupant(new Patus(new Vector2(0, 0), 1, 1, null));
        Patus t = (Patus)grid[0][0].GetOccupant();
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
    }
}
