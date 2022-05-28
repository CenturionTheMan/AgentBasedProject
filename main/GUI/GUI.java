package main.GUI;

import java.awt.*;
import javax.swing.*;

import main.Entity;
import main.Node;
import main.Simulation;
import main.Vector2;
import main.ActiveSubclass.*;
import main.GUI.CustomActionListener.ActionType;
import main.StaticSubclass.*;

/**
 * Class used for creating and handling GUI
 */
public class GUI {
    
    //VALUES
    private static JFrame frame;
    private static JPanel left;
    private static JPanel right;
    private static JLayeredPane gridHolder;
    private static JPanel gridBackground;

    private static JTextArea roundCounter; 
    private static JTextArea simStatus; 

    private static JPanel[][] nodes;

    private Simulation simulation;

    static final Color EMPTY = new Color(192,192,192);
    static final Color PODBUS = new Color(102,255,255);
    static final Color GIMBUS = new Color(138,79,226);
    static final Color PATUS = new Color(255,153,153);
    static final Color LICBUS = new Color(79,226,143);
    static final Color STUDENT= new Color(216,79,226);
    static final Color DEBIL= new Color(79,226,187);
    static final Color GIMBAZA= new Color(81,186,186);
    static final Color LICBAZA= new Color(79,48,127);
    static final Color UCZELNIA= new Color(56,156,99);
    static final Color PIWO= new Color(245,226,58);
    static final Color EGZAMIN= new Color(58,108,245);

    //GETTERS && SETTERS
    public static void SetSimulationStatus(String status) 
    {
        if(simStatus == null) return; 
        simStatus.setText(status); 
    }

    //CTOR
    public GUI(Simulation sim) {
        simulation = sim;
        InitGUI(sim.GetGridMap().GetGrid());
    }

