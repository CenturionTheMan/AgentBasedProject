package main.DataGathering;

import java.io.FileWriter;
import java.io.IOException;

import main.Simulation;
import main.Vector2;

/**
 * This is a raw class designed for generating data for statistics
 */
public class DataGathering {
    
    Simulation sim;
    boolean doStatiscitcGathering;

    /**
     * This method is used to gather generated data
     * @param sim This parameter is used to execute commands regarding simulation
     * @param doStatiscticGathering This parameter sets a boolean value for gathering of the statistics. True allows it.
     */
    public DataGathering(Simulation sim, boolean doStatiscticGathering) {
        this.sim = sim;
        this.doStatiscitcGathering = doStatiscticGathering;

        if(doStatiscticGathering)
        {
            PodbusScenario();
        }
    }

    /**
     * This method generates data for scenario with growing Podbus amount
     */
    public void PodbusScenario()
    {
        Simulation.SetIsPrintingGrid(false);
        String result = "gridSize.x,gridSize.y,numberOfEndCondition,numberOfRounds,initialNumberOfGimbaza,initialNumberOfLicbaza,initialNumberOfUczelnia,initialNumberOfPiwo,initialNumberOfEgzamin,initialNumberOfPodbus,initialNumberOfGimbus,initialNumberOfPatus,initialNumberOfLicbus,initialNumberStudent,initialNumberOfDebil,finNumberOfPodbus,finNumberOfGimbus,finNumberOfPatus,finNumberOfLicbus,finNumberStudent,finNumberOfDebil,finNumberOfPiwo,finNumberOfEgzamin\n";
        int repeatAmount = 50;

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
        
        WriteToFile("main\\DataGathering\\PudbusTest.txt", result);

        Simulation.SetIsPrintingGrid(true);
    }

    /**
     * This method runs test with given initial values and returns its formatted as string results
     * @param gridSize This parameter indicates the size of the grid
     * @param debil_speedANDvision This parameter sets the speed and vision amount for Debil entities
     * @param gimbus_speedANDvision This parameter sets the speed and vision amount for Gimbus entities
     * @param licbus_speedANDvision This parameter sets the speed and vision amount for Licbus entities
     * @param patus_speedANDvision This parameter sets the speed and vision amount for Patus entities
     * @param podbus_speedANDvision This parameter sets the speed and vision amount for Podbus entities
     * @param student_speedANDvision This parameter sets the speed and vision amount for Student entities
     * @param debilInitAmount This parameter sets the initial amount of Debil entities
     * @param gimbusInitAmount This parameter sets the initial amount of Gimbus entities
     * @param licbusInitAmount This parameter sets the initial amount of Licbus entities
     * @param patusInitAmount This parameter sets the initial amount of Patus entities
     * @param podbusInitAmount This parameter sets the initial amount of Podbus entities
     * @param studentInitAmount This parameter sets the initial amount of Student entities
     * @param gimbazaInitAmount This parameter sets the initial amount of Gimbaza entities
     * @param licbazaInitAmount This parameter sets the initial amount of Licbaza entities
     * @param uczelniaInitAmount This parameter sets the initial amount of Uczelnia entities
     * @param piwoInitAmount This parameter sets the initial amount of Piwo entities
     * @param egzaminInitAmount This parameter sets the initial amount of Egzamin entities
     */
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

    /**
     * This method writes string to file with given path
     * @param path This parameter indicates path where file will be written
     * @param text This parameter indicates text to be written in file
     */
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
