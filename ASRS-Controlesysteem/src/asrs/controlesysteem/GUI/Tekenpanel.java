package asrs.controlesysteem.GUI;

import asrs.controlesysteem.bestelling.Artikel;
import asrs.controlesysteem.bestelling.Locatie;
import asrs.controlesysteem.bestelling.Order;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class Tekenpanel extends JPanel {

    private Order order;
    private int boxHoogte;
    private int boxBreedte;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        tekenSchappen(g);
        if (!(order == null)) {
            tekenArtikelen(g);
            if (!order.getLocatie().isEmpty()) {
                tekenRoute(g);
            }
        }
    }

    //Veldgrote is x 700 en y 200
    public void tekenSchappen(Graphics g) {
        //Bereken de breedte en hoogte van 1 vak
        boxBreedte = (int) (907 / 5);
        boxHoogte = (int) (267 / 5);
        //Tekenen de lijnen van de schappen
        for (int i = 0; i < 909; i += boxBreedte) {
            g.setColor(Color.black);
            g.drawLine(i, 0, i, boxHoogte * 5);
        }
        for (int i = 0; i < 269; i += boxHoogte) {
            g.setColor(Color.black);
            g.drawLine(0, i, boxBreedte * 5, i);
        }
    }

    //Tekenen van de artikelen
    public void tekenArtikelen(Graphics g) {
        for (Artikel artikel : order.getArtikelen()) {
            Locatie loc = artikel.getLocatie();
            System.out.println(loc);
            int xAs = loc.getX() - 1;
            int yAs = loc.getY();
            int xStart = (xAs) * boxBreedte + 1;
            int yStart = (yAs) * boxHoogte + 1;
            g.setColor(Color.orange);
            g.fillRect(xStart, yStart, boxBreedte - 1, boxHoogte - 1);
        }
        System.out.println("=====================");
    }

    //Veldgrote is x 700 en y 200
    public void tekenRoute(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Locatie start;
        Locatie einde;
        int beginX = 0;
        int beginY = 265;
        int eindeX = 0;
        int eindeY = 0;
        for (int i = 0; i < order.getLocatie().size() - 1; i++) {
            if (i > 0) {
                start = order.getLocatie().get(i);
                beginX = ((start.getX() - 1) * boxBreedte) + (boxBreedte / 2) - 5;
                beginY = ((start.getY()) * boxHoogte) + (boxHoogte / 2) - 5;
            }
            einde = order.getLocatie().get(i + 1);
            eindeX = ((einde.getX() - 1) * boxBreedte) + (boxBreedte / 2) + 5;
            eindeY = ((einde.getY()) * boxHoogte) + (boxHoogte / 2) + 5;
            Point beginP = new Point(beginX, beginY);
            Point eindeP = new Point(eindeX, eindeY);
            tekenPijl(g2d, eindeP, beginP);
        }
        if (order.getLocatie().size() == order.getRoute().size()) {
            Point beginL = new Point(eindeX - 5, eindeY - 5);
            Point eindeL = new Point(0, 265);
            tekenPijl(g2d, eindeL, beginL);
        }

    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private void tekenPijl(Graphics2D g2, Point boven, Point onder) {
        double phi = Math.toRadians(20);
        int barb = 15;
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
