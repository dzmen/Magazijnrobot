/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.bestelling;

import asrs.controlesysteem.BPP.AlgoritmeBPP;
import asrs.controlesysteem.TSP.AlgoritmeTSP;
import asrs.controlesysteem.readers.SQLReader;
import asrs.controlesysteem.readers.XMLReader;
import java.util.ArrayList;

/**
 *
 * @author Danny
 */
public class Order {

    private ArrayList<Integer> artikelnrs;
    private ArrayList<Artikel> artikelen, doos1, doos2, route;
    private ArrayList<Locatie> locatie;
    private ArrayList<Artikel> nietBeschikbaar;

    public Order(XMLReader xml, SQLReader sql) {
        this.artikelnrs = new ArrayList<>(xml.getArtikelen());
        this.route = new ArrayList<>();
        this.artikelen = new ArrayList<>();
        this.locatie = new ArrayList<>();
        this.nietBeschikbaar = new ArrayList<>();
        for (int artikel : artikelnrs) {
            Artikel art = new Artikel(sql, artikel);
            if (!art.getNaam().isEmpty()) {
                artikelen.add(art);
            } else {
                nietBeschikbaar.add(art);
            }
        }

    }

    public ArrayList<Locatie> getLocatie() {
        return locatie;
    }

    public void setLocatie(ArrayList<Locatie> locatie) {
        this.locatie = locatie;
    }

    public ArrayList<Artikel> getNietBeschikbaar() {
        return nietBeschikbaar;
    }

    public void genereerRoute() {
        AlgoritmeTSP simpel = new AlgoritmeTSP(this);
        simpel.berekenRoute();
        this.route = simpel.getRoute();
    }

    public void genereerDozen() {
        AlgoritmeBPP fill = new AlgoritmeBPP(this);
        fill.runAlgorithm();
        doos1 = fill.getDoos1();
        doos2 = fill.getDoos2();
    }

    public ArrayList<Artikel> getArtikelen() {
        return artikelen;
    }

    public ArrayList<Artikel> getRoute() {
        return route;
    }
}
