/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensics_app;

/**
 *
 * @author Ayaa
 */
public class BookmarksContent {
    //url
        String Url;
        //name
	String Name;
        //type
	String Type;
        //date Added
	String DateAdded;
       //set URL
    public void SetURL(String Url)
    {
        this.Url=Url;
    }
    //get url
    public String GetURL(){
        
        return this.Url;
    } 
    //set URL
    public void SetName(String Name)
    {
        this.Name=Name;
    }
    //get url
    public String GetName(){
        
        return this.Name;
    }
    //set URL
    public void SetType(String Type)
    {
        this.Type=Type;
    }
    //get url
    public String GetType(){
        
        return this.Type;
    }
    //set URL
    public void SetDateAdded(String DateAdded)
    {
        this.DateAdded=DateAdded;
    }
    //get url
    public String GetDateAdded(){
        
        return this.DateAdded;
    }
}
