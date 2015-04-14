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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Hugo
 */
public class Scherm extends JFrame {

    private Tekenpanel tekenpaneel;
    private JComboBox selectiemenu;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JButton xmlSelect;
    private JTextArea jcomp6;
    private JLabel jcomp7;
    private JButton programeExe;

    public Scherm() {

        this.setTitle("BPP-simulator");
        this.setSize(820, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        String[] jcomp2Items = {"Numerieke Algoritme", "Gretige Algoritme", "Balans Algoritme"};

        //construct components
        tekenpaneel = new Tekenpanel();
        selectiemenu = new JComboBox(jcomp2Items);
        jcomp3 = new JLabel("Algoritme");
        jcomp4 = new JLabel("XML-Bestand");
        xmlSelect = new JButton("selecteer bestand...");
        jcomp6 = new JTextArea(5, 5);
        jcomp7 = new JLabel("newLabel");
        programeExe = new JButton("Execute");

        this.setLayout(null);
        //set component bounds (only needed by Absolute Positioning)
        tekenpaneel.setBounds(30, 20, 500, 400);
        selectiemenu.setBounds(575, 85, 155, 25);
        jcomp3.setBounds(575, 50, 100, 25);
        jcomp4.setBounds(575, 190, 100, 25);
        xmlSelect.setBounds(570, 215, 155, 30);
        jcomp6.setBounds(30, 435, 315, 105);
        jcomp7.setBounds(360, 435, 260, 105);
        programeExe.setBounds(570, 300, 100, 25);
        //add components
        add(tekenpaneel);
        add(selectiemenu);
        add(jcomp3);
        add(jcomp4);
        add(xmlSelect);
        add(jcomp6);
        add(jcomp7);
        add(programeExe);

    }
}
