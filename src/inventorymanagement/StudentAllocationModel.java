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
public class StudentAllocationModel {
    SimpleStringProperty assetID ,studentName,assetName,className,resourceName,manufactureSN;

    public StudentAllocationModel() {
        assetID = new SimpleStringProperty("");
        studentName= new SimpleStringProperty("");
        assetName = new SimpleStringProperty("");
        className =new SimpleStringProperty("");
        resourceName = new SimpleStringProperty("");
        manufactureSN= new SimpleStringProperty("");
    }
    public StudentAllocationModel(String assetID ,String studentName,String className,String assetName,String resourceName,String manufactureSN){
        
       this.assetID = new SimpleStringProperty(assetID);
       this.studentName = new SimpleStringProperty(studentName);
       this.assetName = new SimpleStringProperty(assetName);
       this.className = new SimpleStringProperty(className);
       this.resourceName = new SimpleStringProperty(resourceName);
       this.manufactureSN = new SimpleStringProperty(manufactureSN);
    }
    
    public String getAssetID(){return assetID.get();}
    public void setAssetID(String id){assetID.set(id);}
    
    public String getStudentName(){return studentName.get();}
    public void setStudentName(String id){studentName.set(id);}
    
    public String getAssetName(){return assetName.get();}
    public void setAssetName(String id){assetName.set(id);}
    
    public String getResourceName(){return resourceName.get();}
    public void setResourceName(String id) { resourceName.set(id); }
    
    public String getClassName(){return className.get();}
    public void setClassName(String id) { className.set(id); }
    
    public String getManufactureSN(){return manufactureSN.get();}
    public void setManufactureSN(String id){manufactureSN.set(id);}
    
            
            
}

