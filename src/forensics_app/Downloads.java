/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import static forensics_app.SharedModel.ParsingTime;
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
public class Downloads extends SharedModel<Object> {

    static public int size;

    /*Description  : bnrg3 l data mn l sqllite file fe resultset 3adi 
       *we b3d keda bn3ml list mn l object DownloadContent we nbd2 nfadi l resultset f objects
       *return : list of objects from DownloadContent
     */
    // mal7oza l time hena l mfrod yrg3 3ala hy2t time stamp 3ala tol 
    public ArrayList<DownloadsContent> ReturnData(int time) {
        ArrayList<DownloadsContent> listOf_Downloads_content = new ArrayList();
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        DownloadsContent Download = null;
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
            resultSet = statement.executeQuery("select tab_url,mime_type,start_time "
                    + "from downloads "
                    + "where start_time > " + stime
                    + " order by start_time desc");
            String mime = "";
            String[] parts;
            while (resultSet.next()) {
                Download = new DownloadsContent();
                Download.SetUrl(resultSet.getString("tab_url"));
                mime = resultSet.getString("mime_type");
                parts = mime.split("/");
                Download.SetMimeType(parts[0]);
                Download.SetTime(mime);
                listOf_Downloads_content.add(Download);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return listOf_Downloads_content;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*Description  : nafs l fkra feli 3mlnah fel hisotry hn3ml call le function l return data we 
     *n7dflha l timestamp ali hwa l fatra ali htgeb feha l data . we b3d kda hnb3t col
     *mimetype le function percentages
     *return : 2d list ali rag3a mn function Percentage	
     */
    public List<List> Analysis(int time, JProgressBar PBar) throws IOException {

        List<List> Percentage_list = new ArrayList<List>();
        ArrayList<DownloadsContent> Data_List = new ArrayList<DownloadsContent>();
        Data_List = ReturnData(time);
        int url_finshid;
        ArrayList<String> Mime_Type = new ArrayList<String>();
        size = Data_List.size();
        for (int i = 0; i < size; i++) {
            url_finshid = ((i + 1) * 100) / size;
            Mime_Type.add(Data_List.get(i).GetMimeType());
            PBar.setValue(url_finshid);
            PBar.update(PBar.getGraphics());
        }
        PBar.setValue(100);
        PBar.update(PBar.getGraphics());
        Percentage_list = GetPercentage(Mime_Type);

        return Percentage_list;
    }
}
