/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.connector;

import asrs.controlesysteem.GUI.Scherm;
import asrs.controlesysteem.bestelling.Artikel;
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
import java.io.IOException;
import java.util.ArrayList;

public class DeVerbinder implements SerialPortEventListener {

    private SerialPort TSPVerbinding, BPPVerbinding;
    private final String portTSP = "COM4";             //De poort waarmee de applicatie moet verbinden
    private final String portBPP = "COM5";             //De poort waarmee de applicatie moet verbinden
    private static final int TIME_OUT = 2000;    //time in milliseconds (De wachtijd hoelang het kan duren voor een antwoord)
    private static final int BAUD_RATE = 9600; //baud rate to 9600bps (de snelheid waarmee de applicatie communiseerd met de arduino)
    private BufferedReader BPPInput, TSPInput;
    private OutputStream BPPOutput, TSPOutput;
    //Variable voor de route
    private final Scherm scherm;
    //De artikelen
    private ArrayList<Artikel> route;
    //De locatie van de op te halen artikel
    private Locatie loc;
    //Huidige pakket
    private int huidigePakket = 0;

    //Zoek de poort
    public DeVerbinder(Scherm scherm) {
        this.scherm = scherm;
        VerbindTSP();
        VerbindBPP();
    }

    //Hiermee verbind je de applicatie met de arduino
    public boolean VerbindTSP() {
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
                    return true;
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else {
                scherm.log(portTSP + " wel gevonden maar niet serial!");
            }
        } else {
            scherm.log("Kan geen verbinding maken met usb");
        }
        return false;
    }
    //end of portConncet method

    //Hiermee verbind je de applicatie met de arduino
    public boolean VerbindBPP() {
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
                    return true;
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else {
                scherm.log(portBPP + " wel gevonden maar niet serial!");
            }
        } else {
            scherm.log("Kan geen verbinding maken met usb");
        }
        return false;
    }
    //end of portConncet method

    //readWrite serial port. Dit event gaat automatisch in een loop wanneer de port open wordt gezet en wordt verbonden met deze class
    @Override
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) { //if data available on serial port
            try {
                String inputLine = TSPInput.readLine();
                System.out.println(inputLine);
                if (inputLine.equalsIgnoreCase("ydone")) {
                    sendTSP("krijgpakket:" + huidigePakket);
                }
                if (inputLine.equalsIgnoreCase("pakketdone")) {
                    stuurPakketten();
                }
                if (inputLine.equalsIgnoreCase("dropdone")) {
                    dropPakketten();
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        } else if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) { //if data available on serial port
            try {
                String inputLine = BPPInput.readLine();
                System.out.println(inputLine);
                if (inputLine.equalsIgnoreCase("xdone")) {
                    sendTSP("yas:" + loc.getY());
                }
                if (inputLine.equalsIgnoreCase("zetdone")) {
                    sendTSP("drop:" + huidigePakket);
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    public void stuurPakketten(ArrayList<Artikel> route) {
        this.route = new ArrayList<>(route);
        stuurPakketten();
    }

    private void stuurPakketten() {
        huidigePakket++;
        if (huidigePakket > route.size()) {
            scherm.log("Pakketten zullen nu naar de bakken gebracht worden!");
            dropPakketten();
        } else {
            loc = route.get(huidigePakket - 1).getLocatie();
            scherm.nextLocation(huidigePakket);
            try {
                sendBPP("xas:" + loc.getX());
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    private void dropPakketten() {
        huidigePakket--;
        if (huidigePakket > 0) {
            //Uitvoeren
            try {
                sendBPP("zetdoos:" + huidigePakket);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        } else {
            scherm.log("De robot is nu klaar!");
        }
    }

    private void sendTSP(String data) {
        data = data + '\n';
        try {
            TSPOutput.write(data.getBytes());
        } catch (IOException ex) {
            System.out.println("TSP data niet verzonden");
        }
    }

    private void sendBPP(String data) {
        data = data + '\n';
        try {
            BPPOutput.write(data.getBytes());
        } catch (IOException ex) {
            System.out.println("BPP data niet verzonden");
        }
    }
}
