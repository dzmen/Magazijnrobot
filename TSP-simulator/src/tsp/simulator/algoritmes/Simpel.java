/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.algoritmes;

import java.util.ArrayList;
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
        // Route opnieuw doorlopen

        for (int currentloc = 1; currentloc < route.size() - 2; currentloc++) {
            ArrayList<Locatie> seconditeration = route;
            seconditeration.remove(currentloc);
            seconditeration.remove(currentloc - 1);
            int routeY = route.get(currentloc).getY();
            int routeX = route.get(currentloc).getX();
            int routeY2 = route.get(currentloc - 1).getY();
            int routeX2 = route.get(currentloc - 1).getX();
            double a1 = (routeY - routeY2) / (routeX - routeX2);
            double b1 = (routeY - a1 * routeX);
            //pijl 1: y = a1*x+b1;
            for (int secondit = 1; secondit < seconditeration.size() - 2; secondit++) {
                int a2 = (seconditeration.get(secondit).getY() - seconditeration.get(secondit - 1).getY()) / (seconditeration.get(secondit).getX() - seconditeration.get(secondit - 1).getX());
                int b2 = (seconditeration.get(secondit).getY() - a2 * seconditeration.get(secondit).getX());
                /*a1*x+b1=a2*x+b2
                 (a2*x+b2-a1*x-b1)=0;
                 (a2-a1)*x+b2-b1 =0;
                 (a2-a1)*x=b1-b2;
                 x= (b1-b2)/(a2-a1);
                 */
                double xKruising = (b1 - b2) / (a2 - a1);
                double yKruising = a1 * xKruising + b1;
                if (xkruising > route.get(currentloc.)) {

                }
            }
        }
        //vergelijk eerste functie met een t
        lengte += start.afstandTot(new Locatie(1, maxY)) + 1;
        this.berekenTijd = safeLongToInt(System.nanoTime() - startT);
    }

    // pijl is een classe met een bepaalde grafiek en locaties
    private class Pijl {

        int x1;
        int y1;
        int x2;
        int y2;

        public Pijl(Locatie start, Locatie eind) {
            x1 = start.getX();
            y1 = start.getY();
            x2 = eind.getX();
            y2 = eind.getY();
        }

        public boolean doesIntersect(Pijl pijl2) {
            // create a box where the lines should overlap if possible
            int boundxMin = Math.abs(pijl2.xMin() - this.xMin());
            int boundxMax = Math.abs(pijl2.xMax() - this.xMax());
            int boundyMin = Math.abs(pijl2.yMin() - this.yMin());
            int boundyMax = Math.abs(pijl2.yMax() - this.yMax());

            return false;

        }
        class Intersection{
            double x;
            double y;
            public intersect(double x,double y){
               this.x = x;
               this.y = y;
            }
        }

        public Intersect intersection(Pijl pijl2) {
            int a1;
            if (this.xMax() == x1) {
                a1 = ((this.y1-this.y2) / (this.xMax() - this.xMin()));
            } else{
                a1 =((this.y2-this.y1)/(this.xMax() - this.xMin()));
            }
            int b1 = y1-a1*x1;
            int a2;
            if(pijl2.xMax()== pijl2.x1){
                a2 = ((pijl2.y1-pijl2.y2)/(pijl2.xMax()-pijl2.xMin()));
            } else{
                a2 = ((pijl2.y2-pijl2.y1)/(pijl2.xMax()-pijl2.xMin()));
            }
            int b2 = pijl2.y1-a2*pijl2.x1;
            double intloc
        }

        public int xMax() {
            if (x1 > x2) {
                return x1;
            } else {
                return x2;
            }
        }

        public int xMin() {
            if (x1 < x2) {
                return x1;
            } else {
                return x2;
            }
        }

        public int yMax() {
            if (y1 > y2) {
                return y1;
            } else {
                return y2;
            }
        }

        public int yMin() {
            if (y1 < y2) {
                return y1;
            } else {
                return y2;
            }
        }
    }

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
