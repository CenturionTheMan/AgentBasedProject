package main.GUI;


import javax.swing.*;

import main.Simulation;
import main.Vector2;


import java.awt.event.*;

public class CustomActionListener implements ActionListener, FocusListener {

    public static enum ActionType
    {
        CHANGE_GRIDSIZE_X,
        CHANGE_GRIDSIZE_Y,
        INICIALIZE_GRID,
        RUN_SIMULATION,
        PAUSE_SIMULATION,
        
        CHANGE_DEBIL,
        CHANGE_GIMBUS,
        CHANGE_LICBUS,
        CHANGE_PATUS,
        CHANGE_PODBUS,
        CHANGE_STUDENT,
        CHANGE_GIMBAZA,
        CHANGE_LICBAZA,
        CHANGE_UCZELNIA,
    }

    ActionType actionType;
    Simulation sim;
    JTextField input = null;

    JTextField amount;
    JTextField speed;
    JTextField visionRange;

    public CustomActionListener(ActionType type, Simulation simulation, JTextField input) {
        actionType = type;
        this.sim = simulation;
        this.input = input;
    }

    public CustomActionListener(ActionType type, Simulation simulation, JTextField amount, JTextField speed, JTextField visionRange)
    {
        actionType = type;
        this.sim = simulation;
        this.amount = amount;
        this.speed = speed;
        this.visionRange = visionRange;
    }

    public CustomActionListener(ActionType type, Simulation simulation) {
        actionType = type;
        this.sim = simulation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (actionType) {         
            case INICIALIZE_GRID:
                sim.InitSimulation();
                break;

            case RUN_SIMULATION:
                sim.RunSimulation();
                break;

            case PAUSE_SIMULATION:
                sim.PauseSimulation();
                break;
            default:
                break;
        }

        //System.out.println("WORKING!");
    }


    @Override
    public void focusGained(FocusEvent e) {  
        //System.out.println("focus gain");     
    }

    @Override
    public void focusLost(FocusEvent e) {
        int gridOneDImensionSize = (input != null)? GetIntFormJTextField(input) :0;
        
        int eAmount = (amount != null)? GetIntFormJTextField(amount) : -1;
        int eSpeed = (speed != null)? GetIntFormJTextField(speed) : -1;
        int eVision = (visionRange != null)? GetIntFormJTextField(visionRange) : -1;



        switch (actionType) 
        {
            case CHANGE_GRIDSIZE_Y:
            sim.SetGridSize(new Vector2(sim.GetGridSize().x, gridOneDImensionSize));
            break;

            case CHANGE_GRIDSIZE_X:
            sim.SetGridSize(new Vector2(gridOneDImensionSize,sim.GetGridSize().y));
            break;

            case CHANGE_DEBIL:
            if(eAmount != -1)sim.SetDebilInitAmount(eAmount);
            if(eSpeed != -1) sim.SetDebil_speedANDvision(new Vector2(eSpeed, Simulation.GetDebil_speedANDvision().y));
            if(eVision != -1) sim.SetDebil_speedANDvision(new Vector2(Simulation.GetDebil_speedANDvision().x,eVision));
            break;

            case CHANGE_GIMBUS:
            if(eAmount != -1)sim.SetGimbusInitAmount(eAmount);
            if(eSpeed != -1) sim.SetGimbus_speedANDvision(new Vector2(eSpeed, Simulation.GetGimbus_speedANDvision().y));
            if(eVision != -1) sim.SetGimbus_speedANDvision(new Vector2(Simulation.GetGimbus_speedANDvision().x,eVision));
            break;

            case CHANGE_LICBUS:
            if(eAmount != -1)sim.SetLicbusInitAmount(eAmount);
            if(eSpeed != -1) sim.SetLicbus_speedANDvision(new Vector2(eSpeed, Simulation.GetLicbus_speedANDvision().y));
            if(eVision != -1) sim.SetLicbus_speedANDvision(new Vector2(Simulation.GetLicbus_speedANDvision().x,eVision));
            break;

            case CHANGE_PATUS:
            if(eAmount != -1)sim.SetPatusInitAmount(eAmount);
            if(eSpeed != -1) sim.SetPatus_speedANDvision(new Vector2(eSpeed, Simulation.GetPatus_speedANDvision().y));
            if(eVision != -1) sim.SetPatus_speedANDvision(new Vector2(Simulation.GetPatus_speedANDvision().x,eVision));
            break;

            case CHANGE_PODBUS:
            if(eAmount != -1)sim.SetPodbusInitAmount(eAmount);
            if(eSpeed != -1) sim.SetPodbus_speedANDvision(new Vector2(eSpeed, Simulation.GetPodbus_speedANDvision().y));
            if(eVision != -1) sim.SetPodbus_speedANDvision(new Vector2(Simulation.GetPodbus_speedANDvision().x,eVision));
            break;

            case CHANGE_STUDENT:
            if(eAmount != -1)sim.SetStudentInitAmount(eAmount);
            if(eSpeed != -1) sim.SetStudent_speedANDvision(new Vector2(eSpeed, Simulation.GetStudent_speedANDvision().y));
            if(eVision != -1) sim.SetStudent_speedANDvision(new Vector2(Simulation.GetStudent_speedANDvision().x,eVision));
            break;

            case CHANGE_GIMBAZA:
            if(eAmount != -1)sim.SetGimbazaInitAmount(eAmount);
            break;

            case CHANGE_LICBAZA:
            if(eAmount != -1)sim.SetLicbazaInitAmount(eAmount);
            break;

            case CHANGE_UCZELNIA:
            if(eAmount != -1)sim.SetUczelniaInitAmount(eAmount);
            break;

            default:
                break;
        }

        //System.out.println("focus lost");
    }

    private int GetIntFormJTextField(JTextField t)
    {
        try {
            return Integer.parseInt(t.getText());
        } catch (Exception exc) {
            System.err.println("PARSING ERR");
            return 0;
        }
    }
    
    
}
