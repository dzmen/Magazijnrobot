/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Hugo
 */
public class Scherm extends JFrame implements ActionListener{
    private Graph weergave; //Graphical display for algorithm results
    private Buttonpanel info; //Display with user-input buttons
    private GridLayout a;
    
    public Scherm(){
        this.setSize(1000,820);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("BPP-simulator");
        weergave = new Graph();
        this.add(weergave);
        info = new Buttonpanel();
        this.add(info);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
