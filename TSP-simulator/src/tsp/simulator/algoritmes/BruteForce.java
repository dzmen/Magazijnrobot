/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.algoritmes;

import java.util.ArrayList;
import java.util.Collections;
import tsp.simulator.Locatie;
import tsp.simulator.Order;

/**
 *
 * @author Danny
 */
public class BruteForce implements Algoritmes {

    private ArrayList<Locatie> posities;
    private ArrayList<Locatie> route;
    private int lengte = 999999999;
    private int maxY;
    private int berekenTijd;

    public BruteForce(Order order) {
        this.posities = new ArrayList<>(order.getArtikelen());
        this.maxY = order.getyVeldGrote();
        this.route = new ArrayList<>();
    }

    public ArrayList<Locatie> permutatie(int n, ArrayList<Locatie> route) {
        if (n == 1) {
            return route;
        } else {
            for (int i = 0; i < n; i++) {
                ArrayList<Locatie> newRout = permutatie(n - 1, route);
                ArrayList<Locatie> newRoute = new ArrayList<>();
                newRoute.add(new Locatie(1, maxY));
                for (Locatie loc : newRout) {
                    newRoute.add(loc);
                }
                int huidigeRoute = berekenAfstand(newRoute);
                System.out.println("Optie " + n + ": " + huidigeRoute);
                if (huidigeRoute < this.lengte) {
                    this.lengte = huidigeRoute;
                    this.route = newRoute;
                }
                int j = 0;
                if (n % 2 == 0) {
                    j = i;
                }
                int o = n - 1;
                Collections.swap(route, j, o);
            }

        }
        return route;
    }

    public int berekenAfstand(ArrayList<Locatie> locaties) {
        int lengte = new Locatie(1, maxY).afstandTot(locaties.get(0));
        for (int i = 0; i < locaties.size() - 1; i++) {
            Locatie start = locaties.get(i);
            Locatie eind = locaties.get(i + 1);
            lengte += start.afstandTot(eind);
        }
        lengte += locaties.get(locaties.size() - 1).afstandTot(new Locatie(1, maxY));
        return lengte;
    }

    public void berekenRoute() {
        Long startT = System.nanoTime();
        permutatie(posities.size(), posities);
        //this.berekenTijd = System.nanoTime() - startT);
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
