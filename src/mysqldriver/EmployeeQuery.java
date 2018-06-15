package mysqldriver;

import employeemanagement.EmployeeModel;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static mysqldriver.MySQLHander.STATEMENT;

/**
 *
 * @author JABARI
 */
public class EmployeeQuery {
    
      
    /********************************************************************************************************
     * 
     * @param ID
     * @return 
     */
    public static EmployeeModel getEmployeeByID(String ID){
        
        try{
            String query = "SELECT `id`, `employeeID`, `lastName`, `firstName`, `middleName`, `title`, `dob`,"
                    + " `designation`, `qualification`,`nationality`,"
                    + " `identity`,`postalAddress`, `physicalAddress`, `mobilePhone`,"
                    + " `officePhone`, `email`,`gender`, `enrollDate`, `picture`"
                    + " FROM `employee` WHERE `employeeID` = '"+ID+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new EmployeeModel(result.getString("id"),result.getString("employeeID"),
                        result.getString("firstName"), result.getString("lastName"),
                        result.getString("middleName"), result.getString("title"),
                        result.getString("dob"),result.getString("designation"), 
                        result.getString("qualification"), result.getString("nationality"),
                        result.getString("identity"),result.getString("postalAddress"),
                        result.getString("physicalAddress"), result.getString("mobilePhone"),
                        result.getString("officePhone"), result.getString("gender"), 
                        result.getString("email"),result.getString("enrollDate"), result.getString("picture"));
            }
            return new EmployeeModel();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return new EmployeeModel();
        }
    }
    
    
    public static EmployeeModel getEmployeeByName(String employeeName){
        
        try{
            String query = "SELECT `id`, `employeeID`, `lastName`, `firstName`, `middleName`, `title`, `dob`,"
                    + " `designation`, `qualification`,`nationality`,"
                    + " `identity`,`postalAddress`, `physicalAddress`, `mobilePhone`,"
                    + " `officePhone`, `email`,`gender`, `enrollDate`, `picture`"
                    + " FROM `employee` "
                    + "WHERE CONCAT_WS(' ',`firstName`,`lastName`) = '"+employeeName+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new EmployeeModel(result.getString("id"),result.getString("employeeID"),
                        result.getString("firstName"), result.getString("lastName"),
                        result.getString("middleName"), result.getString("title"),
                        result.getString("dob"),result.getString("designation"), 
                        result.getString("qualification"), result.getString("nationality"),
                        result.getString("identity"),result.getString("postalAddress"),
                        result.getString("physicalAddress"), result.getString("mobilePhone"),
                        result.getString("officePhone"), result.getString("gender"), 
                        result.getString("email"),result.getString("enrollDate"), result.getString("picture"));
            }
            return new EmployeeModel();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return new EmployeeModel();
        }
    }
    
     /**
     * Get students details
     * @param showAll
     * @param departmentID
     * @return 
     */
    public static ObservableList<EmployeeModel> getEmployeeList(boolean showAll){
        ObservableList<EmployeeModel> employees = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `employeeID`, `lastName`, `firstName`, `middleName`, `title`, `dob`,"
                    + " `designation`, `qualification`,`nationality`,"
                    + " `identity`,`postalAddress`, `physicalAddress`, `mobilePhone`,"
                    + " `officePhone`, `email`,`gender`, `enrollDate`, `picture`"
                    + " FROM `employee`";
            
            if(!showAll){
                query += " WHERE `status` = 'PRESENT'";
            }
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                employees.add(new EmployeeModel(result.getString("id"),result.getString("employeeID"),
                        result.getString("firstName"), result.getString("lastName"),
                        result.getString("middleName"), result.getString("title"),
                        result.getString("dob"),result.getString("designation"), 
                        result.getString("qualification"), result.getString("nationality"),
                        result.getString("identity"),result.getString("postalAddress"),
                        result.getString("physicalAddress"), result.getString("mobilePhone"),
                        result.getString("officePhone"), result.getString("gender"), 
                        result.getString("email"),result.getString("enrollDate"), result.getString("picture")));
            }
            return employees;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return employees;
        }
    }
    /**
     * 
     * @return 
     */
    public static ObservableList<String> getEmployeeNameList(){
        ObservableList<String> employees = FXCollections.observableArrayList();
        try{
            String query = "SELECT `lastName`, `firstName`, `middleName` FROM `employee`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                employees.add(result.getString("firstName")+" "+result.getString("lastName"));
            }
            return employees;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return employees;
        }
    }
    
    public static ObservableList<String> getEmployeeIDList(){
        ObservableList<String> employees = FXCollections.observableArrayList();
        try{
            String query = "SELECT `employeeID` FROM `employee`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                employees.add(result.getString("employeeID"));
            }
            return employees;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return employees;
        }
    }
    
    
}
