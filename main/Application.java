package main;


/**
 * Application
 */
public class Application {

    static Simulation simulation;
    static GUI gui;
    public static void main(String[] args) {
        InitApp();
    }


    //*Inits application
    static void InitApp()
    {
        gui = new GUI(); //GUI

        simulation = new Simulation();

        OnRunSimulation(); //TEMP
    }


    //*Hub for running application from gui site
    public static void OnRunSimulation()
    {
        simulation.RunSimulation();
    }
}