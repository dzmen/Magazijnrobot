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
        ArrayList<Locatie> secondroute;
        for (int currentloc = 1; currentloc < route.size() - 2; currentloc++) {
            pijl1 = new Pijl(route.get(currentloc - 1), route.get(currentloc));
            secondroute = new ArrayList<>(route);
            secondroute.remove(currentloc - 1);
            secondroute.remove(currentloc);
            for (int currentloc2 = 1; currentloc2 < secondroute.size() - 2; currentloc2++) {
                pijl2 = new Pijl(secondroute.get(currentloc2 - 1), secondroute.get(currentloc2));
                if (pijl1.doesIntersect(pijl2)==true) {
                    Collections.swap(route, currentloc, currentloc2);
                   
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
        double a1;
        double b1;
        boolean isHorizontaal = false;
        boolean isVerticaal = false;

        public Pijl(Locatie start, Locatie eind) {
            x1 = start.getX();
            y1 = start.getY();
            x2 = eind.getX();
            y2 = eind.getY();
            if (x1 != x2 && y1 != y2) {
                if (this.xMax(x1,x2) == x1) {
                    a1 = ((this.y1 - this.y2) / (this.xMax(x1,x2) - this.xMin(x1,x2)));
                } else {
                    a1 = ((this.y2 - this.y1) / (this.xMax(x1,x2) - this.xMin(x1,x2)));
                }
                double b1 = y1 - a1 * x1;
            } else {
                if (x1 == x2) {
                    isVerticaal = true;
                }
                if (y1 == y2) {
                    isHorizontaal = true;
                }
            }
        }

        //functie die weergeeft 
        public boolean doesIntersect(Pijl pijl2) {
            Intersection kruising;
            //ALS pijl1 verticaal is
            if (this.isVerticaal) {
                //EN Pijl2 verticaal is
                if (pijl2.isVerticaal) {
                    //DAN kruizen ze niet
                    return false;
                } else {
                    //ANDER kunnen ze kruizen
                    //Als pijl2 horizontaal is
                    if (pijl2.isHorizontaal) {
                        kruising = horizontaalverticaal(pijl2, this);

                    } else {
                        //OF als pijl 2 diagonaal is;
                        kruising = diagonaalverticaal(pijl2, this.x1);
                    }
                }
            } else {
                //ALS pijl 1 horizontaal is
                if (this.isHorizontaal) {
                    // EN pijl2 is horizontaal
                    if (pijl2.isHorizontaal) {
                        //DAN kruizen ze niet
                        return false;
                    } else {
                        if (pijl2.isVerticaal) {
                            kruising = horizontaalverticaal(this, pijl2);
                        } else {
                            kruising = diagonaalhorizontaal(pijl2, this.y1);
                        }
                    }
                } else {
                    if (pijl2.isHorizontaal) {
                        kruising = diagonaalhorizontaal(this, pijl2.y1);
                    } else {
                        if (pijl2.isVerticaal) {
                            kruising = diagonaalverticaal(this, pijl2.x1);
                        } else {
                            kruising = diagonaaldiagonaal(pijl2);
                        }
                    }
                }
            }
            //box waarin de kruising moet plaatsvinden
            int boundxmin = xMax(xMin(this.x1, this.x2), xMin(pijl2.x1, pijl2.x2));
            int boundxmax = xMin(xMax(this.x1, this.x2), xMax(pijl2.x1, pijl2.x2));
            int boundymin = yMax(yMin(this.y1, this.y2), yMin(pijl2.y1, pijl2.y2));
            int boundymax = yMin(xMax(this.y1, this.y2), yMax(pijl2.y1, pijl2.y2));
            if (kruising.x > boundxmin && kruising.x < boundxmax && kruising.y > boundymin && kruising.y < boundymax) {
                return true;
            } else {
                return false;
            }

        }

        //kruispunt van de twee grafieken
        class Intersection {

            double x;
            double y;

            public Intersection(double x, double y) {
                this.x = x;
                this.y = y;
            }
        }

        // geeft het kruispunt tussen een horizontale en een verticale pijl
        public Intersection horizontaalverticaal(Pijl horizontaal, Pijl verticaal) {
            return new Intersection(verticaal.x1, horizontaal.y1);
        }

        // geeft het kruispunt tussen een diagonale en een verticale pijl
        public Intersection diagonaalverticaal(Pijl diagonaal, int verticaal) {
            return new Intersection(verticaal, diagonaal.a1 * verticaal + diagonaal.b1);
        }

        public Intersection diagonaalhorizontaal(Pijl diagonaal, int horizontaal) {
            return new Intersection((horizontaal - diagonaal.b1) / diagonaal.a1, horizontaal);
        }

        public Intersection diagonaaldiagonaal(Pijl pijl2) {

            double xCross = pijl2.b1 - b1 / (a1 - pijl2.a1);
            double yCross = a1 * xCross + b1;
            return new Intersection(xCross, yCross);

        }

        public int xMax(int x1, int x2) {
            if (x1 > x2) {
                return x1;
            } else {
                return x2;
            }
        }

        public int xMin(int x1, int x2) {
            if (x1 < x2) {
                return x1;
            } else {
                return x2;
            }
        }

        public int yMin(int y1, int y2) {
            if (y1 < y2) {
                return y1;
            } else {
                return y2;
            }
        }

        public int yMax(int y1, int y2) {
            if (y1 > y2) {
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
