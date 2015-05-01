/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem;

import asrs.controlesysteem.readers.SQLReader;
import asrs.controlesysteem.readers.XMLReader;
import java.util.ArrayList;

/**
 *
 * @author Danny
 */
public class Order {

    private ArrayList<Integer> artikelnrs;
    private ArrayList<Locatie> artikelen;
    private ArrayList<Locatie> route;

    public Order(XMLReader xml, SQLReader sql) {
        this.artikelnrs = new ArrayList<>(xml.getArtikelen());
        this.artikelen = new ArrayList<Locatie>();
        this.route = new ArrayList<Locatie>();
    }

    public void genereerArtikelen() {
        int aantalArtikelen = 9;
        for (int i = 0; i < aantalArtikelen; i++) {
            //if (!artikelen.contains(new Locatie(x, y))) {
            //    artikelen.add(new Locatie(x, y));
            //}
        }

    }

    public void genereerRoute(int index) {

    }

    public ArrayList<Locatie> getArtikelen() {
        return artikelen;
    }

    public ArrayList<Locatie> getRoute() {
        return route;
    }
}
