/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import com.itextpdf.text.BaseColor;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Ayaa
 */
public class SharedModel<E> extends ArrayList<E> {

    private Map<E, Integer> count = new HashMap<E, Integer>();

    // There are several entry points to this class
    // this is just to show one of them.
    public boolean add(E element) {
        if (!count.containsKey(element)) {
            count.put(element, 1);
        } else {
            count.put(element, count.get(element) + 1);
        }
        return super.add(element);
    }

    // This method belongs to CountItemList interface ( or class ) 
    // to used you have to cast.
    public int getCount(E element) {
        if (!count.containsKey(element)) {
            return 0;
        }
        return count.get(element);
    }
////////////////check connection

    public static boolean isInternetReachable() {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
            Object objData = urlConnect.getContent();
            return true;
        } catch (UnknownHostException e) {
            //e.printStackTrace();
            return false;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
    }

    ///////// pdfWriter
    public static void writePDF(List<List<List>> final_result) {
        List<List> al = new ArrayList<List>();
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
        Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLUE);
        Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        String fileName = System.getProperty("user.home")+"\\Desktop\\"+ System.currentTimeMillis() + ".pdf";
        Document document = null;
        document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.add(new Phrase("\n"));
            document.add(new Paragraph("ForEye Smart Report", redFont));
            document.add(new Phrase("\n\n"));
            for (int z = 0; z < 3; z++) {
                al = final_result.get(z);
                switch (z) {
                    case 0:
                        if (al != null) {
                            document.add(new Phrase("\n-> History\n", blueFont));
                            document.add(new Paragraph("This user interseted in  " + al.get(0).get(0) + "  by " + String.valueOf(al.get(1).get(0)) + "%", catFont));
                            document.add(new Phrase("\nthe rest of report\n", subFont));
                            for (int j = 0; j < al.get(0).size(); j++) {
                                document.add(new Phrase(al.get(0).get(j) + "  by " + String.valueOf(al.get(1).get(j)) + "%"));
                                document.add(new Phrase("\n"));
                            }
                            document.add(new Phrase("\n\n"));
                        }
                        break;
                    case 1:
                        if (al != null) {
                            document.add(new Phrase("\n-> Download\n", blueFont));
                            document.add(new Paragraph("This user interseted in  " + al.get(0).get(0) + "  by " + String.valueOf(al.get(1).get(0)) + "%", catFont));
                            document.add(new Phrase("\nthe rest of report\n", subFont));
                            for (int j = 0; j < al.get(0).size(); j++) {
                                document.add(new Phrase(al.get(0).get(j) + "  by " + String.valueOf(al.get(1).get(j)) + "%"));
                                document.add(new Phrase("\n"));
                            }
                            document.add(new Phrase("\n\n"));
                        }
                        break;
                    case 2:
                        if (al != null) {
                            document.add(new Phrase("\n-> Bookmarks\n", blueFont));
                            document.add(new Paragraph("This user interseted in  " + al.get(0).get(0) + "  by " + String.valueOf(al.get(1).get(0)) + "%", catFont));
                            document.add(new Phrase("\nthe rest of report\n", subFont));
                            for (int j = 0; j < al.get(0).size(); j++) {
                                document.add(new Phrase(al.get(0).get(j) + "  by " + String.valueOf(al.get(1).get(j)) + "%"));
                                document.add(new Phrase("\n"));
                            }
                            document.add(new Phrase("\n\n"));
                        }
                        break;
                }
            }
            document.close();
            writer.close();
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(fileName);
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    // no application registered for PDFs
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*Description  : bn3ml array list of strings we na5od l url ntl3 l keywords bta3to we b3den n3mla 
    add fel list de 
    *we lw mlo4 keywords bn7ot NaN fe l list we we b3d keda n3mlha return	
    *return : array list of keywords
     */
    public static ArrayList<String> GetKeywords(String URl) throws IOException {
        ArrayList<String> keywords_list = new ArrayList<String>();
        org.jsoup.nodes.Document doc;
        doc = null;
        String content = null;
        String[] split = {""};
        try {
            //jsoup.nodes.Document doc;
            doc = Jsoup.connect(URl).userAgent("IE").get();
            content = (doc.select("head meta[name=keywords]").attr("content"));
            split = content.split("\\s*,\\s*");

        } catch (Exception e) {
        }

        if (split.length > 1 || (split.length > 0 && split[0] != null && !split[0].isEmpty())) {
            content = content.toLowerCase();
            keywords_list = new ArrayList(Arrays.asList(content.split(", ")));
            return keywords_list;
        } else {
            keywords_list.add("NaN");
            return keywords_list;
        }

    }

