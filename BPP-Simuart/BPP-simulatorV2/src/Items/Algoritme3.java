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
        ArrayList<Pakket> newpackets = new ArrayList<>();
        while(packets.size()>0){
            double lowVal = 1.0;
            int lowget = 0;
            int i = 0;
            for(Pakket ab: packets){
                if(ab.getSize()<lowVal){
                    lowVal = ab.getSize();
                    lowget = i;
                }
                i++;
            }
            newpackets.add(packets.get(lowget));
            packets.remove(lowget);
            System.out.println(packets.size());
        }
        double cont = 0.0;
        int box = 0;
        for(Pakket cd: newpackets){
            System.out.println(cd.getSize());
        }
        for(Pakket bc: newpackets){
            if(bc.getSize()+cont>1){
                box++;
                boxes.get(box).addPakket(bc);
                cont = bc.getSize();
            } else{
                boxes.get(box).addPakket(bc);
                cont = cont + bc.getSize();
            }
        }
        //verander de array (zie TemplateAlgoritme
        dozen = boxes;
    }

}
