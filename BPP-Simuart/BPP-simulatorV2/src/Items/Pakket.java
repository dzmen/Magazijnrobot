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

    private double size = -2;

    public Pakket() {
        while (size < 0.1) {
            size = round(Math.random(), 1);
        }
    }

    public double getSize() {
        return this.size;
    }
}
