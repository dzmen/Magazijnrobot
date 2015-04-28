/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import bpp.sim.Doos;
import bpp.sim.Pakket;
import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public class Size extends Template {

    @Override
    public ArrayList<Doos> runAlgorithm(ArrayList<Doos> dozen, ArrayList<Pakket> pakkett) {
        ArrayList<Pakket> pakketten = new ArrayList<>(pakkett);
        ArrayList<Pakket> newpacks = new ArrayList<>();
        //sorteert de lijst van klein naar groot;
        while (pakketten.size() > 0) {
            double smallest = 1;
            int pakket = 0;
            for (int a = 0; a < pakketten.size(); a++) {
                if (pakketten.get(a).getSize() < smallest) {
                    pakket = a;
                    smallest = pakketten.get(a).getSize();
                }
            }
            newpacks.add(pakketten.get(pakket));
            pakketten.remove(pakket);

        }
        this.fillboxes(dozen, newpacks);
        return dozen;
    }

}
