/**
 * Application
 */
public class Application {

    //private Node[][] grid 
    //private bool isSimulationActive get; set;


    public static void main(String[] args) {
        InitApp();
    }

    static void InitApp()
    {
        //GUI
    }

    //TEMP FUNCION (most likely will be replaced by GUI handling)
    static void SetupBeginCondiotions()
    {
        //setup values

        //StartSimulation(init values)
    }

    static void StartSimulation()
    {
        //if(isSimulationActive) return

        //Setup grid 
            //set size
        
        //For each node in grid
            //set terrain types
            //spawn entities with some probability
            //setup neighboures for each entity
        
        //isSimulation active = true
        //Run Update
    }

    static void Update()
    {
        //while (isSimulationActive)
            
            //EntityType check = null
            //bool shouldSimEnd = true

            //For each node in grid
                //entity = node.occupant
                //if(entity = null) continue
                
                //if(entity.isOpen)
                    //if(DoMove) -> Do move will return true if entity dies in turn
                        //continue
                    //setup neighboures
                    
                    //if(check == null) check = entity.GetType()
                    //else if (check != entity.GetType()) shouldSimEnd = true

                    //entity.isOpen = false
                //
            //

            //if(shouldSimEnd)
                //isSimulationActive = false
                //continue

            //For each node in grid
                //entity = node.occupant
                //if(entity = null) continue
                //entity.isOpen = true
            //

            //GUI UPDATE
        //

        //Some sumarize after sim end??
    }
}