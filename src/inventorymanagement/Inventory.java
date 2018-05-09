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
    SimpleStringProperty inventoryID, inventoryName,inventoryDescription, inventoryManSn, inventoryGovSn, inventoryYears, inventoryCost, inventoryPurchaseOrder, inventoryLocation, inventoryBatch, inventoryDate, inventoryDept, inventoryStaffID, inventoryQuantity,inventorySupplierID,inventoryCaptureDate, invetoryCaptuteStaff, schoolID;

    public Inventory() {
        inventoryID = new SimpleStringProperty("");
        inventoryName = new SimpleStringProperty("");
        inventoryDescription= new SimpleStringProperty("");
        inventoryManSn= new SimpleStringProperty("");
        inventoryGovSn= new SimpleStringProperty("");
        inventoryYears= new SimpleStringProperty("");
        inventoryCost= new SimpleStringProperty("");
        inventoryPurchaseOrder= new SimpleStringProperty("");
        inventoryLocation= new SimpleStringProperty("");
        inventoryBatch = new SimpleStringProperty("");
        inventoryDate= new SimpleStringProperty("");
        inventoryDept= new SimpleStringProperty("");
        inventoryStaffID= new SimpleStringProperty("");
        inventoryQuantity= new SimpleStringProperty("");
        inventorySupplierID= new SimpleStringProperty("");
        inventoryCaptureDate = new SimpleStringProperty("");
        invetoryCaptuteStaff= new SimpleStringProperty("");
        schoolID= new SimpleStringProperty("");
        
    }
    
    public Inventory(String inventoryID,String inventoryName,String inventoryDescription,String inventoryManSn,String inventoryGovSn,
            String inventoryYears,String inventoryCost,String inventoryPurchaseOrder,String inventoryLocation,String inventoryBatch,String inventoryDate,String inventoryDept,String inventoryStaffID,
            String inventoryQuantity,String inventorySupplierID, String inventoryCaptureDate,String invetoryCaptuteStaff,String schoolID){
        
       this.inventoryID = new SimpleStringProperty(inventoryID);
       this.inventoryName = new SimpleStringProperty(inventoryName);
       this.inventoryDescription= new SimpleStringProperty(inventoryDescription);
       this.inventoryManSn= new SimpleStringProperty(inventoryManSn);
       this.inventoryGovSn= new SimpleStringProperty(inventoryGovSn);
       this.inventoryYears= new SimpleStringProperty(inventoryYears);
       this.inventoryCost= new SimpleStringProperty(inventoryCost);
       this.inventoryPurchaseOrder= new SimpleStringProperty(inventoryPurchaseOrder);
       this.inventoryLocation= new SimpleStringProperty(inventoryLocation);
       this.inventoryBatch= new SimpleStringProperty(inventoryBatch);
       this.inventoryDate= new SimpleStringProperty(inventoryDate);
       this.inventoryDept= new SimpleStringProperty(inventoryDept);
       this.inventoryStaffID= new SimpleStringProperty(inventoryStaffID);
       this.inventoryQuantity= new SimpleStringProperty(inventoryQuantity);
       this.inventorySupplierID= new SimpleStringProperty(inventorySupplierID);
       this.inventoryCaptureDate= new SimpleStringProperty(inventoryCaptureDate);
       this.invetoryCaptuteStaff= new SimpleStringProperty(invetoryCaptuteStaff);
       this.schoolID= new SimpleStringProperty(schoolID);
    }
    
    public String getInventoryID(){return inventoryID.get();}
    public void setInventoryID(String id){inventoryID.set(id);}
    
    public String getInventoryName(){return inventoryName.get();}
    public void setInventoryName(String id){inventoryName.set(id);}
    
    public String getInventoryDescription(){return inventoryDescription.get();}
    public void setInventoryDescription(String id){inventoryDescription.set(id);}
    
    public String getInventoryManSn(){return inventoryManSn.get();}
    public void setInventoryManSn(String id){inventoryManSn.set(id);}
    
    public String getInventoryGovSn(){return inventoryGovSn.get();}
    public void setInventoryGovSn(String id){inventoryGovSn.set(id);}
    
    public String getInventoryYears(){return inventoryYears.get();}
    public void setInventoryYears(String id){inventoryYears.set(id);}
    
    public String getInventoryCost(){return inventoryCost.get();}
    public void setInventoryCost(String id){inventoryCost.set(id);}
    
    public String getInventoryPurchaseOrder(){return inventoryPurchaseOrder.get();}
    public void setInventoryPurchaseOrder(String id){inventoryPurchaseOrder.set(id);}
    
    public String getInventoryLocation(){return inventoryLocation.get();}
    public void setInventoryLocation(String id){inventoryLocation.set(id);}
    
    public String getInventoryBatch(){return inventoryBatch.get();}
    public void setInventoryBatch(String id){inventoryBatch.set(id);}
    
    public String getInventoryDate(){return inventoryDate.get();}
    public void setInventoryDate(String id){inventoryDate.set(id);}
    
    public String getInventoryDept(){return inventoryDept.get();}
    public void setInventoryDept(String id){inventoryDept.set(id);}
    
    public String getInventoryStaffID(){return inventoryStaffID.get();}
    public void setInventoryStaffID(String id){inventoryStaffID.set(id);}
    
    public String getInventoryQuantity(){return inventoryQuantity.get();}
    public void setInventoryQuantity(String id){inventoryQuantity.set(id);}

    public String getInventorySupplierID(){return inventorySupplierID.get();}
    public void setInventorySupplierID(String id){inventorySupplierID.set(id);}
    
     public String getInventoryCaptureDate(){return inventoryCaptureDate.get();}
    public void setInventoryCaptureDate(String id){inventoryCaptureDate.set(id);}
    
     public String getInvetoryCaptuteStaff(){return invetoryCaptuteStaff.get();}
    public void setInvetoryCaptuteStaff(String id){invetoryCaptuteStaff.set(id);}
    
    public String getSchoolID(){return schoolID.get();}
    public void setSchoolID(String id){schoolID.set(id);}
    
}

