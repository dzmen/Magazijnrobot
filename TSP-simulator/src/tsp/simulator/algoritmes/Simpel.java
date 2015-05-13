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
        // Route opnieuw doorlopen
        Pijl pijl1;
        Pijl pijl2;
        for (int pos1 = 1; pos1 < route.size(); pos1++) {
            pijl1 = new Pijl(route.get(pos1 - 1), route.get(pos1));
            for (int pos2 = 1; pos2 < route.size(); pos2++) {
                pijl2 = new Pijl(route.get(pos2 - 1), route.get(pos2));
                if (pijl1.doesIntersect(pijl2)) {
                    Collections.swap(route, pos1, pos2);
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
        double a;
        double b;
        boolean isVert = false;
        boolean isHor = false;

        public Pijl(Locatie start, Locatie eind) {
            x1 = start.getX();
            y1 = start.getY();
            x2 = eind.getX();
            y2 = eind.getY();
            // get a and b voor de formule y=ax+b;
            if (x1 != x2 && y1 != y2) {
                if (getMax(x1, x2) == x1) {
                    a = (double) (y1 - y2) / (x1 - x2);
                } else {
                    a = (double) (y2 - y1) / (x2 - x1);
                }
                b = (double) y1 - a * x1;

            } else {
                if (x1 == x2) {
                    isVert = true;
                } else {
                    isVert = false;
                }
                if (y1 == y2) {
                    isHor = true;
                } else {
                    isHor = false;
                }
            }
        }

        public boolean doesIntersect(Pijl pijl2) {
            Intersection kruising;
            if (this.isHor) {
                if (pijl2.isHor) {
                    System.out.println("Werkt hor?");
                    return false;
                } else {
                    if (pijl2.isVert) {
                        kruising = HorizontaalVerticaal(pijl2.x1, this.y1);
                    } else {
                        kruising = HorizontaalDiagonaal(pijl2, this.y1);
                    }
                }
            } else if (this.isVert) {

                if (pijl2.isVert) {
                    System.out.println("Werkt vert?");
                    return false;
                } else {
                    if (pijl2.isHor) {
                        kruising = HorizontaalVerticaal(this.x1, pijl2.y1);
                    } else {
                        kruising = VerticaalDiagonaal(pijl2, this.x1);
                    }
                }
            } else {

                if (pijl2.a == this.a) {
                    System.out.println("Werkt dia?" + a);
                    return false;
                } else {
                    if (pijl2.isHor) {
                        kruising = HorizontaalDiagonaal(this, pijl2.y1);
                    } else {
                        if (pijl2.isVert) {
                            kruising = VerticaalDiagonaal(this, pijl2.x1);
                        } else {
                            kruising = DiagonaalDiagonaal(pijl2);
                        }
                    }
                }
            }
            int boundxmin = getMax(getMin(this.x1, this.x2), getMin(pijl2.x1, pijl2.x2));
            int boundxmax = getMin(getMax(this.x1, this.x2), getMax(pijl2.x1, pijl2.x2));
            int boundymin = getMax(getMin(this.y1, this.y2), getMin(pijl2.y1, pijl2.y2));
            int boundymax = getMin(getMax(this.y1, this.y2), getMax(pijl2.y1, pijl2.y2));
            if (kruising.x > boundxmin && kruising.x < boundxmax && kruising.y > boundymin && kruising.y < boundymax) {
                return true;
            } else {
                return false;
            }
        }

        class Intersection {

            double x;
            double y;

            public Intersection(double x, double y) {
                this.x = x;
                this.y = y;
            }
        }

        public Intersection HorizontaalVerticaal(int x, int y) {
            return new Intersection(x, y);
        }

        public Intersection HorizontaalDiagonaal(Pijl diagonaal, int y) {
            double x = (diagonaal.b - y) / (diagonaal.a);
            return new Intersection(x, y);
        }

        public Intersection VerticaalDiagonaal(Pijl diagonaal, int x) {
            double y = diagonaal.a * x + diagonaal.b;
            return new Intersection(x, y);
        }

        public Intersection DiagonaalDiagonaal(Pijl p2) {
            double x = (p2.b - this.b) / (this.a - p2.a);
            double y = this.a * x + this.b;
            return new Intersection(x, y);
        }

        public int getMax(int a, int b) {
            if (a > b) {
                return a;
            } else {
                return b;
            }
        }

        public int getMin(int a, int b) {
            if (a < b) {
                return a;
            } else {
                return b;
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
