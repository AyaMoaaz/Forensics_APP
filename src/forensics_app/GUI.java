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
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import org.json.simple.parser.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m_ela
 */
public class GUI extends JFrame {

    String x;
    String name;
    List<List> hist_result = new ArrayList<List>();
    List<List> book_result = new ArrayList<List>();
    List<List> down_result = new ArrayList<List>();
    List<List<List>> final_result = new ArrayList<>();
    //frame 1
    JLabel text = new JLabel("Select Duration For Your Report");
    JLabel text2 = new JLabel("Choose at least one File");

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
    //farme 2
    JLabel progress;
    JLabel scan;

    JLabel hist;
    JLabel history_urls;
    JLabel hist_analysis;
    JLabel hist_performance;
    JLabel hist_type;

    JLabel book;
    JLabel bookmarks_urls;
    JLabel book_analysis;
    JLabel book_performance;
    JLabel book_type;

    JLabel down;
    JLabel downloads_urls;
    JLabel down_analysis;
    JLabel down_performance;
    JLabel down_type;

    JButton back_btn;
    JButton save_btn;

    JLabel noCon = new JLabel(new ImageIcon("noCon.png"));
    JButton check_btn = new JButton("Check");
    JLabel noConText = new JLabel("check your connection");

    int days = 0;

    public GUI() throws IOException {
        checkCon();
        final_result.add(null);
        final_result.add(null);
        final_result.add(null);
    }

    public void checkCon() {
        if (SharedModel.isInternetReachable() == true) {
            startGUI();
        } else {
            noConGUI();
        }
    }

