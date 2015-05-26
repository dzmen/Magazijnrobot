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
    public ArrayList<Doos> runAlgorithm(ArrayList<Doos> dozen, ArrayList<Pakket> pakket) {
        ArrayList<Pakket> pakketten = new ArrayList<>(pakket);
        ArrayList<Pakket> fitable = new ArrayList<>();
        ArrayList<Pakket> nonfitable = new ArrayList<>();
        ArrayList<Pakket> newArray = new ArrayList<>();
        double content = 0;
        // Er wordt een selectie gemaakt van pakketten die in een doos kunnen passen.
        while (pakketten.size() > 0) {
            for (Pakket aa : pakketten) {
                if (aa.getSize() + content > 1) {
                    nonfitable.add(aa);
                } else {
                    //de pakketten die in de doos kunnen pakken.
                    fitable.add(aa);
                }
            }
            
            if (fitable.size() > 0) {
                double currentValue = 0;
                int currentIndex = 0;
                // het grootste pakket dat in de doos past wordt geselecteerd.
                for (int ab = 0; ab < fitable.size(); ab++) {
                    if (fitable.get(ab).getSize() > currentValue) {
                        currentValue = fitable.get(ab).getSize();
                        currentIndex = ab;
                    }
                }
                // Het pakket wordt aan de nieuwe array toegevoegd
                newArray.add(fitable.get(currentIndex));
                // De inhoud van de doos wordt aangepast
                content = content + fitable.get(currentIndex).getSize();
                // het pakket wordt uit de selectie verwijdert
                fitable.remove(currentIndex);
                pakketten.clear();
                //de selectie wordt weer teruggeplaatst in pakketten
                for (Pakket ac : fitable) {
                    pakketten.add(ac);
                }
                for (Pakket af : nonfitable) {
                    pakketten.add(af);
                }
                // de selectie wordt leeggemaakt.
                fitable.clear();
                nonfitable.clear();

            } else {
                //er wordt overgestapt op een tweede doos.
                content = 0;
                nonfitable.clear();
            }
        }
        this.fillboxes(dozen, newArray);
        return dozen;
    }

}
