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

    private ArrayList<ArrayList<Pakket>> pakkettenlijst = new ArrayList<>();

    @Override
    public ArrayList<Doos> runAlgorithm(ArrayList<Doos> dozen, ArrayList<Pakket> pakketten) {
        makelist(pakketten.size(), pakketten);
        ArrayList<Pakket> kleinstecombinatie = new ArrayList<>();
        int min= dozen.size();
        for(ArrayList<Pakket> aa:pakkettenlijst){
            int aantaldozen =1;
            double inhoud =0;
            for(Pakket ab:aa){
                if(ab.getSize()+inhoud>1){
                    aantaldozen++;
                    inhoud=0;
                }
                inhoud=inhoud+ab.getSize();
                
            }
            if(aantaldozen<min){
                kleinstecombinatie=aa;
            }
            
        }
        this.fillboxes(dozen,kleinstecombinatie);
        return dozen;
    }

    public ArrayList<Pakket> makelist(int n, ArrayList<Pakket> packets) {
        if (n == 1) {
            return packets;
        } else {
            for (int i = 0; i < n; i++) {
                pakkettenlijst.add(makelist(n - 1, packets));
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
