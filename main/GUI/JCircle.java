package main.GUI;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class JCircle extends JPanel {
    
    public void paint(Graphics g){
        g.drawOval(0, 0, 150, 150); 
        g.fillOval(0, 0, 150, 150);
        setSize(200,200);
        g.setColor(Color.RED); 
    }
}
