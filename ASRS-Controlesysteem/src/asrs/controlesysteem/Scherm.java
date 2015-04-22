/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Quinten
 */
public class Scherm extends JFrame implements ActionListener {
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
        tekenpanel = new Tekenpanel();
        jTStatus = new JTextArea (5, 5);
        jBInvoeren = new JButton ("XML-order ophalen");
        jBUitvoeren = new JButton ("Uitvoeren");
        jBbestand = new JLabel("Bestand kiezen...");
        
        
        //set components properties
        jTOrder.setEnabled (false);
        jTStatus.setEnabled (false);
        jTOrder.setBorder(BorderFactory.createLineBorder(Color.black));
        tekenpanel.setBorder(BorderFactory.createLineBorder(Color.black));
        jTStatus.setBorder(BorderFactory.createLineBorder(Color.black));
        //adjust size and set layout
        setPreferredSize (new Dimension (960, 640));
        setLayout (null);

        //add components
        add (jTOrder);
        add (tekenpanel);
        add (jTStatus);
        add (jBInvoeren);
        add (jBUitvoeren);
        add (jBbestand);
        
        //set component bounds (only needed by Absolute Positioning)
        jTOrder.setBounds (10, 315, 275, 275);
        tekenpanel.setBounds (10, 10 , 910, 275);
        jTStatus.setBounds (300, 315, 275, 275);
        jBInvoeren.setBounds (720, 315, 200, 50);
        jBUitvoeren.setBounds (720, 535 , 200, 50);
        jBbestand.setBounds(720, 375, 200, 25);
        jBInvoeren.addActionListener(this);
        setSize(960,640);
        setVisible (true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
         if (e.getSource() == jBInvoeren) {
            JFileChooser openFile = new JFileChooser();
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Order file (*.xml)", "xml");
            openFile.setFileFilter(xmlFilter);
            int resultaat = openFile.showOpenDialog(null);
            if (resultaat == openFile.APPROVE_OPTION) {
                XMLReader reader = new XMLReader(openFile.getSelectedFile());
                if (reader.getCompleet()) {
                    jBbestand.setText(openFile.getSelectedFile().getName());
                } else {
                    jBbestand.setText("Geen geldig bestand geselecteerd!");
                }
            }
             }
         }
}   

