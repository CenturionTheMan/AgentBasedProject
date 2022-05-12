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

        Simulation sim = new Simulation(/*setup values*/);
        SetupBeginCondiotions(sim);
    }

    //TEMP FUNCION (most likely will be replaced by GUI handling)
    static void SetupBeginCondiotions(Simulation sim)
    {
        //setup values
        
    }
}