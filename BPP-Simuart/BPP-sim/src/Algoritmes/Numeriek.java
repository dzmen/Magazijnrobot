/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import bpp.sim.Doos;
import bpp.sim.Pakket;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Hugo
 */
public class Numeriek extends Template {

    private ArrayList<Pakket>pakketlijst = new ArrayList<>();
    private int aantalDozen = 999999;
    @Override
    public ArrayList<Doos> runAlgorithm(ArrayList<Doos> dozen, ArrayList<Pakket> pakket) {
        ArrayList<Pakket> pakketten = new ArrayList<>(pakket);
        makelist(pakketten.size(), pakketten);
        
        this.fillboxes(dozen, pakketlijst);
        return dozen;
    }

    public ArrayList<Pakket> makelist(int n, ArrayList<Pakket> packets) {
        if (n == 1) {
            return packets;
        } else {
            for (int i = 0; i < n; i++) {
                ArrayList <Pakket> newLijst  = makelist(n - 1, packets);
                
                int dozen = 1;
                double inhoud = 0;
                for (Pakket ab : newLijst) {
                    if (ab.getSize() + inhoud > 1) {
                        dozen++;
                        inhoud = 0;
                    }
                    inhoud = inhoud + ab.getSize();
                     
                }
                System.out.println("nieuw:" + dozen);
                if (dozen < aantalDozen) {
                    this.aantalDozen = dozen;
                    this.pakketlijst = new ArrayList<>(newLijst);
                    System.out.println(aantalDozen);
                 }
                //omwissellen waardes 
                int j = 0;
                if (n % 2 == 0) {
                    j = i;
                }
                int o = n - 1;
                Collections.swap(packets, j, o);
            }

        }
        return packets;
    }

}
