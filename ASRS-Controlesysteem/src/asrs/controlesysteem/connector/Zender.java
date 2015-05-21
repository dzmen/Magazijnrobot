/*
 *   Stap 1 = X as
 *   Stap 2 = Y as
 *   Stap 3 = Z as vooruit
 *   Stap 4 = Y as 2 omhoog
 *   Stap 5 = Z as achteruit
 *   Stap 6 = Y as 2 omlaag
 *
 */
package asrs.controlesysteem.connector;

import asrs.controlesysteem.GUI.Scherm;
import asrs.controlesysteem.bestelling.Locatie;
import java.util.ArrayList;
import org.zu.ardulink.Link;
import org.zu.ardulink.event.AnalogReadChangeEvent;
import org.zu.ardulink.event.AnalogReadChangeListener;
import org.zu.ardulink.protocol.IProtocol;

public class Zender {

    private static final long serialVersionUID = -8011033975724290405L;
    //De communicatie lijnen met de arduino
    private Link lMagazijn;
    private Link lInpak;
    //Pinnen voor X as
    private int pinXdir = 6; //M1 direction
    private int pinXpwm = 7; //M1 speed
    private int xSpeed = 90;
    private int pinXLed = 13;
    //Pinnen voor Y as
    private int pinYdir = 4; //M2
    private int pinYpwm = 5; //M2
    private int ySpeed = 110;
    private int pinYLed = 13;
    //Pinnen voor de Z as
    private int pinZdir = 7; //M2 //ON is vooruit
    private int pinZpwm = 6; //M2
    private int zSpeed = 110;
    private int pinZLed = 12;
    //Pin ldr
    private int pinYLDR = 0;
    private int pinZLDR = 1;
    private int pinXLDR = 0;
    //Overige gegevens
    private int huidigeX = 2;
    private int huidigeY = 0;
    //De hoogtes van elke vak in stippen
    private int[] vakkenY = new int[]{0, 6, 7, 7, 5, 7};
    //De breedte van de vakken in stippen
    private int[] vakkenX = new int[]{0, 5, 7, 6, 5, 5};
    //De diepte van de vakken in stippen
    private int[] vakkenZ = new int[]{0, 7, 5, 3, 1};
    //Het scherm om een visuale weergave te kunnen doen
    private Scherm scherm;
    //De artikelen
    private ArrayList<Locatie> route;
    //De locatie van de op te halen artikel
    private Locatie loc;
    //De huidige stap waar die is
    private int stap = 0;
    //Huidige pakket
    private int huidigePakket = 0;
    //Dit zorgt voor de positie movement
    private int counterN = 0;
    //Wait time
    private int waittime = 5;

