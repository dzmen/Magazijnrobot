/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.TSP;

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
import java.io.IOException;

public class NotInUseArduinoTSP implements SerialPortEventListener {

    private SerialPort serialPort;
    private CommPortIdentifier portId = null;
    private String portName = "COM13";             //De poort waarmee de applicatie moet verbinden
    private static final int TIME_OUT = 2000;    //time in milliseconds (De wachtijd hoelang het kan duren voor een antwoord)
    private static final int BAUD_RATE = 9600; //baud rate to 9600bps (de snelheid waarmee de applicatie communiseerd met de arduino)
    private BufferedReader input;
    private OutputStream output;
    private String message;
    private boolean connected = false;
    private boolean opgepakt;
    private Scherm scherm;

    //Zoek de poort
    public NotInUseArduinoTSP(Scherm scherm) {
        this.scherm = scherm;
        //Hij kijkt of de poort bestaat waar we verbinding mee gaan maken
        CommPortIdentifier ports = null;
        try {
            ports = CommPortIdentifier.getPortIdentifier(portName);
        } catch (NoSuchPortException ex) {
            message = portName + " is niet gevonden!";
        }
        //following line checks whether there is the port i am looking for and whether it is serial
        if (ports.getPortType() == CommPortIdentifier.PORT_SERIAL) {
            message = portName + " is gevonden!";
            portId = ports;
        } else {
            message = portName + " wel gevonden maar niet serial!";
        }
    }

    //Hiermee verbind je de applicatie met de arduino
    public void Connect() {
        if (portId != null) {
            //verbind met poort
            try {
                serialPort = (SerialPort) portId.open("ArduinoTSP", TIME_OUT);   //open de serialport. String is port naam, time_out is de tijd dat reactie kan duren
                message = "Verbinding met " + portName + " is gelukt!";

                //set serial port parameters
                serialPort.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                connected = true;
            } catch (PortInUseException e) {
                message = portName + " is al in gebruik door een andere applicatie!";
            } catch (NullPointerException e2) {
                message = portName + " is zijn verbinding verloren?";
            } catch (UnsupportedCommOperationException e3) {
            }

            //input and output channels
            try {
                //defining reader and output stream
                input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                output = serialPort.getOutputStream();
                //adding listeners to input and output streams
                serialPort.addEventListener(this);
                serialPort.notifyOnDataAvailable(true);
                serialPort.notifyOnOutputEmpty(true);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            message = "Kan geen verbinding maken met usb";
        }
    }
    //end of portConncet method

    //readWrite serial port. Dit event gaat automatisch in een loop wanneer de port open wordt gezet en wordt verbonden met deze class
    @Override
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) { //if data available on serial port
            try {
                String inputLine = input.readLine();
                System.out.println(inputLine);
                if (inputLine.equalsIgnoreCase("done")) {
                    scherm.nextLocation();
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    public String getMessage() {
        return this.message;
    }

    public boolean setPackage(Locatie loc) {
        try {
            String send = loc.getX() + "," + loc.getY();
            output.write(send.getBytes());
            return true;
        } catch (IOException ex) {
            message = ex.getMessage();
            return false;
        }
    }

    //closePort method
    public void close() {
        if (serialPort != null) {
            serialPort.close(); //close serial port
        }
        input = null;        //close input and output streams
        output = null;
    }
}
