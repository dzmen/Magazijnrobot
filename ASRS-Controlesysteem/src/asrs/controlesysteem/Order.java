/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem;

import javax.xml.bind.annotation.*;
/**
 *
 * @author Quinten
 */
@XmlRootElement
public class Order {
    XMLReader  Bestelling;
    
        @XmlElement(name="bestelling")
        public XMLReader  getBestelling(){
                return Bestelling;
        }
        public void setBestelling(XMLReader Bestelling){
                this.Bestelling = Bestelling;
        }

    
}
