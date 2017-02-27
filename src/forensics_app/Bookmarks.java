/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Ayaa
 */
public class Bookmarks {
    public static String jsonFile = "C:\\Users\\Ayaa\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\bookmarks";
    static int count = 0;
    /*Description  : bnrg3 l data mn l sqllite file fe resultset 3adi 
      * we b3d keda bn3ml list mn l object DownloadContent we nbd2 nfadi l resultset f objects
      * return : list of objects from DownloadContent
      */
    // mal7oza l time hena l mfrod yrg3 3ala hy2t time stamp 3ala tol 
     ArrayList<BookmarksContent>  RetrunData() 
    {
      ArrayList<BookmarksContent> listOf_bookmarks_content=new ArrayList();
        // a file reader class to access the file using string file path 
        FileReader reader = null;
        try {
            reader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // access the file
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) new JSONParser().parse(reader);
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String checksum = (String) jsonObject.get("checksum");

        JSONObject root = (JSONObject) jsonObject.get("roots");

        Set<String> set = root.keySet();
        JSONArray childrens = null;
        JSONObject obj = null;
        for (String string : set) {
            try {
                obj = (JSONObject) root.get(string);
            } catch (ClassCastException e) {

            }
            if (obj.containsKey("children")) {
                try {
                    childrens = (JSONArray) obj.get("children");
                    listOf_bookmarks_content=printContent(childrens);
                   
                } catch (Exception e) {

                }
            }
            
        }
       
        // display , how many urls we have found  
        System.out.println("count is " + count);
         return listOf_bookmarks_content;
    }
    public static  ArrayList<BookmarksContent> printContent(JSONArray childrens) {
       ArrayList<BookmarksContent> list_bookmarks =new ArrayList<BookmarksContent>();
       ArrayList<String> type_bookmarks = new ArrayList<String>();
       ArrayList<String> urls = new ArrayList<String>();
       ArrayList<String> dateAdded = new ArrayList<String>();
       ArrayList<String> Name = new ArrayList<String>();
       BookmarksContent book=new BookmarksContent();
        JSONObject temp = null;
        for (int i = 0; i < childrens.size(); i++) {
            // get object using index from childrens array
            temp = (JSONObject) childrens.get(i);
            if (temp.containsKey("children")) {
                printContent((JSONArray) temp.get("children"));
            }
            // get url
            String url = (String) temp.get("url");
            urls.add(url);
            book.SetURL(url);
            String name = (String) temp.get("name");
            Name.add(name);
            book.SetName(name);
            String type = (String) temp.get("type");
            type_bookmarks.add(type);
            book.SetType(type);
            String date_added = (String) temp.get("date_added");
            dateAdded.add(date_added);
            book.SetDateAdded(date_added);       
            list_bookmarks.add(book);
          
            if (url != null) {
//                System.out.println("url: "+url+"\n"+"name: "+ name+"\n"+"type: "+type+"\n"+"date added: "+date_added);
              System.out.println("url: "+book.GetURL()+"\n"+"name: "+ book.GetName()+"\n"+"type: "+book.GetType()+"\n"+"date added: "+book.GetDateAdded());
                count++;
                
            }
    }
        return list_bookmarks;
    }
    /*
      *nafs l fkra feli 3mlnah fel hisotry hn3ml call le function l return data 
      *n7dflha l timestamp ali hwa l fatra ali htgeb feha l data we n3ml for loop ntl3 beha l keywords
      *we n7ded l type we b3d kda n7dfha lel percentage we n3ml return fel a5r
      *return : 2d list ali rag3a mn function Percentage
    */
      ArrayList<ArrayList<String>> Analysis(int time)
      {
          ArrayList<ArrayList<String>> Percentage_list = new ArrayList<ArrayList<String>>();
          return Percentage_list;
      } 
}
