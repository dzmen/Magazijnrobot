/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.GUI;

import asrs.controlesysteem.readers.XMLReader;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Quinten
 */
public class Scherm extends JFrame implements ActionListener {

    private JScrollPane scOrder;
    private Tekenpanel tekenpanel;
    private JTextArea jTOrder, jTStatus;
    private JButton jBInvoeren, jBUitvoeren;
    private JLabel jBbestand;
    private XMLReader xmlreader;

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

        //Voeg items toe aan scherm
        add(scOrder);
        add(tekenpanel);
        add(jTStatus);
        add(jBInvoeren);
        add(jBUitvoeren);
        add(jBbestand);

        //Zet de positie van de items
        jTStatus.setBounds(10, 315, 325, 275);
        tekenpanel.setBounds(10, 10, 910, 270);
        scOrder.setBounds(350, 315, 325, 275);
        jBInvoeren.setBounds(720, 315, 200, 50);
        jBUitvoeren.setBounds(720, 535, 200, 50);
        jBbestand.setBounds(720, 375, 200, 25);
        jBInvoeren.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jBInvoeren) {
            JFileChooser openFile = new JFileChooser();
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Order file (*.xml)", "xml");
            openFile.setFileFilter(xmlFilter);
            int resultaat = openFile.showOpenDialog(null);
            if (resultaat == openFile.APPROVE_OPTION) {
                xmlreader = new XMLReader(openFile.getSelectedFile(), this);
                jTStatus.append(xmlreader.getMelding() + "\n");
                if (xmlreader.getCompleet()) {
                    jBbestand.setText("Bestand: " + openFile.getSelectedFile().getName());
                    printOrder(jTOrder);
                } else {
                    jBbestand.setText("Geen geldig bestand geselecteerd!");
                }
            }
        }
    }

    //Print de order in een textarea
    private void printOrder(JTextArea jTOrder) {
        jTOrder.setText("");
        jTOrder.append("Ordernummer: " + xmlreader.getOrdernr() + "\n");
        jTOrder.append("Naam : " + xmlreader.getNaam() + "\n");
        jTOrder.append("Adres: " + xmlreader.getAdres() + "\n");
        jTOrder.append("Postcode: " + xmlreader.getPostcode() + "\n");
        jTOrder.append("Plaats : " + xmlreader.getPlaats() + "\n");
        jTOrder.append("Datum : " + xmlreader.getDatum() + "\n\n");

        jTOrder.append("Artikelen:\n");
        for (int i = 0; i < xmlreader.getArtikelen().size();) {
            jTOrder.append("artikelnr: " + xmlreader.getArtikelen().get(i) + "\n");
            i++;
        }
    }
}