    public void noConGUI() {
        home.removeAll();
        setTitle("Forensics APP");
        setSize(700, 629);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(330, 100);
        add(home, BorderLayout.CENTER);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#0e4a60")));
        home.setVisible(true);

        home.setLayout(null);
        noCon.setBounds(0, 150, 300, 60);
        noCon.setForeground(new Color(198, 218, 229));
        noCon.setFocusable(false);
        noCon.setFont(new Font("calibri", Font.PLAIN, 19));
        home.add(noCon);

        noConText.setBounds(180, 160, 250, 45);
        noConText.setForeground(new Color(198, 218, 229));
        noConText.setFocusable(false);
        noConText.setFont(new Font("calibri", Font.PLAIN, 19));
        home.add(noConText);

        check_btn.setBounds(260, 300, 150, 40);
        check_btn.setForeground(new Color(198, 218, 229));
        check_btn.setBackground(new Color(0, 13, 30, 44));
        check_btn.setOpaque(false);
        check_btn.setFocusPainted(false);
        check_btn.setBorderPainted(false);
        check_btn.setContentAreaFilled(false);
        check_btn.setFont(new Font("calibri", Font.PLAIN, 20));
        home.add(check_btn);
        check_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCon();
            }
        });

    }

    public void startGUI() {
        home.removeAll();
        setTitle("Forensics APP");
        setSize(700, 629);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(330, 100);

        add(home, BorderLayout.CENTER);
        pack();
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#0e4a60")));
        home.setVisible(true);
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

        report_btn.setBounds(270, 500, 150, 40);
        report_btn.setForeground(new Color(198, 218, 229));
        report_btn.setBackground(new Color(0, 13, 30, 44));
        report_btn.setOpaque(false);
        report_btn.setFocusPainted(false);
        report_btn.setBorderPainted(false);
        report_btn.setContentAreaFilled(false);
        report_btn.setFont(new Font("calibri", Font.PLAIN, 20));
        home.add(report_btn);

        history = new JCheckBox("History");
        history.setName("history");
        history.setMnemonic(KeyEvent.VK_H);
        history.setSelected(false);
        history.setOpaque(false);
        history.setForeground(new Color(198, 218, 229));
        history.setBackground(new Color(0, 13, 30, 44));
        history.setFont(new Font("calibri", Font.PLAIN, 16));
        history.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (history.isSelected() == false) {
                    selectall.setSelected(false);
                } else {
                    if (bookmarks.isSelected() && downloads.isSelected() == true) {
                        selectall.setSelected(true);
                    }
                }
            }
        });

        bookmarks = new JCheckBox("Bookmarks");
        bookmarks.setName("bookmarks");
        bookmarks.setMnemonic(KeyEvent.VK_B);
        bookmarks.setSelected(false);
        bookmarks.setOpaque(false);
        bookmarks.setForeground(new Color(198, 218, 229));
        bookmarks.setBackground(new Color(0, 13, 30, 44));
        bookmarks.setFont(new Font("calibri", Font.PLAIN, 16));
        bookmarks.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (bookmarks.isSelected() == false) {
                    selectall.setSelected(false);
                } else {
                    if (downloads.isSelected() && history.isSelected() == true) {
                        selectall.setSelected(true);
                    }
                }
            }
        });

        downloads = new JCheckBox("Downloads");
        downloads.setName("downloads");
        downloads.setMnemonic(KeyEvent.VK_D);
        downloads.setSelected(false);
        downloads.setOpaque(false);
        downloads.setForeground(new Color(198, 218, 229));
        downloads.setBackground(new Color(0, 13, 30, 44));
        downloads.setFont(new Font("calibri", Font.PLAIN, 16));
        downloads.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (downloads.isSelected() == false) {
                    selectall.setSelected(false);
                } else {
                    if (bookmarks.isSelected() && history.isSelected() == true) {
                        selectall.setSelected(true);
                    }
                }
            }
        });

        selectall = new JCheckBox("Select All");
        selectall.setName("selectall");
        selectall.setMnemonic(KeyEvent.VK_S);
        selectall.setSelected(false);
        selectall.setOpaque(false);
        selectall.setForeground(new Color(198, 218, 229));
        selectall.setBackground(new Color(0, 13, 30, 44));
        selectall.setFont(new Font("calibri", Font.PLAIN, 16));
        selectall.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {

                if (selectall.isSelected()) {
                    history.setSelected(true);
                    bookmarks.setSelected(true);
                    downloads.setSelected(true);
                }

            }
        });

        text2.setBounds(100, 210, 300, 60);
        text2.setForeground(new Color(198, 218, 229));
        text2.setFocusable(false);
        text2.setFont(new Font("calibri", Font.PLAIN, 20));
        home.add(text2);

        JCheckBox checklist[] = {selectall, history, bookmarks, downloads};

        checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.setOpaque(false);
        checkPanel.setBounds(420, 230, 400, 100);
        checkPanel.add(selectall);
        checkPanel.add(history);
        checkPanel.add(bookmarks);
        checkPanel.add(downloads);

        home.add(checkPanel, BorderLayout.CENTER);

