/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import javax.swing.JTextArea;

/**
 *
 * @author Hugo
 */
class Statscherm extends JTextArea {

    public Statscherm() {
        this.setBounds(30, 430, 300, 100);
        this.setBackground(Color.white);
        this.setVisible(true);
    }

}
