/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ayaa
 */
public class Forensics_APP {

    /**
     * @param args the command line arguments
     * @throws org.json.simple.parser.ParseException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws org.json.simple.parser.ParseException, IOException {
        
        //History hist = new History();
        //System.out.println(hist.Analysis(1));
        if (SharedModel.isInternetReachable()){
            //our code should be here
            System.out.println("network connection available ");
        }
        else
            System.out.println("check your internet connection");

    }

}
