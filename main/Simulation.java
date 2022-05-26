package main;

import main.ActiveSubclass.*;
import main.GUI.GUI;
import main.StaticSubclass.*;

public class Simulation {
    
    //===============================================================VALUES
    private GridMap gridMap; //class used for handling grid
    
    private Thread updateThread; //thread used for performing simulation
    private int timeBetweenSteps = 100; //time [in ms] between printing grid

    private boolean isRunning = false; //says whether simualtion is running

    public static int RoundCount = 0; //says how many round was already performed

    //======================= INIT VALUES ===================================================
    private static Vector2 gridSize = new Vector2(5, 5); //size of map

    private static Vector2 Debil_speedANDvision = new Vector2(1, 2); //idicates speed(amound of moves in one round) and vision range of Debil
    private static Vector2 Gimbus_speedANDvision = new Vector2(1, 2); //idicates speed(amound of moves in one round) and vision range of Gimbus
    private static Vector2 Licbus_speedANDvision = new Vector2(1, 2); //idicates speed(amound of moves in one round) and vision range of Licbus
    private static Vector2 Patus_speedANDvision = new Vector2(1, 2); //idicates speed(amound of moves in one round) and vision range of Patus
    private static Vector2 Podbus_speedANDvision = new Vector2(1, 4); //idicates speed(amound of moves in one round) and vision range of Podbus
    private static Vector2 Student_speedANDvision = new Vector2(1, 2); //idicates speed(amound of moves in one round) and vision range of Student

    private static int DebilInitAmount = 0; //amount of Debils units placed on map at the beginning of simulation
    private static int GimbusInitAmount = 0; //amount of Gimbus units placed on map at the beginning of simulation
    private static int LicbusInitAmount = 1; //amount of Licbis units placed on map at the beginning of simulation
    private static int PatusInitAmount = 1; //amount of Patus units placed on map at the beginning of simulation
    private static int PodbusInitAmount = 2; //amount of Podbus units placed on map at the beginning of simulation
    private static int StudentInitAmount = 1; //amount of Student units placed on map at the beginning of simulation

    private static int GimbazaInitAmount = 1; //amount of Gimbaza units placed on map at the beginning of simulation
    private static int LicbazaInitAmount = 1; //amount of Licbaza units placed on map at the beginning of simulation
    private static int UczelniaInitAmount = 1; //amount of Uczelnia units placed on map at the beginning of simulation


    //===============================================================SETTERS && GETTERS
    public void SetGridSize(Vector2 size) { gridSize = size; }

    public GridMap GetGridMap() { return gridMap; }

    public void SetDebil_speedANDvision(Vector2 speedANDvision) { Debil_speedANDvision = speedANDvision; }
    public static Vector2 GetDebil_speedANDvision() {return Debil_speedANDvision; }
    public void SetGimbus_speedANDvision(Vector2 speedANDvision) { Gimbus_speedANDvision = speedANDvision; }
    public static Vector2 GetGimbus_speedANDvision() {return Gimbus_speedANDvision; }
    public void SetLicbus_speedANDvision(Vector2 speedANDvision) { Licbus_speedANDvision = speedANDvision; }
    public static Vector2 GetLicbus_speedANDvision() {return Licbus_speedANDvision; }
    public void SetPatus_speedANDvision(Vector2 speedANDvision) { Patus_speedANDvision = speedANDvision; }
    public static Vector2 GetPatus_speedANDvision() { return Patus_speedANDvision; }
    public void SetPodbus_speedANDvision(Vector2 speedANDvision) { Podbus_speedANDvision = speedANDvision; }
    public static Vector2 GetPodbus_speedANDvision() { return Podbus_speedANDvision; }
    public void SetStudent_speedANDvision(Vector2 speedANDvision) { Student_speedANDvision = speedANDvision; }
    public static Vector2 GetStudent_speedANDvision() { return Student_speedANDvision; }

    public void SetDebilInitAmount(int amount) { DebilInitAmount = amount; }
    public void SetGimbusInitAmount(int amount) { GimbusInitAmount = amount; }
    public void SetLicbusInitAmount(int amount) { LicbusInitAmount = amount; }
    public void SetPatusInitAmount(int amount) { PatusInitAmount = amount; }
    public void SetPodbusInitAmount(int amount) { PodbusInitAmount = amount; }
    public void SetStudentInitAmount(int amount) { StudentInitAmount = amount; }

    public void SetGimbazaInitAmount(int amount) { GimbazaInitAmount = amount; }
    public void SetLicbazaInitAmount(int amount) { LicbazaInitAmount = amount; }
    public void SetUczelniaInitAmount(int amount) { UczelniaInitAmount = amount; }

    public int GetAllUnitsInitAmount()
    {
        return DebilInitAmount + GimbusInitAmount + LicbazaInitAmount + PatusInitAmount + PodbusInitAmount + StudentInitAmount + GimbazaInitAmount + LicbazaInitAmount + UczelniaInitAmount;
    }

    public boolean IsRunning() { return isRunning; }



    //==================================================CTOR
    public Simulation() {
        gridMap = new GridMap(gridSize);

        UpdateThread u = new UpdateThread();
        updateThread = new Thread(u);
    }