    /**
     * Inits GUI
     * @param gridMap double array with nodes used for simulation
     */
    private void InitGUI(Node[][] gridMap)
    {
        frame = new JFrame();
        left = new JPanel();
        right = new JPanel();
        gridHolder = new JLayeredPane();

        //left.setBackground(Color.BLUE);
        left.setPreferredSize(new Dimension(400,700));
        left.setLayout(null);
        left.add(gridHolder);

        //right.setBackground(new Color(26,13,0));
        right.setPreferredSize(new Dimension(170,700));
        right.setSize(new Dimension(170,700));
        right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));

        gridHolder.setBounds(0,0,700,700);
        InicializeNodeGridGui(gridMap);

        CreateUserHandlingGuiPart();

        frame.add(left);
        frame.add(right);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
        frame.setTitle("Unit Based Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,740);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    //*Creates gui part for changing simulation input values
    private void CreateUserHandlingGuiPart()
    {
        Dimension d = new Dimension(70,30);
        Dimension activeEnt = new Dimension(50,30);
        
        //! ROW 1
        var line1 = new JPanel();
        line1.setLayout(new FlowLayout());

        var gridSizeLabel = new JLabel("Grid Size [width | height]:");
        gridSizeLabel.setFont(new Font("Serif",Font.BOLD,15));
        gridSizeLabel.setPreferredSize(new Dimension(210,30));

        var gridYField = new JTextField(Integer.toString(simulation.GetGridSize().x)); 
        gridYField.setPreferredSize(d);
        gridYField.addFocusListener(new CustomActionListener(ActionType.CHANGE_GRIDSIZE_X, simulation,gridYField));

        var gridXField = new JTextField(Integer.toString(simulation.GetGridSize().y)); 
        gridXField.setPreferredSize(d);
        gridXField.addFocusListener(new CustomActionListener(ActionType.CHANGE_GRIDSIZE_Y, simulation,gridXField));

        line1.add(gridSizeLabel);
        line1.add(gridXField);
        line1.add(gridYField);
        right.add(line1);

        //! ROW 
        right.add(SetupOneValuesChangerInterface(d, ActionType.CHANGE_GIMBAZA, "Gimbaza's amount", simulation.GetGimbazaInitAmount(),GIMBAZA));

        //! ROW 
        right.add(SetupOneValuesChangerInterface(d, ActionType.CHANGE_LICBAZA, "Licbaza's amount", simulation.GetLicbazaInitAmount(),LICBAZA));

        //! ROW 
        right.add(SetupOneValuesChangerInterface(d, ActionType.CHANGE_UCZELNIA, "Uczelnia's amount", simulation.GetUczelniaInitAmount(),UCZELNIA));

        //! ROW 
        right.add(SetupOneValuesChangerInterface(d, ActionType.CHANGE_PIWO, "Piwo's amount", simulation.GetPiwoInitAmount(),PIWO));

        //! ROW 
        right.add(SetupOneValuesChangerInterface(d, ActionType.CHANGE_EGZAMIN, "Egzamin's amount", simulation.GetEgzaminInitAmount(),EGZAMIN));

        //! ROW 
        right.add(SetupActiveEntityInterface(activeEnt, ActionType.CHANGE_PODBUS, "Podbus settings [amount | speed | vision]:", simulation.GetPodbusInitAmount(), Simulation.GetPodbus_speedANDvision(),PODBUS));
        //! ROW 
        right.add(SetupActiveEntityInterface(activeEnt, ActionType.CHANGE_GIMBUS, "Gimbus settings [amount | speed | vision]:", simulation.GetGimbusInitAmount(), Simulation.GetGimbus_speedANDvision(),GIMBUS));
        //! ROW 
        right.add(SetupActiveEntityInterface(activeEnt, ActionType.CHANGE_PATUS, "Patus settings [amount | speed | vision]:", simulation.GetPatusInitAmount(), Simulation.GetPatus_speedANDvision(),PATUS));
        //! ROW 
        right.add(SetupActiveEntityInterface(activeEnt, ActionType.CHANGE_LICBUS, "Licbus settings [amount | speed | vision]:", simulation.GetLicbusInitAmount(), Simulation.GetLicbus_speedANDvision(),LICBUS));
        //! ROW 
        right.add(SetupActiveEntityInterface(activeEnt, ActionType.CHANGE_STUDENT, "Student settings [amount | speed | vision]:", simulation.GetStudentInitAmount(), Simulation.GetStudent_speedANDvision(),STUDENT));
        //! ROW 
        right.add(SetupActiveEntityInterface(activeEnt, ActionType.CHANGE_DEBIL, "Debil settings [amount | speed | vision]:", simulation.GetDebilInitAmount(), Simulation.GetDebil_speedANDvision(),DEBIL));

        //! ROW 
        right.add(SetupOneValuesChangerInterface(d,new Dimension(250,30), ActionType.CHANGE_TIME_STEPS, "Time between simulation iterations", Simulation.GetTimeBetweenSteps()));

        //! ROW
        var statusLine = new JPanel();
        statusLine.setLayout(new FlowLayout());

        roundCounter = new JTextArea("Round number: 0");
        roundCounter.setPreferredSize(new Dimension(140,40));
        roundCounter.setBackground(null);
        roundCounter.setFont(new Font("Serif",Font.BOLD,14));

        simStatus = new JTextArea("Simulation status: INICIALIZED");
        simStatus.setPreferredSize(new Dimension(220,40));
        simStatus.setBackground(null);
        simStatus.setFont(new Font("Serif",Font.BOLD,14));
        simStatus.setLineWrap(true);
        simStatus.setWrapStyleWord(true);

        statusLine.add(roundCounter);
        statusLine.add(simStatus);
        right.add(statusLine);

        //! ROW 
        var line11 = new JPanel();
        line11.setLayout(new FlowLayout());

        var inicializeButton = new JButton("Inicialize Grid");
        inicializeButton.addActionListener(new CustomActionListener(ActionType.INICIALIZE_GRID, simulation));

        var pauseButton = new JButton("Pause Simulation");
        pauseButton.addActionListener(new CustomActionListener(ActionType.PAUSE_SIMULATION, simulation));

        var startButton = new JButton("Run Simulation");
        startButton.addActionListener(new CustomActionListener(ActionType.RUN_SIMULATION, simulation));

        line11.add(inicializeButton);
        line11.add(pauseButton);
        line11.add(startButton);

        right.add(line11);
    }


    //*Returns new JPanel with given color and size
    //Color col - color
    //Dimension size - size
    private JPanel CreateJPanelWithColor(Color col, Dimension size)
    {
        var temp = new JPanel();
        temp.setBackground(col);
        temp.setPreferredSize(size);
        return temp;
    }


    //*Sets gui row for changing settings of active entity
    //Dimension d - size of row elements
    //ActionType type - enum which indicates what acion to perform
    //String text - name of row
    //int amount - inicial amount of given active entity
    //Vector2 speedAndVision - inicial speed and vision range of given active entity
    //Color color - color of given entity on map, if null no color
    private JPanel SetupActiveEntityInterface(Dimension activeEnt, ActionType type, String text, int amount, Vector2 speedAndVision, Color color)
    {
        var line = new JPanel();
        line.setLayout(new FlowLayout());

        var label = new JLabel(text);
        label.setFont(new Font("Serif",Font.BOLD,13));
        label.setPreferredSize(new Dimension(250,30));

        var amountTF = new JTextField(Integer.toString(amount)); 
        amountTF.setPreferredSize(activeEnt);
        amountTF.addFocusListener(new CustomActionListener(type, simulation, amountTF,null,null));
        
        var speedTF = new JTextField(Integer.toString(speedAndVision.x)); 
        speedTF.setPreferredSize(activeEnt);
        speedTF.addFocusListener(new CustomActionListener(type, simulation, null,speedTF,null));

        var visionTF = new JTextField(Integer.toString(speedAndVision.y)); 
        visionTF.setPreferredSize(activeEnt);
        visionTF.addFocusListener(new CustomActionListener(type, simulation, null,null,visionTF));

        if(color != null)line.add(right.add(CreateJPanelWithColor(color, new Dimension(15,15))));
        line.add(label);
        line.add(amountTF);
        line.add(speedTF);
        line.add(visionTF);
        return line;
    }

    //*Sets gui row for changing settings of one property
    //Dimension inputFieldSize - size of input field
    //ActionType type - enum which indicates what acion to perform
    //String text - name of row
    //int amount - inicial amount of given property
    //Color color - color of given static entity on map, if null no color
    private JPanel SetupOneValuesChangerInterface(Dimension inputFieldSize, ActionType type, String text, int amount, Color color)
    {
        var line = new JPanel();
        line.setLayout(new FlowLayout());

        var label = new JLabel(text);
        label.setFont(new Font("Serif",Font.BOLD,15));
        label.setPreferredSize(new Dimension(210,30));

        var tF = new JTextField(Integer.toString(amount)); 
        tF.setPreferredSize(inputFieldSize);
        tF.addFocusListener(new CustomActionListener(type, simulation, tF,null,null));
        
        if(color != null)line.add(right.add(CreateJPanelWithColor(color, new Dimension(15,15))));
        line.add(label);
        line.add(tF);
        return line;
    }

    //*Sets gui row for changing settings of one property
    //Dimension inputFieldSize - size of input field
    //Dimension labelSize - size of label
    //ActionType type - enum which indicates what acion to perform
    //String text - name of row
    //int amount - inicial amount of given property
    private JPanel SetupOneValuesChangerInterface(Dimension inputFieldSize, Dimension labelSize, ActionType type, String text, int amount)
    {
        var line = new JPanel();
        line.setLayout(new FlowLayout());

        var label = new JLabel(text);
        label.setFont(new Font("Serif",Font.BOLD,15));
        label.setPreferredSize(labelSize);

        var tF = new JTextField(Integer.toString(amount)); 
        tF.setPreferredSize(inputFieldSize);
        tF.addFocusListener(new CustomActionListener(type, simulation, tF,null,null));
        
        line.add(label);
        line.add(tF);
        return line;
    }



    //*Iniciates gui part which handle showing grid
    //Node[][] gridMap - double array with nodes used for simulation
    public static void InicializeNodeGridGui(Node[][] gridMap)
    {
        if(gridHolder == null) return;

        if(gridBackground != null)
        {
            gridHolder.removeAll();
        }

        nodes = new JPanel[gridMap.length][gridMap[0].length];

        gridBackground = new JPanel(new GridLayout(gridMap.length,gridMap[0].length,1,1));
        gridBackground.setBounds(1,1,698,698);
        gridBackground.setSize(700,700);

        gridHolder.add(gridBackground,JLayeredPane.DEFAULT_LAYER);

        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[i].length; j++) {
                var panel = new JPanel();
                panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                nodes[i][j] = panel;
                gridBackground.add(panel);

                Entity e = gridMap[i][j].GetOccupant();        
                SetPanelByUnit(panel, e);
            }
        }

        gridHolder.repaint();
        gridHolder.revalidate();
    }

    //*Updates grid in gui
    //Node[][] gridMap - double array with nodes used for simulation
    public static void UpdateGridGui(Node[][] gridMap, int roundNumber)
    {
 

        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[i].length; j++) {
                
                SetPanelByUnit(nodes[i][j], gridMap[i][j].GetOccupant());
            }
        }

        SetRoundCounter(roundNumber);

        // JPanel temp = new JPanel();
        // temp.setBackground(Color.ORANGE);
        // temp.setLocation(nodes[1][1].getLocation());
        // temp.setSize(nodes[1][1].getSize());
        // gridHolder.add(temp,JLayeredPane.DRAG_LAYER);

        gridHolder.repaint();
        gridHolder.revalidate();
    }

    //*Chages color of panel judging by entity which occupies such node
    //JPanel panel - gui's node in grid
    //Entity e - entity which based on color is picked
    private static void SetPanelByUnit(JPanel panel, Entity e)
    {
        panel.removeAll();
        if(e == null)
        {
            panel.setBackground(EMPTY);
        }
        else if(e instanceof Debil)
        {
            panel.setBackground(DEBIL);
        }
        else if(e instanceof Gimbus)
        {
            panel.setBackground(GIMBUS);
        }
        else if(e instanceof Licbus)
        {
            panel.setBackground(LICBUS);
        }
        else if(e instanceof Patus)
        {
            panel.setBackground(PATUS);
        }
        else if(e instanceof Podbus)
        {
            panel.setBackground(PODBUS);
        }
        else if(e instanceof Student)
        {
            panel.setBackground(STUDENT);
        }
        else if(e instanceof Egzamin)
        {
            panel.setBackground(EGZAMIN);
            //anel.add(new JCircle());
        }
        else if(e instanceof Gimbaza)
        {
            panel.setBackground(GIMBAZA);
            //panel.add(new JCircle());
        }
        else if(e instanceof Licbaza)
        {
            panel.setBackground(LICBAZA);
            //panel.add(new JCircle());
        }
        else if(e instanceof Piwo)
        {
            panel.setBackground(PIWO);
            //panel = new JCircle();
        }
        else if(e instanceof Uczelnia)
        {
            panel.setBackground(UCZELNIA);
            //panel.getGraphics().fillOval(0, 0, 200, 200);
        }

        panel.repaint();
        panel.revalidate();
    }

    //*Updates round number on GUI
    private static void SetRoundCounter(int roundNumber) { roundCounter.setText("Round number: " + Integer.toString(roundNumber)); }

