/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import bpp.sim.Doos;
import bpp.sim.Pakket;
import bpp.sim.Sorter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Hugo
 */
class Graphicspanel extends JPanel {

    private Sorter bestelling = new Sorter();

    public Graphicspanel() {
    }

    public Sorter getOrder() {
        return this.bestelling;
    }

    @Override
    public void paintComponent(Graphics g) {
        //lijst met kleuren
        Color[] kleuren = {Color.red, Color.blue, Color.orange, Color.green, Color.pink, Color.yellow, Color.magenta, Color.cyan};
        super.paintComponent(g);
        setBackground(Color.white);
        // X en y coördinaten geven de locaties van dozen en pakketten aan
        int x = 0;
        int y = 0;
        int width = this.getWidth() / 20;
        //als dozenaantal kleiner is dan 20 krijgen ze allemaal de zelfde breedte.
        if (bestelling.getDozen().size() * width > this.getWidth()) {
            width = this.getWidth() / bestelling.getDozen().size();
        }
        // hoogte van het paneel wordt opgehaald
        int height = this.getHeight();
        int color=0;
        if (bestelling.getDozen().size() > 0) {
            for (Doos aa : bestelling.getDozen()) {
                // voor elke doos wordt een rechthoek getekend
                g.drawRect(x + 5, y + 5, width - 10, height - 10);
                int px = x + 7;
                int pw = width - 14;
                int py = 0;
                
                for (Pakket ab : aa.getPakketten()) {
                    if (color >= kleuren.length) {
                        color = 0;
                    }
                    // pakketten worden geplaatst met 'willekeurige' kleuren
                    g.setColor(kleuren[color]);
                    py = (int) (py + (ab.getSize() * (height - 10)));
                    int ph = (int) ((height - 10) * ab.getSize());
                    g.fillRect(px+1, height-py-3, pw-2, ph-6);
                    g.setColor(Color.black);
                    g.drawRect(px, height - py - 4, pw, (int) ph - 4);
                    color++;
                }
                x = x + width;
            }
        }

    }

}
