package asrs.controlesysteem.bestelling;

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
        return (5- this.y);
    }

    @Override
    public String toString() {
        return "Locatie{" + "x=" + x + ", y=" + y + '}';
    }

    //Berekent de afstand tot de andere locatie met de stelling van pitagoras
    public int afstandTot(Locatie loc) {
        int xdiff = Math.abs(x - loc.getX());
        int ydiff = Math.abs(y - loc.getY());
        return xdiff + ydiff;
    }

    //De standaard equals functie overschreven om de contains goed te laten verlopen
    @Override
    public boolean equals(Object o) {
        if (o instanceof Locatie) {
            return o.hashCode() == hashCode();
        }
        return false;
    }

    //Is blijkbaar nodig als je de equals overschrijft
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.x;
        hash = 83 * hash + this.y;
        return hash;
    }

}
