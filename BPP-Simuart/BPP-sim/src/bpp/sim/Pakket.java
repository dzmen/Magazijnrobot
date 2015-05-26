/**
 * KFN01
 * ICTM2e
 * KBS major 2 2014/2015
 * Quinten Riphagen
 * Hugo Timmerman
 * Robbing Geerlings
 * Danny Zwiggelaar
 */
package bpp.sim;

import static jdk.nashorn.internal.objects.NativeMath.round;

// Dit is een pakket.
public class Pakket {
    double size;
    // Het pakket kan een bepaalde (voor de simulator willekeurige) grootte hebben.
    public Pakket(){
        int a = (int) (Math.random()*90);
        size = ((double)a+1)/100;
    }
    // De grootten van het pakket kan opgehaald worden.
    public double getSize(){
        return this.size;
    }
    // De grootte kan ook handmatig aangepast worden.
    public void setSize(double size){
        this.size = size;
    }
    
}
