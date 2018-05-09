/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqldriver;

import inventorymanagement.Facilities;
import inventorymanagement.FacilitiesStatus;
import inventorymanagement.FacilitiesType;
import inventorymanagement.Inventory;
import inventorymanagement.PolicyDocument;
import inventorymanagement.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static mysqldriver.MySQLHander.STATEMENT;

/**
 *
 * @author MOILE
 */
public class InventoryQuery {

    
    
    
    /**************************************************************
    *       inventory management 
    *************************************************************/
    
      public static ObservableList<Inventory> inventoryList(String filter){
        ObservableList<Inventory> item = FXCollections.observableArrayList();
        try{
            String query = " SELECT `inventoryName`,`inventoryID`,`inventoryCost`,`inventoryLocation`,`inventoryBatch`"
                         + ",`inventoryDate`,`inventoryQuantity`,`inventorySupplierID`,`inventoryStaffID`"
                         + " FROM `inventory`,`school`"
                         + " WHERE `inventory`.`schoolID` = `school`.`id`";
            
            if("ALL".equalsIgnoreCase(filter)){
               query = " SELECT `inventoryName`,`inventoryID`,`inventoryCost`,`inventoryLocation`,`inventoryBatch`"
                         + ",`inventoryDate`,`inventoryQuantity`,`inventorySupplierID`,`inventoryStaffID`"
                         + " FROM `inventory`,`school`";
            }
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                /* Inventory(String inventoryID,String inventoryName, String inventoryCost,String inventoryLocation,
            String inventoryBatch,String inventoryDate,String inventoryStaff,String inventoryQuantity)
                */
                item.add(new Inventory(result.getString("inventoryID"),result.getString("inventoryName")
                        ,result.getString("inventoryCost"),result.getString("inventoryLocation"),result.getString("inventoryBatch"),
                        result.getString("inventoryDate"),result.getString("inventoryStaffID"),result.getString("inventoryQuantity")));
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
      
        public static ObservableList<Facilities>  facilitiesList (String filter){
        ObservableList<Facilities> item = FXCollections.observableArrayList();
        try{
            String query = " SELECT facilitiesID,facilitiesName,facilitiesTypeName,"
                    + "deptID,facilitiesStatus,facilitiesCapacity,schoolID"
                         + " FROM `facilities`,`school`,`facilities_type`"
                         + " WHERE `facilities`.`schoolID` = `school`.`id` "
                         + "AND `facilities`.`facilitiesTypeID`=`facilities_type`.`facilitiesTypeID` ";
            
            if("ALL".equalsIgnoreCase(filter)){
               query = " SELECT facilitiesID,facilitiesName,facilitiesTypeName,"
                    + "deptID,facilitiesStatus,facilitiesCapacity,schoolID"
                         + " FROM `facilities`,`school`,`facilities_type`"
                         + " WHERE `facilities`.`schoolID` = `school`.`id` "
                         + "AND `facilities`.`facilitiesTypeID`=`facilities_type`.`facilitiesTypeID` ";
            }
            
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                String dept="";
                if(result.getString("deptID").equals("0"))
                {
                    dept="General Purpose";
                }   
                else{
                    dept=AdminQuery.getDepartmentByID(result.getString("deptID")).getDepartmentName();
                }
                item.add(new Facilities(result.getString("facilitiesID"),result.getString("facilitiesName")
                        ,result.getString("facilitiesTypeName"),dept,result.getString("facilitiesStatus"),
                        result.getString("facilitiesCapacity"),result.getString("schoolID")));
            }
            
            return item;
           } 
            catch(Exception ex){
                 System.out.println(ex.getMessage());
                 return item;
            }
        }
         
        public static ObservableList<FacilitiesType>  facilitiesTypeList (String filter){
        ObservableList<FacilitiesType> item = FXCollections.observableArrayList();
        try{
            String query = " SELECT `facilities_type`.`facilitiesTypeID`,`facilitiesTypeName`"
                         + " FROM `facilities_type`,`facilities`,`school`"
                         + " WHERE `facilities`.`schoolID` = `school`.`id`"
                    + "AND `facilities_type`.`facilitiesTypeID`=`facilities`.`facilitiesTypeID`";
            
            if("ALL".equalsIgnoreCase(filter)){
                    query = " SELECT `facilities_type`.facilitiesTypeID,facilitiesTypeName"
                          + " FROM `facilities_type`,`facilities`,`school`"
                            + " WHERE `facilities`.`schoolID` = `school`.`id`"
                            + "AND `facilities_type`.`facilitiesTypeID`=`facilities`.`facilitiesTypeID`";
            }
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
               // int facilitiesTypeNo = InventoryQuery.getFacilitiesTypeNo("");
                item.add(new FacilitiesType(result.getString("facilitiesTypeID"),
                        result.getString("facilitiesTypeName"),""));
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
        
    public int getFacilitiesTypeNo(String faclityType)
    {
        String query="SELECT COUNT(*) FROM facilities WHERE `facilitiesTypeID`='"+faclityType+"";
        try {
            ResultSet res = STATEMENT.executeQuery(query);
            if(res.next())
            {
                return res.getInt(0);
            }
        }
        catch(SQLException e){
            System.out.println("failed ...!!");
            return 0;
        }
        return 0;
    }            
                
     public static ObservableList<String>  getFacilitiesTypeList (String filter){
        ObservableList<String> item = FXCollections.observableArrayList();
        try{
            String query = " SELECT `facilities_type`.`facilitiesTypeID`,`facilitiesTypeName`,"
                    + "`deptID`,`facilitiesStatus`,`facilitiesCapacity`,`schoolID`"
                         + " FROM `facilities_type`,`facilities`,`school`"
                         + " WHERE `facilities`.`schoolID` = `school`.`id`"
                    + "AND `facilities_type`.`facilitiesTypeID`=`facilities`.`facilitiesTypeID`";
            
            if("ALL".equalsIgnoreCase(filter)){
                    query = " SELECT `facilities_type`.facilitiesTypeID,facilitiesTypeName,"
                          + "`deptID`,`facilitiesStatus`, `facilitiesCapacity`,`schoolID`"
                          + " FROM `facilities_type`,`facilities`,`school`"
                            + " WHERE `facilities`.`schoolID` = `school`.`id` "
                            + "AND `facilities_type`.`facilitiesTypeID`=`facilities`.`facilitiesTypeID`";
            }
            ResultSet result = STATEMENT.executeQuery(query);
            System.out.println(query);
            while(result.next()){
                
                item.add((result.getString("facilitiesTypeName")));
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }   
        
    public static ObservableList<FacilitiesStatus>  facilitiesStatusList (String filter){
        ObservableList<FacilitiesStatus> item = FXCollections.observableArrayList();
        try{
            String query = "SELECT facilitiesStatusID,facilitiesID,facilitiesResourceID,facilitiesStatusAvailable"
                         + ",facilitiesStatusDamage"
                         + " FROM `facilities_status`"
                         + " WHERE 1";
            
            if("ALL".equalsIgnoreCase(filter)){
                    query = "SELECT facilitiesStatusID,facilitiesID,facilitiesResourceID,facilitiesStatusAvailable"
                         + ",facilitiesStatusDamage"
                         + " FROM `facilities_status`"
                         + " WHERE 1";
            }
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                item.add(new FacilitiesStatus(result.getString("facilitiesStatusID"),result.getString("facilitiesID"),
                        result.getString("facilitiesResourceID"),result.getString("facilitiesStatusAvailable")
                        ,result.getString("facilitiesStatusDamage")));
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
    
    
       public static ObservableList<FacilitiesStatus> getFacilitiesStatus(String id){
        ObservableList<FacilitiesStatus> leave = FXCollections.observableArrayList();
        try{
            String query = "SELECT facilitiesStatusID,facilitiesID,facilitiesResourceID,facilitiesStatusAvailable"
                         + ",facilitiesStatusDamage"
                         + " FROM `facilities_status`"
                         + " WHERE facilitiesID='"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                leave.add(new FacilitiesStatus(result.getString("facilitiesStatusID"),result.getString("facilitiesID"),
                        result.getString("facilitiesResourceID"),result.getString("facilitiesStatusAvailable")
                        ,result.getString("facilitiesStatusDamage")));
            }
            return leave;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return leave;
        }
    }
    
    
    
    
    public static ObservableList<String> getFacilitiesName(String id){
        ObservableList<String> leave = FXCollections.observableArrayList();
        try{
            String query = "SELECT `facilitiesName`"
                         + " FROM `facilities`"
                         + " WHERE `facilitiesID`='"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                leave.add(result.getString("facilitiesName"));
            }
            return leave;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return leave;
        }
    }
    
     public static ObservableList<String> getResourceName(String id){
        ObservableList<String> leave = FXCollections.observableArrayList();
        try{
            String query = "SELECT `facilitiesResourceName`"
                         + " FROM `facilities_resource`"
                         + " WHERE `facilitiesResourceID`='"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                leave.add(result.getString("facilitiesResourceName"));
            }
            return leave;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return leave;
        }
    }
     
      public static ObservableList<String> getSupplierNames(){
        ObservableList<String> leave = FXCollections.observableArrayList();
        try{
            String query = " SELECT `supplierID`,`supplierName`,`supplierEmail`,`supplierPhone`,`supplierCell`"
                         + ",`supplierPhysical`,`supplierPostal`,`supplierFax`"
                         + " FROM `suppliers`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                leave.add(result.getString("supplierName"));
            }
            return leave;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return leave;
        }
    }
      
       public static Supplier getSupplierByName(String name){
        try{
            String query = " SELECT `supplierID`,`supplierName`,`supplierEmail`,`supplierPhone`,`supplierCell`"
                         + ",`supplierPhysical`,`supplierPostal`,`supplierFax`"
                         + " FROM `suppliers`"
                         + "WHERE `supplierName`='"+name+"'";
           System.out.println(query);
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Supplier(result.getString("supplierID"),result.getString("supplierName")
                        ,result.getString("supplierEmail"),result.getString("supplierPhone"),result.getString("supplierCell"),
                        result.getString("supplierPhysical"),result.getString("supplierPostal"),result.getString("supplierFax"));
            }
            return null;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return null;
        }
    }
     
   public static ObservableList<Supplier> supplierList(){
        ObservableList<Supplier> item = FXCollections.observableArrayList();
        try{
            String query = " SELECT `supplierID`,`supplierName`,`supplierEmail`,`supplierPhone`,`supplierCell`"
                         + ",`supplierPhysical`,`supplierPostal`,`supplierFax`"
                         + " FROM `suppliers`"
                         + " WHERE `supplier`.`schoolID` = `school`.`id`";
            
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                item.add(new Supplier(result.getString("supplierID"),result.getString("supplierName")
                        ,result.getString("supplierEmail"),result.getString("supplierPhone"),result.getString("supplierCell"),
                        result.getString("supplierPhysical"),result.getString("supplierPostal"),result.getString("supplierFax")));
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
   
   public static ObservableList<PolicyDocument> policiesList(){
        ObservableList<PolicyDocument> item = FXCollections.observableArrayList();
        try{
            String query = " SELECT `policiesID`,`policiesName`,`policyDocDate`,`policyDocStaff`"
                         + " FROM `policy_documents`,`school`"
                         + " WHERE `policies`.`schoolID` = `school`.`id`";
            
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                item.add(new PolicyDocument(result.getString("policiesID"),result.getString("policiesName")
                        ,result.getString("policyDocDate"),result.getString("policyDocStaff")));
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
        
                
}
