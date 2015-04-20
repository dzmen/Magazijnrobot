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
public abstract class TemplateAlgoritme {

    public ArrayList<Doos> dozen;
    public ArrayList<Pakket> pakketten;

    public abstract void runAlgoritme(ArrayList<Doos> boxes, ArrayList<Pakket> packets);

    public ArrayList<Doos> getBoxes() {
        return this.dozen;
    }
    public ArrayList<Pakket> getPackets(){
        return this.pakketten;
    }
}
