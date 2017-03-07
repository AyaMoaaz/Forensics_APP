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
     */
    public static void main(String[] args)throws org.json.simple.parser.ParseException, IOException {
    Bookmarks book=new Bookmarks();
    book.Analysis(50);
    }

}
