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
public class DownloadsContent {
     String Url;
     String Time;
     String MimeType;
	

    //set url
    public void SetUrl(String Url)
    {
        this.Url=Url;
    }
    //get url
    public String GetUrl(){
        
        return this.Url;
    }
    //set time
    public void SetTime(String Time)
    {
        this.Time=Time;
    }
    //get time
    public String GetTime(){
        
        return this.Time;
    }
    //set mime type
    public void SetMimeType(String MimeType)
    {
        this.MimeType=MimeType;
    }
    //get mime type
    public String GetMimeType(){
        
        return this.MimeType;
    }
}
