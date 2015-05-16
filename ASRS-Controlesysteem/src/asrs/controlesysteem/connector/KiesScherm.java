/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.connector;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.zu.ardulink.Link;
import org.zu.ardulink.gui.SerialConnectionPanel;

public class KiesScherm extends JDialog implements ActionListener {

    //Ik zou echt niet weten wat dit doet. Dit moet er in voor ardulink, meer weet ik niet....
    private static final long serialVersionUID = -5884548646729927244L;
    private final JButton jBConnect;
    private final JTextArea jTLog;
    private final SerialConnectionPanel serialConnectionPanel;
    private final Link link;

    public KiesScherm(JFrame frame, Link link) {
        super(frame, true);
        setTitle("Kies de poort van: " + link.getName());
        this.link = link;
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        //Dit is een jpanel die gemaakt is door ardulink. Hierin kan je een usb poort zoeken en de bitrate aangeven
        serialConnectionPanel = new SerialConnectionPanel();

        jBConnect = new JButton("Connect");
        jTLog = new JTextArea();
        add(serialConnectionPanel, BorderLayout.NORTH);
        add(jTLog, BorderLayout.CENTER);
        add(jBConnect, BorderLayout.SOUTH);

        jBConnect.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jBConnect) {
            //Controlleren of er een port geselecteerd is en op de poort begint met COM (Dit is alleen bij windows het geval)
            if (!serialConnectionPanel.getConnectionPort().isEmpty() && serialConnectionPanel.getConnectionPort().startsWith("COM")) {
                String connectionPort = serialConnectionPanel.getConnectionPort();
                //Zet de bitrate
                int baudRate = Integer.parseInt(serialConnectionPanel.getBaudRate());
                link.connect(connectionPort, baudRate);
                if (link.isConnected()) {
                    this.setVisible(false);
                } else {
                    jTLog.append("Verbinding niet gelukt! \n");
                }
            }
        }
    }
}
