/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.bestelling;

import asrs.controlesysteem.readers.SQLReader;
import java.util.ArrayList;

public class Artikel {

    private int artikelNr, aantal;
    private double grote;
    private final String naam;
    private Locatie loc;

    public Artikel(int artikelNr, int grote, int aantal, String naam, Locatie loc) {
        this.aantal = aantal;
        this.artikelNr = artikelNr;
        this.grote = grote;
        this.aantal = aantal;
        this.naam = naam;
        this.loc = loc;
    }

    public Artikel(SQLReader sql, int nr) {
        ArrayList<Object> artikel = sql.getArtikel(nr);
        this.artikelNr = nr;
        if (!artikel.isEmpty()) {
            this.naam = (String) artikel.get(1);
            this.loc = (Locatie) artikel.get(2);
            this.grote = (double) artikel.get(3);
            this.aantal = (int) artikel.get(4);
        } else {
            this.naam = "";
        }
    }

    public double getGrote() {
        return grote;
    }

    public int getAantal() {
        return aantal;
    }

    public String getNaam() {
        return naam;
    }

    public Locatie getLocatie() {
        return loc;
    }

    public int getArtikelNr() {
        return artikelNr;
    }

    @Override
    public String toString() {
        return "Artikel{" + "artikelNr=" + artikelNr + ", grote=" + grote + ", aantal=" + aantal + ", naam=" + naam + ", loc=" + loc + '}';
    }

}
