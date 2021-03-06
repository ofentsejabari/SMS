/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqldriver;

import inventorymanagement.BookCopies;
import inventorymanagement.BookModel;
import inventorymanagement.Facilities;
import inventorymanagement.FacilitiesStatus;
import inventorymanagement.FacilitiesType;
import inventorymanagement.Inventory;
import inventorymanagement.PolicyDocument;
import inventorymanagement.StudentAllocationModel;
import inventorymanagement.StudentResourceList;
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
            String query = "SELECT `inventoryID`, `inventoryName`, `inventoryDescription`, `inventoryManSn`,"
                        + " `inventoryGovSn`, `inventoryYears`, `inventoryCost`, `inventoryPurchaseOrder`,"
                        + " `inventoryLocation`, `inventoryBatch`, `inventoryDate`, `inventoryDept`, `inventoryStaffID`,"
                        + " `inventoryQuantity`, `inventorySupplierID`, `inventoryCaptureDate`, `invetoryCaptuteStaff`, "
                        + "`schoolID` "
                        + " FROM `inventory`,`school`"
                        + " WHERE `inventory`.`schoolID` = `school`.`id`";
            
            if("ALL".equalsIgnoreCase(filter)){
              query = "SELECT `inventoryID`, `inventoryName`, `inventoryDescription`, `inventoryManSn`,"
                        + " `inventoryGovSn`, `inventoryYears`, `inventoryCost`, `inventoryPurchaseOrder`,"
                        + " `inventoryLocation`, `inventoryBatch`, `inventoryDate`, `inventoryDept`, `inventoryStaffID`,"
                        + " `inventoryQuantity`, `inventorySupplierID`, `inventoryCaptureDate`, `invetoryCaptuteStaff`, "
                        + "`schoolID` "
                        + " FROM `inventory`,`school`"
                        + " WHERE `inventory`.`schoolID` = `school`.`id`";
            }
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            int count =0;
            while(result.next()){
                
            
                item.add(new Inventory(""+count,result.getString("inventoryName")
                        ,result.getString("inventoryDescription"),result.getString("inventoryManSn"),result.getString("inventoryGovSn"),
                        result.getString("inventoryYears"),result.getString("inventoryCost"),result.getString("inventoryPurchaseOrder"),result.getString("inventoryLocation"),
                        result.getString("inventoryBatch"),result.getString("inventoryDate"),result.getString("inventoryDept"),result.getString("inventoryStaffID"),result.getString("inventoryQuantity"),
                result.getString("inventorySupplierID"),result.getString("inventoryCaptureDate"),result.getString("invetoryCaptuteStaff"),result.getString("schoolID")));
            
                count++;
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
        public static ObservableList<BookCopies> getBookCopiesByBookId(String book_id){
        
            ObservableList<BookCopies> item = FXCollections.observableArrayList();
            String query="SELECT `id`, `serial_number`,`book_location`, `edition`,`book_id`"
                    + "FROM `book_copies`"
                    + "WHERE `book_id`='"+book_id+"'";
            try{
                ResultSet rs =STATEMENT.executeQuery(query);
                
                while(rs.next()){
                       item.add(new BookCopies(rs.getString("id"),rs.getString("serial_number"), rs.getString("book_location")
                               , rs.getString("edition"),"",""));
                }
                return item;
            }
            catch(Exception e){
                return item;
            }
        
        }
        public static ObservableList<Facilities>  facilitiesList (String filter){
        ObservableList<Facilities> item = FXCollections.observableArrayList();
        try{
            String query = "SELECT facilitiesID,facilitiesName,facilitiesTypeName,deptID,facilitiesStatus,facilitiesCapacity,"
                       + "schoolID FROM `facilities`,`school`,`facilities_type` "
                       + "WHERE `facilities`.`schoolID` = `school`.`id` "
                       + "AND facilities_type.facilitiesTypeID=facilities.facilitiesTypeID"
                    + " AND deptID='0'";
            ResultSet result = STATEMENT.executeQuery(query);
            while(result.next()){
                item.add(new Facilities(result.getString("facilitiesID"),result.getString("facilitiesName")
                        ,result.getString("facilitiesTypeName"),"GENERAL PURPOSE",result.getString("facilitiesStatus"),
                        result.getString("facilitiesCapacity"),result.getString("schoolID")));
            }
            query = "SELECT facilitiesID,facilitiesName,facilitiesTypeName,departmentName,facilitiesStatus,facilitiesCapacity,schoolID "
                    + "FROM `facilities`,`school`,`facilities_type`,`department` "
                    + "WHERE `facilities`.`schoolID` = `school`.`id` AND facilities_type.facilitiesTypeID=facilities.facilitiesTypeID "
                    + "AND department.id=deptID";
            result = STATEMENT.executeQuery(query);
            while(result.next()){
                item.add(new Facilities(result.getString("facilitiesID"),result.getString("facilitiesName")
                        ,result.getString("facilitiesTypeName"),result.getString("departmentName"),result.getString("facilitiesStatus"),
                        result.getString("facilitiesCapacity"),result.getString("schoolID")));
            }
            
            return item;
           } 
            catch(Exception ex){
                 System.out.println(ex.getMessage());
                 return item;
            }
        }
        /*
            Inventory books list view 
        
        */
        public static ObservableList<String> getBookNames()
        {
            ObservableList<String> items = FXCollections.observableArrayList();
            
            String query ="SELECT `name` FROM book WHERE 1";
              try{
                  ResultSet rs=STATEMENT.executeQuery(query);
                   while(rs.next())
                   {
                       items.add(rs.getString("name"));
                   }
                  
                  return items;
              }
              catch(Exception e){
                  System.out.println(e);
                  return items;
              }
        }
        public static BookModel getBooksByName(String name){
            
                BookModel items = new BookModel();
                String query = "SELECT `id`, `name`, `author`, `publisher`, `isbn`, `date`, `subjectID` "
                        + "FROM `book` WHERE `name`='"+name+"'";
                try{
                    ResultSet rs = STATEMENT.executeQuery(query);
                 
                    if(rs.next()){
                        return new BookModel(rs.getString("id"),rs.getString("name"),rs.getString("author")
                                ,rs.getString("publisher"),rs.getString("isbn"),rs.getString("subjectID"));
                    }
                 
                    return items;
                 }
                catch(Exception e){
                    System.out.println(e);
                    return items;
                }
        }
        public static BookModel getBooksById(String id){
            
                BookModel items = new BookModel();
                String query = "SELECT `id`, `name`, `author`, `publisher`, `isbn`, `date`, `subjectID` "
                        + "FROM `book` WHERE `id`="+id+"";
                try{
                    ResultSet rs = STATEMENT.executeQuery(query);
                 
                    if(rs.next()){
                        return new BookModel(rs.getString("id"),rs.getString("name"),rs.getString("author")
                                ,rs.getString("publisher"),rs.getString("isbn"),rs.getString("subjectID"));
                    }
                 
                    return items;
                 }
                catch(Exception e){
                    System.out.println(e);
                    return items;
                }
        }
         public static ObservableList<String> getBookNamesBySubject(String subject){
            
                ObservableList<String> items = FXCollections.observableArrayList();
                String query = "SELECT `name`"
                             + "FROM `book` "
                             + "WHERE `subjectID`='"+subject+"'";
                try{
                    ResultSet rs = STATEMENT.executeQuery(query);
                 
                    while(rs.next()){
                        items.add(rs.getString("name"));
                    }
                 
                    return items;
                 }
                catch(Exception e){
                    System.out.println(e);
                    return items;
                }
        }
        public static String updateBook(BookModel book)
        {
            ObservableList<String> items = FXCollections.observableArrayList();
            
            String query ="INSERT INTO `book`(`id`, `name`, `author`, `publisher`, `subjectID`, `isbn`, `date`) "
                    + "VALUES (0,'"+book+"',[value-3],"
                    + "[value-4],[value-5],[value-6],[value-7])";
              try{
                  
        
                    STATEMENT.addBatch(query);
                    STATEMENT.executeBatch();

                    return "";
                } 
                catch(Exception ex){
                     System.out.println(ex.getMessage());
                     return ex.getMessage();
                }
        }
        
        public static ObservableList<FacilitiesType>  facilitiesTypeList (String filter){
        ObservableList<FacilitiesType> item = FXCollections.observableArrayList();
        try{
            String query = " SELECT `facilitiesTypeName`,COUNT(*)"
                         + " FROM `facilities_type`,`facilities`"
                         + " WHERE `facilities_type`.`facilitiesTypeID`=`facilities_type`.`facilitiesTypeID`"
                         + " GROUP BY `facilities_type`.`facilitiesTypeID`";;
            
            if("ALL".equalsIgnoreCase(filter)){
                    query = " SELECT `facilitiesTypeName`,COUNT(*)"
                         + " FROM `facilities_type`,`facilities`"
                         + " WHERE `facilities_type`.`facilitiesTypeID`=`facilities`.`facilitiesTypeID`"
                         + " GROUP BY `facilities_type`.`facilitiesTypeID`";
            }
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            int count=1;
            while(result.next()){
                item.add(new FacilitiesType(""+count,
                        result.getString("facilitiesTypeName"),result.getString(2)));
                count++;
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
            String query = " SELECT `facilitiesTypeName`"
                         + " FROM `facilities_type`"
                         + " WHERE 1";
            
            if("ALL".equalsIgnoreCase(filter)){
                   query = " SELECT `facilitiesTypeName`"
                         + " FROM `facilities_type`"
                         + " WHERE 1";
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
    
   
    
       public static ObservableList<FacilitiesStatus> getFacilitiesStatus(String name){
        ObservableList<FacilitiesStatus> leave = FXCollections.observableArrayList();
        try{
            String query = "SELECT facilitiesStatusID,`facilities_status`.`facilitiesID`,facilitiesResourceID,facilitiesStatusAvailable"
                         + ",facilitiesStatusDamage"
                         + " FROM `facilities_status`, `facilities`"
                         + " WHERE facilitiesName='"+name+"' AND `facilities`.`facilitiesID` = `facilities_status`.`facilitiesID`";
           
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
    public static ObservableList<String> getFacilitiesTypeId(String name){
        ObservableList<String> leave = FXCollections.observableArrayList();
        try{
            String query = "SELECT `facilitiesTypeID`"
                         + " FROM `facilities_type`"
                         + " WHERE facilitiesTypeName='"+name+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                leave.add(result.getString("facilitiesTypeID"));
            }
            return leave;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return leave;
        }
    }   
       
       
    public static String getFacilitiesId(String name){
        String leave = "";
        try{
            String query = "SELECT `facilitiesID`"
                         + " FROM `facilities`"
                         + " WHERE `facilitiesName`='"+name+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                leave = result.getString("facilitiesID");
            }
            return leave;
        } 
        catch(Exception ex){
             return "";
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
    
    public static ObservableList<String> getFacilitiesNames(){
        ObservableList<String> leave = FXCollections.observableArrayList();
        try{
            String query = "SELECT `facilitiesName`"
                         + " FROM `facilities`,`school`"
                         + " WHERE `facilities`.`schoolID`=`school`.`id`";
           
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
    public static String getResourceId(String name){
        String leave = "";
        try{
            String query = "SELECT `facilitiesResourceID`"
                         + " FROM `facilities_resource`"
                         + " WHERE `facilitiesResourceName`='"+name+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                leave=result.getString("facilitiesResourceID");
            }
            return leave;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return "";
        }
    }
    
     public static String getResourceName(String id){
        String leave = "";
        try{
            String query = "SELECT `facilitiesResourceName`"
                         + " FROM `facilities_resource`"
                         + " WHERE `facilitiesResourceID`='"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                leave=result.getString("facilitiesResourceName");
            }
            return leave;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return "";
        }
    }
    
     public static ObservableList<String> getResourceNames(){
        ObservableList<String> leave = FXCollections.observableArrayList();
        try{
            String query = "SELECT `facilitiesResourceName`"
                         + " FROM `facilities_resource`"
                         + " WHERE 1";
           
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
           //System.out.println(query);
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
            String query = " SELECT `policyDocID`,`policyDocName`,`policyDocDate`,`policyDocStaff`"
                         + " FROM `policy_document`,`school`"
                         + " WHERE `policy_document`.`schoolID` = `school`.`id`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                item.add(new PolicyDocument(result.getString("policyDocID"),result.getString("policyDocName")
                        ,result.getString("policyDocDate"),result.getString("policyDocStaff")));
            }
            
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
   
    public static String updatePolicyDocument(PolicyDocument pdoc, boolean update){
        
        try{
            String insertQuery = "INSERT INTO `policy_document` (`policyDocID`, `policyDocName`, `policyDocDate`, `policyDocStaff`)"
                    + " VALUES ('"+pdoc.getPolicyDocID()+"', '"+pdoc.getPolicyDocName()+"','"+pdoc.getPolicyDocDate()+"',"
                    + "'"+pdoc.getPolicyDocStaff()+"')";
            
            
            String updateQuery = "UPDATE `policy_document` SET `policyDocName`='"+pdoc.getPolicyDocID()+"' "
                    + " WHERE `policyDocID`= '"+pdoc.getPolicyDocID()+"'";
            
            if(update){
                STATEMENT.addBatch(updateQuery);
            }else{
                STATEMENT.addBatch(insertQuery);
            }
            
            STATEMENT.executeBatch();
            
            return "";
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return ex.getMessage();
        }
    }
        
    
    public static String updateInventoryItem(Inventory item, boolean update){
        
        try{
            //getInventoryType(item.getInventoryID());
            
            
            String insertQuery = "INSERT INTO `inventory` (`inventoryID`, `inventoryName`, `inventoryDescription`,"
                    + " `inventoryManSn`, `inventoryGovSn`, `inventoryYears`, `inventoryCost`, `inventoryPurchaseOrder`, "
                    + "`inventoryLocation`, `inventoryBatch`, `inventoryDate`, `inventoryDept`, `inventoryStaffID`, `inventoryQuantity`,"
                    + " `inventorySupplierID`, `inventoryCaptureDate`, `invetoryCaptuteStaff`, `schoolID`,`inventory_typeID`)"
                    + " VALUES (0,'"+item.getInventoryName()+"','"+item.getInventoryDescription()+"','"+item.getInventoryManSn()+"',"
                    + "'"+item.getInventoryGovSn()+"','"+item.getInventoryYears()+"','"+item.getInventoryCost()+"','"+item.getInventoryPurchaseOrder()+"',"
                    + "'"+item.getInventoryLocation()+"','"+item.getInventoryBatch()+"','"+item.getInventoryDate()+"','"+item.getInventoryDept()+"'"
                    + ",'"+item.getInventoryStaffID()+"','"+item.getInventoryQuantity()+"','"+item.getInventorySupplierID()+"',now()"
                    + ",'"+item.getInvetoryCaptuteStaff()+"','"+item.getSchoolID()+"','')";
            
            STATEMENT.addBatch(insertQuery);
            STATEMENT.executeBatch();
            
            return "";
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return ex.getMessage();
        }
     }
    
     public static String updateFacilityItem(Facilities item, boolean update){
        
        try{ 
            String insertQuery = "INSERT INTO `facilities` (facilitiesID,facilitiesName,facilitiesTypeID,deptID,facilitiesStatus,"
                    + "facilitiesCapacity,schoolID)"
                    + " VALUES (0,'"+item.getFacilitiesName()+"','"+item.getFacilitiesType()+"','"+item.getFacilitiesDept()+"','"+item.getFacilitiesStatus()+"'"
                    + ",'"+item.getFacilitiesCapacity()+"','100')";
            
           // System.out.println(insertQuery);
            
            STATEMENT.addBatch(insertQuery);
     
            STATEMENT.executeBatch();
            
            return "";
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return ex.getMessage();
        }
    }
     
     public static String updateSupplierItem(Supplier item, boolean update){
        
        try{ 
            
            String insertQuery = "INSERT INTO `suppliers`(`supplierID`, `supplierName`, `supplierPhone`, `supplierFax`, "
                    + "`supplierEmail`, `supplierPostal`, `supplierPhysical`, `schoolID`, `supplierCell`) "
                    + "VALUES (0,'"+item.getSupplierName()+"','"+item.getSupplierPhone()+"','"+item.getSupplierFax()+"',"
                    + "'"+item.getSupplierEmail()+"','"+item.getSupplierPostal()+"','"+item.getSupplierPhysical()+"','100',"
                    + "'"+item.getSupplierCell()+"')";
            
            if(update){
                
             insertQuery = "UPDATE `suppliers` SET `supplierName`='"+item.getSupplierName()+"',`supplierPhone`='"+item.getSupplierPhone()+"',"
                     + "`supplierFax`='"+item.getSupplierFax()+"',`supplierEmail`='"+item.getSupplierEmail()+"',"
                     + "`supplierPostal`='"+item.getSupplierPostal()+"',`supplierPhysical`='"+item.getSupplierPhysical()+"'"
                     + ",`supplierCell`='"+item.getSupplierCell()+"' WHERE `supplierID`="+item.getID()+"";
            }
            STATEMENT.addBatch(insertQuery);
     
            STATEMENT.executeBatch();
            
            return "";
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return ex.getMessage();
        }
    }
     
    public static String addFacilitiesStatus(FacilitiesStatus facilities,boolean state){
        try{
            
            String query = "INSERT INTO `facilities_status`(`facilitiesStatusID`, `facilitiesID`, "
                    + "`facilitiesResourceID`, `facilitiesStatusAvailable`, `facilitiesStatusDamage`)"
                    + " VALUES(0,'"+facilities.getFacilitiesID()+"','"+facilities.getFacilitiesResourceID()+"'"
                    + ",'"+facilities.getFacilitiesStatusAvailable()+"','"+facilities.getFacilitiesStatusDamage()+"')";
           
            STATEMENT.executeUpdate(query);
     
            return "";
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return ""+ex.getMessage().toString();
        }
    }
       
    
    public static ObservableList<StudentAllocationModel> getStudentAllocation(String filter,String filter2){
        ObservableList<StudentAllocationModel> item =  FXCollections.observableArrayList();
        
        try{
            
                       String query  = "SELECT `student_inventoryID`,`inventoryName`,`inventoryDept`,`inventoryManSn`"
                               + "FROM `student`,`inventory`,`student_inventory`,`inventory_type`"
                               + "WHERE `student`.`studentID`=`student_inventoryStudentID` "
                               + "AND `studentID`='"+filter+"'"
                               + "AND `inventory`.`inventory_typeID`=`inventory_type`.`inventory_typeName`";
                       
                        if(!filter2.equals("")){
                                query  = " SELECT `student_inventoryID`,`inventoryName`,`inventoryDept`,`inventoryManSn`"
                                        +" FROM `student`,`inventory`,`student_inventory`,`inventory_type`"
                                        +" WHERE `student`.`studentID`=`student_inventoryStudentID` "
                                        +" AND studentID='"+filter+"'"
                                        +" AND `inventory`.`inventory_typeID`=`inventory_type`.`inventory_typeName`"
                                        +" AND `inventory`.`inventory_typeID`='"+filter2+"'";

                        }
            
                        ResultSet result = STATEMENT.executeQuery(query);
                        int count=1;
                        while(result.next()){
                            item.add(new StudentAllocationModel(""+count,result.getString("inventoryName")
                                    ,result.getString("inventoryManSn")));
                            count++;
                        }
                        return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    } //StudentResourceList
    
    public static ObservableList<StudentResourceList> getStudentResourceList(String stream_id){
        ObservableList<StudentResourceList> item =  FXCollections.observableArrayList();
        try{
                String query = "SELECT `studentID`,CONCAT_WS(' ',`firstName`,`lastName`) AS `studentName`,className "
                        + "FROM `student`,class "
                        + "WHERE class.classID=student.classID";
                if(!stream_id.equals("ALL"))
                {
                    System.out.println("----"+stream_id+"----");
                    query = "SELECT `studentID`,CONCAT_WS(' ',`firstName`,`lastName`) AS `studentName`,className "
                            + "FROM `student`,class "
                            + "WHERE stream='"+stream_id+"' AND class.classID=student.classID";
                }
                ResultSet result = STATEMENT.executeQuery(query);

                while(result.next()){
                        item.add(new StudentResourceList(result.getString("studentID"),result.getString("studentName"),
                                result.getString("className"),""));
                }
                return item;
            
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
    
      public static ObservableList<String> getInventoryType(){
        ObservableList<String> item =  FXCollections.observableArrayList();
        try{
            String query = "SELECT `inventory_typeID`, `inventory_typeName` FROM `inventory_type` WHERE 1";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                item.add(result.getString("inventory_typeName"));
            }
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }
      
    public static String getInventoryTypeName(String inventoryTypeID){
        String item="";
        try{
            String query = "SELECT `inventory_typeName` FROM `inventory_type` WHERE `inventory_typeID`='"+inventoryTypeID+"'" ;
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                item=result.getString("inventory_typeName");
            }
            return item;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return item;
        }
    }  
    
    public static String getAllocatedResourceNo(String str){
        
        String query = "SELECT COUNT(*) FROM `student_inventory` WHERE student_inventoryStudentID='"+str+"'";
           
         try {
            ResultSet res = STATEMENT.executeQuery(query);
            if(res.next())
            {
                return res.getInt(0)+"";
            }
        }
        catch(SQLException e){
            System.out.println("failed ...!!");
            return "FALIED";
        }
        return "0";
    }
}
