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
public class Fill extends Template {

    @Override
    public ArrayList<Doos> runAlgorithm(ArrayList<Doos> dozen, ArrayList<Pakket> pakketten) {
        ArrayList<Pakket> fitable = new ArrayList<>();
        ArrayList<Pakket> nonfitable = new ArrayList<>();
        ArrayList<Pakket> newArray = new ArrayList<>();
        double content = 0;
        while (pakketten.size() > 0) {
            for (Pakket aa : pakketten) {
                if (aa.getSize() + content > 1) {
                    nonfitable.add(aa);
                } else {
                    fitable.add(aa);
                }
            }
            if (fitable.size() > 0) {
                double currentValue = 0;
                int currentIndex = 0;
                for (int ab = 0; ab < fitable.size(); ab++) {
                    if (fitable.get(ab).getSize() > currentValue) {
                        currentValue = fitable.get(ab).getSize();
                        currentIndex = ab;
                    }
                }
                newArray.add(fitable.get(currentIndex));
                content = content+ fitable.get(currentIndex).getSize();
                fitable.remove(currentIndex);
                pakketten.clear();
                for (Pakket ac : fitable) {
                    pakketten.add(ac);
                }
                for(Pakket af: nonfitable){
                    pakketten.add(af);
                }
                fitable.clear();
                nonfitable.clear();
             
            } else {
                content = 0;
                nonfitable.clear();
            }
        }
        this.fillboxes(dozen, newArray);
        return dozen;
    }

}
