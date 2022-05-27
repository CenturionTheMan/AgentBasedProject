package main.DataGathering;

import java.io.FileWriter;
import java.io.IOException;

import main.Simulation;
import main.Vector2;

//*Raw class for generationg data for statistics
//*
public class DataGathering {
    
    Simulation sim;
    
    public DataGathering(Simulation sim) {
        this.sim = sim;

        //PodbusScenario();
    }

    //*Generates data for scenario with growing podbus amount
    public void PodbusScenario()
    {
        Simulation.SetIsPrintingGrid(false);
        String result = "gridSize.x,gridSize.y,numberOfEndCondition,numberOfRounds,inicialNumberOfGimbaza,inicialNumberOfLicbaza,inicialNumberOfUczelnia,inicialNumberOfPiwo,inicialNumberOfEgzamin,inicialNumberOfPodbus,inicialNumberOfGimbus,inicialNumberOfPatus,inicialNumberOfLicbus,inicialNumberStudent,inicialNumberOfDebil,finNumberOfPodbus,finNumberOfGimbus,finNumberOfPatus,finNumberOfLicbus,finNumberStudent,finNumberOfDebil,finNumberOfPiwo,finNumberOfEgzamin";
        int repeatAmount = 10;

        for(int i=0;i<repeatAmount;i++){
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 1, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 10, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){ 
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 50, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){    
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 100, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 200, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){ 
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 300, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 400, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 500, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 600, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 700, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 800, 0, 3, 2, 1,0,0);
        }
        for(int i=0;i<repeatAmount;i++){ 
            result += RunTest(new Vector2(100, 100), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 2), new Vector2(1, 1), new Vector2(1, 4), new Vector2(1, 2), 0, 0, 0, 0, 900, 0, 3, 2, 1,0,0);
        }
        
        WriteToFile("main\\DataGathering\\PudbusTest", result);

        Simulation.SetIsPrintingGrid(true);
    }

    //*Runs test with given iniciaol values and returns its formated as string results
    private String RunTest(Vector2 gridSize, Vector2 debil_speedANDvision, Vector2 gimbus_speedANDvision, Vector2 licbus_speedANDvision, 
    Vector2 patus_speedANDvision, Vector2 podbus_speedANDvision, Vector2 student_speedANDvision, int debilInitAmount, int gimbusInitAmount, int licbusInitAmount, 
    int patusInitAmount, int podbusInitAmount, int studentInitAmount, int gimbazaInitAmount, int licbazaInitAmount, int uczelniaInitAmount, int piwoInitAmount, int egzaminInitAmount)
    {
        sim.SetupSimulationProperties(gridSize,debil_speedANDvision,gimbus_speedANDvision,licbus_speedANDvision,
        patus_speedANDvision,podbus_speedANDvision,student_speedANDvision,debilInitAmount,gimbusInitAmount,licbusInitAmount,
        patusInitAmount,podbusInitAmount,studentInitAmount,gimbazaInitAmount,licbazaInitAmount,uczelniaInitAmount,piwoInitAmount,egzaminInitAmount);
        sim.InitSimulation();
        sim.RunSimulationWithoutNewThred();
        return sim.GetResult().toString() +"\n";
    }

    //*Writes string to file with given path
    //String path - path where file will be writen
    //String text - text to be written in file
    private void WriteToFile(String path, String text)
    {
        try {
        FileWriter myWriter = new FileWriter(path);
        myWriter.write(text);
        myWriter.close();
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
}
