/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Items.Algoritme1;
import Items.Algoritme2;
import Items.Algoritme3;
import Items.Doos;
import Items.Pakket;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    ArrayList<Pakket> packs;
    ArrayList<Doos> boxs;

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

        //JTextField setup TODO
        //add all content to scherm
        this.add(tekenpaneel);
        this.add(genPackages);
        this.add(selectAl);
        this.add(execute);
        this.add(avg);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Genereer random paketten.
        if (e.getSource() == genPackages) {
            // STAP1: Delete oude data.
            tekenpaneel.removePakketten();
            tekenpaneel.removeDozen();

            // STAP2: Genereer willekeurig aantal pakketten (tussend de 5 en 20).
            int b = (int) (Math.random() * 5 + 15);
            for (int i = 0; i < b; i++) {
                tekenpaneel.addpakket();
            }
            // print data;
            System.out.println("======================");
            for (Pakket a : tekenpaneel.getPakketten()) {
                System.out.println("Pakktet :" + a.getSize());
            }

            // STAP3: Gebruik van versimpeld gretig algoritme om maximum aantal dozen te genereren.
            //(minimaal 1 doos nodig)
            tekenpaneel.addDoos();
            tekenpaneel.addDoos();
            tekenpaneel.addDoos();
            double i = 0.0;
            for (int a = 0; a < tekenpaneel.getPakketten().size(); a++) {
                double u = tekenpaneel.getPakketten().get(a).getSize();
                if (i + u > 1) {
                    tekenpaneel.addDoos();
                    i = u;
                } else {
                    i = i + u;
                }
            }
            packs = tekenpaneel.getPakketten();
            boxs = tekenpaneel.getDozen();

            // STAP4: Aantal dozen weergeven in Graphpanel.
            tekenpaneel.repaint();

            // STAP5: knoppen aanzetten.
            selectAl.setEnabled(true);
            execute.setEnabled(true);

        }
        // gebruik maken van algoritmes
        
        if (e.getSource() == execute) {
            // Maak algoritmes aan voor gebruik.
            Algoritme1 alg1 = new Algoritme1();
            Algoritme2 alg2 = new Algoritme2();
            Algoritme3 alg3 = new Algoritme3();
            tekenpaneel.SetDozen(tekenpaneel.getDozen());
            tekenpaneel.SetPakketten(tekenpaneel.getPakketten());

            for (Doos ab : boxs) {
                ab.deletePakketen();
            }
            if (selectAl.getSelectedIndex() == 1) {

                alg1.runAlgoritme(boxs, packs);
                tekenpaneel.SetDozen(alg1.getBoxes());
                System.out.println("Algoritme2 uitgevoerd");
            }
            if (selectAl.getSelectedIndex() == 0) {
                alg2.runAlgoritme(boxs, packs);
                tekenpaneel.SetDozen(alg2.getBoxes());
                System.out.println("Algoritme1 uitgevoerd");
            }
            if (selectAl.getSelectedIndex() == 2) {
                alg3.runAlgoritme(boxs, packs);
                tekenpaneel.SetDozen(alg3.getBoxes());
                System.out.println("Algoritme3 uitgevoerd");
            }
            
            tekenpaneel.repaint();
        }
    }

}
