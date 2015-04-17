/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Hugo
 */
class Buttonpanel extends JPanel{
    private JButton genPackages;    //Generates random packages
    private JComboBox AlgorithmSelect;  //Selectmenu for algorithms
    private JButton runAlgorithm;       //Run selected algorithm
    private JButton calcAverage;        //Calculate result average
    
    public Buttonpanel(){
        this.setBackground(Color.blue);
        this.setBounds(800, 10, 180, 800);
    }
}
