/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import tsp.simulator.Locatie;
import tsp.simulator.Order;

/**
 *
 * @author Danny
 */
public class TekenPanel extends JPanel {

    private Order order;
    private int boxHoogte;
    private int boxBreedte;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        if (!(order == null)) {
            tekenSchappen(g);
            if (!order.getRoute().isEmpty()) {
                tekenRoute(g);
            }
        }
    }

    //Veldgrote is x 700 en y 200
    public void tekenSchappen(Graphics g) {
        //Bereken de breedte en hoogte van 1 vak
        boxBreedte = (int) (700 / order.getxVeldGrote());
        boxHoogte = (int) (200 / order.getyVeldGrote());
        //Tekenen de lijnen van de schappen
        for (int i = 0; i < 705; i += boxBreedte) {
            g.setColor(Color.black);
            g.drawLine(i, 0, i, boxHoogte * order.getyVeldGrote());
        }
        for (int i = 0; i < 205; i += boxHoogte) {
            g.setColor(Color.black);
            g.drawLine(0, i, boxBreedte * order.getxVeldGrote(), i);
        }
        //Tekenen van de artikelen
        for (Locatie loc : order.getArtikelen()) {
            int xStart = (loc.getX() - 1) * boxBreedte + 1;
            int yStart = (loc.getY() - 1) * boxHoogte + 1;
            g.setColor(Color.orange);
            g.fillRect(xStart, yStart, boxBreedte - 1, boxHoogte - 1);
        }
    }

    //Veldgrote is x 700 en y 200
    public void tekenRoute(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Locatie start;
        Locatie einde;
        int beginX = 0;
        int beginY = 200;
        int eindeX = 0;
        int eindeY = 0;
        for (int i = 0; i < order.getRoute().size() - 1; i++) {
            if (i > 0) {
                start = order.getRoute().get(i);
                beginX = ((start.getX()-1) * boxBreedte) + (boxBreedte / 2)+(int)(Math.random()*6);
                beginY = ((start.getY()-1) * boxHoogte) + (boxHoogte / 2)+(int)(Math.random()*6);
            }
            einde = order.getRoute().get(i + 1);
            eindeX = ((einde.getX()-1) * boxBreedte) + (boxBreedte / 2)-(int)(Math.random()*6);
            eindeY = ((einde.getY()-1) * boxHoogte) + (boxHoogte / 2)-(int)(Math.random()*6);
            Point beginP = new Point(beginX, beginY);
            Point eindeP = new Point(eindeX, eindeY);
            tekenPijl(g2d, eindeP, beginP);
        }
        Point beginL = new Point(eindeX - 5, eindeY - 5);
        Point eindeL = new Point(0, 200);
        tekenPijl(g2d, eindeL, beginL);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private void tekenPijl(Graphics2D g2, Point boven, Point onder) {
        double phi = Math.toRadians(20);
        int barb = Math.min(boxBreedte, boxHoogte)/2;
        //Zet de kleur van de pijl
        g2.setPaint(Color.black);
        //Dit zorgt voor een mooie soepele lijn
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Teken de rechte lijn
        g2.draw(new Line2D.Double(onder, boven));
        double dy = boven.y - onder.y;
        double dx = boven.x - onder.x;
        //De hoek berekenen vanaf de lijn
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + phi;
        //De schuine lijnen tekenen
        for (int j = 0; j < 2; j++) {
            //Bepaal x en y voor schuinelijn
            x = boven.x - barb * Math.cos(rho);
            y = boven.y - barb * Math.sin(rho);
            g2.draw(new Line2D.Double(boven.x, boven.y, x, y));
            rho = theta - phi;
        }
    }
}
