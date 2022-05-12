package main;


/**
 * Application
 */
public class Application {

    public static void main(String[] args) {
        InitApp();
    }

    static void InitApp()
    {
        //GUI
        SetupBeginCondiotions();
    }

    //TEMP FUNCION (most likely will be replaced by GUI handling)
    static void SetupBeginCondiotions()
    {
        //setup values
        Simulation sim = new Simulation(/*setup values*/);
    }
}