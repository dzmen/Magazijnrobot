/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Algoritmes.Fill;
import Algoritmes.Gretig;
import Algoritmes.Numeriek;
import Algoritmes.Size;
import bpp.sim.Doos;
import bpp.sim.Pakket;
import bpp.sim.Resultaat;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Hugo
 */
public class Scherm extends JFrame implements ActionListener {

    private Graphicspanel tekenscherm;
    private Logpanel tekstpanel;
    private JScrollPane Scrollscherm;
    private JButton genereerPakketten, uitvoeren, gemiddelde;
    private JComboBox selectAlgoritme;
    private ArrayList<Resultaat> resultaten;
    int Tottime = 0; 
    int Totdozen = 0;
    Resultaat nieuwste = null;

    public Scherm() {
        // width
        int ScreenWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8);
        // height
        int ScreenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);

        this.setSize(ScreenWidth, ScreenHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("BPP-simulator");
        ScreenHeight = ScreenHeight - 60;
        ScreenWidth = ScreenWidth - 25;

        //Graphicspanel dimension setup
        int xbound = 0;
        int ybound = 0;
        int boundwidth = (int) (ScreenWidth * 0.8);
        int boundheight = (int) (ScreenHeight * 0.6);
        tekenscherm = new Graphicspanel();
        tekenscherm.setBounds(xbound + 5, ybound + 5, boundwidth - 10, boundheight - 10);

        //Logpanel dimension setup
        resultaten = new ArrayList<>();
        tekstpanel = new Logpanel();
        tekstpanel.setEditable(false);
        tekstpanel.setText("Programma gestart");
        tekstpanel.setFont(new Font("Arial", Font.PLAIN, ScreenHeight / 35));
        tekstpanel.setBounds(xbound + 5, ybound + boundheight + 5, boundwidth - 10, ScreenHeight - boundheight - 10);
        Scrollscherm = new JScrollPane(tekstpanel);
        Scrollscherm.setBounds(xbound + 5, ybound + boundheight + 5, boundwidth - 10, ScreenHeight - boundheight - 10);

        xbound = xbound + boundwidth;
        boundwidth = ScreenWidth - boundwidth;
        boundheight = ScreenHeight / 20;
        Font buttonfont = new Font("Arial", Font.PLAIN, boundheight / 2);

        //genPacks dimension setup
        genereerPakketten = new JButton("Genereer nieuwe pakketten");
        genereerPakketten.addActionListener(this);
        genereerPakketten.setFont(buttonfont);
        genereerPakketten.setBounds(xbound + 5, ybound + 5, boundwidth - 10, boundheight - 10);
        ybound = ybound + boundheight;

        //pickAlg dimension setup
        String[] lijst = {"First Pick algoritme", "Numeriek Algoritme", "Size Algoritme", "Fill Algoritme"};
        selectAlgoritme = new JComboBox(lijst);
        selectAlgoritme.setFont(buttonfont);
        selectAlgoritme.setBounds(xbound + 5, ybound + 5, boundwidth - 10, boundheight - 10);
        selectAlgoritme.setEnabled(false);
        ybound = ybound + boundheight;

        //execute dimension setup
        uitvoeren = new JButton("Uitvoeren");
        uitvoeren.setFont(buttonfont);
        uitvoeren.addActionListener(this);
        uitvoeren.setBounds(xbound + 5, ybound + 5, boundwidth - 10, boundheight - 10);
        uitvoeren.setEnabled(false);
        ybound = ybound + boundheight;

        //avg dimension setup
        gemiddelde = new JButton("Gemiddelde berekenen");
        gemiddelde.setFont(buttonfont);
        gemiddelde.addActionListener(this);
        gemiddelde.setBounds(xbound + 5, ybound + 5, boundwidth - 10, boundheight - 10);
        gemiddelde.setEnabled(false);
        ybound = ybound + boundheight;

        this.add(tekenscherm);
        this.add(Scrollscherm);
        this.add(genereerPakketten);
        this.add(selectAlgoritme);
        this.add(uitvoeren);
        this.add(gemiddelde);
        this.setVisible(true);

    }
    int aangeklikt = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == genereerPakketten) {
            aangeklikt++;

            //pakketten genereren
            tekenscherm.getOrder().genPackets(20, 50);
            tekenscherm.repaint();
            if (aangeklikt > 0) {
                selectAlgoritme.setEnabled(true);
                uitvoeren.setEnabled(true);
                if (aangeklikt > 1) {
                    gemiddelde.setEnabled(true);
                }
            }
            tekstpanel.append("\nPakketten en dozen zijn gegenereerd");
            // statusbalk aantal gegenereerde pakketten
            resultaten.add(new Resultaat(tekenscherm.getOrder().getPakketten()));
            nieuwste = resultaten.get(resultaten.size() - 1);
            for (Pakket lijstprint : tekenscherm.getOrder().getPakketten()) {
             //j  tekstpanel.append("\n" + lijstprint.getSize());
            }

        }
        if (e.getSource() == uitvoeren) {
            long time = System.currentTimeMillis();
            int time2;
            int dozen;
            dozen = 0;
          //  dozen = tekenscherm.getOrder().getDozen();
            tekenscherm.getOrder().emptyDozen();
            tekstpanel.append("\nAlgoritme is uitgevoerd\n");
            if (selectAlgoritme.getSelectedIndex() == 0) {
                //todo first pick algoritme
                Gretig a = new Gretig();
                tekenscherm.getOrder().setDozen(a.runAlgorithm(tekenscherm.getOrder().getDozen(), tekenscherm.getOrder().getPakketten()));
                time2 = (int) (System.currentTimeMillis() - time);
                tekstpanel.append(nieuwste.updateResult(0, "First Pick", time2, tekenscherm.getOrder().getDozen()));
               
                    Tottime = Tottime + time2;
                    Totdozen = Totdozen + dozen;

            }
            if (selectAlgoritme.getSelectedIndex() == 1) {
                Numeriek a = new Numeriek();
                //todo numeriek algoritme
                tekenscherm.getOrder().setDozen(a.runAlgorithm(tekenscherm.getOrder().getDozen(), tekenscherm.getOrder().getPakketten()));
                time2 = (int) (System.currentTimeMillis() - time);
                 tekstpanel.append(nieuwste.updateResult(1, "Numeriek", time2, tekenscherm.getOrder().getDozen()));
                 
                   Tottime = Tottime + time2;
                   Totdozen = Totdozen + dozen;
            }
            if (selectAlgoritme.getSelectedIndex() == 2) {
                //todo size algoritme
                Size a = new Size();
                tekenscherm.getOrder().setDozen(a.runAlgorithm(tekenscherm.getOrder().getDozen(), tekenscherm.getOrder().getPakketten()));
                time2 = (int) (System.currentTimeMillis() - time);
                 tekstpanel.append(nieuwste.updateResult(2, "Size", time2, tekenscherm.getOrder().getDozen())) ;
                 
                   Tottime = Tottime + time2;
                   Totdozen = Totdozen + dozen;
            }
            if (selectAlgoritme.getSelectedIndex() == 3) {
                //todo fill algoritme
                Fill a = new Fill();
                tekenscherm.getOrder().setDozen(a.runAlgorithm(tekenscherm.getOrder().getDozen(), tekenscherm.getOrder().getPakketten()));
                time2 = (int) (System.currentTimeMillis() - time);
                tekstpanel.append (nieuwste.updateResult(0, "Fill", time2, tekenscherm.getOrder().getDozen()));
                
                  Tottime = Tottime + time2;
                  Totdozen = Totdozen + dozen;
            }
            
            time = System.currentTimeMillis() - time;
            
     
            //algoritmes uitvoeren
            for (Doos ab : tekenscherm.getOrder().getDozen()) {
                System.out.println("\nDoos");
                for (Pakket ac : ab.getPakketten()) {
                    System.out.println("- " + ac.getSize());
                }
            }
            tekenscherm.repaint();
        }
        if (e.getSource() == gemiddelde) {
            // gemiddelde berekenen
               tekstpanel.append("\n-------------\nGemiddelde:");
               long time = System.currentTimeMillis();
               int time2;
               int dozen;
               dozen = 0;
               time2 = (int) (System.currentTimeMillis() - time);  
               time2 = (Tottime + time2 ) / 2;
               dozen = (Totdozen + dozen) / 2;
               
               
                
             if (selectAlgoritme.getSelectedIndex() == 0) {
            tekstpanel.append(nieuwste.updateResult(0, "First Pick", time2, tekenscherm.getOrder().getDozen()));
             }
              if (selectAlgoritme.getSelectedIndex() == 1) {
            tekstpanel.append(nieuwste.updateResult(1, "Numeriek", time2, tekenscherm.getOrder().getDozen()));
             }
               if (selectAlgoritme.getSelectedIndex() == 2) {
            tekstpanel.append(nieuwste.updateResult(2, "Size", time2, tekenscherm.getOrder().getDozen()));
             }
                if (selectAlgoritme.getSelectedIndex() == 3) {
            tekstpanel.append(nieuwste.updateResult(3, "Fill", time2, tekenscherm.getOrder().getDozen()));
             }
            
           // tijd = results[index][1]
           // tijd =  tijd + nieuwe tijd / 2
          
            
        }
    }

}