/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;


import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author MOILE
 */
public class FacilitiesStatus {
    SimpleStringProperty facilitiesStatusID,facilitiesID,facilitiesResourceID,facilitiesStatusAvailable
            ,facilitiesStatusDamage;

    public FacilitiesStatus() {
        facilitiesStatusID = new SimpleStringProperty("");
        facilitiesID = new SimpleStringProperty("");
        facilitiesResourceID = new SimpleStringProperty("");
        facilitiesStatusAvailable = new SimpleStringProperty("");
        facilitiesStatusDamage = new SimpleStringProperty("");
    }
    public FacilitiesStatus(String facilitiesStatusID,String facilitiesID,String facilitiesResourceID
            ,String facilitiesStatusAvailable,String facilitiesStatusDamage)
    {
        
        this.facilitiesStatusID = new SimpleStringProperty(facilitiesStatusID);
        this.facilitiesResourceID = new SimpleStringProperty(facilitiesResourceID);
        this.facilitiesID = new SimpleStringProperty(facilitiesID);
        this.facilitiesStatusAvailable = new SimpleStringProperty(facilitiesStatusAvailable);
        this.facilitiesStatusDamage = new SimpleStringProperty(facilitiesStatusDamage);
    }
    
    public String getFacilitiesResourceID(){return facilitiesResourceID.get();}
    public void setFacilitiesResourceID(String id){facilitiesResourceID.set(id);}
    
    public String getFacilitiesStatusID (){return facilitiesStatusID.get();}
    public void setFacilitiesStatusID(String id){facilitiesStatusID.set(id);}
    
    public String getFacilitiesID (){return facilitiesID.get();}
    public void setFacilitiesID(String id){facilitiesID.set(id);}
    
    public String getFacilitiesStatusAvailable(){return facilitiesStatusAvailable.get();}
    public void setFacilitiesStatusAvailable(String id){facilitiesStatusAvailable.set(id);}
            
    public String getFacilitiesStatusDamage(){return facilitiesStatusDamage.get();}
    public void setFacilitiesStatusDamage(String id){facilitiesStatusDamage.set(id);}
            
}