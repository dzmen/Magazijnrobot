/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem;


import java.awt.*;
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
    private JTextArea jTOrder;
    private Tekenpanel tekenpanel;
    private JTextArea jTStatus;
    private JButton jBInvoeren;
    private JButton jBUitvoeren;
    private JLabel jBbestand;
    public Scherm(){
        setTitle("AS/RS-Controlesysteem");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //construct components
        jTOrder = new JTextArea (5, 5);
        scOrder =  new JScrollPane(jTOrder);
        tekenpanel = new Tekenpanel();
        jTStatus = new JTextArea (5, 5);
        jBInvoeren = new JButton ("XML-order ophalen");
        jBUitvoeren = new JButton ("Uitvoeren");
        jBbestand = new JLabel("Bestand kiezen...");
        
        
        //set components properties
        jTOrder.setEditable (false);
        jTStatus.setEditable (false);
        jTOrder.setBorder( new TitledBorder ( new EtchedBorder (), "Bestelling" ) );;
        tekenpanel.setBorder(new TitledBorder ( new EtchedBorder (), "Route" ) );;
        jTStatus.setBorder( new TitledBorder ( new EtchedBorder (), "Status" ) );;
        //adjust size and set layout
        setPreferredSize (new Dimension (980, 660));
        setLayout (null);

        //add components
        add (scOrder);
        add (tekenpanel);
        add (jTStatus);
        add (jBInvoeren);
        add (jBUitvoeren);
        add (jBbestand);
        
        //set component bounds (only needed by Absolute Positioning)
        jTStatus.setBounds (10, 315, 275, 275);
        tekenpanel.setBounds (10, 10 , 910, 275);
        scOrder.setBounds (300, 315, 275, 275);
        jBInvoeren.setBounds (720, 315, 200, 50);
        jBUitvoeren.setBounds (720, 535 , 200, 50);
        jBbestand.setBounds(720, 375, 200, 25);
        jBInvoeren.addActionListener(this);
        setSize(960,640);
        setVisible (true);
    }
    
    public void printOrder(String string){
            jTOrder.append(" " + string + "\n");
    }
    @Override
    public void actionPerformed(ActionEvent e){
         if (e.getSource() == jBInvoeren) {
            JFileChooser openFile = new JFileChooser();
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Order file (*.xml)", "xml");
            openFile.setFileFilter(xmlFilter);
            int resultaat = openFile.showOpenDialog(null);
            if (resultaat == openFile.APPROVE_OPTION) {
                XMLReader reader = new XMLReader(openFile.getSelectedFile(), this);
                if (reader.getCompleet()) {
                    jBbestand.setText("Bestand "+openFile.getSelectedFile().getName());
                } else {
                    jBbestand.setText("Geen geldig bestand geselecteerd!");
                }
            }
             }
         }
}   

