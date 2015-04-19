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
        this.setPreferredSize(new Dimension(780,580));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.white);
        g.setColor(Color.black);
        g.drawLine(10, 10, 20, 20);
    }

    public void addDoos() {
        dozen.add(new Doos(this.getHeight()-20));
    }

    public void addpakket() {
        pakketten.add(new Pakket());
    }
    public void removePakket(){
        pakketten.clear();
    }

    public ArrayList getDozen() {
        return this.dozen;
    }

    public ArrayList<Pakket> getPakketten() {
        return this.pakketten;
    }

}