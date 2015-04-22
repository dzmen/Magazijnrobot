/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmes;

import java.util.ArrayList;
import tsp.simulator.Locatie;
import tsp.simulator.Order;

/**
 *
 * @author Danny
 */
public class Simpel implements Algoritmes {

    private ArrayList<Locatie> posities;
    private ArrayList<Locatie> route;
    private int maxY;
    private int berekenTijd;

    public Simpel(Order order) {
        this.posities = new ArrayList<>(order.getArtikelen());
        this.maxY = order.getyVeldGrote();
        this.route = new ArrayList<Locatie>();
    }

    @Override
    public void berekenRoute() {
        Long startT = System.currentTimeMillis();
        Locatie start = new Locatie(1, maxY);
        route.add(start);
        while (posities.size() > 0) {
            Locatie temploc = null;
            int tempafstand = 9999;
            ArrayList<Integer> afstanden = new ArrayList<Integer>();
            for (Locatie loc : posities) {
                int afstand = (int) start.afstandTot(loc);
                if (afstand < tempafstand) {
                    tempafstand = afstand;
                    temploc = loc;
                }
            }
            route.add(temploc);
            posities.remove(temploc);
            start = temploc;
        }
        for (int i = 0; i < route.size(); i++) {
            System.out.println("Artikel pos: " + route.get(i).toString());
        }
        Long eindT = System.currentTimeMillis();
        System.out.println(eindT - startT);
        this.berekenTijd = safeLongToInt(eindT - startT);
    }

    @Override
    public int getBerekenTijd() {
        return this.berekenTijd;
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
