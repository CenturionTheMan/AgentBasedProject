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
 * This class is used for creating and handling GUI
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

    static final Color EMPTY = new Color(224,224,224);
    static final Color PODBUS = new Color(147,255,0);
    static final Color GIMBUS = new Color(35,243,254);
    static final Color PATUS = new Color(255,0,0);
    static final Color LICBUS = new Color(248,0,255);
    static final Color STUDENT= new Color(128,24,230);
    static final Color DEBIL= new Color(255,94,153);
    static final Color GIMBAZA= new Color(64,193,153);
    static final Color LICBAZA= new Color(77,136,172);
    static final Color UCZELNIA= new Color(211,112,214);
    static final Color PIWO= new Color(245,226,58);
    static final Color EGZAMIN= new Color(242,127,13);

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
     * This method is used to initialise GUI
     * @param gridMap This parameter represents a double array with nodes used for simulation
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
        InicializeNodeGridGui(gridMap, 0);

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

    /**
     * This method creates GUI part for changing simulation input values
     */
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

    /**
     * This method returns new JPanel with given colour and size
     * @param col This parameter represents the colour
     * @param size This parameter represents the size
     */
    private JPanel CreateJPanelWithColor(Color col, Dimension size)
    {
        var temp = new JPanel();
        temp.setBackground(col);
        temp.setPreferredSize(size);
        return temp;
    }

    /**
     * This method sets a GUI row for changing settings of active entity
     * @param activeEnt This parameter represents size of row elements
     * @param type This parameter enumerates which indicates what action to perform
     * @param text This parameter represents name of a row
     * @param amount This parameter represents the initial amount of given active entity
     * @param speedAndVision This parameter represents the initial speed and vision range of given active entity
     * @param color This parameter represents color of given entity on map, if null - no color
     */
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

    /**
     * This method sets a GUI row for changing settings of one property
     * @param inputFieldSize This parameter represents the size of input field
     * @param type This parameter enumerates which indicates what action to perform
     * @param text This parameter represents name of a row
     * @param amount This parameter represents the initial amount of given active entity
     * @param color This parameter represents color of given entity on map, if null - no color
     */
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

    /**
     * This method sets a GUI row for changing settings of one property
     * @param inputFieldSize This parameter represents the size of input field
     * @param labelSize This parameter represents the size of a label
     * @param type This parameter enumerates which indicates what action to perform
     * @param text This parameter represents name of a row
     * @param amount This parameter represents the initial amount of given active entity
     */
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

    /**
     * This method initiates GUI part which handles showing the grid
     * @param gridMap This parameter represents a double array with nodes used for simulation
     * @param roundNumber This parameter represents a round number
     */
    public static void InicializeNodeGridGui(Node[][] gridMap, int roundNumber)
    {
        if(gridHolder == null) return;

        if(gridBackground != null)
        {
            gridHolder.removeAll();
        }

        if(roundCounter != null)
        {
            SetRoundCounter(roundNumber);
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

    /**
     * This method updates grid in GUI
     * @param gridMap This parameter represents a double array with nodes used for simulation
     * @param roundNumber This parameter represents a round number
     */
    public static void UpdateGridGui(Node[][] gridMap, int roundNumber)
    {
 

        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[i].length; j++) {
                
                SetPanelByUnit(nodes[i][j], gridMap[i][j].GetOccupant());
            }
        }

        SetRoundCounter(roundNumber);

        gridHolder.repaint();
        gridHolder.revalidate();
    }

    /**
     * This method changes colour of the panel, judging by an entity which occupies such node
     * @param panel This parameter represents a GUI's node in grid
     * @param e This parameter represents an entity which based on color is picked
     */
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

    /**
     * This method updates round number on GUI
     * @param roundNumber This parameter represents a round number
     */
    private static void SetRoundCounter(int roundNumber) { roundCounter.setText("Round number: " + Integer.toString(roundNumber)); }

//===========================================================================================CONSOLE PRINTING

    /**
     * This method prints grid in console and put thread to sleep for given amount
     * @param gridMap This parameter represents a double array with nodes used for simulation
     * @param timeBetweenSteps This parameter indicates how long should a thread sleep after printing grid
     */
    public static void PrintGridInConsole(Node[][] gridMap, int timeBetweenSteps)
    {
        GUI.GridCreatorForConsole(gridMap);
        try { Thread.sleep(timeBetweenSteps); } 
        catch (InterruptedException e) { e.printStackTrace(); }
    }

    /**
     * This method prints a grid console
     * @param grid This parameter represents a double array with nodes used for simulation
     */
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
