/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.algoritmes;

import java.util.ArrayList;
import tsp.simulator.Locatie;

/**
 *
 * @author Danny
 */
interface Algoritmes {

    public ArrayList<Locatie> getRoute();

    public abstract int getBerekenTijd();

    public abstract int getLengte();

}
