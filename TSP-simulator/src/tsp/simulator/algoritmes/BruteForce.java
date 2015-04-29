/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.algoritmes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import tsp.simulator.Locatie;
import tsp.simulator.Order;
import static tsp.simulator.algoritmes.Simpel.safeLongToInt;

/**
 *
 * @author Danny
 */
public class BruteForce implements Algoritmes {
   private ArrayList<Locatie> posities;
   private ArrayList<Locatie> route;
   private int betereRoute;
   private int lengte;
    private int maxY;
    private int berekenTijd;
    public BruteForce(Order order) {
        this.posities = new ArrayList<>(order.getArtikelen());
        this.maxY = order.getyVeldGrote();
        this.route = new ArrayList();
    }


    public int berekenAantPermutaties(int ArrayGrootte){
        int j = 1;
        for(int i = 1; i < ArrayGrootte; i ++){
            j = i * j;
        }
        return j;
    }
    
    public ArrayList<Locatie> permute(int n, ArrayList<Locatie> locaties){
            if(n == 1){
                return locaties;
            } else {
                for(int i = 0; i < n; i++){
                    permute(n-1, locaties); 
                    int huidigeRoute = berekenAfstand(locaties);
                    if(huidigeRoute < betereRoute){
                        huidigeRoute = betereRoute;
                        this.route = locaties;
                    }
                    int j =0;
                    if(n%2 == 0){
                        j=i;
                    }
                    int o= n-1;
                    Collections.swap(locaties, j, o);
                }
            }
        return locaties;
    }
    
    public int berekenAfstand(ArrayList<Locatie> locaties){
        int lengte = new Locatie(1, maxY).afstandTot(locaties.get(0));
        for (int i = 0; i  <locaties.size() - 1; i++) {
            Locatie start = locaties.get(i);
            Locatie eind =  locaties.get(i + 1);
            lengte += start.afstandTot(eind);
        } 
        lengte += locaties.get(locaties.size() - 1).afstandTot( new Locatie(1, maxY));
        return lengte;
    }
    public void berekenRoute() {
        ArrayList<Locatie> besteRoute = new ArrayList<>(permute(posities.size(), posities));
        Long startT = System.nanoTime();
        Locatie start = new Locatie(1, maxY);
        route.add(start);
        while (besteRoute.size() > 0) {
            Locatie temploc = null;
            int tempafstand = 9999;
            for (Locatie loc : besteRoute) {
                int afstand = start.afstandTot(loc);
                if (afstand < tempafstand) {
                    tempafstand = afstand;
                    temploc = loc;
                }
            }
            lengte += tempafstand;
            route.add(temploc);
            besteRoute.remove(temploc);
            start = temploc;
        }
        lengte += start.afstandTot(new Locatie(1, maxY)) + 1;
        this.berekenTijd = safeLongToInt(System.nanoTime() - startT);
    }

    @Override
    public int getBerekenTijd() {
        return this.berekenTijd;
    }
    @Override
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
