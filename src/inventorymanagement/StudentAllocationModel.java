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
    SimpleStringProperty assetID ,assetName,manufactureSN;

    public StudentAllocationModel() {
        assetID = new SimpleStringProperty("");
        assetName = new SimpleStringProperty("");
        manufactureSN= new SimpleStringProperty("");
    }
    public StudentAllocationModel(String assetID ,String assetName,String manufactureSN){
       this.assetID = new SimpleStringProperty(assetID);
       this.assetName = new SimpleStringProperty(assetName);
       this.manufactureSN = new SimpleStringProperty(manufactureSN);
    }
    
    public String getAssetID(){return assetID.get();}
    public void setAssetID(String id){assetID.set(id);}
    
    public String getAssetName(){return assetName.get();}
    public void setAssetName(String id){assetName.set(id);}
    
    public String getManufactureSN(){return manufactureSN.get();}
    public void setManufactureSN(String id){manufactureSN.set(id);}
    
}

