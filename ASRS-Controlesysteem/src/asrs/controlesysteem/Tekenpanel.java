/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author Quinten
 */
public class Tekenpanel extends JPanel{
     public Tekenpanel(){
      
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.WHITE);
        setSize(910 , 275);
        g.setColor(Color.BLACK);
        g.drawOval( 20, 150, 50, 50);
        g.setColor(Color.red);
        g.fillOval(20 ,150, 48, 48);
        g.setColor(Color.YELLOW);
        g.drawLine(0, 0, 300, 200);
        g.drawLine(300,0,0,200);
    }
}
