/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import static java.lang.Package.getPackage;
import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public class Algoritme3 extends TemplateAlgoritme {

    @Override
    public void runAlgoritme(ArrayList<Doos> boxes, ArrayList<Pakket> packets) {
        //Voer het algoritme uit met gebruik van boxes en packets.

        int a = boxes.size();
        int b = 0;
        int c = packets.size();
        int d;
        int current = 0;
        double lowest = 1;
        ArrayList<Pakket> newpackets = new ArrayList<Pakket>();
        while (packets.size() > 0) {
            for (d = 0; d < c; d++) {
                if (packets.get(d).getSize() > lowest) {
                } else {
                    lowest = packets.get(d).getSize();
                    current = d;
                }
            }
            newpackets.add(packets.get(d));
            packets.remove(d);
        }
         a = boxes.size();
         b = 0;
         c = newpackets.size();
         d = 0;
         double corrent = 0.0;

        while (b < a && d < c) {
            if (packets.get(d).getSize() + corrent > 1) {
                b++;

                boxes.get(b).addPakket(newpackets.get(d));
                corrent = newpackets.get(d).getSize();
                d++;

            } else {
                boxes.get(b).addPakket(newpackets.get(d));

                corrent = current + newpackets.get(d).getSize();
                d++;
            }
        }

        //verander de array (zie TemplateAlgoritme
        dozen = boxes;
    }

}
