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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Ayaa
 */
public class SharedModel<E> extends ArrayList<E> {
    
    private Map<E,Integer> count = new HashMap<E,Integer>();

    // There are several entry points to this class
    // this is just to show one of them.
    public boolean add( E element  ) { 
        if( !count.containsKey( element ) ){
            count.put( element, 1 );
        } else { 
            count.put( element, count.get( element ) + 1 );
        }
        return super.add( element );
    }

    // This method belongs to CountItemList interface ( or class ) 
    // to used you have to cast.
    public int getCount( E element ) { 
        if( ! count.containsKey( element ) ) {
            return 0;
        }
        return count.get( element );
    }


    /*Description  : bn3ml array list of strings we na5od l url ntl3 l keywords bta3to we b3den n3mla 
    add fel list de 
    *we lw mlo4 keywords bn7ot NaN fe l list we we b3d keda n3mlha return	
    *return : array list of keywords
     */
    ArrayList<String> GetKeywords(String URl) throws IOException {
        ArrayList<String> keywords_list = new ArrayList<String>();
        Document doc = Jsoup.connect(URl).userAgent("Mozilla").get();
        String content = (doc.select("head meta[name=keywords]").attr("content"));
        String[] split = content.split("\\s*,\\s*");
        if (split.length > 1 || (split.length > 0 && split[0] != null && !split[0].isEmpty())) {
            content = content.toLowerCase();
            keywords_list = new ArrayList(Arrays.asList(content.split(",")));
            /*for (int i = 0; i < keywords_list.size(); i++) {
                System.out.println(keywords_list.get(i));
            }*/
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
    ArrayList<String> GetTokens(String Url) {
        
        ArrayList<String> tokens_list = new ArrayList<String>();
        StringTokenizer token;
        ArrayList<String> stopWords = new ArrayList<>(Arrays.asList("a","the",
                "is","are","in","on","and","to","all","with","http",
                "www","https","net","org","com","gov","eg","uk",
                "apk","edu","rar","tv","pdf","ppt","pptx","dll",
                "dat","dmg","dwg","jar","zip","xml","bin","xls",
                "xlxs","mkv","lnk","lhg","من","فى","على","إلى",
                "و","عن"));
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
            
        while (token.hasMoreTokens()){
            word = token.nextToken();
            for (int i = 0; i < stopWords.size() ; i++){
                
                if (word.equals(stopWords.get(i)))
                {
                    test = true;
                    break;
                }
            }
            if (test == false)
            {
                tokens_list.add(word);
            }
        }
        return tokens_list;
    }

    /*Description  : bta5od list of strings (ali heya l types) we bt3ml l kol no3 (type) count we 
      *tbd2 t7sb l percentage le kol type we trg3lna 2d list kol type we 2odamo l percentage bta3o 
      *return : 2d list of strings [type name , percentage]
     */
    List<List> GetPercentage(ArrayList<String> Types_list) {
        List<List> Percentage_list= new ArrayList<List>();
        ArrayList<String> types=new ArrayList<String>();
        ArrayList<Integer> vals=new ArrayList<Integer>();
        Types_list.add("sport");
        Types_list.add("social");
        Types_list.add("social");
        Types_list.add("news");
        Types_list.add("sport");
        Types_list.add("sport");
        Types_list.add("social");
        Types_list.add("social");
        Types_list.add("social");
        Types_list.add("sport");
       for(String typo: Types_list){
           int sum=(( SharedModel<String> )Types_list).getCount( typo );
           int total=sum*100/Types_list.size();
           types.add(typo);
           vals.add(total);
    }
       Percentage_list.add(types);
       Percentage_list.add(vals);
       return Percentage_list;
        //test commit

    }

    /*Description  : bta5od l time we t7wlo le timestamp ali hyd5lo l user
	*y3ni msln l user hy2ol 2 days hngeb l tare5 bta3 enhrda we n7awlo l timestamp
	*we ntr7 mno 2 days we b3d keda n3mlo return
        *return : String timestamp
     */
    String ParsingTime(int time) {
        long UserTime = 86400 * time; // de 3dad l ayam ali da5lha l user bel sec
        long NowTime = (System.currentTimeMillis() / 1000) - UserTime; // timestamp now
        BigInteger x1 = new BigInteger(String.valueOf(NowTime));
        BigInteger x2 = new BigInteger("11644473600");
        BigInteger x3 = (x1.add(x2)).multiply(BigInteger.valueOf(1000000));
        return x3.toString();
    }

    Date ParsingTimestamp(String timestamp) {
        BigInteger db_value = new BigInteger(timestamp);
        BigInteger sub = new BigInteger("11644473600");
        BigInteger div = new BigInteger("1000000");
        BigInteger result = (db_value.divide(div)).subtract(sub);
        Timestamp stamp = new Timestamp(result.longValue() * 1000);
        Date date_stamp = new Date(stamp.getTime());// el stamp 3la 4kl date (old);
        return date_stamp;
    }
}
