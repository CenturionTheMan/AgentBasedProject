package main;

import main.ActiveSubclass.*;
import main.DataGathering.SimulationResult;
import main.GUI.GUI;
import main.StaticSubclass.*;

/**
 * This class represents the simulation process of the program
 */
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
    private static int PiwoInitAmount = 0; //amount of Uczelnia units placed on map at the beginning of simulation
    private static int EgzaminInitAmount = 0; //amount of Uczelnia units placed on map at the beginning of simulation


    //===============================================================SETTERS && GETTERS

    /**
     * This setter sets the size of the gridMap
     * @param size This parameter represents the size of the grid to be set
     */
    public void SetGridSize(Vector2 size) { gridSize = size; }

    /**
     * This getter returns the gridMap's size
     */
    public Vector2 GetGridSize() { return gridSize; }

    /**
     * This getter returns the gridMap
     */
    public GridMap GetGridMap() { return gridMap; }

    /**
     * This getter returns the results
     */
    public SimulationResult GetResult() { return result; }

    /**
     * This setter sets the boolean value of the isPrintingGrid property
     * @param val This parameter represents a boolean value
     */
    public static void SetIsPrintingGrid(boolean val) { isPrintingGrid = val; }

    /**
     * This setter sets time between steps of an entity
     * @param val This parameter represents the time
     */
    public static void SetTimeBetweenSteps(int val) { timeBetweenSteps = val; }

    /**
     * This getter returns the time between steps of an entity
     */
    public static int GetTimeBetweenSteps() { return timeBetweenSteps; }

    /**
     * This setter sets the speed and vision values of Debil entity
     * @param speedANDvision This parameter represents these values
     */
    public void SetDebil_speedANDvision(Vector2 speedANDvision) { Debil_speedANDvision = speedANDvision; }

    /**
     * This getter returns the values of Debil's speed and vision
     */
    public static Vector2 GetDebil_speedANDvision() {return Debil_speedANDvision; }

    /**
     * This setter sets the speed and vision values of Gimbus entity
     * @param speedANDvision This parameter represents these values
     */
    public void SetGimbus_speedANDvision(Vector2 speedANDvision) { Gimbus_speedANDvision = speedANDvision; }

    /**
     * This getter returns the values of Gimbus' speed and vision
     */
    public static Vector2 GetGimbus_speedANDvision() {return Gimbus_speedANDvision; }

    /**
     * This setter sets the speed and vision values of Licbus entity
     * @param speedANDvision This parameter represents these values
     */
    public void SetLicbus_speedANDvision(Vector2 speedANDvision) { Licbus_speedANDvision = speedANDvision; }

    /**
     * This getter returns the values of Licbus' speed and vision
     */
    public static Vector2 GetLicbus_speedANDvision() {return Licbus_speedANDvision; }

    /**
     * This setter sets the speed and vision values of Patus entity
     * @param speedANDvision This parameter represents these values
     */
    public void SetPatus_speedANDvision(Vector2 speedANDvision) { Patus_speedANDvision = speedANDvision; }

    /**
     * This getter returns the values of Patus' speed and vision
     */
    public static Vector2 GetPatus_speedANDvision() { return Patus_speedANDvision; }

    /**
     * This setter sets the speed and vision values of Podbus entity
     * @param speedANDvision This parameter represents these values
     */
    public void SetPodbus_speedANDvision(Vector2 speedANDvision) { Podbus_speedANDvision = speedANDvision; }

    /**
     * This getter returns the values of Podbus' speed and vision
     */
    public static Vector2 GetPodbus_speedANDvision() { return Podbus_speedANDvision; }

    /**
     * This setter sets the speed and vision values of Student entity
     * @param speedANDvision This parameter represents these values
     */
    public void SetStudent_speedANDvision(Vector2 speedANDvision) { Student_speedANDvision = speedANDvision; }

    /**
     * This getter returns the values of Student's speed and vision
     */
    public static Vector2 GetStudent_speedANDvision() { return Student_speedANDvision; }

    /**
     * This setter sets the initial amount of Debil entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetDebilInitAmount(int amount) { DebilInitAmount = amount; }

    /**
     * This getter returns the amount of Debil entities
     */
    public int GetDebilInitAmount() { return DebilInitAmount; }

    /**
     * This setter sets the initial amount of Gimbus entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetGimbusInitAmount(int amount) { GimbusInitAmount = amount; }

    /**
     * This getter returns the amount of Gimbus entities
     */
    public int GetGimbusInitAmount() { return GimbusInitAmount; }

    /**
     * This setter sets the initial amount of Licbus entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetLicbusInitAmount(int amount) { LicbusInitAmount = amount; }

    /**
     * This getter returns the amount of Licbus entities
     */
    public int GetLicbusInitAmount() { return LicbusInitAmount; }

    /**
     * This setter sets the initial amount of Patus entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetPatusInitAmount(int amount) { PatusInitAmount = amount; }

    /**
     * This getter returns the amount of Patus entities
     */
    public int GetPatusInitAmount() { return PatusInitAmount; }

    /**
     * This setter sets the initial amount of Podbus entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetPodbusInitAmount(int amount) { PodbusInitAmount = amount; }

    /**
     * This getter returns the amount of Podbus entities
     */
    public int GetPodbusInitAmount() { return PodbusInitAmount; }

    /**
     * This setter sets the initial amount of Student entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetStudentInitAmount(int amount) { StudentInitAmount = amount; }

    /**
     * This getter returns the amount of Student entities
     */
    public int GetStudentInitAmount() { return StudentInitAmount; }

    /**
     * This setter sets the initial amount of Gimbaza entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetGimbazaInitAmount(int amount) { GimbazaInitAmount = amount; }

    /**
     * This getter returns the amount of Gimbaza entities
     */
    public int GetGimbazaInitAmount() { return GimbazaInitAmount; }

    /**
     * This setter sets the initial amount of Licbaza entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetLicbazaInitAmount(int amount) { LicbazaInitAmount = amount; }

    /**
     * This getter returns the amount of Licbaza entities
     */
    public int GetLicbazaInitAmount() { return LicbazaInitAmount; }

    /**
     * This setter sets the initial amount of Uczelnia entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetUczelniaInitAmount(int amount) { UczelniaInitAmount = amount; }

    /**
     * This getter returns the amount of Uczelnia entities
     */
    public int GetUczelniaInitAmount() { return UczelniaInitAmount; }

    /**
     * This setter sets the initial amount of Piwo entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetPiwoInitAmount (int amount) {PiwoInitAmount = amount;}

    /**
     * This getter returns the amount of Piwo entities
     */
    public int GetPiwoInitAmount() { return PiwoInitAmount; }

    /**
     * This setter sets the initial amount of Egzamin entities on the gridMap
     * @param amount This parameter represents the amount of entities
     */
    public void SetEgzaminInitAmount (int amount) {EgzaminInitAmount = amount;}

    /**
     * This getter returns the amount of Egzamin entities
     */
    public int GetEgzaminInitAmount() { return EgzaminInitAmount; }

    /**
     * This getter returns the amount of all entities
     */
    public int GetAllUnitsInitAmount()
    {
        return PiwoInitAmount + EgzaminInitAmount + DebilInitAmount + GimbusInitAmount + LicbazaInitAmount + PatusInitAmount + PodbusInitAmount + StudentInitAmount + GimbazaInitAmount + LicbazaInitAmount + UczelniaInitAmount;
    }

    /**
     * This boolean returns true or false whether the simulation is running
     */
    public boolean IsRunning() { return isRunning; }



    //==================================================CTOR

    /**
     * This constructor creates the simulation
     */
    public Simulation() {
        gridMap = new GridMap(gridSize);

        UpdateThread u = new UpdateThread();
        updateThreadClassObject = u;
    }



    //==================================================METHODS

    /**
     * This method initiates grid for simulation
     */
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
        result.inicialNumberOfPiwo = PiwoInitAmount;
        result.inicialNumberOfEgzamin = EgzaminInitAmount;
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
        for (int i = 0; i < PiwoInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Piwo(null));
        }
        for (int i = 0; i < EgzaminInitAmount; i++) {
            GridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Egzamin(null));
        }
        //


        //print fin grid
        if(isPrintingGrid)
        {
            GUI.PrintGridInConsole(gridMap.GetGrid(),0);
            GUI.InicializeNodeGridGui(gridMap.GetGrid(), 0);
            GUI.SetSimulationStatus("Simulation status: INICIALIZED");
        }
        
    }

    /**
     * This method runs simulation without awaking new thread
     */
    public void RunSimulationWithoutNewThred()
    {
        isRunning = true;
        updateThreadClassObject.run();
    }

    /**
     * This method runs simulation in new thread
     */
    public void RunSimulation()
    {
        GUI.SetSimulationStatus("Simulation status: RUNNING");
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

    /**
     * This method will pause simulation if any is currently running
     */
    public void PauseSimulation()
    {
        if(isRunning)
        {
            updateThread.suspend();
            GUI.SetSimulationStatus("Simulation status: PAUSED");
        }
    }

    /**
     * This method will setup values used as begin conditions for simulation
     * @param gridSize This parameter represents the size of the gridMap
     * @param debil_speedANDvision This parameter represents the speed and vision values of Debil entity
     * @param gimbus_speedANDvision This parameter represents the speed and vision values of Gimbus entity
     * @param licbus_speedANDvision This parameter represents the speed and vision values of Licbus entity
     * @param patus_speedANDvision This parameter represents the speed and vision values of Patus entity
     * @param podbus_speedANDvision This parameter represents the speed and vision values of Podbus entity
     * @param student_speedANDvision This parameter represents the speed and vision values of Student entity
     * @param debilInitAmount This parameter represents the initial amount of Debil entities
     * @param gimbusInitAmount This parameter represents the initial amount of Gimbus entities
     * @param licbusInitAmount This parameter represents the initial amount of Licbus entities
     * @param patusInitAmount This parameter represents the initial amount of Patus entities
     * @param podbusInitAmount This parameter represents the initial amount of Podbus entities
     * @param studentInitAmount This parameter represents the initial amount of Student entities
     * @param gimbazaInitAmount This parameter represents the initial amount of Gimbaza entities
     * @param licbazaInitAmount This parameter represents the initial amount of Licbaza entities
     * @param uczelniaInitAmount This parameter represents the initial amount of Uczelnia entities
     * @param piwoInitAmount This parameter represents the initial amount of Piwo entities
     * @param egzaminInitAmount This parameter represents the initial amount of Egzamin entities
     */
    public void SetupSimulationProperties(Vector2 gridSize, Vector2 debil_speedANDvision, Vector2 gimbus_speedANDvision, Vector2 licbus_speedANDvision, 
    Vector2 patus_speedANDvision, Vector2 podbus_speedANDvision, Vector2 student_speedANDvision, int debilInitAmount, int gimbusInitAmount, int licbusInitAmount, 
    int patusInitAmount, int podbusInitAmount, int studentInitAmount, int gimbazaInitAmount, int licbazaInitAmount, int uczelniaInitAmount, int piwoInitAmount, int egzaminInitAmount)
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
        Simulation.PiwoInitAmount = piwoInitAmount;
        Simulation.EgzaminInitAmount = egzaminInitAmount;
    }

    /**
     * This class is for handling multithreading
     */
    class UpdateThread implements Runnable
    {
        @Override

        //*Buffer for running Update in new thread
        public void run() {
            Update();
        }

        /**
         * This method is used to handle turns cycles
         */
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
                    GUI.UpdateGridGui(gridMap.GetGrid(), RoundCount);
                }

                numberOfEndCon = CheckEndConditions(!isRunning);
                if(numberOfEndCon != 0) 
                {
                    isRunning = false;
                    GUI.SetSimulationStatus("Simulation status: ENDED WITH CONDITION NUMBER " + numberOfEndCon);
                }

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

        /**
         * This method checks whether any of end conditions is met, if so returns it number, otherwise returns 0
         * @param isFirstCon This parameter is a bool returned by DoMove, used to identify condition number 1
         */
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
