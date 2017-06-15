/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author m_ela
 */
public class GUI extends JFrame {

    final static int interval = 1000;
    int i;
    Thread H;
    Thread B;
    Thread D;
    String x;
    JLabel text = new JLabel("Select Duration For Your Report");
    JButton report_btn = new JButton("Get Report");
    JLabel forensics = new JLabel(new ImageIcon("bg.jpg"));
    String[] duration = {"2 Days", "3 Days", "4 Days", "5 Days", "6 Days", "Month", "ALL"};
    JProgressBar pbar;
    JComboBox comboBox = new JComboBox(duration);

    public GUI() throws IOException {
        setSize(700, 640);
        setTitle("Forensics APP");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(forensics);

        forensics.setLayout(null);
        text.setBounds(100, 150, 300, 60);
        text.setForeground(new Color(198, 218, 229));
        text.setFocusable(false);
        text.setFont(new Font("calibri", Font.PLAIN, 19));
        forensics.add(text);

        comboBox.setBounds(420, 160, 170, 45);
        comboBox.setForeground(new Color(198, 218, 229));
        comboBox.setBackground(new Color(13, 30, 44));
        comboBox.setFocusable(false);
        comboBox.setFont(new Font("calibri", Font.PLAIN, 18));
        forensics.add(comboBox);

        report_btn.setBounds(260, 300, 150, 40);
        report_btn.setForeground(new Color(198, 218, 229));
        report_btn.setBackground(new Color(0, 13, 30, 44));
        report_btn.setOpaque(false);
        report_btn.setFocusPainted(false);
        report_btn.setBorderPainted(false);
        report_btn.setContentAreaFilled(false);

        report_btn.setFont(new Font("calibri", Font.PLAIN, 20));
        forensics.add(report_btn);
        int minimum = 0;
        int maximum = 100;
        pbar = new JProgressBar(minimum, maximum);
        pbar.setBounds(200, 550, 300, 30);
        pbar.setForeground(new Color(0, 137, 172));
        pbar.setBackground(new Color(13, 30, 44));
        pbar.setStringPainted(true);
        pbar.setValue(0);

        forensics.add(pbar);
        History hist = new History();
        H = new Thread() {
            public void run() {
                try {
                    x = String.valueOf(comboBox.getSelectedItem());
                    int days = 0;
                        switch (x){
                            case "Month":days = 30;break;
                            case "ALL" :days = -1;break;
                            default: days = Character.getNumericValue(x.charAt(0));break; 
                        }
                    System.out.println(hist.Analysis(days, pbar) + "||" + days);

                    report_btn.setEnabled(true);

                } catch (IOException ex) {

                }
            }

        };
        report_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                report_btn.setEnabled(false);
                H.start();

            }
        });

    }

}
