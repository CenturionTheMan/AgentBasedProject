package main;

import main.GUI.GUI;

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
        simulation = new Simulation();

        simulation.InitSimulation();

        gui = new GUI(simulation.GetGridMap().GetGrid()); //GUI 
        
        simulation.RunSimulation();
    }


    //*Hub for running application from gui site
    public static void OnRunSimulation()
    {
        simulation.InitSimulation();
        simulation.RunSimulation();
    }
}