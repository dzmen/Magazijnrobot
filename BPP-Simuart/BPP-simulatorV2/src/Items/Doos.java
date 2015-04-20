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
public class Doos {

    private int size;
    private ArrayList<Pakket> packets;

    public Doos(int s) {
        size = s;
        packets = new ArrayList<Pakket>();
    }

    public int getSize() {
        return this.size;
    }
    public void addPakket(Pakket a){
        packets.add(a);
    }
    public ArrayList<Pakket> getPakketten(){
        return this.packets;
    }
    public void deletePakketen(){
        packets.clear();
    }
}
