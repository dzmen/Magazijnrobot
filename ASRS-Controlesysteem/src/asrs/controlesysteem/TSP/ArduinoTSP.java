/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.TSP;

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
import java.util.Scanner;

public class ArduinoTSP implements SerialPortEventListener {

    private SerialPort serialPort;
    private CommPortIdentifier portId = null;
    private String portName = "COM13";             //De poort waarmee de applicatie moet verbinden
    private static final int TIME_OUT = 2000;    //time in milliseconds (De wachtijd hoelang het kan duren voor een antwoord)
    private static final int BAUD_RATE = 9600; //baud rate to 9600bps (de snelheid waarmee de applicatie communiseerd met de arduino)
    private BufferedReader input;
    private OutputStream output;
    private String name;
    Scanner inputName;

    //Zoek de poort
    public ArduinoTSP() {
        //Hij kijkt of de poort bestaat waar we verbinding mee gaan maken
        CommPortIdentifier ports = null;
        try {
            ports = CommPortIdentifier.getPortIdentifier(portName);
        } catch (NoSuchPortException ex) {
            System.out.println(portName + " is niet gevonden!");
        }
        //following line checks whether there is the port i am looking for and whether it is serial
        if (ports.getPortType() == CommPortIdentifier.PORT_SERIAL) {
            System.out.println(portName + " is gevonden!");
            portId = ports;
        } else {

        }

    }

    //Hiermee verbind je de applicatie met de arduino
    public void Connect() {
        //verbind met poort
        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);   //down cast the comm port to serial port
            //give the name of the application
            //time to wait
            System.out.println("Verbinding met " + portName + "is gelukt!");

            //set serial port parameters
            serialPort.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

        } catch (PortInUseException e) {
            System.out.println(portName + " is al in gebruik door een andere applicatie!");
            System.exit(1);
        } catch (NullPointerException e2) {
            System.out.println(portName + "is zijn verbinding verloren?");
        } catch (UnsupportedCommOperationException e3) {
            System.out.println(e3.toString());
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

    }
    //end of portConncet method

    //readWrite serial port. Dit event gaat automatisch in een loop wanneer de port open wordt gezet en wordt verbonden met deze class
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) { //if data available on serial port
            try {
                String inputLine = input.readLine();
                System.out.println(inputLine);
                inputName = new Scanner(System.in); //get user name
                name = inputName.nextLine();
                name = name + '\n'; // \n is nodig om aan te geven aan de arduino dat de zin of woord afgesloten is
                output.write(name.getBytes());     //verzend de info naar de arduino
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    //closePort method
    private void close() {
        if (serialPort != null) {
            serialPort.close(); //close serial port
        }
        input = null;        //close input and output streams
        output = null;
    }
}
