package main;


import main.DataGathering.DataGathering;
import main.GUI.GUI;

/**
 * This class represents the application itself
 */
public class Application {

    static final boolean doStatiscicGathering = false;

    static Simulation simulation;
    static GUI gui;
    static DataGathering dataGahtering;

    public static void main(String[] args) {
        InitApp();
    }

    /**
     * This method is used to initialise the application
     */
    static void InitApp()
    {
        simulation = new Simulation();
        simulation.InitSimulation();

        gui = new GUI(simulation); //GUI 
        
        dataGahtering = new DataGathering(simulation, doStatiscicGathering);
    }
}