/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import tsp.simulator.readers.XMLReader;

public class Scherm extends JFrame implements ActionListener {

    private JComboBox algoritme;
    private JTextArea tLog;
    private JLabel lAlgoritme, lResultaten, lVolledige, lGretig, lSimpel, lXMLbestand;
    private JLabel lVolledigeR, lSimpelR, lGretigR;
    private JButton bBestand, bUitvoeren;
    private TekenPanel vel;
    private String[] algoritmeItems = {"Volledige enumeratie", "Simpel Gretig algoritme", "Gretig algoritme"};

    public Scherm() {
        setTitle("TSP-Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 480);
        setLayout(null);

        //componenten maken
        lAlgoritme = new JLabel("Algoritme:");
        //Dropdown menu
        algoritme = new JComboBox(algoritmeItems);
        //Graphic beeld
        vel = new TekenPanel();
        //Logboek
        tLog = new JTextArea(5, 5);
        tLog.setEditable(false);
        //Resultaten
        lResultaten = new JLabel("Resultaten:");
        lGretig = new JLabel("Gretig algoritme:");
        lGretigR = new JLabel("0 sec, 0 cm");
        lVolledige = new JLabel("Volledige enumeratie:");
        lVolledigeR = new JLabel("0 sec, 0 cm");
        lSimpel = new JLabel("Simpel Gretig algoritme:");
        lSimpelR = new JLabel("0 sec, 0cm");
        bBestand = new JButton("bestand kiezen..");
        lXMLbestand = new JLabel("XML bestand:");
        bUitvoeren = new JButton("Uitvoeren");
        JScrollPane tLogger = new JScrollPane(tLog);
        //add componenten aan scherm
        add(lAlgoritme);
        add(algoritme);
        add(lXMLbestand);
        add(bUitvoeren);
        //De grafische demo
        add(vel);
        //De logboek
        add(tLogger);
        //De resultaten
        add(lResultaten);
        add(lVolledige);
        add(lVolledigeR);
        add(lGretig);
        add(lGretigR);
        add(lSimpel);
        add(lSimpelR);
        add(bBestand);

        //Zet alle items op positie
        lAlgoritme.setBounds(525, 15, 100, 25);
        algoritme.setBounds(500, 45, 200, 25);
        vel.setBounds(25, 20, 440, 290);
        tLogger.setBounds(25, 320, 285, 100);
        //De resultaten
        lResultaten.setBounds(320, 315, 100, 25);
        lVolledige.setBounds(320, 335, 150, 25);
        lVolledigeR.setBounds(450, 335, 100, 25);
        lSimpel.setBounds(320, 355, 150, 25);
        lSimpelR.setBounds(465, 355, 100, 25);
        lGretig.setBounds(320, 375, 100, 25);
        lGretigR.setBounds(425, 375, 100, 25);

        lXMLbestand.setBounds(475, 145, 100, 25);
        bBestand.setBounds(565, 145, 150, 25);
        bBestand.addActionListener(this);
        bUitvoeren.setBounds(595, 370, 100, 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bBestand) {
            JFileChooser openFile = new JFileChooser();
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Order file (*.xml)", "xml");
            openFile.setFileFilter(xmlFilter);
            int resultaat = openFile.showOpenDialog(null);
            if (resultaat == openFile.APPROVE_OPTION) {
                XMLReader reader = new XMLReader(openFile.getSelectedFile());
                if (reader.getCompleet()) {
                    bBestand.setText(openFile.getSelectedFile().getName());
                } else {

                }
            }
        }
    }
}
