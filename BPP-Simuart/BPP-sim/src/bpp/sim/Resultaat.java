/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpp.sim;

import java.util.ArrayList;

/**
 *
 * @author robbin
 */
public class Resultaat {
   private int aantdozen;
   private ArrayList<Pakket> pakketlijst;
   private String[][] results = new String[4][3];
   
   public Resultaat(ArrayList<Pakket> lijst){
       pakketlijst = lijst;       
   }
   public String updateResult(int index,String naam,int time,ArrayList<Doos> dozen){
       results[index][0] = naam;
       results[index][1] = Integer.toString(time);
       results[index][2] = Integer.toString(aantalDozen(dozen));
       return("\nResultaten van het "+results[index][0]+" algoritme:\nBenodigde tijd: "+results[index][1]+" ms\nAantal gebruikte dozen:"+results[index][2]+"\n");
       
   }
   public int aantalDozen(ArrayList<Doos> aa){
       int n = 0;
       for(Doos ab:aa){
           if(ab.getPakketten().size()>0){
              n++;
           }
       }
       return n;
   }
}

// araylist pakketten

// elke alg > tijd/dozen/pakketten
// String [][] algoritme = new string [4][3];
