package main;

import main.DataGathering.DataGathering;
import main.GUI.GUI;

/**
 * Application
 */
public class Application {

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

        gui = new GUI(simulation.GetGridMap().GetGrid()); //GUI 
        
        dataGahtering = new DataGathering(simulation);

        //simulation.RunSimulation();
        //simulation.RunSimulationWithoutNewThred();
    }


    //*Hub for running application from gui site
    public static void OnRunSimulation()
    {
        simulation.InitSimulation();
        simulation.RunSimulation();
    }
}