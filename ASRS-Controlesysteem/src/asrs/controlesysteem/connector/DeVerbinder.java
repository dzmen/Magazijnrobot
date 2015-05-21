/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.connector;

import asrs.controlesysteem.GUI.Scherm;
import asrs.controlesysteem.bestelling.Locatie;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.util.ArrayList;

public class DeVerbinder implements SerialPortEventListener {

    private SerialPort TSPVerbinding, BPPVerbinding;
    private String portTSP = "COM13";             //De poort waarmee de applicatie moet verbinden
    private String portBPP = "COM12";             //De poort waarmee de applicatie moet verbinden
    private static final int TIME_OUT = 2000;    //time in milliseconds (De wachtijd hoelang het kan duren voor een antwoord)
    private static final int BAUD_RATE = 9600; //baud rate to 9600bps (de snelheid waarmee de applicatie communiseerd met de arduino)
    private BufferedReader BPPInput, TSPInput;
    private OutputStream BPPOutput, TSPOutput;
    //Variable voor de route
    private Scherm scherm;
    //De artikelen
    private ArrayList<Locatie> route;
    //De locatie van de op te halen artikel
    private Locatie loc;
    //Huidige pakket
    private int huidigePakket = 0;

    //Zoek de poort
    public DeVerbinder(Scherm scherm) {
        this.scherm = scherm;
    }

    //Hiermee verbind je de applicatie met de arduino
    public void VerbindTSP() {
        //Hij kijkt of de poort bestaat waar we verbinding mee gaan maken
        CommPortIdentifier poort = null;
        try {
            poort = CommPortIdentifier.getPortIdentifier(portTSP);
        } catch (NoSuchPortException ex) {
            scherm.log(portTSP + " is niet gevonden!");
        }
        if (poort != null) {
            //following line checks whether there is the port i am looking for and whether it is serial
            if (poort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                scherm.log(portTSP + " is gevonden!");
                //Verbinden met de port
                try {
                    TSPVerbinding = (SerialPort) poort.open("ArduinoTSP", TIME_OUT);   //open de serialport. String is port naam, time_out is de tijd dat reactie kan duren
                    scherm.log("Verbinding met " + portTSP + " is gelukt!");

                    //set serial port parameters
                    TSPVerbinding.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                } catch (PortInUseException e) {
                    scherm.log(portTSP + " is al in gebruik door een andere applicatie!");
                } catch (NullPointerException e2) {
                    scherm.log(portTSP + " is zijn verbinding verloren?");
                } catch (UnsupportedCommOperationException e3) {
                }

                //input and output channels
                try {
                    //defining reader and output stream
                    TSPInput = new BufferedReader(new InputStreamReader(TSPVerbinding.getInputStream()));
                    TSPOutput = TSPVerbinding.getOutputStream();
                    //adding listeners to input and output streams
                    TSPVerbinding.addEventListener(this);
                    TSPVerbinding.notifyOnDataAvailable(true);
                    TSPVerbinding.notifyOnOutputEmpty(true);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else {
                scherm.log(portTSP + " wel gevonden maar niet serial!");
            }
        } else {
            scherm.log("Kan geen verbinding maken met usb");
        }
    }
    //end of portConncet method

    //Hiermee verbind je de applicatie met de arduino
    public void VerbindBPP() {
        //Hij kijkt of de poort bestaat waar we verbinding mee gaan maken
        CommPortIdentifier poort = null;
        try {
            poort = CommPortIdentifier.getPortIdentifier(portBPP);
        } catch (NoSuchPortException ex) {
            scherm.log(portBPP + " is niet gevonden!");
        }
        if (poort != null) {
            //following line checks whether there is the port i am looking for and whether it is serial
            if (poort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                scherm.log(portBPP + " is gevonden!");
                //Verbinden met de port
                try {
                    BPPVerbinding = (SerialPort) poort.open("ArduinoBPP", TIME_OUT);   //open de serialport. String is port naam, time_out is de tijd dat reactie kan duren
                    scherm.log("Verbinding met " + portBPP + " is gelukt!");

                    //set serial port parameters
                    BPPVerbinding.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                } catch (PortInUseException e) {
                    scherm.log(portBPP + " is al in gebruik door een andere applicatie!");
                } catch (NullPointerException e2) {
                    scherm.log(portBPP + " is zijn verbinding verloren?");
                } catch (UnsupportedCommOperationException e3) {
                }

                //input and output channels
                try {
                    //defining reader and output stream
                    BPPInput = new BufferedReader(new InputStreamReader(BPPVerbinding.getInputStream()));
                    BPPOutput = BPPVerbinding.getOutputStream();
                    //adding listeners to input and output streams
                    BPPVerbinding.addEventListener(this);
                    BPPVerbinding.notifyOnDataAvailable(true);
                    BPPVerbinding.notifyOnOutputEmpty(true);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else {
                scherm.log(portBPP + " wel gevonden maar niet serial!");
            }
        } else {
            scherm.log("Kan geen verbinding maken met usb");
        }
    }
    //end of portConncet method

    //readWrite serial port. Dit event gaat automatisch in een loop wanneer de port open wordt gezet en wordt verbonden met deze class
    @Override
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE && evt.getSource() == TSPVerbinding) { //if data available on serial port
            try {
                String inputLine = TSPInput.readLine();
                System.out.println(inputLine);
                if (inputLine.equalsIgnoreCase("ydone")) {
                    String send = "getpakket:" + huidigePakket;
                    TSPOutput.write(send.getBytes());
                    //todo
                } else if (inputLine.equalsIgnoreCase("pakketdone")) {
                    stuurPakketten();
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        } else if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE && evt.getSource() == BPPVerbinding) { //if data available on serial port
            try {
                String inputLine = TSPInput.readLine();
                System.out.println(inputLine);
                if (inputLine.equalsIgnoreCase("xdone")) {
                    String send = "yas:" + loc.getY();
                    TSPOutput.write(send.getBytes());
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    //closePort method
    public void close() {
        if (TSPVerbinding != null) {
            TSPVerbinding.close(); //close serial port
            TSPInput = null;        //close input and output streams
            TSPOutput = null;
        }
        if (BPPVerbinding != null) {
            BPPVerbinding.close(); //close serial port
            BPPInput = null;        //close input and output streams
            BPPOutput = null;
        }
    }

    public void stuurPakketten(ArrayList<Locatie> route) {
        this.route = new ArrayList<>(route);
        stuurPakketten();
    }

    private void stuurPakketten() {
        huidigePakket++;
        if (huidigePakket > route.size()) {
            //Done met pakken
        } else {
            loc = route.get(huidigePakket - 1);
            try {
                String send = "xas:" + loc.getX();
                BPPOutput.write(send.getBytes());
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }
}
