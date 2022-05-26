package main;

import main.ActiveSubclass.*;
import main.DataGathering.SimulationResult;
import main.GUI.GUI;
import main.StaticSubclass.*;

public class Simulation {
    
    //===============================================================VALUES
    private GridMap gridMap; //class used for handling grid
    
    private Thread updateThread; //thread used for performing simulation
    private UpdateThread updateThreadClassObject; //instanciated object of UpdateThreadClass
    private static int timeBetweenSteps = 500; //time [in ms] between printing grid
    private boolean isRunning = false; //says whether simualtion is running

    private SimulationResult result = new SimulationResult(); //hold results of simulation

    public static int RoundCount = 0; //says how many round was already performed

    //======================= INIT VALUES ===================================================
    private static boolean isPrintingGrid = true;//if true grid will be printed in console and in gui
    
    private static Vector2 gridSize = new Vector2(10, 10); //size of map

    private static Vector2 Debil_speedANDvision = new Vector2(1, 2); //idicates speed(amound of moves in one round) and vision range of Debil
    private static Vector2 Gimbus_speedANDvision = new Vector2(1, 2); //idicates speed(amound of moves in one round) and vision range of Gimbus
    private static Vector2 Licbus_speedANDvision = new Vector2(1, 2); //idicates speed(amound of moves in one round) and vision range of Licbus
    private static Vector2 Patus_speedANDvision = new Vector2(1, 1); //idicates speed(amound of moves in one round) and vision range of Patus
    private static Vector2 Podbus_speedANDvision = new Vector2(1, 4); //idicates speed(amound of moves in one round) and vision range of Podbus
    private static Vector2 Student_speedANDvision = new Vector2(1, 2); //idicates speed(amound of moves in one round) and vision range of Student

    private static int DebilInitAmount = 0; //amount of Debils units placed on map at the beginning of simulation
    private static int GimbusInitAmount = 0; //amount of Gimbus units placed on map at the beginning of simulation
    private static int LicbusInitAmount = 1; //amount of Licbis units placed on map at the beginning of simulation
    private static int PatusInitAmount = 1; //amount of Patus units placed on map at the beginning of simulation
    private static int PodbusInitAmount = 2; //amount of Podbus units placed on map at the beginning of simulation
    private static int StudentInitAmount = 1; //amount of Student units placed on map at the beginning of simulation

    private static int GimbazaInitAmount = 2; //amount of Gimbaza units placed on map at the beginning of simulation
    private static int LicbazaInitAmount = 1; //amount of Licbaza units placed on map at the beginning of simulation
    private static int UczelniaInitAmount = 1; //amount of Uczelnia units placed on map at the beginning of simulation


    //===============================================================SETTERS && GETTERS
    public void SetGridSize(Vector2 size) { gridSize = size; }
    public Vector2 GetGridSize() { return gridSize; }
    
    public GridMap GetGridMap() { return gridMap; }

    public SimulationResult GetResult() { return result; }

    public static void SetIsPrintingGrid(boolean val) { isPrintingGrid = val; }

    public static void SetTimeBetweenSteps(int val) { timeBetweenSteps = val; }
    public static int GetTimeBetweenSteps() { return timeBetweenSteps; }

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
    public int GetDebilInitAmount() { return DebilInitAmount; }
    public void SetGimbusInitAmount(int amount) { GimbusInitAmount = amount; }
    public int GetGimbusInitAmount() { return GimbusInitAmount; }
    public void SetLicbusInitAmount(int amount) { LicbusInitAmount = amount; }
    public int GetLicbusInitAmount() { return LicbusInitAmount; }
    public void SetPatusInitAmount(int amount) { PatusInitAmount = amount; }
    public int GetPatusInitAmount() { return PatusInitAmount; }
    public void SetPodbusInitAmount(int amount) { PodbusInitAmount = amount; }
    public int GetPodbusInitAmount() { return PodbusInitAmount; }
    public void SetStudentInitAmount(int amount) { StudentInitAmount = amount; }
    public int GetStudentInitAmount() { return StudentInitAmount; }

    public void SetGimbazaInitAmount(int amount) { GimbazaInitAmount = amount; }
    public int GetGimbazaInitAmount() { return GimbazaInitAmount; }
    public void SetLicbazaInitAmount(int amount) { LicbazaInitAmount = amount; }
    public int GetLicbazaInitAmount() { return LicbazaInitAmount; }
    public void SetUczelniaInitAmount(int amount) { UczelniaInitAmount = amount; }
    public int GetUczelniaInitAmount() { return UczelniaInitAmount; }

    public int GetAllUnitsInitAmount()
    {
        return DebilInitAmount + GimbusInitAmount + LicbazaInitAmount + PatusInitAmount + PodbusInitAmount + StudentInitAmount + GimbazaInitAmount + LicbazaInitAmount + UczelniaInitAmount;
    }

