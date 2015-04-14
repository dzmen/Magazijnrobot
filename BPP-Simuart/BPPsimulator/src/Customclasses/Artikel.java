package Customclasses;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mans
 */
public class Artikel {
    
    private int size;
    private String name;
    private String pakketID;
    private Vector locatie;
    
    public Artikel(){
        
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getPakketID() {
        return pakketID;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPakketID(String pakketID) {
        this.pakketID = pakketID;
    }
    
    
    
}
