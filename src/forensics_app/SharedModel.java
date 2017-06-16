/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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

    /*Description  : bn3ml array list of strings we na5od l url ntl3 l keywords bta3to we b3den n3mla 
    add fel list de 
    *we lw mlo4 keywords bn7ot NaN fe l list we we b3d keda n3mlha return	
    *return : array list of keywords
     */
    public static ArrayList<String> GetKeywords(String URl) throws IOException {
        ArrayList<String> keywords_list = new ArrayList<String>();
        Document doc = null;
        String content = null;
        String[] split = {""};
        try {
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
        ArrayList<Double> vals = new ArrayList<Double>();
        for (String typo : Types_list) {
            if (!types.contains(typo)) {
                int sum = Collections.frequency(Types_list, typo);
                double total = ((double) sum * 100 / (double) Types_list.size());
                types.add(typo);
                vals.add(total);
            }
        }
        Percentage_list.add(types);
        Percentage_list.add(vals);
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

    public static Date ParsingTimestamp(String timestamp) {
        BigInteger db_value = new BigInteger(timestamp);
        BigInteger sub = new BigInteger("11644473600");
        BigInteger div = new BigInteger("1000000");
        BigInteger result = (db_value.divide(div)).subtract(sub);
        Timestamp stamp = new Timestamp(result.longValue() * 1000);
        Date date_stamp = new Date(stamp.getTime());// el stamp 3la 4kl date (old);
        return date_stamp;
    }

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

//    public static String CheckDictionary(ArrayList<String> keywords) {
//        String Type = "unknown";
//        Hashtable dict = new Hashtable(); // da l dictionary 
//        dict = Dictoionary();
//        for (int i = 0; i < keywords.size(); i++) {
//            try {
//                Type = dict.get(keywords.get(i)).toString();
//                if (!Type.equals("unknown")) {
//                    break;
//                }
//            } catch (Exception e) {
//            }
//        }
//        return Type;
//
//    }
//    public static Hashtable Dictoionary() {
//        Hashtable dict = new Hashtable();
//
//        /**
//         * ***********social media************
//         */
//        dict.put("facebook", "social");
//        dict.put("twitter", "social");
//        dict.put("instgram", "social");
//        dict.put("whatsapp", "social");
//        /**
//         * ***********sports***********
//         */
//        dict.put("football", "sport");
//        dict.put("sports", "sport");
//        dict.put("the middle east ", "sport");
//        dict.put("africa", "sport");
//        dict.put("abo tricka", "sport");
//        dict.put("bascketball ", "sport");
//        dict.put("volleyball ", "sport");
//        dict.put("professional wrestling", "sport");
//        dict.put("karate", "sport");
//        /**
//         * ********music ***************
//         */
//        dict.put("record", "music");
//        dict.put("sounds", "music");
//        dict.put("audio", "music");
//        dict.put("record ", "music");
//        dict.put("trackes", "music");
//        dict.put("sound", "music");
//        /**
//         * **************games**********
//         */
//        dict.put("ubi soft", "games");
//        dict.put("ubi", "games");
//        dict.put("assassinscreed", "games");
//        dict.put("far cry", "games");
//        dict.put("games", "games");
//        dict.put("video game", "games");
//        dict.put("videogame", "games");
//        dict.put("video games", "games");
//        /**
//         * ***********news***************
//         */
//        dict.put("news", "news");
//        dict.put("breaking news", "news");
//        dict.put("latest news", "news");
//        dict.put("news online", "news");
//        dict.put("news", "news");
//        dict.put("weather", "news");
//        dict.put("law", "news");
//        dict.put("money", "news");
//        dict.put("reports", "news");
//        dict.put("special reports", "news");
//        /**
//         * ********************hacking ************************
//         */
//        dict.put("hacker", "hacking");
//        dict.put("hacking", "hacking");
//        /**
//         * *******************mail***********************
//         */
//        dict.put("email", "email");
//        dict.put("yahoo email", "email");
//        dict.put("contact email", "email");
//        dict.put("phone email", "email");
//        dict.put("gmail", "email");
//        /**
//         * **************************************
//         */
//
//        return dict;
//    }
}