    public boolean IsRunning() { return isRunning; }



    //==================================================CTOR
    public Simulation() {
        gridMap = new GridMap(gridSize);

        UpdateThread u = new UpdateThread();
        updateThreadClassObject = u;
    }



    //==================================================METHODS
    
    
    //*Inits grid for simulation
    public void InitSimulation()
    {
        Entity.ResetAmountOfAllSubclasses();
        RoundCount =0;

        if(isRunning)
        {
            updateThread.interrupt();
            isRunning = false;
        }

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


        result.inicialNumberOfDebil = DebilInitAmount;
        result.inicialNumberOfGimbus =GimbusInitAmount;
        result.inicialNumberOfLicbus = LicbusInitAmount;
        result.inicialNumberOfPatus = PatusInitAmount;
        result.inicialNumberOfPodbus = PodbusInitAmount;
        result.inicialNumberStudent = StudentInitAmount;
        result.inicialNumberOfGimbaza = GimbazaInitAmount;
        result.inicialNumberOfLicbaza = LicbazaInitAmount;
        result.inicialNumberOfUczelnia = UczelniaInitAmount;
        result.gridSize = gridSize;

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
        if(isPrintingGrid)GUI.PrintGridInConsole(gridMap.GetGrid(),0);
        if(GUI.gridHolder != null && isPrintingGrid)GUI.InicializeNodeGridGui(gridMap.GetGrid());
    }

    //*Runs simulation without awaking new thread
    public void RunSimulationWithoutNewThred()
    {
        isRunning = true;
        updateThreadClassObject.run();
    }

    //*Runs simulation in new thread
    public void RunSimulation()
    {
        if(!isRunning)
        {
            updateThread = new Thread(updateThreadClassObject);
            isRunning = true;
            updateThread.start();
        }
        else
        {
            if(updateThread.isAlive())updateThread.resume();
        }
    }

    //*Will pause simulation if any is currently running
    public void PauseSimulation()
    {
        if(isRunning)
        {
            updateThread.suspend();
        }
    }

    //*Will setup values used as begin conditions for simulation
    public void SetupSimulationProperties(Vector2 gridSize, Vector2 debil_speedANDvision, Vector2 gimbus_speedANDvision, Vector2 licbus_speedANDvision, 
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

        //*Buffer for running Update in new thread
        public void run() {
            Update();
        }
        
        //*Handle turns cycles
        private void Update()
        {
            int numberOfEndCon = 0;

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
                                break;
                            }
                        }
                    }
                }
                
                RoundCount++;        
                    
                if(isPrintingGrid)
                {
                    System.out.println("Round: [" + RoundCount + "]");
                    GUI.PrintGridInConsole(gridMap.GetGrid(),timeBetweenSteps);
                    GUI.UpdateGridGui(gridMap.GetGrid());
                }

                numberOfEndCon = CheckEndConditions(!isRunning);
                if(numberOfEndCon != 0) isRunning = false;

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


            result.finNumberOfDebil = Debil.amount;
            result.finNumberOfEgzamin = Egzamin.amount;
            result.finNumberOfGimbus = Gimbus.amount;
            result.finNumberOfLicbus = Licbus.amount;
            result.finNumberOfPatus = Patus.amount;
            result.finNumberOfPiwo = Piwo.amount;
            result.finNumberOfPodbus = Podbus.amount;
            result.finNumberStudent = Student.amount;
            
            result.numberOfRounds = RoundCount;
            result.numberOfEndCondition = numberOfEndCon;

            System.out.println("SIM END");
        }

        //*Checks whether any of end conditions is met, if so returns it number, otherwise returns 0
        //boolean isFirstCon - bool retured by DoMove, used to identify condition number 1
        private int CheckEndConditions(boolean isFirstCon)
        {
            if(isFirstCon)
            {
                System.out.println("End condition nr (1) was met");
                return 1;
            }
            if(Piwo.amount == 0 && Gimbus.amount < 2 && Podbus.amount == 0 && Patus.amount == 0) 
            {
                System.out.println("End condition nr (2) was met");
                return 2;
            }
            if(Egzamin.amount ==0 && Student.amount ==0 && Podbus.amount == 0 && Licbus.amount == 0 && Gimbus.amount == 0 && Debil.amount == 0)
            {
                System.out.println("End condition nr (3) was met");
                return 3;
            }
            if(Student.amount ==0 && Podbus.amount == 0 && Licbus.amount == 0 && Gimbus.amount == 0 && Debil.amount == 0 && Patus.amount == 0)
            {
                System.out.println("End condition nr (4) was met");
                return 4;
            }
            if(Simulation.RoundCount == 600)
            {
                System.out.println("End condition nr (5) was met");
                return 5;
            }

            return 0;
        }
    }


}
