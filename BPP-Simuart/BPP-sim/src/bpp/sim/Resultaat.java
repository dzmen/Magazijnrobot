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
// Dit is een resultaat
public class Resultaat {
   // In het resultaat kunnen de uitkomsten van een algoritme opgeslagen worden:
    // Het aantel dozen
   private int aantdozen;
   // De pakketten
   private ArrayList<Pakket> pakketlijst;
   // en de string waar deze waarden worden weergeven.
   private String[][] results = new String[4][3];
   
   public Resultaat(ArrayList<Pakket> lijst){
       pakketlijst = lijst;       
   }
   // De nieuwste resultaten worden weergeven
   public String updateResult(int index,String naam,int time,ArrayList<Doos> dozen){
       results[index][0] = naam;
       results[index][1] = Integer.toString(time);
       results[index][2] = Integer.toString(aantalDozen(dozen));
       return("\nResultaten van het "+results[index][0]+" algoritme:\nBenodigde tijd: "+results[index][1]+" ms\nAantal gebruikte dozen:"+results[index][2]+"\n");
       
   }
   // het aantal GEVULDE DOZEN wordt berekend.
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
