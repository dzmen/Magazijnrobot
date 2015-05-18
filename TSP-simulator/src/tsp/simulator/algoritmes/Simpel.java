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
public class Simpel implements Algoritmes {

    private ArrayList<Locatie> posities;
    private ArrayList<Locatie> route;
    private int lengte;
    private int maxY;
    private long berekenTijd;

    public Simpel(Order order) {
        this.posities = new ArrayList<>(order.getArtikelen());
        this.maxY = order.getyVeldGrote();
        this.route = new ArrayList<Locatie>();
    }

    public void berekenRoute() {
        Long startT = System.nanoTime();
        Locatie start = new Locatie(1, maxY);
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
            lengte += tempafstand;
            route.add(temploc);
            posities.remove(temploc);
            start = temploc;
        }
        Pijl one;
        Pijl two;
        for (int a = 1; a < route.size(); a++) {
            one = new Pijl(route.get(a - 1), route.get(a));
            for (int b = 1; b < route.size(); b++) {
                two = new Pijl(route.get(b - 1), route.get(b));
                if (one.IntersectWithinBounds(two)) {
                    Collections.swap(route, a, b);
                    a = 1;
                }
            }
        }
        //vergelijk eerste functie met een t
        lengte += start.afstandTot(new Locatie(1, maxY)) + 1;
        this.berekenTijd = safeLongToInt(System.nanoTime() - startT);
    }

    // De klasse pijl is een pijl die wordt weergeven
    public class Pijl {

        //startlocatie pijl
        int xstart;
        int ystart;
        //eindlocatie pijl
        int xeind;
        int yeind;
        /*
         elke pijl heeft de functie: y = ax+b (lineaire functie);
         */
        double a;
        double b;
        //als de pijl verticaal is.
        boolean isverticaal = false;

        public Pijl(Locatie begin, Locatie Eind) {
            xstart = begin.getX();
            ystart = begin.getY();
            xeind = Eind.getX();
            yeind = Eind.getY();
            // controleer of de pijl verticaal is
            if (xstart == xeind) {
                isverticaal = true;
            } else {
                //bereken a, vervolgens b.
                a = (ystart - yeind) / (xstart - xeind);
                b = ystart - a * xstart;
            }
        }

        public boolean IntersectWithinBounds(Pijl p2) {
            double xmax = (double) min(max(this.xstart, this.xeind), max(p2.xstart, p2.xeind));
            double xmin = (double) max(min(this.xstart, this.xeind), min(p2.xstart, p2.xeind));
            double ymax = (double) min(max(this.ystart, this.yeind), max(p2.ystart, p2.yeind));
            double ymin = (double) max(min(this.ystart, this.yeind), min(p2.ystart, p2.yeind));
            if (this.doesIntersect(p2)) {
                Intersection kruizing = getIntersect(p2);
                double x = kruizing.x;
                double y = kruizing.y;
                if (x > xmin && x < xmax && y > ymin && y < ymax) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }

        // kruising van twee functies(zie getIntersect);
        public class Intersection {

            double x;
            double y;

            public Intersection(double x, double y) {
                this.x = x;
                this.y = y;
            }

        }

        // Checkt of er een kruising kan plaatsvinden
        public boolean doesIntersect(Pijl p2) {
            if (this.isverticaal && p2.isverticaal || p2.a == this.a) {
                return false;
            } else {
                return true;
            }
        }

        // haal kruispunt op;
        public Intersection getIntersect(Pijl p2) {
            double x;
            double y;
            /*
             this.a*x +this.b = p2.a*x +p2.b 
             (this.a-p2.a)x = p2.b-this.b;
            
             */
            if (this.isverticaal) {
                x = this.xeind;
                y = (double) p2.a * x + this.b;
            } else {
                if (p2.isverticaal) {
                    x = p2.xeind;
                    y = (double) this.a * x + this.b;
                } else {
                    x = (double) (p2.b - this.b) / (this.a - p2.a);
                    y = (double) this.a * x + this.b;
                }
            }
            return new Intersection(x, y);
        }

        public int max(int a, int b) {
            if (a > b) {
                return a;
            } else {
                return b;
            }
        }

        public int min(int a, int b) {
            if (a < b) {
                return a;
            } else {
                return b;
            }
        }
    }

    // pijl is een classe met een bepaalde grafiek en locaties
    @Override
    public long getBerekenTijd() {
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
