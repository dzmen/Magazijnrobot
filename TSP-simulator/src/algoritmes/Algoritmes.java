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
public abstract class Algoritmes {

    private ArrayList<Locatie> posities;
    private ArrayList<Locatie> route;
    private int berekenTijd;

    public abstract void berekenRoute();

    public ArrayList<Locatie> getRoute() {
        return route;
    }

    public abstract int getBerekenTijd();

    public abstract void setPosities(ArrayList<Locatie> locaties);

}
