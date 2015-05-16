/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.connector;

import org.zu.ardulink.Link;
import org.zu.ardulink.event.AnalogReadChangeEvent;
import org.zu.ardulink.event.AnalogReadChangeListener;
import org.zu.ardulink.protocol.IProtocol;
import org.zu.ardulink.protocol.MessageInfo;

public class Zender implements AnalogReadChangeListener {

    private static final long serialVersionUID = -5884548646729927244L;
    private String mode;
    private Link lMagazijn;
    private Link lInpak;
    //Pinnen boven beneden
    private int pinXdir = 4;
    private int pinXpwm = 5;
    //Pinnen voor achter
    private int pinYdir = 7;
    private int pinYpwm = 6;
    //Pin ldr
    private int pinLDR = 0;

    public Zender(String mode) {
        this.mode = mode;
        try {
            if (mode.equalsIgnoreCase("m")) {
                lMagazijn = Link.createInstance("Magazijn robot");
            } else {
                lInpak = Link.createInstance("Verpakings robot");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        if (mode.equalsIgnoreCase("m")) {
            return lMagazijn.isConnected();
        } else {
            return lInpak.isConnected();
        }
    }

    public void stuurCommandoMagazijn(int command) {
        MessageInfo info = new MessageInfo();
        try {
            switch (command) {
                case 0:
                    //Command: X pos+
                    lMagazijn.sendPowerPinIntensity(pinXdir, IProtocol.POWER_LOW);
                    lMagazijn.sendPowerPinIntensity(pinXpwm, IProtocol.POWER_HIGH);
                    break;
                case 1:
                    //Command: X pos-
                    lMagazijn.sendPowerPinIntensity(pinXdir, 254);
                    lMagazijn.sendPowerPinIntensity(pinXpwm, 254);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(info.getMessageReceived());
        }
    }

    public Link getMagazijnLink() {
        return lMagazijn;
    }

    public Link getInpakLink() {
        return lInpak;
    }

    @Override
    public void stateChanged(AnalogReadChangeEvent e) {
        //Hierin komen de waarders die de ldr gaan afgeven

    }

    @Override
    public int getPinListening() {
        return this.pinLDR;
    }
}
