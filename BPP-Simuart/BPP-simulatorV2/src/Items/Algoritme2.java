/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Hugo
 */
public class Algoritme2 extends TemplateAlgoritme{
    private ArrayList<ArrayList<Pakket>> pakketten = new ArrayList<>();
    @Override
    public void runAlgoritme(ArrayList<Doos> boxes, ArrayList<Pakket> packets) {
        makelist(packets.size(),packets);
        ArrayList<Pakket> bestpossible = new ArrayList<>();
        int min = boxes.size();
        for(ArrayList<Pakket> aa: pakketten){
            int doos = 1;
            double size =0;
            System.out.println("=======");
            for(Pakket ab: aa){
                System.out.println("TEST"+doos+": "+ab.getSize());
                if(ab.getSize()+size>1){
                    doos++;
                    size = ab.getSize();
                } else{
                    size = ab.getSize()+ size;
                }                
            }
            if(doos<min){
                bestpossible =aa;
                min =doos;
            }
        }
        int box = 0;
        double cont = 0.0;
        for(Pakket bb: bestpossible){
            if(bb.getSize()+cont>1){
                box++;
                boxes.get(box).addPakket(bb);
                cont = bb.getSize();
            } else{
                boxes.get(box).addPakket(bb);
                cont = bb.getSize()+cont;
            }
        }
        
        //Voer het algoritme uit met gebruik van boxes en packets.

        
        //verander de array (zie TemplateAlgoritme
        dozen = boxes;
    }
    public ArrayList<Pakket> makelist(int n, ArrayList<Pakket> packets){
        if(n==1){
            return packets;
        } else{
            for(int i=0;i<n;i++){
                pakketten.add(makelist(n-1,packets));
                int j =0;
                if(n%2 == 0){
                    j=i;
                }
                int o = n-1;
                Collections.swap(packets, j, o);
            }
            
        }
        return packets;
    }
    
}
