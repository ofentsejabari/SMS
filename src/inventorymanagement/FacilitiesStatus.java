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
    SimpleStringProperty facilitiesStatusID,facilitiesStatusResource,facilitiesStatusAvailable,facilitiesStatusDamaged;

    public FacilitiesStatus() {
        facilitiesStatusID = new SimpleStringProperty("");
        facilitiesStatusResource = new SimpleStringProperty("");
        facilitiesStatusAvailable = new SimpleStringProperty("");
        facilitiesStatusDamaged = new SimpleStringProperty("");
    }
    public FacilitiesStatus(String facilitiesStatusID,String facilitiesStatusResource,String facilitiesStatusAvailable,String facilitiesStatusDamaged){
        
        this.facilitiesStatusID = new SimpleStringProperty(facilitiesStatusID);
        this.facilitiesStatusResource = new SimpleStringProperty(facilitiesStatusResource);
        this.facilitiesStatusAvailable = new SimpleStringProperty(facilitiesStatusAvailable);
        this.facilitiesStatusDamaged = new SimpleStringProperty(facilitiesStatusDamaged);
    }
    
    public String getFacilitiesStatusResource(){return facilitiesStatusResource.get();}
    public void setFacilitiesStatusResource(String id){facilitiesStatusResource.set(id);}
    
    public String getFacilitiesStatusID (){return facilitiesStatusID.get();}
    public void setFacilitiesStatusID(String id){facilitiesStatusID.set(id);}
    
    public String getFacilitiesStatusAvailable(){return facilitiesStatusAvailable.get();}
    public void setFacilitiesStatusAvailable(String id){facilitiesStatusAvailable.set(id);}
            
    public String getFacilitiesStatusDamaged(){return facilitiesStatusDamaged.get();}
    public void setFacilitiesStatusDamaged(String id){facilitiesStatusDamaged.set(id);}
            
}

