/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

//import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
//import java.util.List;
import java.util.Set;
import javax.swing.JProgressBar;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Ayaa
 */
public class Bookmarks extends SharedModel<Object> {

    static public int size;

    public ArrayList<BookmarksContent> ReturnData(int time) throws org.json.simple.parser.ParseException, IOException {
        String user = System.getProperty("user.name");
        String jsonFile = "C:\\Users\\" + user + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\bookmarks";
        FileReader reader = new FileReader(jsonFile);
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(reader);
        JSONObject root = (JSONObject) jsonObject.get("roots");
        Set<String> set = root.keySet();
        JSONArray childrens = null;
        JSONObject obj = null;
        ArrayList<BookmarksContent> list_bookmarks = new ArrayList<BookmarksContent>();
        BookmarksContent book = null;
        set.remove("sync_transaction_version");
        BigInteger user_value;
        if (time != -1) {
            user_value = new BigInteger(ParsingTime(time));
        } else {
            user_value = new BigInteger(String.valueOf(time));
        }
        BigInteger db_value = null;
        for (String string : set) {
            obj = (JSONObject) root.get(string);
            if (obj.containsKey("children")) {
                childrens = (JSONArray) obj.get("children");
                JSONObject temp = null;
                for (int i = 0; i < childrens.size(); i++) {
                    temp = (JSONObject) childrens.get(i);
                    db_value = new BigInteger(String.valueOf(temp.get("date_added")));
                    if (db_value.compareTo(user_value) == 1) {
                        book = new BookmarksContent();
                        book.SetURL(temp.get("url").toString());
                        book.SetName(temp.get("name").toString());
                        book.SetType(temp.get("type").toString());
                        book.SetDateAdded(temp.get("date_added").toString());
                        list_bookmarks.add(book);
                    }

                }
            }
        }
        //    for (int j = 0; j < list_bookmarks.size(); j++) {
        //          System.out.println(list_bookmarks.get(j).GetURL());
//        }

        return list_bookmarks;
    }

    /*
     *nafs l fkra feli 3mlnah fel hisotry hn3ml call le function l return data 
     *n7dflha l timestamp ali hwa l fatra ali htgeb feha l data we n3ml for loop ntl3 beha l keywords
     *we n7ded l type we b3d kda n7dfha lel percentage we n3ml return fel a5r
     *return : 2d list ali rag3a mn function Percentage
     */
    //
    List<ArrayList<String>> Analysis(int time,JProgressBar PBar) throws org.json.simple.parser.ParseException, IOException {
        List Percentage_list = new ArrayList<ArrayList<String>>();
        ArrayList<BookmarksContent> list_bookmarks = new ArrayList<BookmarksContent>();
        ArrayList<String> keywords_list = new ArrayList<String>();
        ArrayList<String> types_list = new ArrayList<String>();
        String type;
        String name;
        String url;
        list_bookmarks = ReturnData(time);
        int url_finshid;
        size = list_bookmarks.size();
        System.out.println("bookmarks_size: " + list_bookmarks.size());
        for (int i = 0; i < size; i++) {
            url_finshid = ((i + 1) * 100) / size;

            name = list_bookmarks.get(i).GetName();
            url = list_bookmarks.get(i).GetURL();
            keywords_list = GetKeywords(url);
            if (!keywords_list.contains("NaN")) {
                type = CheckDictionary(keywords_list);
                types_list.add(type);
            } else {
                keywords_list.clear();
                keywords_list.addAll(GetTokens(url));
                keywords_list.addAll(GetTokens(name));
                type = CheckDictionary(keywords_list);
                types_list.add(type);
            }
            System.out.println(i + ") " + url + "||" + url_finshid);
            PBar.setValue(url_finshid);
            PBar.update(PBar.getGraphics());
        }
        Percentage_list = GetPercentage(types_list);
        return Percentage_list;
    }
}
