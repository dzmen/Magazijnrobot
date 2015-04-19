/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 *
 * @author Hugo
 */
public class Pakket {

    private double size;

    public Pakket() {
        int a = (int) (Math.random()*9);
        size = ((double)a+1)/10;
    }

    public double getSize() {
        return this.size;
    }
    public void setSize(double size){
        this.size=size;
    }
}
