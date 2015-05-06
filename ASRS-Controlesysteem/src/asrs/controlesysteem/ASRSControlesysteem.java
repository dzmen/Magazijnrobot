/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem;

import asrs.controlesysteem.TSP.ArduinoTSPNew;

/**
 *
 * @author Quinten
 */
public class ASRSControlesysteem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        //new Scherm();
        ArduinoTSPNew myTest = new ArduinoTSPNew();  //creates an object of the class
        myTest.initialize();
        myTest.portConnect();
        System.out.println("Started");
        while (1 > 0);       //wait till any activity
    }

}
