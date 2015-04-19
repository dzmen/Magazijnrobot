/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Items.Pakket;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Hugo
 */
public class Scherm extends JFrame implements ActionListener {

    private Graphpanel tekenpaneel;
    private JButton genPackages, execute, avg;
    private JComboBox selectAl;

    public Scherm() {
        // Scherm formaat en standaard instellingen.
        this.setSize(1000, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("BPP-simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        //Graphpanel setup
        tekenpaneel = new Graphpanel();
        //tekenpaneel.setVisible(true);
        tekenpaneel.setBounds(10, 10, 780, 580);

        //GenPackages setup
        genPackages = new JButton("Genereer pakketten");
        genPackages.addActionListener(this);
        genPackages.setBounds(810, 10, 180, 20);

        //selectAl setup
        String[] aa = {"Numeriek Algoritme", "Gretig Algoritme", "Small/Large Algoritme"};
        selectAl = new JComboBox(aa);
        selectAl.setBounds(810, 40, 180, 20);
        selectAl.setEnabled(false);

        //execute setup.
        execute = new JButton("Uitvoeren");
        execute.addActionListener(this);
        execute.setBounds(810, 70, 180, 20);
        execute.setEnabled(false);

        //avg setup
        avg = new JButton("Bereken gemiddelde");
        avg.addActionListener(this);
        avg.setBounds(810, 100, 180, 20);
        avg.setEnabled(false);

        this.add(tekenpaneel);
        this.add(genPackages);
        this.add(selectAl);
        this.add(execute);
        this.add(avg);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == genPackages) {
            //Verwijder oude pakketten(als die er zijn)
            tekenpaneel.removePakketten();
            //verwijdert alle oude dozen
            tekenpaneel.removeDozen();
            //Genereer willekeurig aantal pakketten tussen de 5 en 20 stuks
            int b = (int) (Math.random() * 15 + 5);
            for (int i = 0; i < b; i++) {
                tekenpaneel.addpakket();
            }

            //Versimpeld gretig algoritme om max aantal dozen te berekenen.
            tekenpaneel.addDoos();
            Pakket c = new Pakket();
            c.setSize(1.0);
            System.out.println("===");
            double i = 0.0;
            for (int a = 0; a < tekenpaneel.getPakketten().size(); a++) {
                
                if (i + tekenpaneel.getPakketten().get(a).getSize() < 1) {

                    i = i + tekenpaneel.getPakketten().get(a).getSize();
                } else {
                    i = 0.0;
                    tekenpaneel.addDoos();
                }
                System.out.println("Pakket " + (a + 1) + ":" + tekenpaneel.getPakketten().get(a).getSize());
            }
            tekenpaneel.repaint();
            //activeer sommige knoppen voor eerste gebruik
            selectAl.setEnabled(true);
            execute.setEnabled(true);

        }
        if (e.getSource() == execute) {
            System.out.println(selectAl.getSelectedIndex());
        }
    }

}
