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
class Graphpanel extends JPanel {

    private ArrayList<Doos> dozen = new ArrayList<Doos>();
    private ArrayList<Pakket> pakketten = new ArrayList<Pakket>();

    public Graphpanel() {
        setPreferredSize(new Dimension(780, 580));
        this.setVisible(true);
    }

    public void PaintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.white);
        g.setColor(Color.black);
        g.drawLine(10, 10, 20, 20);
    }

    public void addDoos() {
        dozen.add(new Doos());
    }

    public void addpakket() {
        pakketten.add(new Pakket());
    }

    public ArrayList getDozen() {
        return this.dozen;
    }

    public ArrayList getPakketten() {
        return this.pakketten;
    }

}
