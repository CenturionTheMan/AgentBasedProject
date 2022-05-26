package main.DataGathering;

import main.Vector2;

public class SimulationResult {
    
    /* 
    *Class used for gathering data after simulation
    */

    public int numberOfEndCondition;
    public int numberOfRounds;

    public Vector2 gridSize;
    
    public int inicialNumberOfDebil;
    public int inicialNumberOfGimbus;
    public int inicialNumberOfLicbus;
    public int inicialNumberOfPatus;
    public int inicialNumberOfPodbus;
    public int inicialNumberStudent;
    public int inicialNumberOfGimbaza;
    public int inicialNumberOfLicbaza;
    public int inicialNumberOfUczelnia;

    public int finNumberOfDebil;
    public int finNumberOfGimbus;
    public int finNumberOfLicbus;
    public int finNumberOfPatus;
    public int finNumberOfPodbus;
    public int finNumberStudent;
    public int finNumberOfPiwo;
    public int finNumberOfEgzamin;

    public String GetSummarize()
    {
        return gridSize.x+ "," +gridSize.y+ "," + numberOfEndCondition + "," + numberOfRounds + "," + inicialNumberOfGimbaza + "," + inicialNumberOfLicbaza + "," + inicialNumberOfUczelnia + "," +  inicialNumberOfPodbus 
        + "," + inicialNumberOfGimbus + "," + inicialNumberOfPatus  + "," + inicialNumberOfLicbus + "," + inicialNumberStudent + "," + inicialNumberOfDebil + "," + finNumberOfPodbus 
        + "," + finNumberOfGimbus + "," + finNumberOfPatus + "," + finNumberOfLicbus + "," + finNumberStudent + "," + finNumberOfDebil + "," + finNumberOfPiwo + "," + finNumberOfEgzamin; 
    }
}
