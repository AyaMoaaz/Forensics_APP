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
import javax.swing.border.AbstractBorder;

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
    int days = 0;
   

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
        history.setMnemonic(KeyEvent.VK_H);
        history.setSelected(false);
        history.setOpaque(false);
        history.setForeground(new Color(198, 218, 229));
        history.setBackground(new Color(0, 13, 30, 44));
        history.setFont(new Font("calibri", Font.PLAIN, 16));

        bookmarks = new JCheckBox("Bookmarks");
        bookmarks.setMnemonic(KeyEvent.VK_B);
        bookmarks.setSelected(false);
        bookmarks.setOpaque(false);
        bookmarks.setForeground(new Color(198, 218, 229));
        bookmarks.setBackground(new Color(0, 13, 30, 44));
        bookmarks.setFont(new Font("calibri", Font.PLAIN, 16));

        downloads = new JCheckBox("Downloads");
        downloads.setMnemonic(KeyEvent.VK_D);
        downloads.setSelected(false);
        downloads.setOpaque(false);
        downloads.setForeground(new Color(198, 218, 229));
        downloads.setBackground(new Color(0, 13, 30, 44));
        downloads.setFont(new Font("calibri", Font.PLAIN, 16));

        selectall = new JCheckBox("Select All");
        selectall.setMnemonic(KeyEvent.VK_S);
        selectall.setSelected(false);
        selectall.setOpaque(false);
        selectall.setForeground(new Color(198, 218, 229));
        selectall.setBackground(new Color(0, 13, 30, 44));
        selectall.setFont(new Font("calibri", Font.PLAIN, 16));

        checkPanel = new JPanel(new GridLayout(1, 1));
        checkPanel.setOpaque(false);
        checkPanel.setBounds(150, 350, 450, 100);

        checkPanel.add(selectall);
        checkPanel.add(history);
        checkPanel.add(bookmarks);
        checkPanel.add(downloads);
        home.add(checkPanel, BorderLayout.CENTER);
        //home.setVisible(false);

        report_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                x = String.valueOf(comboBox.getSelectedItem());
                days = 0;
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
                System.out.println(" ---------------- " + RunHistory(days));

            }
        });

//////////////////////////////////////////////////////////////////////////
        int minimum = 0;
        int maximum = 100;
        pbar = new JProgressBar(minimum, maximum);
        pbar.setBounds(200, 500, 350, 50);
        pbar.setFont(new Font("calibri", Font.PLAIN, 18));
        pbar.setForeground(Color.decode("#0e4a60"));
        pbar.setBackground(Color.decode("#02283f"));
        pbar.setStringPainted(true);
        pbar.setValue(0);
        
        
        home.add(pbar);
    }

    public int RunHistory(int days) {
        try {
            History hist = new History();

            System.out.println(hist.Analysis(days, pbar) + "||" + days);
            return hist.size;
        } catch (IOException ex) {

        }
        return 0;
    }
}
