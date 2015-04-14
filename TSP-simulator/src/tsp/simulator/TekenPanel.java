/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator;

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        g.setColor(Color.yellow);
        g.drawLine(0, 0, 680, 480);
        g.drawLine(625, 0, 0, 440);
        g.setColor(Color.red);
        g.fillOval(20, 180, 120, 120);
        g.setColor(Color.black);
        //g.drawOval(20, 180, 121, 121);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2F));  // set stroke width of 10
        g2D.drawOval(20, 180, 121, 121);

    }
}
