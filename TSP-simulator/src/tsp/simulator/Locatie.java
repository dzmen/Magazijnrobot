/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator;

/**
 *
 * @author Danny
 */
public class Locatie {

    private int x;
    private int y;

    public Locatie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double afstandTot(Locatie loc) {
        double xdiff = Math.abs(x - loc.getX());
        double ydiff = Math.abs(y - loc.getY());
        return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
    }
}
