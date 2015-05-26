/**
 * KFN01 ICTM2e KBS major 2 2014/2015 Quinten Riphagen Hugo Timmerman Robbing
 * Geerlings Danny Zwiggelaar
 */
package bpp.sim;

import java.util.ArrayList;

// Dit is het systeem dat de dozen en pakketten aanmaakt.
public class Sorter {

    private ArrayList<Doos> dozen = new ArrayList<>();
    private ArrayList<Pakket> pakketten = new ArrayList<>();

    public void genPackets(int min, int max) {
        //oude data wordt weggehaald
        dozen.clear();
        pakketten.clear();
        //genereert een getal tussen het minimum en het maximum
        int n = (int) (Math.random() * (max - min) + min);
        //willekeurig aantal pakketten wordt aangemaakt;
        for (int a = 0; a < n; a++) {
            pakketten.add(new Pakket());
        }
        double current = 0;
        System.out.println("\n Pakketten:");
        for (Pakket ab : pakketten) {
            System.out.println("=>"+ab.getSize());
            dozen.add(new Doos());
        }
    }

    public ArrayList<Doos> getDozen() {
        return this.dozen;
    }

    public ArrayList<Pakket> getPakketten() {
        return this.pakketten;
    }

    public void setDozen(ArrayList<Doos> lijst) {
        this.dozen = lijst;
    }
    // dozen worden leeggemaakt.
    public void emptyDozen() {
        for (Doos a : dozen) {
            a.Clear();
        }
    }

}
