package main;

import java.util.Random;

import javax.lang.model.util.ElementScanner14;

import main.ActiveSubclass.*;
import main.StaticSubclass.*;

public class Simulation {
    
    //VALUES
    private Vector2 gridSize;
    
    private GridMap gridMap;

    //setup pod grida
    private Vector2 Debil_speedANDvision;
    private Vector2 Gimbus_speedANDvision;
    private Vector2 Licbus_speedANDvision;
    private Vector2 Patus_speedANDvision;
    private Vector2 Podbus_speedANDvision;
    private Vector2 Student_speedANDvision;

    private int DebilInitAmount = 0;
    private int GimbusInitAmount = 3;
    private int LicbusInitAmount = 2;
    private int PatusInitAmount = 0;
    private int PodbusInitAmount = 5;
    private int StudentInitAmount = 0;

    private int GimbazaInitAmount = 4;
    private int LicbazaInitAmount = 3;
    private int PodbazaInitAmount = 5;
    private int UczelniaInitAmount = 2;

    private boolean isRunning = false;


    //SETTERS && GETTERS
    public void SetGridSize(Vector2 size) { gridSize = size; }

    public void SetDebil_speedANDvision(Vector2 speedANDvision) { Debil_speedANDvision = speedANDvision; }
    public void SetGimbus_speedANDvision(Vector2 speedANDvision) { Gimbus_speedANDvision = speedANDvision; }
    public void SetLicbus_speedANDvision(Vector2 speedANDvision) { Licbus_speedANDvision = speedANDvision; }
    public void SetPatus_speedANDvision(Vector2 speedANDvision) { Patus_speedANDvision = speedANDvision; }
    public void SetPodbus_speedANDvision(Vector2 speedANDvision) { Podbus_speedANDvision = speedANDvision; }
    public void SetStudent_speedANDvision(Vector2 speedANDvision) { Student_speedANDvision = speedANDvision; }

    public void SetDebilInitAmount(int amount) { DebilInitAmount = amount; }
    public void SetGimbusInitAmount(int amount) { GimbusInitAmount = amount; }
    public void SetLicbusInitAmount(int amount) { LicbusInitAmount = amount; }
    public void SetPatusInitAmount(int amount) { PatusInitAmount = amount; }
    public void SetPodbusInitAmount(int amount) { PodbusInitAmount = amount; }
    public void SetStudentInitAmount(int amount) { StudentInitAmount = amount; }

    public void SetGimbazaInitAmount(int amount) { GimbazaInitAmount = amount; }
    public void SetLicbazaInitAmount(int amount) { LicbazaInitAmount = amount; }
    public void SetPodbazaInitAmount(int amount) { PodbazaInitAmount = amount; }
    public void SetUczelniaInitAmount(int amount) { UczelniaInitAmount = amount; }

    public int GetAllUnitsInitAmount()
    {
        return DebilInitAmount + GimbusInitAmount + LicbazaInitAmount + PatusInitAmount + PodbusInitAmount + StudentInitAmount + GimbazaInitAmount + LicbazaInitAmount + PodbazaInitAmount + UczelniaInitAmount;
    }

    public boolean IsRunning() { return isRunning; }



    //CTOR
    public Simulation() {

        //BASE VALUES (ON APP RUN)
        SetGridSize(new Vector2(10, 15));
        
        SetDebil_speedANDvision(new Vector2(1, 1));
        SetGimbus_speedANDvision(new Vector2(1, 1));
        SetLicbus_speedANDvision(new Vector2(1, 1));
        SetPatus_speedANDvision(new Vector2(1, 1));
        SetPodbus_speedANDvision(new Vector2(1, 1));
        SetStudent_speedANDvision(new Vector2(1, 1));


        gridMap = new GridMap(gridSize);
    }



    //Methods
    public void RunSimulation()
    {
        if(isRunning)
        {
            //end previus simulation and create new one
        }

        //Set nodes in grid
        gridMap.InitGrid(gridSize);


        //Puts units on map
        gridMap.SetUnitsOnMap(DebilInitAmount, new Debil(null,Debil_speedANDvision,null),gridSize);
        gridMap.SetUnitsOnMap(GimbusInitAmount, new Gimbus(null,Gimbus_speedANDvision,null),gridSize);
        gridMap.SetUnitsOnMap(LicbusInitAmount, new Licbus(null,Licbus_speedANDvision,null),gridSize);
        gridMap.SetUnitsOnMap(PatusInitAmount, new Patus(null,Patus_speedANDvision,null),gridSize);
        gridMap.SetUnitsOnMap(PodbusInitAmount, new Podbus(null,Podbus_speedANDvision,null),gridSize);
        gridMap.SetUnitsOnMap(StudentInitAmount, new Student(null,Student_speedANDvision,null),gridSize);
        
        gridMap.SetUnitsOnMap(GimbazaInitAmount, new Gimbaza(null),gridSize);
        gridMap.SetUnitsOnMap(LicbazaInitAmount, new Licbaza(null),gridSize);
        gridMap.SetUnitsOnMap(PodbazaInitAmount, new Podbaza(null),gridSize);
        gridMap.SetUnitsOnMap(UczelniaInitAmount, new Uczelnia(null),gridSize);
        //


        //print fin grid
        GUI.PrintGrid(gridMap.GetGrid());



        isRunning = true;
        Update(); //Run Update in new thread (prob)
    }


    private void Update()
    {
        int tempRoundCount = 2;

        while(/*isRunning*/tempRoundCount >0)
        {
            tempRoundCount--;


            for (int i = 0; i < gridMap.GetGrid().length; i++) {
                for (int j = 0; j < gridMap.GetGrid()[i].length; j++) {

                    Entity current = gridMap.GetGrid()[i][j].GetOccupant(); //get entity

                    if(current == null) continue; //if none cont

                    if(current instanceof Static_Entity) //if static 
                    {

                    }

                    if(current instanceof Active_Entity) //if active
                    {
                        Active_Entity active = (Active_Entity)current; //casting
                        
                        if(active.DoMove(gridMap.GetGrid())) //do move
                        {
                            isRunning = false; //end if cond met
                        }
                    }


                    //GUI UPDATE
                }
            }

            GUI.PrintGrid(gridMap.GetGrid());

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
    }
}
