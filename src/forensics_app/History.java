/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JProgressBar;

/**
 *
 * @author Ayaa
 */
public class History extends SharedModel<Object> {

    static public int size;// by3ml return lel size bta3 l urls

//    /*Description  : bnrg3 l data mn l sqllite file fe resultset 3adi 
//	*we b3d keda bn3ml list mn l object HistoryContent we nbd2 nfadi l resultset f object	
//        *return : list of objects from HistoryContent
//     */
    public ArrayList<HistoryContent> ReturnData(int time) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        ArrayList<HistoryContent> listOf_History_content = new ArrayList<HistoryContent>();
        HistoryContent hs = null;
        String stime = "";
        String user = System.getProperty("user.name");
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\" + user + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History");
            statement = connection.createStatement();

            if (time != -1) {
                stime = ParsingTime(time);

            } else { // 34an y3ml select all lel data 
                stime = "-1";
            }
            resultSet = statement.executeQuery("select url,title,last_visit_time "
                    + "from urls "
                    + "where last_visit_time > " + stime
                    + " order by last_visit_time desc");
            while (resultSet.next()) {
                hs = new HistoryContent();
                hs.SetUrl(resultSet.getString("url"));
                hs.SetTitle(resultSet.getString("title"));
                hs.SetVisitTime(resultSet.getString("last_visit_time"));
                listOf_History_content.add(hs);
            }
            resultSet.close();
            statement.close();
            connection.close();

            return listOf_History_content;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    /*Description  : hena m4 hn3ml 7aga aktr mn enna n3ml call lel functons ali 3mlnha fo2 we 4wayt loops	
//     *l2wel hanadi 3ala function l return data we n7otha f list we b3d keda hnm4i 3ala row row fe l	
//     *list de be loop na5od l url bta3ha we n3mlo pass le function l GetKeywords we nrag3 l keywords	
//     *f list we b3d keda n3ml check lw l list de mfha4 8er (NaN) n5leh ytl3 l tokens 	
//     *l awl n3ml call le function GetTokens wn7dflha l url we b3d keda nenadi 3ala nafs la function 
//     *we n7dfhla l title 
//     *b3d keda n3ml check 3al keywords aw l tokens de fel dictionary we n7aded l type 
//     *kol da gwa l loop 
//     *b3d ma l loop t5las n3ml call le function l percentage
//     *we ndeha l list bta3t l opject we n7dedlo l col bta3 l type
//     *we l return bta3ha hn7oto f 2d list l2wel 
//     *we b3d keda n3ml l algorithm ali hn7ded beh l user 2a3d kam sa3a fel yom 
//     *we b3d ma n7dedo ndfeo fel 2d list yb2a hwa a5er row 
//     *we b3d keda n3ml return lel 2d list de
//     *return : 2d list ali rag3a mn function Percentage
//     */
    public List<List> Analysis(int time, JProgressBar PBar) throws IOException {
        List<List> Percentage_list = new ArrayList<List>();
        ArrayList<HistoryContent> listOf_History_content = new ArrayList<HistoryContent>();
        ArrayList<String> keywords_list = new ArrayList<String>();
        ArrayList<String> types = new ArrayList<String>();
        String type;
        String URL;
        String title;
        int url_finshid;// l percentages ali 5lest 34an trg3 lel progress bar
        listOf_History_content = ReturnData(time);
        size = listOf_History_content.size(); // byrg3 l size bta3 l urls
        for (int i = 0; i < size; i++) {

            url_finshid = ((i + 1) * 100) / size;
            /**
             *
             */
            URL = listOf_History_content.get(i).GetUrl();
            title = listOf_History_content.get(i).GetTitle();
            keywords_list = GetKeywords(URL);

            if (!keywords_list.contains("NaN")) {
                type = CheckDictionary(keywords_list);
                types.add(type);
            } else {
                keywords_list.clear();
                keywords_list.addAll(GetTokens(URL));
                keywords_list.addAll(GetTokens(title));
                type = CheckDictionary(keywords_list);
                types.add(type);
            }
            System.out.println(i + ") " + URL + "||" + url_finshid);
            // bnb3t l finished percentage lel progress bar

            PBar.setValue(url_finshid);
            PBar.update(PBar.getGraphics());

        }
        PBar.setValue(100);
        PBar.update(PBar.getGraphics());

        Percentage_list = GetPercentage(types);
        return Percentage_list;
    }
}
