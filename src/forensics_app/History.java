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
public class History {
    
       /*Description  : bnrg3 l data mn l sqllite file fe resultset 3adi 
	*we b3d keda bn3ml list mn l object HistoryContent we nbd2 nfadi l resultset f object	
        *return : list of objects from HistoryContent
	*/


     ArrayList<HistoryContent> ReturnData(int time)
     {
          ArrayList<HistoryContent> listOf_History_content=new ArrayList();
          return listOf_History_content;
     }
   
     

		
    /*Description  : hena m4 hn3ml 7aga aktr mn enna n3ml call lel functons ali 3mlnha fo2 we 4wayt loops	
     *l2wel hanadi 3ala function l return data we n7otha f list we b3d keda hnm4i 3ala row row fe l	
     *list de be loop na5od l url bta3ha we n3mlo pass le function l GetKeywords we nrag3 l keywords	
     *f list we b3d keda n3ml check lw l list de mfha4 8er (NaN) n5leh ytl3 l tokens 	
     *l awl n3ml call le function GetTokens wn7dflha l url we b3d keda nenadi 3ala nafs la function 
     *we n7dfhla l title 
     *b3d keda n3ml check 3al keywords aw l tokens de fel dictionary we n7aded l type 
     *kol da gwa l loop 
     *b3d ma l loop t5las n3ml call le function l percentage
     *we ndeha l list bta3t l opject we n7dedlo l col bta3 l type
     *we l return bta3ha hn7oto f 2d list l2wel 
     *we b3d keda n3ml l algorithm ali hn7ded beh l user 2a3d kam sa3a fel yom 
     *we b3d ma n7dedo ndfeo fel 2d list yb2a hwa a5er row 
     *we b3d keda n3ml return lel 2d list de
     *return : 2d list ali rag3a mn function Percentage
     */
     ArrayList<ArrayList<String>> Analysis(int time)
      {
          ArrayList<ArrayList<String>> Percentage_list = new ArrayList<ArrayList<String>>();
          return Percentage_list;
      }
}
