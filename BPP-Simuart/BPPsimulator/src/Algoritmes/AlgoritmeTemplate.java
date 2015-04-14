/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Customclasses.Order;

/**
 *
 * @author Hugo
 */
public abstract class AlgoritmeTemplate {

    protected String naam;
    protected Order bestelling;
    protected float genTijd;

    public String getNaam() {
        return this.naam;
    }

    public AlgoritmeTemplate(String naam) {
        this.naam = naam;
    }

    public abstract void RunAlgoritme();
}
