/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Items.Doos;
import Items.Pakket;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Hugo
 */
public class Graphpanel extends JPanel {

    private ArrayList<Doos> dozen = new ArrayList<Doos>();
    private ArrayList<Pakket> pakketten = new ArrayList<Pakket>();

    public Graphpanel() {
        this.setPreferredSize(new Dimension(780, 580));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.white);
        g.setColor(Color.black);
        int a = 5;
        //Tekent elke doos
        for (Doos aa : dozen) {
            //breedte van de doos wordt berekend door de grootte van het venster gedeeld door het aantal dozen.
            int baseW = (int)((this.getWidth() - 10) / dozen.size());
            //hoogste van de doos is het hoogste van het venster met een marge van 10
            int baseH = aa.getSize();
            //de breedte wordt met 5 ingekort, dit om de frames te splitten
            g.drawRect(a, 10, baseW-5, baseH);
            
            int y = aa.getSize();
            for (Pakket o : aa.getPakketten()) {
                y = (int) (y - baseH* o.getSize());
                int xLoc = a+2;
                g.drawRect(xLoc,y+12, baseW - 9,(int)( baseH *o.getSize()-4));
            }
            a = a + ((this.getWidth() - 10) / dozen.size());
        }
    }

    public void addDoos() {
        dozen.add(new Doos(this.getHeight() - 20));
    }

    public void addpakket() {
        pakketten.add(new Pakket());
    }

    public void removePakketten() {
        pakketten.clear();
    }

    public void removeDozen() {
        dozen.clear();
    }

    public ArrayList<Doos> getDozen() {
        return this.dozen;
    }

    public ArrayList<Pakket> getPakketten() {
        return this.pakketten;
    }
    public void SetDozen(ArrayList<Doos> newDozen){
        this.dozen = newDozen;
    }
        public void SetPakketten(ArrayList<Pakket> newpacks){
        this.pakketten =newpacks;
    }

}
