package com.temr1.Lesson2_3_maven;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainWindow extends JFrame implements ActionListener {
    private final TxtDataBase txtDataBase = new TxtDataBase();
    private final JTextArea textArea = new JTextArea();

    public MainWindow(){
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createButtons();

        textArea.setBounds(10,200,450,250);
        textArea.setLineWrap(true);
        add(textArea);

        setLayout(null);
        setVisible(true);

        txtDataBase.save("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "S":
                String fact = Objects.requireNonNull(FactsFromAPI.getFact());
                txtDataBase.save(fact);
                break;


            case "R":
                String json = txtDataBase.read();
                if (json != null && !json.isEmpty()){
                    CatFact catFact = FactsFromAPI.readCatFactFromJson(txtDataBase.read());
                    textArea.setText(catFact.fact);
                }

                repaint();
                break;

            case "C":
                txtDataBase.save("");
                textArea.setText("");
                break;
        }
    }

    private void createButtons(){
        createButton(50,50,50,50,"S");
        createButton(110,50,50,50,"R");
        createButton(170,50,50,50,"C");
    }

    private void createButton(int x, int y, int width, int height, String name){
        JButton button = new JButton(name);
        button.setBounds(x,y,width,height);
        button.addActionListener(this);
        add(button);
    }
}