//===========================================================================================CONSOLE PRINTING

    //*Prints grid in console and put thred to sleep for given amount
    //Node[][] grid - double array with nodes used for simulation
    //int timeBetweenSteps - idicates how long should thred sleep after printing grid
    public static void PrintGridInConsole(Node[][] gridMap, int timeBetweenSteps)
    {
        GUI.GridCreatorForConsole(gridMap);
        try { Thread.sleep(timeBetweenSteps); } 
        catch (InterruptedException e) { e.printStackTrace(); }
    }
    
    //*Prints grid console
    //Node[][] grid - double array with nodes used for simulation
    private static void GridCreatorForConsole(Node[][] grid)
    {
        for (int i = 0; i < grid[0].length; i++) {
            System.out.print("===");
        }
        System.out.print("\n");
        for (int i = 0; i <grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                
                Entity temp = grid[i][j].GetOccupant();

                if(temp == null)
                {
                    System.out.print(" --");
                }
                else if(temp instanceof Debil)
                {
                    System.out.print(" de");
                }
                else if(temp instanceof Gimbus)
                {
                    System.out.print(" gi");
                }
                else if(temp instanceof Licbus)
                {
                    System.out.print(" li");
                }
                else if(temp instanceof Patus)
                {
                    System.out.print(" pa");
                }
                else if(temp instanceof Podbus)
                {
                    System.out.print(" po");
                }
                else if(temp instanceof Student)
                {
                    System.out.print(" st");
                }
                else if(temp instanceof Egzamin)
                {
                    System.out.print(" EG");
                }
                else if(temp instanceof Gimbaza)
                {
                    System.out.print(" GI");
                }
                else if(temp instanceof Licbaza)
                {
                    System.out.print(" LI");
                }
                else if(temp instanceof Piwo)
                {
                    System.out.print(" PI");
                }
                else
                {
                    System.out.print(" UC");
                }
            }
            System.out.print("\n");
        }

        for (int i = 0; i < grid[0].length; i++) {
            System.out.print("===");
        }
        System.out.print("\n\n");
    }
}