    //==================================================METHODS
    
    
    //*Inits grid for simulation
    public void InitSimulation()
    {
        Entity.ResetAmountOfAllSubclasses();
        RoundCount =0;

        if(GetAllUnitsInitAmount() > gridSize.x*gridSize.y)
        {
            System.err.println("Unites summarized amount must be smaller than amount of nodes in map!");
            return;
        }

        if(GimbazaInitAmount < 1 || UczelniaInitAmount < 1 || LicbazaInitAmount < 1)
        {
            System.err.println("There must be at least one school of each type on map!");
            return;
        }

        //Set nodes in grid
        gridMap.InitGrid(gridSize);

        //Put units on map
        for (int i = 0; i < DebilInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Debil(Debil_speedANDvision));
        }
        for (int i = 0; i < GimbusInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Gimbus(Gimbus_speedANDvision));
        }
        for (int i = 0; i < LicbusInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Licbus(Licbus_speedANDvision));
        }
        for (int i = 0; i < PatusInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Patus(Patus_speedANDvision));
        }
        for (int i = 0; i < PodbusInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Podbus(Podbus_speedANDvision));
        }
        for (int i = 0; i < StudentInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Student(Student_speedANDvision));
        }

        for (int i = 0; i < GimbazaInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Gimbaza(null));
        }
        for (int i = 0; i < LicbazaInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Licbaza(null));
        }
        for (int i = 0; i < UczelniaInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Uczelnia(null));
        }
        //

        //SETUP NEIGHB.
        gridMap.SetupNeighbours();


        //print fin grid
        GUI.PrintGridInConsole(gridMap.GetGrid(),500);
    }

    //*Runs simulation in new thread
    public void RunSimulation()
    {
        if(isRunning)
        {
            isRunning = false;
            updateThread.stop();
        }

        isRunning = true;
        updateThread.start();
    }

    //*Will setup values used as begin conditions for simulation
    public static void SetupSimulationProperties(Vector2 gridSize, Vector2 debil_speedANDvision, Vector2 gimbus_speedANDvision, Vector2 licbus_speedANDvision, 
    Vector2 patus_speedANDvision, Vector2 podbus_speedANDvision, Vector2 student_speedANDvision, int debilInitAmount, int gimbusInitAmount, int licbusInitAmount, 
    int patusInitAmount, int podbusInitAmount, int studentInitAmount, int gimbazaInitAmount, int licbazaInitAmount, int uczelniaInitAmount)
    {
        Simulation.gridSize = gridSize;

        Simulation.Debil_speedANDvision = debil_speedANDvision;
        Simulation.Gimbus_speedANDvision = gimbus_speedANDvision;
        Simulation.Licbus_speedANDvision = licbus_speedANDvision;
        Simulation.Patus_speedANDvision = patus_speedANDvision;
        Simulation.Podbus_speedANDvision = podbus_speedANDvision;
        Simulation.Student_speedANDvision = student_speedANDvision;
    
        Simulation.DebilInitAmount = debilInitAmount;
        Simulation.GimbusInitAmount = gimbusInitAmount;
        Simulation.LicbusInitAmount = licbusInitAmount;
        Simulation.PatusInitAmount = patusInitAmount;
        Simulation.PodbusInitAmount = podbusInitAmount;
        Simulation.StudentInitAmount = studentInitAmount;
    
        Simulation.GimbazaInitAmount = gimbazaInitAmount;
        Simulation.LicbazaInitAmount = licbazaInitAmount;
        Simulation.UczelniaInitAmount = uczelniaInitAmount;
    }



    //Class for handling multithreading
    class UpdateThread implements Runnable
    {
        @Override

        //*Buffer for running Update
        public void run() {
            Update();
        }
        
        //*Handle turns cycles
        private void Update()
        {
            while(isRunning)
            {
                for (int i = 0; i < gridMap.GetGrid().length; i++) 
                {
                    for (int j = 0; j < gridMap.GetGrid()[i].length; j++) 
                    {
                        Entity current = gridMap.GetGrid()[i][j].GetOccupant(); //get entity

                        if(current == null) continue; //if none cont

                        if(current instanceof Gimbaza) //if static 
                        {
                            Gimbaza gim = (Gimbaza)current;
                            gim.DoMove(gridMap.GetGrid());;
                        }


                        if(current instanceof Active_Entity) //if active
                        {
                            Active_Entity active = (Active_Entity)current; //casting
                            
                            if(active.DoMove(gridMap.GetGrid(),null)) //do move
                            {
                                isRunning = false; //end if cond met
                            }
                        }
                        else if(Student.amount ==0 && Podbus.amount == 0 && Licbus.amount == 0 && Gimbus.amount == 0 && Debil.amount == 0 && Patus.amount == 0)
                        {
                            isRunning = false;
                            System.out.println("End condition nr (4) was met");
                        }
                    }
                }
                
                RoundCount++;
                System.out.println("Round: [" + RoundCount + "]");
                
                GUI.PrintGridInConsole(gridMap.GetGrid(),timeBetweenSteps);
                GUI.UpdateGridGui(gridMap.GetGrid());

                //Set all active entities as open for next round
                for (Node[] nodes : gridMap.GetGrid()) {
                    for (Node node : nodes) {
                        if(node.GetOccupant() != null)
                        {
                            node.GetOccupant().SetToOpen();
                        }
                    }
                }
            }
            System.out.println("SIM END");
        }
    }


}
