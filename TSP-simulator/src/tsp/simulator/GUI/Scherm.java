/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.GUI;

import java.awt.*;
import javax.swing.*;

public class Scherm extends JFrame {

    private JLabel lGemiddelde;

    private JComboBox dAlgoritme;
    private JTextArea tLog;
    private JLabel lAlgoritme, lResultaten, lVolledige, lGretig, lSimpel;
    private JLabel rVolledige, rGretig, rSimpel;
    private JLabel gVolledige, gSimpel, gGretig;
    private JButton bUitvoeren, bOrder;
    private TekenPanel vel;
    private String[] algoritmeItems = {"Volledige enumeratie", "Simpel Gretig algoritme", "Gretig algoritme"};

    public Scherm() {
        this.setTitle("TSP-Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750, 480);
        this.setLayout(null);
        this.setResizable(false);
        String[] dAlgoritmeItems = {"Volledige enumeratie", "Simpel Gretig algoritme", "Gretig algoritme"};

        //De labels (namen)
        lResultaten = new JLabel("Resultaten:");
        lVolledige = new JLabel("Volledige enumeratie:");
        lSimpel = new JLabel("Simpel Gretig algoritme:");
        lGretig = new JLabel("Gretig algoritme:");
        //De labels (resultaten)
        rVolledige = new JLabel("0 sec, 0 cm");
        rSimpel = new JLabel("0 sec, 0 cm");
        rGretig = new JLabel("0 sec, 0 cm");
        //De labels (gemiddelde)
        lGemiddelde = new JLabel("Gemiddelde:");
        gVolledige = new JLabel("0 sec, 0 cm");
        gSimpel = new JLabel("0 sec, 0 cm");
        gGretig = new JLabel("0 sec, 0 cm");
        //De algoritme keuzen
        lAlgoritme = new JLabel("Algoritme:");
        dAlgoritme = new JComboBox(dAlgoritmeItems);
        //Graphic beeld
        vel = new TekenPanel();
        //Logboek
        tLog = new JTextArea(5, 5);
        tLog.setEditable(false);
        JScrollPane tLogger = new JScrollPane(tLog);
        //De button uitvoeren
        bUitvoeren = new JButton("Uitvoeren");
        //De butten order genereren
        bOrder = new JButton("Genereren order");

        //Items toevoegen aan scherm
        add(tLogger);
        add(lResultaten);
        add(lVolledige);
        add(lSimpel);
        add(lGretig);
        add(rVolledige);
        add(rSimpel);
        add(rGretig);
        add(lAlgoritme);
        add(vel);
        add(dAlgoritme);
        add(bUitvoeren);
        add(lGemiddelde);
        add(gVolledige);
        add(gSimpel);
        add(gGretig);
        add(bOrder);

        //Posities van items instellen
        tLogger.setBounds(20, 255, 270, 145);
        lResultaten.setBounds(315, 275, 100, 25);
        lVolledige.setBounds(315, 300, 130, 25);
        lSimpel.setBounds(315, 325, 150, 25);
        lGretig.setBounds(315, 350, 130, 25);
        rVolledige.setBounds(465, 300, 100, 25);
        rSimpel.setBounds(465, 325, 100, 25);
        rGretig.setBounds(465, 350, 100, 25);
        lAlgoritme.setBounds(360, 410, 100, 25);
        vel.setBounds(25, 25, 700, 200);
        dAlgoritme.setBounds(440, 410, 150, 25);
        bUitvoeren.setBounds(610, 410, 100, 25);
        lGemiddelde.setBounds(560, 275, 100, 25);
        gVolledige.setBounds(560, 300, 100, 25);
        gSimpel.setBounds(560, 325, 100, 25);
        gGretig.setBounds(560, 350, 100, 25);
        bOrder.setBounds(200, 410, 150, 25);
    }
}
