package main.GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;

import main.Entity;
import main.Node;
import main.Vector2;
import main.ActiveSubclass.*;
import main.StaticSubclass.*;


public class GUI {
    
    //VALUES
    private static JFrame frame;
    private static JPanel left;
    private static JPanel right;
    private static JLayeredPane gridHolder;
    private static JPanel gridBackground;

    private static JPanel[][] nodes;


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

    //CTOR
    public GUI(Node[][] gridMap) {
        InitGUI(gridMap);
    }


    private void InitGUI(Node[][] gridMap)
    {
        frame = new JFrame();
        left = new JPanel();
        right = new JPanel();
        gridHolder = new JLayeredPane();

        //left.setBackground(Color.BLUE);
        left.setBounds(0,0,700,700);
        left.setLayout(null);
        left.add(gridHolder);

        //right.setBackground(Color.RED);
        right.setSize(400,700);

        gridHolder.setBounds(0,0,700,700);

        InicializeNodeGridGui(gridMap);

        frame.add(left);
        frame.add(right);

        frame.setTitle("Unit Based Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100,740);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public void InicializeNodeGridGui(Node[][] gridMap)
    {
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

    public static void UpdateGridGui(Node[][] gridMap)
    {
 

        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[i].length; j++) {
                
                SetPanelByUnit(nodes[i][j], gridMap[i][j].GetOccupant());
                System.out.println(nodes[i][j].getLocation().x + " " + nodes[i][j].getLocation().y);
            }
        }

        // JPanel temp = new JPanel();
        // temp.setBackground(Color.ORANGE);
        // temp.setLocation(nodes[1][1].getLocation());
        // temp.setSize(nodes[1][1].getSize());
        // gridHolder.add(temp,JLayeredPane.DRAG_LAYER);

        gridHolder.repaint();
        gridHolder.revalidate();
    }

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
