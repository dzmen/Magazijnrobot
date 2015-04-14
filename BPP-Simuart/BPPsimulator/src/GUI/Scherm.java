/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Algoritmes.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Hugo
 */
public class Scherm extends JFrame implements ActionListener {
    
    // All objects initialized

    private Tekenpanel tekenpaneel;
    private JComboBox selectiemenu;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JButton xmlSelect;
    private JTextArea jcomp6;
    private JLabel jcomp7;
    private JButton programeExe;
    private Algoritme1 alg1 = new Algoritme1("Numerieke Algoritme");
    private Algoritme2 alg2 = new Algoritme2("Gretige Algoritme");
    private Algoritme3 alg3 = new Algoritme3("Balans Algoritme");

    @SuppressWarnings("empty-statement")
    public Scherm() {

        this.setTitle("BPP-simulator");
        this.setSize(820, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        AlgoritmeTemplate[] algs = new AlgoritmeTemplate[3];
        algs[0] = alg1;
        algs[1] = alg2;
        algs[2] = alg3;
        String[] algoritmes = {alg1.getNaam(), alg2.getNaam(), alg3.getNaam()};

        //construct components
        tekenpaneel = new Tekenpanel();
        selectiemenu = new JComboBox(algoritmes);
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
        programeExe.addActionListener(this);
        xmlSelect.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        //Button "Execute"
        if (e.getSource() == programeExe) {
            int a = selectiemenu.getSelectedIndex();
            System.out.println(a);
            System.out.println("Button pressed");
        }
        
        // Button "Bestand kiezen"
        if (e.getSource() == xmlSelect) {
            JFileChooser openFile = new JFileChooser();
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Order file (*.xml)", "xml");
            openFile.setFileFilter(xmlFilter);
            int resultaat = openFile.showOpenDialog(null);
            if (resultaat == openFile.APPROVE_OPTION) {
                //new XMLReader(openFile.getSelectedFile());
                xmlSelect.setText(openFile.getSelectedFile().getName());
            }
        }
    }

}
