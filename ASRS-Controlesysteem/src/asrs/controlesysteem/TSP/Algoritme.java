/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.TSP;

import asrs.controlesysteem.bestelling.Artikel;
import java.util.ArrayList;
import asrs.controlesysteem.bestelling.Locatie;
import asrs.controlesysteem.bestelling.Order;

/**
 *
 * @author Danny
 */
public class Algoritme {

    private ArrayList<Locatie> posities;
    private ArrayList<Locatie> route;

    public Algoritme(Order order) {
        this.posities = new ArrayList<>();
        for (Artikel art : order.getArtikelen()) {
            this.posities.add(art.getLocatie());
        }
        this.route = new ArrayList<>();
    }

    public void berekenRoute() {
        Locatie start = new Locatie(1, 5);
        route.add(start);
        while (posities.size() > 0) {
            Locatie temploc = null;
            int tempafstand = 9999;
            for (Locatie loc : posities) {
                int afstand = start.afstandTot(loc);
                if (afstand < tempafstand) {
                    tempafstand = afstand;
                    temploc = loc;
                }
            }
            route.add(temploc);
            posities.remove(temploc);
            start = temploc;
        }
    }

    public ArrayList<Locatie> getRoute() {
        return this.route;
    }
}
