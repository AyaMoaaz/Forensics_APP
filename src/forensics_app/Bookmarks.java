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
public class Bookmarks {
    /*Description  : bnrg3 l data mn l sqllite file fe resultset 3adi 
      * we b3d keda bn3ml list mn l object DownloadContent we nbd2 nfadi l resultset f objects
      * return : list of objects from DownloadContent
      */
    // mal7oza l time hena l mfrod yrg3 3ala hy2t time stamp 3ala tol 
      ArrayList<BookmarksContent>  RetrunData(String time) 
    {
       
     ArrayList<BookmarksContent> listOf_bookmarks_content=new ArrayList();
     return listOf_bookmarks_content;
        
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
