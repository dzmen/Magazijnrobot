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
        // TESTFUNCTIE

        ArrayList<Locatie> Tr = new ArrayList<>();
        Tr.add(new Locatie(0, 0));
        Tr.add(new Locatie(1, 1));
        Tr.add(new Locatie(3, 1));
        Tr.add(new Locatie(2, 1));
        Tr.add(new Locatie(3, 4));
        Tr.add(new Locatie(1, 4));
        Tr.add(new Locatie(4, 1));
        Tr.add(new Locatie(0, 0));
        Pijl p1;
        Pijl p2;
        for (int a = 1; a < Tr.size() - 1; a++) {
            p1 = new Pijl(Tr.get(a - 1), Tr.get(a));
            System.out.println("Pijl " + a + ": A: " + p1.a + " B: " + p1.b);
            for (int b = 1; b < Tr.size(); b++) {
                p2 = new Pijl(Tr.get(b - 1), Tr.get(b));
                if (p1.isParrallel(p2)) {
                    System.out.println("   Parrallel Pijl " + b + ": A: " + p2.a + " B: " + p2.b);
                    if (p1.doesOverlap(p2)) {
                        System.out.print("  OVERLAPT!\n");
                    }
                } else {
                    if (p1.doesIntersect(p2)) {
                        System.out.println("   Kruist Pijl " + b + ": A: " + p2.a + " B: " + p2.b);
                    }
                }
                for(double uu = Math.min(p1.xm, p2.xm); uu <=Math.max(p1.xM, p2.xM);uu =uu+0.1){
                    if(p1.withinbounds(uu,p1.xa,p1.xb)&&p1.withinbounds(uu,p2.xa,p2.xb)){
                        System.out.print("[]");
                    }
                }
                

            }

            System.out.println("");
        }

//        Pijl p1;
//        Pijl p2;
//        for(int a=1; a<route.size();a++){
//            p1 = new Pijl(route.get(a-1),route.get(a));
//            for(int b=1; b<route.size();b++){
//                p2 = new Pijl(route.get(b-1),route.get(b));
//                if(p1.checklines(p2)){
//                    Collections.swap(route, a, b);
//                    a = 1;
//                    break;
//                }
//            }
//        }
        //vergelijk eerste functie met een t
        lengte += start.afstandTot(new Locatie(1, maxY)) + 1;

        this.berekenTijd = safeLongToInt(System.nanoTime() - startT);
    }

// De klasse pijl is een pijl die wordt weergeven
    public class Pijl {

        double xa;
        double xb;
        double xm;
        double xM;
        double ya;
        double yb;
        double ym;
        double yM;
        double a;
        double b;
        boolean vert = false;

        // Tested! works.
        public Pijl(Locatie start, Locatie eind) {
            xa = start.getX();
            ya = start.getY();
            xb = eind.getX();
            yb = eind.getY();
            xm = Math.min(xa, xb);
            xM = Math.max(xa, xb);
            ym = Math.min(ya, yb);
            yM = Math.max(ya, yb);
            if (xa == xb) {
                vert = true;
            } else {
                a = (ya - yb) / (xa - xb);
                b = ya - a * xa;
            }
        }// Einde public Pijl

        public boolean withinbounds(double x, double a, double b) {
            if (x > Math.min(a, b) && x < Math.max(a, b)) {
                return true;
            } else {
                return false;
            }
        }

        public boolean withinbounds(double x, double a, double b, double y, double c, double d) {
            if (x > Math.min(a, b) && x < Math.max(a, b) && y > Math.min(c, d) && y < Math.max(c, d)) {
                return true;
            } else {
                return false;
            }
        }

        public boolean checklines(Pijl p2) {
            if (isParrallel(p2)) {
                if (doesOverlap(p2)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (doesIntersect(p2)) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        //check of twee parrallelen elkaar overlappen
        public boolean doesOverlap(Pijl p2) {
            boolean overlap = false;
            if (isParrallel(p2) && p2.xa == xa) {
                for (double u = Math.min(ym, p2.ym); u < Math.max(yM, p2.yM); u = u + 0.2) {
                    if (withinbounds(u, ya, yb) && withinbounds(u, p2.ya, p2.yb)) {
                        overlap = true;
                    }
                }
            } else {
                if (isParrallel(p2) && b == p2.b) {
                    for (double u = Math.min(xm, p2.xm); u < Math.max(xM, p2.xM); u = u + 0.02) {
                        if (withinbounds(u, xa, xb) && withinbounds(u, p2.xa, p2.xb)) {
                            overlap = true;
                        }
                    }
                }
            }
            return overlap;
        }

        public boolean doesIntersect(Pijl p2) {
            double x;
            double y;
            if (vert) {
                x = xa;
                y = p2.a * x + p2.b;
            } else {
                if (p2.vert) {
                    x = p2.xa;
                } else {
                    x = (p2.b - b) / (a - p2.a);
                }
                y = a * x + b;
            }
            if (withinbounds(x, xm, xM, y, ym, yM) && withinbounds(x, p2.xm, p2.xM, y, p2.ym, p2.yM)) {
                return true;
            } else {
                return false;
            }
        }

        // Checked: Functional
        public boolean isParrallel(Pijl p2) {
            if (p2.vert && vert || a == p2.a) {
                return true;
            } else {
                return false;
            }
        }

    }// Einde public class Pijl

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
