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
public class PolicyDocument {
    SimpleStringProperty policyDocID, policyDocName,policyDocDate,policyDocStaff;

    public PolicyDocument() {
        policyDocID = new SimpleStringProperty("");
        policyDocName = new SimpleStringProperty("");
        policyDocDate = new SimpleStringProperty("");
        policyDocStaff = new SimpleStringProperty("");
    }
    
    public PolicyDocument(String policyDocID,String policyDocName,String policyDocDate,String policyDocStaff){
        
       this.policyDocID = new SimpleStringProperty(policyDocID);
       this.policyDocName = new SimpleStringProperty(policyDocName);
       this.policyDocDate = new SimpleStringProperty(policyDocDate);
       this.policyDocStaff = new SimpleStringProperty(policyDocStaff);
    }
    
    public String getPolicyDocName(){return policyDocName.get();}
    public void setPolicyDocName(String id){policyDocName.set(id);}
    
    public String getPolicyDocID(){return policyDocID.get();}
    public void setPolicyDocID(String id){policyDocID.set(id);}
    
    public String getPolicyDocDate(){return policyDocDate.get();}
    public void setPolicyDocDate(String id){policyDocDate.set(id);}
    
     public String getPolicyDocStaff(){return policyDocStaff.get();}
    public void setPolicyDocStaff(String id){policyDocStaff.set(id);}
    
    
    

    public String toString(){
        return policyDocName.get();
    
    }
}

