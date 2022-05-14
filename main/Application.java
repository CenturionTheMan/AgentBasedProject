package main;


/**
 * Application
 */
public class Application {

    static Simulation simulation;

    public static void main(String[] args) {
        InitApp();
    }

    static void InitApp()
    {
        //GUI


        simulation = new Simulation();
        //TEMP
        OnRunSimulation();
    }

    public static void OnRunSimulation()
    {
        simulation.RunSimulation();
    }
}