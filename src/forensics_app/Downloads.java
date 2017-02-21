/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import java.util.ArrayList;

/**
 *
 * @author Ayaa
 */
public class Downloads {
    /*Description  : bnrg3 l data mn l sqllite file fe resultset 3adi 
       *we b3d keda bn3ml list mn l object DownloadContent we nbd2 nfadi l resultset f objects
       *return : list of objects from DownloadContent
      */
      // mal7oza l time hena l mfrod yrg3 3ala hy2t time stamp 3ala tol 
     ArrayList<DownloadsContent> ReturnData(int time)
            
    {
        ArrayList<DownloadsContent> listOf_Downloads_content = new ArrayList();
        return listOf_Downloads_content;
    }
   
     /*Description  : nafs l fkra feli 3mlnah fel hisotry hn3ml call le function l return data we 
     *n7dflha l timestamp ali hwa l fatra ali htgeb feha l data . we b3d kda hnb3t col
     *mimetype le function percentages
     *return : 2d list ali rag3a mn function Percentage	
     */
      ArrayList<ArrayList<String>>  Analysis(int time)
    {
       
          ArrayList<ArrayList<String>> Percentage_list = new ArrayList<ArrayList<String>>();
          return Percentage_list;
    }
}
