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
public class HistoryContent {
     //url
    public String Url;
    //title
    public String Title;
    //visited time
    public String VisitTime;
    //type of url
    public String SiteType;
    
    //set URL
    public void SetUrl(String Url)
    {
        this.Url=Url;
    }
    //get url
    public String GetUrl(){
        
        return this.Url;
    }
    //set title
    public void SetTitle(String Title)
    {
        this.Title=Title;
    }
    //getTitle
     public String GetTitle(){
        
        return this.Title;
    }
     //set site type
      public void SiteType(String SiteType)
    {
        this.SiteType=SiteType;
    }
      //get site type
       public String GetSiteType(){
        
        return this.SiteType;
    }
       //set visit date
       public void SetVisitTime(String VisitTime)
    {
        this.VisitTime=VisitTime;
    }
       //get visit date
        public String GetVisitTime(){
        
        return this.VisitTime;
    }
}
