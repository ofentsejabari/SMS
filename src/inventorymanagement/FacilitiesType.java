/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;


import javafx.beans.property.SimpleStringProperty;
import mysqldriver.AdminQuery;
/**
 *
 * @author MOILE
 */
public class FacilitiesType {
    SimpleStringProperty facilitiesTypeID,facilitiesTypeName,facilitiesTypeQuantity,facilitiesDept,facilitiesStatus, facilitiesCapacity,facilitiesSchool;

    public FacilitiesType() {
        facilitiesTypeID = new SimpleStringProperty("");
        facilitiesTypeName = new SimpleStringProperty("");
        facilitiesTypeQuantity = new SimpleStringProperty("");
    }
    public FacilitiesType(String facilitiesTypeID,String facilitiesTypeName,String facilitiesTypeQuantity){
        
       this.facilitiesTypeID = new SimpleStringProperty(facilitiesTypeID);
       this.facilitiesTypeName = new SimpleStringProperty(facilitiesTypeName);
       this.facilitiesTypeQuantity = new SimpleStringProperty(facilitiesTypeQuantity);
    }
    
    public String getFacilitiesTypeName(){return facilitiesTypeName.get();}
    public void setFacilitiesTypeName(String id){facilitiesTypeName.set(id);}
    
    public String getFacilitiesTypeID (){return facilitiesTypeID.get();}
    public void setFacilitiesTypeID(String id){facilitiesTypeID.set(id);}
    
    public String getFacilitiesTypeQuantity(){return facilitiesTypeQuantity.get();}
    public void setFacilitiesTypeQuantity(String id){facilitiesTypeQuantity.set(id);}
            
            
}

