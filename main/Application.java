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

    static void InitApp()
    {
        gui = new GUI(); //GUI

        simulation = new Simulation();

        OnRunSimulation(); //TEMP
    }

    public static void OnRunSimulation()
    {
        simulation.RunSimulation();
    }
}