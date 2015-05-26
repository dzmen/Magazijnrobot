/**
 * KFN01
 * ICTM2e
 * KBS major 2 2014/2015
 * Quinten Riphagen
 * Hugo Timmerman
 * Robbin Geerlings
 * Danny Zwiggelaar
 */
package bpp.sim;

import java.util.ArrayList;

//Dit is een doos.
public class Doos {
    //Een doos kan 0 of meerdere pakketten bevatten.
    private ArrayList<Pakket> packets = new ArrayList<>();
    //Er kunnen pakketten aan dozen worden toegevoegd.
    public void addPakket(Pakket a){
        packets.add(a);
    }
    //Er kunnen pakketten uit dozen worden verwijderd
    public void removePakket(int a){
        packets.remove(a);
    }
    //Dozen kunnen in een keer worden leeggehaald.
    public void Clear(){
            packets.clear();
    }
    //De pakketten kunnen worden opgehaald
    public ArrayList<Pakket> getPakketten(){
        return this.packets;
    }
    
}
