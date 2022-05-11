package main;

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
    }

    private void Update()
    {
        //while (true)

            //For each node in grid
                //entity = node.occupant
                
                //jakis check dla tej szkoły co zmienia miejsce

                //if(entity == null || !(entity instanceof Active_Entity)) continue
                //Active_Entity active = (Active_Entity)entity; 
                
                //if(!Active_Entity.GetIsOpen()) continue;
               
                //Active_Entity.DoMove();
                //Active_Entity.SetIsOpen(false);

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
