/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator;

import algoritmes.Simpel;
import java.util.ArrayList;

/**
 *
 * @author Danny
 */
public class Order {

    private int xVeldGrote;
    private int yVeldGrote;
    private int berekenTijd;
    private int lengte;

    private ArrayList<Locatie> artikelen;

    private ArrayList<Locatie> route;

    public Order() {
        this.artikelen = new ArrayList<Locatie>();
        this.route = new ArrayList<Locatie>();
        this.lengte = 0;
        this.berekenTijd = 0;
        this.xVeldGrote = 0;
        this.yVeldGrote = 0;
    }

    public void genereerArtikelen() {
        //Genereer de veld breedte en hoogte
        this.xVeldGrote = randomWaarde(3, 25);
        this.yVeldGrote = randomWaarde(3, 15);
        //De totaal aantal vakken in het magazijn
        int veldGrote = this.xVeldGrote * this.yVeldGrote;
        //Random artikelen maken
        int aantalArtikelen = randomWaarde(2, veldGrote - 5);
        for (int i = 0; i < aantalArtikelen; i++) {
            int x = randomWaarde(1, this.xVeldGrote);
            int y = randomWaarde(1, this.yVeldGrote);
            if (!artikelen.contains(new Locatie(x, y))) {
                artikelen.add(new Locatie(x, y));
            }
        }

    }

    public void genereerRoute(int index) {
        if (index == 0) {

        } else if (index == 1) {
            Simpel simpel = new Simpel(this);
            simpel.berekenRoute();
            this.route = simpel.getRoute();
            this.berekenTijd = simpel.getBerekenTijd();
            this.lengte = simpel.getLengte();
        } else if (index == 2) {

        }

    }

    public int getBerekenTijd() {
        return this.berekenTijd;
    }

    public int getLengte() {
        return this.lengte;
    }

    public int getxVeldGrote() {
        return xVeldGrote;
    }

    public int getyVeldGrote() {
        return yVeldGrote;
    }

    public ArrayList<Locatie> getArtikelen() {
        return artikelen;
    }

    public ArrayList<Locatie> getRoute() {
        return route;
    }

    //Zorgt ervoor dat je een minimaal en maximaal binnen een randomizer kan gebruiken
    private int randomWaarde(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
