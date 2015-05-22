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
public class AlgoritmeTSP {

    private final ArrayList<Artikel> posities, route;

    public AlgoritmeTSP(Order order) {
        this.posities = new ArrayList<>(order.getArtikelen());
        this.route = new ArrayList<>();
    }

    public void berekenRoute() {
        Artikel start = new Artikel(1, 1, 1, "temp", new Locatie(1, 1));
        route.add(start);
        while (posities.size() > 0) {
            Artikel temploc = null;
            int tempafstand = 9999;
            for (Artikel art : posities) {
                int afstand = start.getLocatie().afstandTot(art.getLocatie());
                if (afstand < tempafstand) {
                    tempafstand = afstand;
                    temploc = art;
                }
            }
            route.add(temploc);
            posities.remove(temploc);
            start = temploc;
        }
    }

    public ArrayList<Artikel> getRoute() {
        return this.route;
    }
}
