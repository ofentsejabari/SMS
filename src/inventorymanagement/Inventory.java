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
public class Inventory {
    SimpleStringProperty inventoryID, inventoryName,inventoryCost, inventoryLocation,
            inventoryBatch,inventoryDate, inventoryStaffID,inventoryQuantity;

    public Inventory() {
        inventoryID = new SimpleStringProperty("");
        inventoryName = new SimpleStringProperty("");
        inventoryCost = new SimpleStringProperty("");
        inventoryLocation = new SimpleStringProperty("");
        inventoryBatch = new SimpleStringProperty("");
        inventoryDate =new SimpleStringProperty("");
        inventoryStaffID = new SimpleStringProperty("");
        inventoryQuantity = new SimpleStringProperty("");
    }
    
    public Inventory(String inventoryID,String inventoryName, String inventoryCost,String inventoryLocation,
            String inventoryBatch,String inventoryDate,String inventoryStaffID,String inventoryQuantity){
        
       this.inventoryID = new SimpleStringProperty(inventoryID);
       this.inventoryName = new SimpleStringProperty(inventoryName);
       this.inventoryCost = new SimpleStringProperty(inventoryCost);
       this.inventoryLocation = new SimpleStringProperty(inventoryLocation);
       this.inventoryBatch = new SimpleStringProperty(inventoryBatch);
       this.inventoryDate = new SimpleStringProperty(inventoryDate);
       this.inventoryStaffID = new SimpleStringProperty(inventoryStaffID);
       this.inventoryQuantity= new SimpleStringProperty(inventoryQuantity);
    }
    
    public String getInventoryName(){return inventoryName.get();}
    public void setInventoryName(String id){inventoryName.set(id);}
    
    public String getInventoryID(){return inventoryID.get();}
    public void setInventoryID(String id){inventoryID.set(id);}
    
    public String getInventoryCost(){return inventoryCost.get();}
    public void setInventoryCost(String id){inventoryCost.set(id);}
    
     public String getInventoryLocation(){return inventoryLocation.get();}
    public void setInventoryLocation(String id){inventoryLocation.set(id);}
    
    
    public String getInventoryBatch(){return inventoryBatch.get();}
    public void setInventoryBatch(String id){inventoryBatch.set(id);}
    
    
    public String getInventoryDate(){return inventoryDate.get();}
    public void setInventoryDate(String id){inventoryDate.set(id);}
    
    public String getInventoryStaffID(){return inventoryStaffID.get();}
    public void setInventoryStaffID(String id){inventoryStaffID.set(id);}
    
    public String getInventoryQuantity(){return inventoryQuantity.get();}
    public void setInventoryQuantity(String id){inventoryQuantity.set(id);}

}

