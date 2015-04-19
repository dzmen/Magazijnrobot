/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Danny
 */
public class TekenPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        g.setColor(Color.BLUE);
        g.drawLine(0, 0, 700, 200);
        g.drawLine(0, 200, 700, 0);
        g.setColor(Color.red);
        g.fillOval(20, 20, 120, 120);
        g.setColor(Color.black);
        //g.drawOval(20, 180, 121, 121);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2F));  // set stroke width of 10
        g2D.drawOval(20, 20, 121, 121);

    }
}
