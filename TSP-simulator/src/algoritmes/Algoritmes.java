/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmes;

import java.util.ArrayList;
import tsp.simulator.Locatie;

/**
 *
 * @author Danny
 */
interface Algoritmes {

    public abstract void berekenRoute();

    public ArrayList<Locatie> getRoute();

    public abstract int getBerekenTijd();

}
