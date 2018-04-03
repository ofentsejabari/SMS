/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqldriver;

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

}
