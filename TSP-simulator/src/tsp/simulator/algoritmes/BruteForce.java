/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.algoritmes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import tsp.simulator.Locatie;
import tsp.simulator.Order;

/**
 *
 * @author Danny
 */
public class BruteForce implements Algoritmes {

    private ArrayList<Locatie> posities;
    private ArrayList<Locatie> route;
    private int lengte;
    private int maxY;
    private int berekenTijd;

    public BruteForce(Order order) {
        this.posities = new ArrayList<>(order.getArtikelen());
        this.maxY = order.getyVeldGrote();
        this.route = new ArrayList<Locatie>();
    }

    
    public int berekenAantPermutaties(int ArrayGrootte){
        int j = 1;
        for(int i = 1; i < ArrayGrootte; i ++){
            j = i * j;
        }
        return j;
    }
    public ArrayList permute(ArrayList order){
        
        
    }
    
    @Override
    public void berekenRoute() {
        for (Locatie loc : posities) {
            ArrayList<Locatie> mogelijk = new ArrayList<Locatie>(posities);
            mogelijk.remove(loc);
            HashMap<Locatie, Integer> afstanden = new HashMap<Locatie, Integer>();
            for (Locatie loca : mogelijk) {
                int afstand = loc.afstandTot(loca);
                afstanden.put(loc, afstand);
            }
            mogelijkheden.put(loc, afstanden);
        }
        Iterator iterator = mogelijkheden.entrySet().iterator();
    }

    @Override
    public int getBerekenTijd() {
        return this.berekenTijd;
    }

    public int getLengte() {
        return this.lengte;
    }

    @Override
    public ArrayList<Locatie> getRoute() {
        return this.route;
    }

    //zet Long om naar Int
    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

}