////////////////////////////////////////////////////////////////////////// frame 2
        progress = new JLabel(new ImageIcon("bg.jpg"));

        add(progress, BorderLayout.CENTER);
        progress.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                loop:
                for (JCheckBox i : checklist) {
                    if (i.isSelected()) {
                        name = i.getName();
                        switch (name) {
                            case "selectall":
                                RunHistory(days);
                                System.out.println("history complate" + "||" + hist_result);
                                try {
                                    RunBookmarks(days);
                                    System.out.println("bookmarks complate " + "||" + book_result);
                                } catch (ParseException ex) {
                                }
                                RunDownloads(days);
                                System.out.println("downloads complate" + "||" + down_result);
                                break loop;
                            case "history":
                                RunHistory(days);
                                System.out.println("history complate" + "||" + hist_result);
                                break;
                            case "bookmarks":
                                try {
                                    RunBookmarks(days);
                                    System.out.println("bookmarks complate" + "||" + book_result);
                                } catch (ParseException ex) {
                                }
                                break;
                            case "downloads":
                                RunDownloads(days);
                                System.out.println("downloads complate" + "||" + down_result);
                                break;
                        }
                    }
                }
                scan.setText("Summary");
                pbar.setVisible(false);
                save_btn.setVisible(true);
                back_btn.setVisible(true);
                loop2:
                for (JCheckBox i : checklist) {
                    if (i.isSelected()) {
                        name = i.getName();
                        switch (name) {
                            case "selectall":
                                history_urls.setVisible(true);
                                hist_analysis.setVisible(true);
                                hist_performance.setVisible(true);
                                hist_type.setVisible(true);

                                downloads_urls.setVisible(true);
                                down_analysis.setVisible(true);
                                down_performance.setVisible(true);
                                down_type.setVisible(true);

                                bookmarks_urls.setVisible(true);
                                book_analysis.setVisible(true);
                                book_performance.setVisible(true);
                                book_type.setVisible(true);
                                break loop2;
                            case "history":
                                history_urls.setVisible(true);
                                hist_analysis.setVisible(true);
                                hist_performance.setVisible(true);
                                hist_type.setVisible(true);
                                break;
                            case "bookmarks":
                                bookmarks_urls.setVisible(true);
                                book_analysis.setVisible(true);
                                book_performance.setVisible(true);
                                book_type.setVisible(true);
                                break;
                            case "downloads":
                                downloads_urls.setVisible(true);
                                down_analysis.setVisible(true);
                                down_performance.setVisible(true);
                                down_type.setVisible(true);
                                break;
                        }
                    }
                }
                for (Object object : final_result) {

                    System.out.println(object + "  ------------------");
                }
            }
        });
        progress.setVisible(false);
        progress.setLayout(null);

        scan = new JLabel("Scanning...");
        scan.setBounds(50, 130, 300, 60);
        scan.setForeground(new Color(255, 255, 255));
        scan.setFocusable(false);
        scan.setFont(new Font("calibri", Font.BOLD, 23));
        progress.add(scan);

        //*history*//
        hist = new JLabel("History");
        hist.setBounds(50, 180, 300, 60);
        hist.setForeground(new Color(198, 218, 229));
        hist.setFocusable(false);
        hist.setFont(new Font("calibri", Font.PLAIN, 22));
        progress.add(hist);
        hist.setVisible(false);

        history_urls = new JLabel("1235");
        history_urls.setBounds(180, 180, 300, 60);
        //#fd6027
        history_urls.setForeground(Color.decode("#fd6027"));
        history_urls.setFocusable(false);
        history_urls.setFont(new Font("calibri", Font.PLAIN, 18));
        progress.add(history_urls);
        history_urls.setVisible(false);

        hist_analysis = new JLabel("History Analysis");
        hist_analysis.setBounds(260, 230, 300, 60);
        hist_analysis.setForeground(new Color(198, 218, 229));
        hist_analysis.setFocusable(false);
        hist_analysis.setFont(new Font("calibri", Font.PLAIN, 17));
        progress.add(hist_analysis);
        hist_analysis.setVisible(false);

        hist_performance = new JLabel("80%");
        hist_performance.setBounds(300, 270, 300, 60);
        hist_performance.setForeground(new Color(244, 116, 66));
        hist_performance.setFocusable(false);
        hist_performance.setFont(new Font("calibri", Font.BOLD, 26));
        progress.add(hist_performance);
        hist_performance.setVisible(false);

        hist_type = new JLabel("Facebook ");
        hist_type.setBounds(280, 310, 300, 60);
        hist_type.setForeground(new Color(198, 218, 229));
        hist_type.setFocusable(false);
        hist_type.setFont(new Font("calibri", Font.PLAIN, 17));
        progress.add(hist_type);
        hist_type.setVisible(false);
        //**//

        //*bookmarks*//
        book = new JLabel("Bookmarks");
        book.setBounds(50, 230, 300, 60);
        book.setForeground(new Color(198, 218, 229));
        book.setFocusable(false);
        book.setFont(new Font("calibri", Font.PLAIN, 22));
        progress.add(book);
        book.setVisible(false);

        bookmarks_urls = new JLabel("12");
        bookmarks_urls.setBounds(180, 230, 300, 60);
        bookmarks_urls.setForeground(Color.decode("#fd6027"));
        bookmarks_urls.setFocusable(false);
        bookmarks_urls.setFont(new Font("calibri", Font.PLAIN, 18));
        progress.add(bookmarks_urls);
        bookmarks_urls.setVisible(false);

        book_analysis = new JLabel("Bookmarks Analysis");
        book_analysis.setBounds(390, 230, 300, 60);
        book_analysis.setForeground(new Color(198, 218, 229));
        book_analysis.setFocusable(false);
        book_analysis.setFont(new Font("calibri", Font.PLAIN, 17));
        progress.add(book_analysis);
        book_analysis.setVisible(false);

        book_performance = new JLabel("10%");
        book_performance.setBounds(430, 270, 300, 60);
        book_performance.setForeground(new Color(244, 116, 66));
        book_performance.setFocusable(false);
        book_performance.setFont(new Font("calibri", Font.BOLD, 26));
        progress.add(book_performance);
        book_performance.setVisible(false);

        book_type = new JLabel("Wiki");
        book_type.setBounds(430, 310, 300, 60);
        book_type.setForeground(new Color(198, 218, 229));
        book_type.setFocusable(false);
        book_type.setFont(new Font("calibri", Font.PLAIN, 17));
        progress.add(book_type);
        book_type.setVisible(false);
        //**//

        //*downloads*//
        down = new JLabel("Downloads");
        down.setBounds(50, 280, 300, 60);
        down.setForeground(new Color(198, 218, 229));
        down.setFocusable(false);
        down.setFont(new Font("calibri", Font.PLAIN, 22));
        progress.add(down);
        down.setVisible(false);

        downloads_urls = new JLabel("125");
        downloads_urls.setBounds(180, 280, 300, 60);
        //#fd6027
        downloads_urls.setForeground(Color.decode("#fd6027"));
        downloads_urls.setFocusable(false);
        downloads_urls.setFont(new Font("calibri", Font.PLAIN, 18));
        progress.add(downloads_urls);
        downloads_urls.setVisible(false);

        down_analysis = new JLabel("Downloads Analysis");
        down_analysis.setBounds(545, 230, 300, 60);
        down_analysis.setForeground(new Color(198, 218, 229));
        down_analysis.setFocusable(false);
        down_analysis.setFont(new Font("calibri", Font.PLAIN, 17));
        progress.add(down_analysis);
        down_analysis.setVisible(false);

        down_performance = new JLabel("10%");
        down_performance.setBounds(585, 270, 300, 60);
        down_performance.setForeground(new Color(244, 116, 66));
        down_performance.setFocusable(false);
        down_performance.setFont(new Font("calibri", Font.BOLD, 26));
        progress.add(down_performance);
        down_performance.setVisible(false);

        down_type = new JLabel("Images and Music");
        down_type.setBounds(550, 310, 300, 60);
        down_type.setForeground(new Color(198, 218, 229));
        down_type.setFocusable(false);
        down_type.setFont(new Font("calibri", Font.PLAIN, 17));
        progress.add(down_type);
        down_type.setVisible(false);
        //**//
        save_btn = new JButton("Save as PDF");
        save_btn.setBounds(420, 530, 150, 40);
        save_btn.setForeground(new Color(198, 218, 229));
        save_btn.setBackground(new Color(0, 13, 30, 44));
        save_btn.setOpaque(false);
        save_btn.setFocusPainted(false);
        save_btn.setBorderPainted(false);
        save_btn.setContentAreaFilled(false);
        save_btn.setFont(new Font("calibri", Font.PLAIN, 22));
        progress.add(save_btn);
        save_btn.setVisible(false);

        back_btn = new JButton("Back");
        back_btn.setBounds(280, 530, 150, 40);
        back_btn.setForeground(new Color(198, 218, 229));
        back_btn.setBackground(new Color(0, 13, 30, 44));
        back_btn.setOpaque(false);
        back_btn.setFocusPainted(false);
        back_btn.setBorderPainted(false);
        back_btn.setContentAreaFilled(false);
        back_btn.setFont(new Font("calibri", Font.PLAIN, 22));
        progress.add(back_btn);
        back_btn.setVisible(false);
        back_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                progress.setVisible(false);
                home.setVisible(true);
                         
                history_urls.setVisible(false);
                hist_analysis.setVisible(false);
                hist_performance.setVisible(false);
                hist_type.setVisible(false);
                hist.setVisible(false);
                
                downloads_urls.setVisible(false);
                down_analysis.setVisible(false);
                down_performance.setVisible(false);
                down_type.setVisible(false);
                down.setVisible(false);

                bookmarks_urls.setVisible(false);
                book_analysis.setVisible(false);
                book_performance.setVisible(false);
                book_type.setVisible(false);
                book.setVisible(false);

                scan.setText("Scanning...");
                pbar.setVisible(true);
                save_btn.setVisible(false);
                back_btn.setVisible(false);

            }
        });

        int minimum = 0;
        int maximum = 100;
        pbar = new JProgressBar(minimum, maximum);
        pbar.setBounds(280, 280, 300, 30);
        pbar.setFont(new Font("calibri", Font.PLAIN, 18));
        pbar.setForeground(Color.decode("#0e4a60"));
        pbar.setBackground(Color.decode("#02283f"));
        pbar.setStringPainted(true);
        pbar.setValue(0);
        progress.add(pbar);
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SharedModel.writePDF(final_result);
            }
        });

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

                if (checklist[0].isSelected() || checklist[1].isSelected()
                        || checklist[2].isSelected() || checklist[3].isSelected()) {

                    loop:
                    for (JCheckBox i : checklist) {
                        if (i.isSelected()) {
                            name = i.getName();
                            switch (name) {
                                case "selectall":
                                    hist.setVisible(true);
                                    book.setVisible(true);
                                    down.setVisible(true);
                                    break loop;
                                case "history":
                                    hist.setVisible(true);
                                    break;
                                case "bookmarks":
                                    book.setVisible(true);
                                    break;
                                case "downloads":
                                    down.setVisible(true);
                                    break;
                            }
                        }
                    }
                    home.setVisible(false);
                    progress.setVisible(true);
                }

            }
        });

    }

