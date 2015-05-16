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
import org.zu.ardulink.Link;
import org.zu.ardulink.gui.SerialConnectionPanel;

public class KiesScherm extends JDialog implements ActionListener {

    private static final long serialVersionUID = -5884548646729927244L;
    private final JButton jBConnect;
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
        serialConnectionPanel = new SerialConnectionPanel();
        jBConnect = new JButton("Connect");
        add(jBConnect, BorderLayout.SOUTH);
        add(serialConnectionPanel, BorderLayout.NORTH);
        jBConnect.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jBConnect) {
            if (!serialConnectionPanel.getConnectionPort().isEmpty() && serialConnectionPanel.getConnectionPort().startsWith("COM")) {
                String connectionPort = serialConnectionPanel.getConnectionPort();
                int baudRate = Integer.parseInt(serialConnectionPanel.getBaudRate());
                link.connect(connectionPort, baudRate);
                if (link.isConnected()) {
                    this.setVisible(false);
                }
            }
        }
    }
}
