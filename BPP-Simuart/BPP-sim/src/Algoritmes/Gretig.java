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
public class Gretig extends Template {

    @Override
    // de pakketten worden van voor naar achter gewoon in de dozen geplaatst.
    public ArrayList<Doos> runAlgorithm(ArrayList<Doos> dozen, ArrayList<Pakket> pakket) {
        ArrayList<Pakket> pakketten = new ArrayList<>(pakket);
        this.fillboxes(dozen, pakketten);
        return dozen;
    }

}