/////////////////Run functions//////////////////////
    public void RunHistory(int days) {
        try {
            pbar.setValue(0);
            pbar.update(pbar.getGraphics());
            History history = new History();
            hist_result = history.Analysis(days, pbar);
            System.out.println(hist_result);
            if (hist_result != null) {
                history_urls.setText(String.valueOf(history.size));
                hist_performance.setText(String.valueOf(hist_result.get(1).get(0)) + "%");
                hist_type.setText(String.valueOf(hist_result.get(0).get(0)));
                final_result.set(0, hist_result);

            } else {
                history_urls.setText("Empty");
                hist_performance.setText("- %");
                hist_type.setText("No Thing");
                final_result.set(0, hist_result);
            }
        } catch (IOException ex) {
        }
    }

    public void RunDownloads(int days) {
        try {
            pbar.setValue(0);
            pbar.update(pbar.getGraphics());
            Downloads downloads = new Downloads();
            down_result = downloads.Analysis(days, pbar);
            if (down_result != null) {
                downloads_urls.setText(String.valueOf(downloads.size));
                down_performance.setText(String.valueOf(down_result.get(1).get(0)) + "%");
                down_type.setText(String.valueOf(down_result.get(0).get(0)));
                final_result.set(1, down_result);
            } else {
                downloads_urls.setText("Empty");
                down_performance.setText("- %");
                down_type.setText("No Thing");
                final_result.set(1, down_result);
            }
        } catch (IOException ex) {

        }

    }

    public void RunBookmarks(int days) throws ParseException {
        try {
            pbar.setValue(0);
            pbar.update(pbar.getGraphics());
            Bookmarks bookmarks = new Bookmarks();
            book_result = bookmarks.Analysis(days, pbar);
            if (book_result != null) {
                bookmarks_urls.setText(String.valueOf(bookmarks.size));
                book_performance.setText(String.valueOf(book_result.get(1).get(0)) + "%");
                book_type.setText(String.valueOf(book_result.get(0).get(0)));
                final_result.set(2, book_result);
            } else {
                bookmarks_urls.setText("Empty");
                book_performance.setText("- %");
                book_type.setText("No Thing");
                final_result.set(2, book_result);
            }

        } catch (IOException ex) {

        }
    }
}
