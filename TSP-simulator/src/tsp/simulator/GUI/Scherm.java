/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;
import tsp.simulator.Order;

public class Scherm extends JFrame implements ActionListener {

    private JLabel lGemiddelde;

    private JComboBox dAlgoritme;
    public JTextArea tLog;
    private JLabel lAlgoritme, lResultaten, lVolledige, lGretig, lSimpel;
    private JLabel rVolledige, rGretig, rSimpel;
    private JLabel gVolledige, gSimpel, gGretig;
    private double iTVolledige = 0, iTSimpel = 0, iTGretig = 0, iLVolledige = 0, iLSimpel = 0, iLGretig = 0;
    private JButton bUitvoeren, bOrder;
    public TekenPanel vel;
    private String[] algoritmeItems = {"Volledige enumeratie", "Simpel Gretig algoritme", "Gretig algoritme"};
    private Order order;

    public Scherm() {
        //Zet de algemene JFrame instellingen
        this.setTitle("TSP-Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750, 480);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        String[] dAlgoritmeItems = {"Volledige enumeratie", "Simpel Gretig algoritme", "Gretig algoritme"};

        //De labels (namen)
        lResultaten = new JLabel("Resultaten:");
        lVolledige = new JLabel("Volledige enumeratie:");
        lSimpel = new JLabel("Simpel Gretig algoritme:");
        lGretig = new JLabel("Gretig algoritme:");
        //De labels (resultaten)
        rVolledige = new JLabel("0 ms, 0 stappen");
        rSimpel = new JLabel("0 ms, 0 stappen");
        rGretig = new JLabel("0 ms, 0 stappen");
        //De labels (gemiddelde)
        lGemiddelde = new JLabel("Gemiddelde:");
        gVolledige = new JLabel("0 ms, 0 stappen");
        gSimpel = new JLabel("0 ms, 0 stappen");
        gGretig = new JLabel("0 ms, 0 stappen");
        //De algoritme keuzen
        lAlgoritme = new JLabel("Algoritme:");
        dAlgoritme = new JComboBox(dAlgoritmeItems);
        dAlgoritme.setEnabled(false);
        //Graphic beeld
        vel = new TekenPanel();
        //Logboek
        tLog = new JTextArea(5, 5);
        tLog.setEditable(false);
        JScrollPane tLogger = new JScrollPane(tLog);
        //De button uitvoeren
        bUitvoeren = new JButton("Uitvoeren");
        bUitvoeren.addActionListener(this);
        bUitvoeren.setEnabled(false);
        //De butten order genereren
        bOrder = new JButton("Genereren order");
        bOrder.addActionListener(this);

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
        rVolledige.setBounds(465, 300, 150, 25);
        rSimpel.setBounds(465, 325, 150, 25);
        rGretig.setBounds(465, 350, 150, 25);
        lAlgoritme.setBounds(360, 410, 100, 25);
        vel.setBounds(25, 25, 703, 203);
        dAlgoritme.setBounds(440, 410, 150, 25);
        bUitvoeren.setBounds(610, 410, 100, 25);
        lGemiddelde.setBounds(620, 275, 120, 25);
        gVolledige.setBounds(620, 300, 150, 25);
        gSimpel.setBounds(620, 325, 150, 25);
        gGretig.setBounds(620, 350, 150, 25);
        bOrder.setBounds(200, 410, 150, 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bOrder)) {
            //Maak een nieuwe order
            this.order = new Order(this);
            this.order.genereerArtikelen();
            //Geef order weer op veld
            vel.setOrder(order);
            vel.repaint();
            //Enable overige knoppen na genereren van veld
            dAlgoritme.setEnabled(true);
            bUitvoeren.setEnabled(true);
            //Log
            tLog.append("breedte van het veld: " + order.getxVeldGrote() + "\n");
            tLog.append("hoogte van het veld: " + order.getyVeldGrote() + "\n");
            tLog.append("aantal artikelen: " + order.getArtikelen().size() + "\n");
            tLog.append("Genereren van order is voltooid. Start nu een algoritme.\n");
            tLog.setCaretPosition(tLog.getDocument().getLength());
        }
        if (e.getSource().equals(bUitvoeren)) {
            order.genereerRoute(dAlgoritme.getSelectedIndex());
            tLog.append("Generatietijd: " + order.getBerekenTijd() + " nanoseconden\n");
            tLog.append("Afstand: " + order.getLengte() + " schappen waar hij langs gaat\n");
            setResultaat(dAlgoritme.getSelectedIndex(), order.getBerekenTijd(), order.getLengte());
            vel.repaint();
            tLog.append("Algoritme met succes uitgevoerd! \n");
            tLog.setCaretPosition(tLog.getDocument().getLength());
        }
    }

    private void setResultaat(int algoritme, long tijd, int lengte) {
        setGemiddelde(algoritme, tijd, lengte);
        //Zorgt ervoor dat de tijd in miniseconden wordt gerekenend (1.12 ms)
        double deTijd = Math.floor(tijd) / 1000000;
        DecimalFormat df = new DecimalFormat("#.###");
        String x = df.format(deTijd);
        if (algoritme == 0) {
            rVolledige.setText(x + " ms, " + lengte + " stappen");
        } else if (algoritme == 1) {
            rSimpel.setText(x + " ms, " + lengte + " stappen");
        } else if (algoritme == 2) {
            rGretig.setText(x + " ms, " + lengte + " stappen");
        }
    }

    private void setGemiddelde(int algoritme, long tijd, int lengte) {
        DecimalFormat df = new DecimalFormat("#.###");
        if (algoritme == 0) {
            this.iLVolledige = (iLVolledige + lengte) / 2;
            iTVolledige = Math.floor((iTVolledige + tijd) / 2) / 1000000;
            String x = df.format(iTVolledige);
            gVolledige.setText(x + " ms, " + iLVolledige + " stappen");
        } else if (algoritme == 1) {
            this.iLSimpel = (iLSimpel + lengte) / 2;
            iTSimpel = Math.floor((iTSimpel + tijd) / 2) / 1000000;
            String x = df.format(iTSimpel);
            this.gSimpel.setText(x + " ms, " + iLSimpel + " stappen");
        } else if (algoritme == 2) {
            this.iLGretig = (iLGretig + lengte) / 2;
            iTGretig = Math.floor((iTGretig + tijd) / 2) / 1000000;
            String x = df.format(iTGretig);
            this.gGretig.setText(x + " ms, " + iLGretig + " stappen");
        }
    }
}
