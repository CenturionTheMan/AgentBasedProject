package main;


import main.DataGathering.DataGathering;
import main.GUI.GUI;

/**
 * Application
 */
public class Application {

    static final boolean doStatiscicGathering = false;

    static Simulation simulation;
    static GUI gui;
    static DataGathering dataGahtering;
    public static void main(String[] args) {
        InitApp();
    }


    //*Inits application
    static void InitApp()
    {
        simulation = new Simulation();
        simulation.InitSimulation();

        gui = new GUI(simulation); //GUI 
        
        dataGahtering = new DataGathering(simulation, doStatiscicGathering);
    }
}