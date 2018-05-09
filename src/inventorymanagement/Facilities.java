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
public class Facilities {
    SimpleStringProperty facilitiesID,facilitiesName,facilitiesType,facilitiesDept,facilitiesStatus, facilitiesCapacity,facilitiesSchool;

    public Facilities() {
        facilitiesID = new SimpleStringProperty("");
        facilitiesName = new SimpleStringProperty("");
        facilitiesType = new SimpleStringProperty("");
        facilitiesDept= new SimpleStringProperty("");
        facilitiesStatus= new SimpleStringProperty("");
        facilitiesCapacity= new SimpleStringProperty("");
        facilitiesSchool=new SimpleStringProperty("");
    }
    public Facilities(String facilitiesID,String facilitiesName,String facilitiesType
            ,String facilitiesDept,String facilitiesStatus,String facilitiesCapacity,String facilitiesSchool){
        
       this.facilitiesID = new SimpleStringProperty(facilitiesID);
       this.facilitiesName = new SimpleStringProperty(facilitiesName);
       this.facilitiesType = new SimpleStringProperty(facilitiesType);
       this.facilitiesSchool = new SimpleStringProperty(facilitiesSchool);
       this.facilitiesDept = new SimpleStringProperty(facilitiesDept);
       this.facilitiesStatus= new SimpleStringProperty(facilitiesStatus);
       this.facilitiesCapacity= new SimpleStringProperty(facilitiesCapacity);
    }
    
    public String getFacilitiesName(){return facilitiesName.get();}
    public void setFacilitiesName(String id){facilitiesName.set(id);}
    
    public String getFacilitiesID (){return facilitiesID.get();}
    public void setFacilitiesID(String id){facilitiesID.set(id);}
    
    public String getFacilitiesType(){return facilitiesType.get();}
    public void setFacilitiesType(String id){facilitiesType.set(id);}
    
    public String getFacilitiesDept(){return facilitiesDept.get();}
    public void setFacilitiesDept(String id)
    {
            facilitiesDept.set(id);
    }
    
    public String getFacilitiesStatus(){return facilitiesStatus.get();}
    public void setFacilitiesStatus(String id){facilitiesStatus.set(id);}
    
    
    public String getFacilitiesCapacity(){return facilitiesCapacity.get();}
    public void setFacilitiesCapacity(String id){facilitiesCapacity.set(id);}
    
    public String getFacilitiesSchool(){return facilitiesSchool.get();}
    public void setFacilitiesSchool(String id){facilitiesSchool.set(id);}
            
            
}

