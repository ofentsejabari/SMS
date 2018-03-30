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
public class Supplier {
    SimpleStringProperty ID, supplierName,supplierEmail, supplierPhone,
            supplierCell, supplierPhysical,supplierPostal, supplierFax;

    public Supplier() {
        ID = new SimpleStringProperty("");
        supplierName = new SimpleStringProperty("");
        supplierEmail = new SimpleStringProperty("");
        supplierPhone = new SimpleStringProperty("");
        supplierCell = new SimpleStringProperty("");
        supplierPhysical =new SimpleStringProperty("");
        supplierPostal = new SimpleStringProperty("");
        supplierFax = new SimpleStringProperty("");
    }
    
    public Supplier(String ID, String supplierName, String supplierEmail, String supplierPhone,
            String supplierCell,String supplierPhysical,String supplierPostal,String supplierFax){
        
       this.ID = new SimpleStringProperty(ID);
       this.supplierName = new SimpleStringProperty(supplierName);
       this.supplierEmail = new SimpleStringProperty(supplierEmail);
       this.supplierPhone = new SimpleStringProperty(supplierPhone);
       this.supplierCell = new SimpleStringProperty(supplierCell);
       this.supplierPhysical = new SimpleStringProperty(supplierPhysical);
       this.supplierPostal = new SimpleStringProperty(supplierPostal);
       this.supplierFax = new SimpleStringProperty(supplierFax);
    }
    
    public String getSupplierName(){return supplierName.get();}
    public void setSupplierName(String id){supplierName.set(id);}
    
    public String getID(){return ID.get();}
    public void setID(String id){ID.set(id);}
    
    public String getSupplierEmail(){return supplierEmail.get();}
    public void setSupplierEmail(String id){supplierEmail.set(id);}
    
     public String getSupplierPhone(){return supplierPhone.get();}
    public void setSupplierPhone(String id){supplierPhone.set(id);}
    
    
    public String getSupplierCell(){return supplierCell.get();}
    public void setSupplierCell(String id){supplierCell.set(id);}
    
    
    public String getSupplierPhysical(){return supplierPhysical.get();}
    public void setSupplierPhysical(String id){supplierPhysical.set(id);}
    
    public String getSupplierPostal(){return supplierPostal.get();}
    public void setSupplierPostal(String id){supplierPostal.set(id);}
    
    public String getSupplierFax(){return supplierFax.get();}
    public void setSupplierFax(String id){supplierFax.set(id);}

}

