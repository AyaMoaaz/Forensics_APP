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
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author m_ela
 */
public class GUI extends JFrame {

    String x;
    JLabel text = new JLabel("Select Duration For Your Report");
    JButton report_btn = new JButton("Get Report");
    JLabel home = new JLabel(new ImageIcon("bg.jpg"));
    String[] duration = {"1 Days", "2 Days", "3 Days", "4 Days", "5 Days", "6 Days", "Month", "ALL"};
    JProgressBar pbar;
    JComboBox comboBox = new JComboBox(duration);
    JCheckBox history;
    JCheckBox bookmarks;
    JCheckBox downloads;
    JCheckBox selectall;
    JPanel checkPanel;

    public GUI() throws IOException {
        setTitle("Forensics APP");
        setSize(700, 629);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setUndecorated (true);
        setResizable(false);
        setVisible(true);
        setLocation(330, 100);

        add(home, BorderLayout.CENTER);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#0e4a60")));

//////////////////////////////////////////////
        home.setLayout(null);
        text.setBounds(100, 150, 300, 60);
        text.setForeground(new Color(198, 218, 229));
        text.setFocusable(false);
        text.setFont(new Font("calibri", Font.PLAIN, 19));
        home.add(text);

        comboBox.setBounds(420, 160, 170, 45);
        comboBox.setForeground(new Color(198, 218, 229));
        comboBox.setBackground(new Color(13, 30, 44));
        comboBox.setFocusable(false);
        comboBox.setFont(new Font("calibri", Font.PLAIN, 18));
        home.add(comboBox);

        report_btn.setBounds(260, 300, 150, 40);
        report_btn.setForeground(new Color(198, 218, 229));
        report_btn.setBackground(new Color(0, 13, 30, 44));
        report_btn.setOpaque(false);
        report_btn.setFocusPainted(false);
        report_btn.setBorderPainted(false);
        report_btn.setContentAreaFilled(false);
        report_btn.setFont(new Font("calibri", Font.PLAIN, 20));
        home.add(report_btn);

        history = new JCheckBox("History");
        history.setMnemonic(KeyEvent.VK_C);
        history.setSelected(true);

        bookmarks = new JCheckBox("Bookmarks");
        bookmarks.setMnemonic(KeyEvent.VK_G);
        bookmarks.setSelected(true);

        downloads = new JCheckBox("Downloads");
        downloads.setMnemonic(KeyEvent.VK_G);
        downloads.setSelected(true);

        selectall = new JCheckBox("Select All");
        selectall.setMnemonic(KeyEvent.VK_G);
        selectall.setSelected(true);
        
        checkPanel = new JPanel(new GridLayout(1, 1));
        checkPanel.add(history);
        checkPanel.add(bookmarks);
        checkPanel.add(downloads);
        checkPanel.add(selectall);
        checkPanel.setBounds(150, 350, 400, 50);
        home.add(checkPanel,BorderLayout.CENTER);
        
        report_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                System.out.println(" ---------------- " + RunHistory());

            }
        });

//////////////////////////////////////////////////////////////////////////
        int minimum = 0;
        int maximum = 100;
        pbar = new JProgressBar(minimum, maximum);
        pbar.setBounds(200, 550, 300, 30);
        pbar.setForeground(new Color(0, 137, 172));
        pbar.setBackground(new Color(13, 30, 44));
        pbar.setStringPainted(true);
        pbar.setValue(0);

        // home.add(pbar);
    }

    public int RunHistory() {
        try {
            History hist = new History();
            x = String.valueOf(comboBox.getSelectedItem());
            int days = 0;
            switch (x) {
                case "Month":
                    days = 30;
                    break;
                case "ALL":
                    days = -1;
                    break;
                default:
                    days = Character.getNumericValue(x.charAt(0));
                    break;
            }
            System.out.println(hist.Analysis(days, pbar) + "||" + days);
            return hist.size;
        } catch (IOException ex) {

        }
        return 0;
    }
}
