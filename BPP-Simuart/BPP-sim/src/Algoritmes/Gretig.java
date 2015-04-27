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
    public ArrayList<Doos> runAlgorithm(ArrayList<Doos> dozen, ArrayList<Pakket> pakketten) {
        this.fillboxes(dozen, pakketten);
        return dozen;
    }

}
