/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.connector;

import asrs.controlesysteem.GUI.Scherm;
import org.zu.ardulink.Link;
import org.zu.ardulink.event.AnalogReadChangeEvent;
import org.zu.ardulink.event.AnalogReadChangeListener;
import org.zu.ardulink.protocol.IProtocol;
import org.zu.ardulink.protocol.MessageInfo;

public class Zender implements AnalogReadChangeListener {

    private static final long serialVersionUID = -8011033975724290405L;
    //De communicatie lijnen met de arduino
    private Link lMagazijn;
    private Link lInpak;
    //Pinnen voor X en Y as
    private int pinXdir = 4;
    private int pinXpwm = 5;
    private int xSpeed = 90;
    //Pinnen voor Voor, Achter en Verpakings robot
    private int pinYdir = 7;
    private int pinYpwm = 6;
    private int ySpeed = 90;
    //Pin ldr
    private int pinLDR = 0;
    //Overige gegevens
    private int huidigeX = 0;
    private int huidigeY = 0;
    //De ldr waarde. Eronder is in gat met led, erboven is in het donker
    private int stapWaarde = 650;
    //De hoogtes van elke vak in stippen
    private int[] vakkenY = new int[]{0, 12, 4, 6, 5};
    //De breedte van de vakken in stippen
    private int[] vakkenX = new int[]{0, 3, 7, 6, 5};
    //kijken of hij naar de ldr moet luisteren
    private boolean luisteren = true;
    private boolean wachten = false;
    private int counter = 0;
    //Het scherm om een visuale weergave te kunnen doen
    private Scherm scherm;

    public Zender(Scherm scherm) {
        this.scherm = scherm;
        try {
            lMagazijn = Link.createInstance("Magazijn robot");
            lInpak = Link.createInstance("Verpakings robot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected(String mode) {
        if (mode.equalsIgnoreCase("m")) {
            return lMagazijn.isConnected();
        } else {
            return lInpak.isConnected();
        }
    }

    //Deze functie is gemaakt om dat er pas geluisterd kan woorden na dat hij weet welke usb poorten hij heeft
    public void startListeners() {
        lMagazijn.addAnalogReadChangeListener(this);
        //lInpak.addAnalogReadChangeListener(this);
    }

    public void stuurtX(int a) {
        if (a < 0) {
            a = Math.abs(a);
            lInpak.sendPowerPinIntensity(pinXdir, IProtocol.POWER_HIGH);
            lInpak.sendPowerPinIntensity(pinXpwm, xSpeed);
        } else {
            lInpak.sendPowerPinIntensity(pinXdir, IProtocol.POWER_LOW);
            lInpak.sendPowerPinIntensity(pinXpwm, xSpeed);
        }
        luisteren = false;
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

    //Dit luisterd naar de LDR weizigingen
    @Override
    public void stateChanged(AnalogReadChangeEvent e) {
        if (luisteren && e.getValue() < stapWaarde && !wachten) {
            wachten = true;
            counter++;
            System.out.println("het licht gezien: " + counter);
            scherm.nextLocation(1);
        } else if (e.getValue() > stapWaarde && wachten) {
            wachten = false;
            System.out.println("het donker gezien");
        }
    }

    @Override
    public int getPinListening() {
        return this.pinLDR;
    }

    //De delay van arduino maar dan in java code
    private static void sleep(int millis) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
