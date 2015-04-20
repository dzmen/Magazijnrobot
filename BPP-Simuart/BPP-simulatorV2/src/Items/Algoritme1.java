/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public class Algoritme1 extends TemplateAlgoritme {

    @Override
    public void runAlgoritme(ArrayList<Doos> boxes, ArrayList<Pakket> packets) {
        int a = boxes.size();
        int b = 0;
        int c = packets.size();
        int d = 0;
        double current = 0;
        while (b < a && d < c) {
            if (packets.get(d).getSize() + current > 1) {
                b++;
                
                boxes.get(b).addPakket(packets.get(d));
                current = packets.get(d).getSize();
                d++;
                
            } else {
                boxes.get(b).addPakket(packets.get(d));

                current = current + packets.get(d).getSize();
                d++;
            }
        }
        dozen = boxes;

    }

}
