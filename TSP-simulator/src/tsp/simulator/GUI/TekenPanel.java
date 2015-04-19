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
import tsp.simulator.Locatie;
import tsp.simulator.Order;

/**
 *
 * @author Danny
 */
public class TekenPanel extends JPanel {

    private Order order;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        if (!(order == null)) {
            tekenSchappen(g);
        }
        //g.setColor(Color.BLUE);
        //g.drawLine(0, 0, 700, 200);
        //g.drawLine(0, 200, 700, 0);
        //g.setColor(Color.red);
        //g.fillOval(20, 20, 120, 120);
        //g.setColor(Color.black);
        //Graphics2D g2D = (Graphics2D) g;
        //g2D.setStroke(new BasicStroke(2F));  // set stroke width of 10
        //g2D.drawOval(20, 20, 121, 121);
    }

    //Veldgrote is x 700 en y 200
    public void tekenSchappen(Graphics g) {
        //Bereken de breedte en hoogte van 1 vak
        int breedte = (int) (700 / order.getxVeldGrote());
        int hoogte = (int) (200 / order.getyVeldGrote());
        //Tekenen de lijnen van de schappen
        for (int i = 0; i < 705; i += breedte) {
            g.setColor(Color.black);
            g.drawLine(i, 0, i, hoogte * order.getyVeldGrote());
        }
        for (int i = 0; i < 205; i += hoogte) {
            g.setColor(Color.black);
            g.drawLine(0, i, breedte * order.getxVeldGrote(), i);
        }
        //Tekenen van de artikelen
        for (Locatie loc : order.getArtikelen()) {
            int xStart = (loc.getX() - 1) * breedte + 1;
            int yStart = (loc.getY() - 1) * hoogte + 1;
            g.setColor(Color.orange);
            g.fillRect(xStart, yStart, breedte - 1, hoogte - 1);
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
