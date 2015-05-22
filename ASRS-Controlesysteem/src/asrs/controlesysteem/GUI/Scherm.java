/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.GUI;

import asrs.controlesysteem.bestelling.Artikel;
import asrs.controlesysteem.bestelling.Locatie;
import asrs.controlesysteem.bestelling.Order;
import asrs.controlesysteem.connector.DeVerbinder;
/*NODIG WANNEER WE GEBRUIK MAKEN VAN ARDULINK
 import asrs.controlesysteem.connector.KiesScherm;
 import asrs.controlesysteem.connector.Zender;
 import org.zu.ardulink.Link;
 */
import asrs.controlesysteem.readers.SQLReader;
import asrs.controlesysteem.readers.XMLReader;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Scherm extends JFrame implements ActionListener {

    private final JScrollPane scOrder;
    private final Tekenpanel tekenpanel;
    private final JTextArea jTOrder, jTStatus;
    private final JScrollPane jSStatus;
    private final JButton jBInvoeren, jBUitvoeren;
    private final JLabel jBbestand;
    private Order order;
    private XMLReader xmlreader;
    private DeVerbinder arduino;
    //NODIG WANNEER WE GEBRUIK MAKEN VAN ARDULINK
    //Zender send;

    public Scherm() {
        //JFrame instellingen
        this.setTitle("AS/RS-Controlesysteem");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(960, 640);

        //Items aanmaken
        jTOrder = new JTextArea(5, 5);
        scOrder = new JScrollPane(jTOrder);
        tekenpanel = new Tekenpanel();
        jTStatus = new JTextArea(5, 5);
        jBInvoeren = new JButton("XML-order ophalen");
        jBUitvoeren = new JButton("Uitvoeren");
        jBbestand = new JLabel("Bestand: Geen bestand gekozen!");

        //De item instellingen
        jTOrder.setEditable(false);
        jTStatus.setEditable(false);
        jTOrder.setBorder(new TitledBorder(new EtchedBorder(), "Bestelling"));
        jTStatus.setBorder(new TitledBorder(new EtchedBorder(), "Status"));
        scOrder.setBorder(null);
        jSStatus = new JScrollPane(jTStatus);

        //Voeg items toe aan scherm
        add(scOrder);
        add(tekenpanel);
        add(jSStatus);
        add(jBInvoeren);
        add(jBUitvoeren);
        add(jBbestand);

        //Zet de positie van de items
        jSStatus.setBounds(10, 315, 325, 275);
        tekenpanel.setBounds(10, 10, 910, 270);
        scOrder.setBounds(350, 315, 325, 275);
        jBInvoeren.setBounds(720, 315, 200, 50);
        jBUitvoeren.setBounds(720, 535, 200, 50);
        jBbestand.setBounds(720, 375, 200, 25);
        jBInvoeren.addActionListener(this);
        jBUitvoeren.addActionListener(this);
        this.setVisible(true);

        /* NODIG WANNEER WE GEBRUIK MAKEN VAN ARDULINK
         //Genereerd 2 dialogen waar je de usb aansluitingen voor de arduinos kan selecteren
         send = new Zender(this);
         Link magazijnLink = send.getMagazijnLink();
         new KiesScherm(this, magazijnLink).setVisible(true);
         Link inpakLink = send.getInpakLink();
         new KiesScherm(this, inpakLink).setVisible(true);
         */
        arduino = new DeVerbinder(this);
        arduino.VerbindTSP();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jBInvoeren) {
            xmlreader = new XMLReader();
            log("=========================================");
            log(xmlreader.getMelding());
            if (xmlreader.getCompleet()) {
                jBbestand.setText("Bestand: " + xmlreader.getBestandNaam());
                SQLReader sqlreader = new SQLReader();
                log(sqlreader.getMelding());
                if (sqlreader.getWerkt()) {
                    order = new Order(xmlreader, sqlreader);
                    printOrder(xmlreader, jTOrder);
                    if (order.getNietBeschikbaar().size() > 0) {
                        log("Er zijn een aantal niet beschikbare artikelen in de order.\nDeze zullen niet opgehaald worden!");
                    }
                    tekenpanel.setOrder(order);
                    tekenpanel.repaint();

                } else {
                    jTOrder.setText("");
                    jBbestand.setText("Er zijn problemen met SQL!");
                }
            } else {
                jTOrder.setText("");
                jBbestand.setText("Geen geldig bestand geselecteerd!");
            }
        }
        //if (e.getSource() == jBUitvoeren && order != null) {
        if (e.getSource() == jBUitvoeren) {
            log("Start genereren route");
            //order.genereerRoute();
            log("Genereren route voltooid!");
            log("Start ophalen pakketen");
            //Dit is een test array om de robot te testen
            ArrayList<Locatie> test = new ArrayList<>();
            test.add(new Locatie(2, 2));
            test.add(new Locatie(2, 1));
            test.add(new Locatie(2, 5));
            test.add(new Locatie(2, 4));
            arduino.stuurPakketten(test);

            /* NODIG WANNEER WE GEBRUIK MAKEN VAN ARDULINK
             send.startListeners();
             send.stuurPakketten(test);
             */
            //De bon kunnen we op het einde laten uitprinten
            /*
             JTextArea printtest = new JTextArea();
             printOrder(xmlreader, printtest);
             try {
             printtest.print();
             } catch (PrinterException ex) {
             //Er ging iets mis
             }
             */
        }
    }

//Print de order in een textarea
    private void printOrder(XMLReader xmlreader, JTextArea jTOrder) {
        jTOrder.setText("");
        jTOrder.append("Ordernummer: " + xmlreader.getOrdernr() + "\n");
        jTOrder.append("Naam : " + xmlreader.getNaam() + "\n");
        jTOrder.append("Adres: " + xmlreader.getAdres() + "\n");
        jTOrder.append("Postcode: " + xmlreader.getPostcode() + "\n");
        jTOrder.append("Plaats : " + xmlreader.getPlaats() + "\n");
        jTOrder.append("Datum : " + xmlreader.getDatum() + "\n\n");

        jTOrder.append("Beschikbare artikelen:\n");
        for (Artikel art : order.getArtikelen()) {
            jTOrder.append("nr: " + art.getArtikelNr() + "\n");
        }

        jTOrder.append("\nNiet beschikbare artikelen:\n");
        for (Artikel art : order.getNietBeschikbaar()) {
            jTOrder.append("nr: " + art.getArtikelNr() + "\n");
        }
    }

    public void log(String melding) {
        jTStatus.append(melding + "\n");
        jTStatus.setCaretPosition(jTStatus.getDocument().getLength());
    }

    public Tekenpanel getTekenpanel() {
        return tekenpanel;
    }

    public Order getOrder() {
        return order;
    }

    public boolean nextLocation(int loc) {
        //Dit zorgt ervoor dat de volgende pijl wordt getekend in het tekenscherm
        ArrayList<Locatie> locaties = new ArrayList<>();
        //Kijken of hij klaar is met tekenen
        if (loc < order.getRoute().size()) {
            for (int i = 0; i <= loc; i++) {
                locaties.add(order.getRoute().get(i));
            }
            order.setLocatie(locaties);
            tekenpanel.repaint();
            return true;
        } else {
            //Wanneer hij klaar is met zijn route moet hij bpp starten
            return false;
        }
    }

}