    /* Description  : kol ali bt3mlo l function de enha lama nb3tlha l url aw l title ,
    *trg3lna l tokens bta3thom f list mo7trma ,
    *we n3ml 7sab l stop words zy l www we l http ,
    *dol m4 3yzhom yrg3o ka tokens we replace lel symbols be space
    *return : array list of tokens
     */
    public static ArrayList<String> GetTokens(String Url) {

        ArrayList<String> tokens_list = new ArrayList<String>();
        StringTokenizer token;
        ArrayList<String> stopWords = new ArrayList<>(Arrays.asList("a", "the",
                "is", "are", "in", "on", "and", "to", "all", "with", "http",
                "www", "https", "net", "org", "com", "gov", "eg", "uk",
                "apk", "edu", "rar", "tv", "pdf", "ppt", "pptx", "dll",
                "dat", "dmg", "dwg", "jar", "zip", "xml", "bin", "xls",
                "xlxs", "mkv", "lnk", "lhg", "من", "فى", "على", "إلى",
                "و", "عن", "html"));
        boolean test = false;
        String word;
        Url = Url.toLowerCase();
        Url = Url.replace(",", " ").replace(".", " ").replace(";", " ")
                .replace("!", " ").replace("[", " ").replace("]", " ")
                .replace(":", " ").replace("?", " ").replace("؟", " ")
                .replace("{", " ").replace("}", " ").replace("/", " ")
                .replace("*", " ").replace("*", " ").replace("&", " ")
                .replace("%", " ").replace("^", " ").replace("$", " ")
                .replace("#", " ").replace("(", " ").replace(")", " ")
                .replace("_", " ").replace("-", " ").replace("[", " ")
                .replace("]", " ").replace("!", " ").replace("@", " ")
                .replace("+", " ").replace("`", " ").replace("<", " ")
                .replace(">", " ").replace("0", " ").replace("1", " ")
                .replace("2", " ").replace("3", " ").replace("7", " ")
                .replace("4", " ").replace("5", " ").replace("6", " ")
                .replace("8", " ").replace("9", " ").replace("10", " ")
                .replace("–", " ").replace("", " ").replace("ð", " ")
                .replace("'", " ").replace("—", " ").replace("\"", " ");

        token = new StringTokenizer(Url);

        while (token.hasMoreTokens()) {
            word = token.nextToken();
            test = false;
            for (int i = 0; i < stopWords.size(); i++) {

                if (word.equals(stopWords.get(i))) {
                    test = true;
                    break;
                }
            }
            if (test == false) {
                tokens_list.add(word);
            }
        }
        return tokens_list;
    }

    /*Description  : bta5od list of strings (ali heya l types) we bt3ml l kol no3 (type) count we 
      *tbd2 t7sb l percentage le kol type we trg3lna 2d list kol type we 2odamo l percentage bta3o 
      *return : 2d list of strings [type name , percentage]
     */
    public static List<List> GetPercentage(ArrayList<String> Types_list) {
        List<List> Percentage_list = new ArrayList<List>();
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<Integer> vals = new ArrayList<Integer>();
        for (String typo : Types_list) {
            if (!types.contains(typo)) {
                int sum = Collections.frequency(Types_list, typo);
                int total = (sum * 100 / Types_list.size());
                types.add(typo);
                vals.add(total);
            }
        }
        //Percentage_list.add(types);
        //Percentage_list.add(vals);
        System.out.println("not sorted types: "+types +"\tnot sorted values: "+vals);
        for (int i = 0; i < vals.size(); i++) {
        if (i < vals.size() - 1) {
            if (vals.get(i) < vals.get(i + 1)) {
                int j = vals.get(i);
                String s = types.get(i);
                vals.remove(i);
                types.remove(i);
                vals.add(i, vals.get(i));
                types.add(i, types.get(i));
                vals.remove(i + 1);
                types.remove(i + 1);
                vals.add(j);
                types.add(s);
                i = -1;
            }
        }
    }
        Percentage_list.add(types);
        Percentage_list.add(vals);
        System.out.println("sorted types: "+types +"\tsorted values: "+vals);
        if (types.size() == 0) {
            Percentage_list = null;
        }
        return Percentage_list;

    }

    /*Description  : bta5od l time we t7wlo le timestamp ali hyd5lo l user
	*y3ni msln l user hy2ol 2 days hngeb l tare5 bta3 enhrda we n7awlo l timestamp
	*we ntr7 mno 2 days we b3d keda n3mlo return
        *return : String timestamp
     */
    public static String ParsingTime(int time) {
        long UserTime = 86400 * time; // de 3dad l ayam ali da5lha l user bel sec
        long NowTime = (System.currentTimeMillis() / 1000) - UserTime; // timestamp now
        BigInteger x1 = new BigInteger(String.valueOf(NowTime));
        BigInteger x2 = new BigInteger("11644473600");
        BigInteger x3 = (x1.add(x2)).multiply(BigInteger.valueOf(1000000));
        return x3.toString();
    }

//    public static Date ParsingTimestamp(String timestamp) {
//        BigInteger db_value = new BigInteger(timestamp);
//        BigInteger sub = new BigInteger("11644473600");
//        BigInteger div = new BigInteger("1000000");
//        BigInteger result = (db_value.divide(div)).subtract(sub);
//        Timestamp stamp = new Timestamp(result.longValue() * 1000);
//        Date date_stamp = new Date(stamp.getTime());// el stamp 3la 4kl date (old);
//        return date_stamp;
//    }
    public static String CheckDictionary(ArrayList<String> keywords) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String Type = "unknown";

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Keywords.db");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("error in connection");
        }

        for (String keyword : keywords) {

            try {
                resultSet = statement.executeQuery("select Type from Keyword where Keyword = '" + keyword + "'");
                while (resultSet.next()) {
                    Type = resultSet.getString("Type").toLowerCase();
                }
            } catch (Exception e) {
                System.out.println("fff");
            }

            if (!Type.equals("unknown")) {
                break;
            }

        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("zz");
        }
        return Type;

    }

}
