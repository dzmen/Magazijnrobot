/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Hugo
 */
public class Scherm extends JFrame {

    Tekenpanel tekenpaneel = new Tekenpanel();
    JButton addXML = new JButton("selecteer bestand");
    JButton ExeProgram = new JButton("Start simulatie");
    JComboBox Algoritme = new JComboBox();
    Statscherm stats;
    ScrollPane ap;

    public Scherm() {
        
        this.setTitle("BPP-simulator");
        this.setSize(820, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.add(tekenpaneel);
        stats = new Statscherm();
        ap.setBounds(30, 20, 500, 400);
        ap = new ScrollPane(stats);
    }
}
