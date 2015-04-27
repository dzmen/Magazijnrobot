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


public class Pakket {
    double size;
    
    public Pakket(){
        int a = (int) (Math.random()*90);
        size = ((double)a+1)/100;
    }
    public double getSize(){
        return this.size;
    }
    public void setSize(double size){
        this.size = size;
    }
    
}
