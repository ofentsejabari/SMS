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
import java.sql.ResultSet;
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
            String query = " SELECT facilitiesID,facilitiesName,facilitiesTypeID,"
                    + "deptID,facilitiesStatus,facilitiesCapacity,schoolID"
                         + " FROM `facilities`,`school`"
                         + " WHERE `facilities`.`schoolID` = `school`.`id`";
            
            if("ALL".equalsIgnoreCase(filter)){
               query = " SELECT facilitiesID,facilitiesName,facilitiesTypeID,"
                    + "deptID,facilitiesStatus, facilitiesCapacity,schoolID"
                         + " FROM `facilities`,`school`"
                         + " WHERE `facilities`.`schoolID` = `school`.`id`";
            }
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                /* Inventory(String inventoryID,String inventoryName, String inventoryCost,String inventoryLocation,
            String inventoryBatch,String inventoryDate,String inventoryStaff,String inventoryQuantity)
                */
                item.add(new Facilities(result.getString("facilitiesID"),result.getString("facilitiesName")
                        ,result.getString("facilitiesTypeID"),result.getString("deptID"),result.getString("facilitiesStatus"),
                        result.getString("facilitiesCapacity"),result.getString("schoolID")));
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
        
      //  
        public static ObservableList<FacilitiesType>  facilitiesTypeList (String filter){
        ObservableList<FacilitiesType> item = FXCollections.observableArrayList();
        try{
            String query = " SELECT `facilities_type`.`facilitiesTypeID`,`facilitiesTypeName`,"
                    + "`deptID`,`facilitiesStatus`,`facilitiesCapacity`,`schoolID`"
                         + " FROM `facilities_type`,`facilities`,`school`"
                         + " WHERE `facilities`.`schoolID` = `school`.`id`";
            
            if("ALL".equalsIgnoreCase(filter)){
                    query = " SELECT `facilities_type`.facilitiesTypeID,facilitiesTypeName,"
                          + "`deptID`,`facilitiesStatus`, `facilitiesCapacity`,`schoolID`"
                          + " FROM `facilities_type`,`facilities`,`school`"
                            + " WHERE `facilities`.`schoolID` = `school`.`id`";
            }
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
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
        
        
    public static ObservableList<FacilitiesStatus>  facilitiesStatusList (String filter){
        ObservableList<FacilitiesStatus> item = FXCollections.observableArrayList();
        try{
            String query = " SELECT facilitiesStatusID,facilitiesStatusResource,facilitiesStatusAvailable"
                         + ",facilitiesStatusDamaged"
                         + " FROM `facilities_status`"
                         + " WHERE 1";
            
            if("ALL".equalsIgnoreCase(filter)){
                    query = " SELECT facilitiesStatusID,facilitiesStatusResource,facilitiesStatusAvailable"
                         + ",facilitiesStatusDamaged"
                         + " FROM `facilities_status`"
                         + " WHERE 1";
            }
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                item.add(new FacilitiesStatus(result.getString("facilitiesStatusID"),
                        result.getString("facilitiesStatusResource"),result.getString("facilitiesStatusAvailable"),result.getString("facilitiesStatusDamaged")));
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
                
}
