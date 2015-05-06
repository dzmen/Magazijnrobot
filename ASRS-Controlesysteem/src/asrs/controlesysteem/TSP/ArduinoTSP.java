/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.TSP;

import org.zu.ardulink.Link;
import org.zu.ardulink.event.ConnectionEvent;
import org.zu.ardulink.event.ConnectionListener;
import org.zu.ardulink.event.DisconnectionEvent;

public class ArduinoTSP implements ConnectionListener {

    private static final long serialVersionUID = -5884548646729927244L;
    private Link link;
    private String port = "COM13";
    private int baudRate = 9600;

    public ArduinoTSP() {
        link = Link.getDefaultInstance();
        link.connect(port, baudRate);
    }

    public void connect() {
        if (!isConnected()) {
            this.link.connect(port, baudRate);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            this.link.disconnect();
        }
    }

    public boolean isConnected() {
        return this.link.isConnected();
    }

    @Override
    public void connected(ConnectionEvent ce) {
        ledOn();
    }

    @Override
    public void disconnected(DisconnectionEvent de) {
        //TODO: Wat te doen als de verbinding verloren is
    }

    public void ledOn() {
        link.sendCustomMessage("ledon");
    }

    public void ledOff() {
        link.sendCustomMessage("ledoff");
    }

}
