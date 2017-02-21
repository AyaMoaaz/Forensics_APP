/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ayaa
 */
public class SharedModel {
    /*Description  : bn3ml array list of strings we na5od l url ntl3 l keywords bta3to we b3den n3mla 
    add fel list de 
    *we lw mlo4 keywords bn7ot NaN fe l list we we b3d keda n3mlha return	
    *return : array list of keywords
    */
     ArrayList<String> GetKeywords(String URl)
    {
        ArrayList<String> keywords_list=new ArrayList<String>();
        
        return keywords_list;
    }
   /* Description  : kol ali bt3mlo l function de enha lama nb3tlha l url aw l title ,
    *trg3lna l tokens bta3thom f list mo7trma ,
    *we n3ml 7sab l stop words zy l www we l http ,
    *dol m4 3yzhom yrg3o ka tokens we replace lel symbols be space
    *return : array list of tokens
   */
     ArrayList<String> GetTokens(String Url,String Title)
    {
        ArrayList<String> tokens_list=new ArrayList<String>();
        
        return tokens_list;
    }
     
     /*Description  : bta5od list of strings (ali heya l types) we bt3ml l kol no3 (type) count we 
      *tbd2 t7sb l percentage le kol type we trg3lna 2d list kol type we 2odamo l percentage bta3o 
      *return : 2d list of strings [type name , percentage]
     */
     ArrayList<ArrayList<String>> GetPercentage (ArrayList<String> Types_list)
	{
	 ArrayList<ArrayList<String>> Percentage_list = new ArrayList<ArrayList<String>>();
          return Percentage_list;
		
	}

       /*Description  : bta5od l time we t7wlo le timestamp ali hyd5lo l user
	*y3ni msln l user hy2ol 2 days hngeb l tare5 bta3 enhrda we n7awlo l timestamp
	*we ntr7 mno 2 days we b3d keda n3mlo return
        *return : String timestamp
	*/
	

    String Parsing_time(int time)
    {
        String timeStamp="";
        return timeStamp;
    }
    /*Description  : bta5od l time stamp we t7wlo le date 3adi 
     *return : date
}
    */
    Date ParsingTimestamp (String timestamp)
	{
            Date parsedDate=new Date();
            return parsedDate;
	} 
}

