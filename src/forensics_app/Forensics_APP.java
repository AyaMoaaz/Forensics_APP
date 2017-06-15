/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import java.io.IOException;

/**
 *
 * @author Ayaa
 */
public class Forensics_APP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws org.json.simple.parser.ParseException, IOException {

        History hist = new History();
        //System.out.println(hist.Analysis(1));
//        System.out.println(hist.Analysis(1));
//        Thread percentage = new Thread() {
//            Boolean finish = false;
//            public void run() {
//                while (!finish) {
//                    int i = hist.Urls_finished(1);
//                    if (i < 100) {
//                        System.out.print(i);
//                    } else {
//                        finish = true;
//                    }
//                }
//            }
//        };
        GUI start = new GUI();

    }

}
