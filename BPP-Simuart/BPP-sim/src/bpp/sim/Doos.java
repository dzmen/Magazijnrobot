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

public class Doos {
    private ArrayList<Pakket> packets = new ArrayList<>();
    
    public void addPakket(Pakket a){
        packets.add(a);
    }
    public void removePakket(int a){
        packets.remove(a);
    }
    public void Clear(){
            packets.clear();
    }
    public ArrayList<Pakket> getPakketten(){
        return this.packets;
    }
    
}
