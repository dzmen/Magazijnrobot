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
        
        
        ArrayList<Pakket> newpackets = new ArrayList<Pakket>();
        int f = boxes.size();
        int g = 0;
        int h = packets.size();
        int i;
        int j;
        Pakket low = new Pakket();
        while(packets.size()>0){
            low.setSize(1.0);
            i = 0;
            j = 0;
            for(Pakket ab: packets){
                if(ab.getSize()<low.getSize()){
                    low = ab;
                    j=i;
                }
                i++;
            }
            newpackets.add(low);
            packets.remove(j);
        }
        packets = newpackets;
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
        //verander de array (zie TemplateAlgoritme
        dozen = boxes;
    }

}