    public Zender(Scherm scherm) {
        this.scherm = scherm;
        try {
            lMagazijn = Link.createInstance("Magazijn robot");
            lInpak = Link.createInstance("Verpakings robot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stuurPakketten() {
        huidigePakket++;
        if (huidigePakket > route.size()) {
            //DONE
        } else {
            loc = route.get(huidigePakket - 1);
            if (loc.getX() != huidigeX) {
                stap = 1;
                stuurX();
            } else {
                stap = 2;
                stuurY();
            }
        }
    }

    private void stuurX() {
        int stappen = 0;
        lInpak.sendPowerPinSwitch(pinXLed, IProtocol.POWER_HIGH);
        if (huidigeX - loc.getX() < 0) {
            for (int i = huidigeX; i >= loc.getX(); i--) {
                stappen += vakkenX[i];
            }
            lInpak.sendPowerPinSwitch(pinXdir, IProtocol.POWER_HIGH);
            sleep(waittime);
            lInpak.sendPowerPinIntensity(pinXpwm, xSpeed);
        } else {
            for (int i = huidigeX; i <= loc.getX(); i++) {
                stappen += vakkenX[i];
            }
            lInpak.sendPowerPinSwitch(pinXdir, IProtocol.POWER_LOW);
            sleep(waittime);
            lInpak.sendPowerPinIntensity(pinXpwm, xSpeed);
        }
        counterN = stappen;
        huidigeX = loc.getX();
    }

    private void stuurY() {
        int stappen = 0;
        lMagazijn.sendPowerPinSwitch(pinYLed, IProtocol.POWER_HIGH);
        if (loc.getY() - huidigeY < 0) {
            //Deze code werkt
            for (int i = huidigeY - 1; i >= loc.getY(); i--) {
                stappen += vakkenY[i];
            }
            System.out.println("Aantal Y 1 stappen: " + stappen);
            lMagazijn.sendPowerPinSwitch(pinYdir, IProtocol.POWER_LOW);
            sleep(waittime);
            lMagazijn.sendPowerPinIntensity(pinYpwm, ySpeed);
        } else {
            //Deze code werkt
            for (int i = huidigeY + 1; i <= loc.getY(); i++) {
                stappen += vakkenY[i];
            }
            System.out.println("Aantal 2 stappen: " + stappen);
            lMagazijn.sendPowerPinSwitch(pinYdir, IProtocol.POWER_HIGH);
            sleep(waittime);
            lMagazijn.sendPowerPinIntensity(pinYpwm, ySpeed);
        }
        counterN = stappen;
        huidigeY = loc.getY();
    }

    private void pakPakket(int stap) {
        if (stap == 1) {
            this.stap = 3;
            counterN = vakkenZ[huidigePakket];
            lMagazijn.sendPowerPinSwitch(pinZLed, IProtocol.POWER_HIGH);
            sleep(waittime);
            lMagazijn.sendPowerPinSwitch(pinZdir, IProtocol.POWER_HIGH);
            lMagazijn.sendPowerPinIntensity(pinZpwm, zSpeed);
        }
        if (stap == 2) {
            this.stap = 4;
            counterN = 3;
            lMagazijn.sendPowerPinSwitch(pinYLed, IProtocol.POWER_HIGH);
            sleep(waittime);
            lMagazijn.sendPowerPinSwitch(pinYdir, IProtocol.POWER_HIGH);
            lMagazijn.sendPowerPinIntensity(pinYpwm, ySpeed);
        }
        if (stap == 3) {
            this.stap = 5;
            counterN = vakkenZ[huidigePakket];
            lMagazijn.sendPowerPinSwitch(pinZLed, IProtocol.POWER_HIGH);
            sleep(waittime);
            lMagazijn.sendPowerPinSwitch(pinZdir, IProtocol.POWER_LOW);
            lMagazijn.sendPowerPinIntensity(pinZpwm, zSpeed);
        }
        if (stap == 4) {
            this.stap = 6;
            counterN = 3;
            lMagazijn.sendPowerPinSwitch(pinYLed, IProtocol.POWER_HIGH);
            sleep(waittime);
            lMagazijn.sendPowerPinSwitch(pinYdir, IProtocol.POWER_LOW);
            lMagazijn.sendPowerPinIntensity(pinYpwm, ySpeed);
        } else if (stap == 5) {
            stuurPakketten();
        }

    }

    public void stuurPakketten(ArrayList<Locatie> route) {
        this.route = new ArrayList<>(route);
        stuurPakketten();
    }

    public boolean isConnected(String mode) {
        if (mode.equalsIgnoreCase("m")) {
            return lMagazijn.isConnected();
        } else {
            return lInpak.isConnected();
        }
    }

    ///////////////////
    ///DE LUISTERAARS
    ///////////////////
    public void startXListener() {
        lInpak.addAnalogReadChangeListener(new AnalogReadChangeListener() {
            //Het punt tussen licht en donker op de sensor
            private final int stapWaarde = 170;
            private int counter = 0;
            private boolean wachten = false;

            //Dit luisterd naar de Lijn sensor weizigingen
            @Override
            public void stateChanged(AnalogReadChangeEvent e) {
                if (stap == 1 && e.getValue() < stapWaarde && !wachten) {
                    wachten = true;
                    counter++;
                    System.out.println("het licht gezien: " + counter);
                    if (counter == counterN) {
                        counter = 0;
                        counterN = 0;
                        lInpak.sendPowerPinIntensity(pinXpwm, IProtocol.POWER_LOW);
                        sleep(waittime);
                        lInpak.sendPowerPinSwitch(pinXLed, IProtocol.POWER_LOW);
                        stap = 2;
                        stuurY();
                    }
                } else if (e.getValue() > stapWaarde + 100 && wachten) {
                    wachten = false;
                    System.out.println("het donker gezien");
                }
                //System.out.println("Waarde: " + e.getValue());
            }

            @Override
            public int getPinListening() {
                return pinXLDR;
            }
        }
        );
    }

    public void startYListener() {
        lMagazijn.addAnalogReadChangeListener(new AnalogReadChangeListener() {
            //Het punt tussen licht en donker op de sensor
            private final int stapWaarde = 170;
            private int counter = 0;
            private boolean wachten = false;

            //Dit luisterd naar de LDR weizigingen
            @Override
            public void stateChanged(AnalogReadChangeEvent e) {
                if (e.getValue() < stapWaarde && !wachten) {
                    wachten = true;
                    if (stap == 2 || stap == 4 || stap == 6) {
                        counter++;
                        System.out.println("het licht gezien: " + counter);
                        if (counter == counterN) {
                            counter = 0;
                            counterN = 0;
                            lMagazijn.sendPowerPinIntensity(pinYpwm, IProtocol.POWER_LOW);
                            sleep(waittime);
                            lMagazijn.sendPowerPinSwitch(pinYLed, IProtocol.POWER_LOW);
                            if (stap == 2) {
                                pakPakket(1);
                            } else if (stap == 4) {
                                pakPakket(3);
                            } else if (stap == 6) {
                                pakPakket(5);
                            }
                        }
                    }

                } else if (e.getValue() > stapWaarde + 100 && wachten) {
                    wachten = false;
                    System.out.println("het donker gezien");
                }
                //System.out.println("Waarde: " + e.getValue());
            }

            @Override
            public int getPinListening() {
                return pinYLDR;
            }
        }
        );
    }

    public void startZListener() {
        lMagazijn.addAnalogReadChangeListener(new AnalogReadChangeListener() {
            private boolean wachten = false;
            private final int stapWaarde = 450;
            private int counter = 0;

            //Dit luisterd naar de LDR weizigingen
            @Override
            public void stateChanged(AnalogReadChangeEvent e) {
                if (e.getValue() < stapWaarde && !wachten) {
                    wachten = true;
                    if (stap == 3 || stap == 5) {
                        counter++;
                        System.out.println("het licht gezien: " + counter);
                        if (counter == counterN) {
                            counter = 0;
                            counterN = 0;
                            lMagazijn.sendPowerPinIntensity(pinZpwm, IProtocol.POWER_LOW);
                            sleep(waittime);
                            lMagazijn.sendPowerPinSwitch(pinZLed, IProtocol.POWER_LOW);
                            if (stap == 3) {
                                pakPakket(2);
                            } else if (stap == 5) {
                                pakPakket(4);
                            }
                        }
                    }
                } else if (e.getValue() > stapWaarde + 100 && wachten) {
                    wachten = false;
                    System.out.println("het donker gezien");
                }
                //System.out.println("Waarde: " + e.getValue());
            }

            @Override
            public int getPinListening() {
                return pinZLDR;
            }
        }
        );
    }

    //De getters
    public Link getMagazijnLink() {
        return lMagazijn;
    }

    public Link getInpakLink() {
        return lInpak;
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
