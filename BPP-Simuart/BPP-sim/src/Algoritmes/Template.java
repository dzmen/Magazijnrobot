/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import bpp.sim.Doos;
import java.util.ArrayList;
import bpp.sim.Pakket;

/**
 *
 * @author Hugo
 */
public abstract class Template {

    public abstract ArrayList<Doos> runAlgorithm(ArrayList<Doos> dozen,ArrayList<Pakket> pakketten);
    
    public ArrayList<Doos> fillboxes(ArrayList<Doos>dozen,ArrayList<Pakket> pakketten){
        for(Doos a:dozen){
            a.Clear();
        }
        double doosinhoud =0;
        int huidigedoos = 0;
        for(Pakket ab: pakketten){
            if(ab.getSize()+doosinhoud>1){
                huidigedoos++;
                doosinhoud=0;
            }
            dozen.get(huidigedoos).addPakket(ab);
            doosinhoud = doosinhoud+ab.getSize();
        }
        return dozen;
        
    }
}
