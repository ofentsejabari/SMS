package mysqldriver;

import employeemanagement.EmployeeModel;
import employeemanagement.NextOfKin;
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
            String query = "SELECT `id`, `employeeID`, CONCAT_WS(' ',`firstName`, `lastName`) AS `fullname`, `middleName`, `title`, `dob`,"
                    + " `designation`, `qualification`,`nationality`,"
                    + " `identity`,`postalAddress`, `physicalAddress`, `mobilePhone`,"
                    + " `officePhone`, `email`,`gender`, `enrollDate`, `picture`"
                    + " FROM `employee`";
            
            if(!showAll){
                query += " WHERE `status` = 'PRESENT'";
            }
            query+=" ORDER BY `fullname`";
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                
                employees.add(new EmployeeModel(result.getString("id"),result.getString("employeeID"),
                        result.getString("fullname"), "",
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
    //
     public static String getEmployeeDesignationId(String description){
        String employees ="";
        try{
            String query = "SELECT `id` FROM `employeeposition` WHERE `description`='"+description+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                //System.out.println(result.getString("id"));
                employees = result.getString("id");
            }
            return employees;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return employees;
        }
    }
    
    public static String getEmployeeDesignation(String designation){
        String employees ="";
        try{
            String query = "SELECT  `description` FROM `employeeposition` WHERE id="+designation;
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                employees = result.getString("description");
            }
            return employees;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return employees;
        }
    }
    public static ObservableList<String> getEmployeeDesignationList(){
        ObservableList<String> employees =FXCollections.observableArrayList();
        try{
            String query = "SELECT  `description` FROM `employeeposition` WHERE 1";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                employees.add(result.getString("description"));
            }
            return employees;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return employees;
        }
    }
    
    public static NextOfKin getNextOfKin(String empID){
        
        NextOfKin nextOfKin = null;
        
        String item ="SELECT `id`, `firstname`, `lastname`, `omang`, `employeeID`, `telephone`, `cellphone`, `relationship`,"
                + " `postal`, `physical`, `email` FROM `next_of_kin` WHERE `employeeID`='"+empID+"'";
        
        try
        {
            ResultSet rs =STATEMENT.executeQuery(item);
            if (rs.next()){
                

               nextOfKin= new NextOfKin(
                        rs.getString("employeeID"), 
                        rs.getString("firstname"), 
                        rs.getString("lastname"),
                        rs.getString("omang"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("cellphone"),
                        rs.getString("relationship"),
                        rs.getString("postal"),
                        rs.getString("physical")
                );
                
            }
            return nextOfKin;
        }
        
        catch(Exception e)
        {
            return nextOfKin;
        }
    
    }
    
    /*
    *   
    */
    public static boolean updateEmployeeProfile(EmployeeModel employee,NextOfKin kin, boolean flag)
    {
           
        
            if (flag){
                String employee_query="UPDATE `employee` SET `title`='"+employee.getTitle()+"',`firstName`='"+employee.getFirstName()+"',"
                    + "`middleName`='"+employee.getMiddleName()+"',`lastName`='"+employee.getLastName()+"',`gender`='"+employee.getGender()+"'"
                    + ",`dob`='"+employee.getDob()+"',`designation`='"+employee.getDesignation()+"',"
                    + "`qualification`='"+employee.getQualification()+"',`nationality`='"+employee.getNationality()+"',`identity`='"+employee.getIdentity()+"'"
                    + ",`postalAddress`='"+employee.getPostalAddress()+"',"
                    + "`physicalAddress`='"+employee.getPhysicalAddress()+"',`officePhone`='"+employee.getTelephone()+"'"
                    + ",`mobilePhone`='"+employee.getCellPhone()+"',`email`='"+employee.getEmail()+"',"
                    + "`enrollDate`='"+employee.getEnrollDate()+"',`picture`='"+employee.getProfilePsicture()+"' WHERE `employeeID`='"+employee.getEmployeeID()+"'";
                
                String kin_query="UPDATE `next_of_kin` SET `id`=0 ,`firstname`='"+kin.getFirstName()+"',"
                        + "`lastname`='"+kin.getSurname()+"',`omang`='"+kin.getOmang()+"',`employeeID`='"+kin.getEmployeeId()+"'"
                        + ",`telephone`='"+kin.getTelephone()+"',`cellphone`='"+kin.getCellphone()+"',`relationship`='"+kin.getRelationship()+"'"
                        + ",`postal`='"+kin.getPostalAddress()+"',`physical`='"+kin.getPhysicalAddress()+"',`email`='"+kin.getEmail()+"'"
                        + " WHERE `employeeID`='"+employee.getEmployeeID()+"'";
                
                try{
                    STATEMENT.executeUpdate(employee_query);
                    STATEMENT.executeUpdate(kin_query);

                    return true;
                }
                catch(Exception e){
                    System.out.println("EEE"+e);
                    return false;
                }
            }
            else{
            /*
            *  add Employee
            */
                try{
                    String employee_query="INSERT INTO `employee`(`id`, `employeeID`, `title`, `firstName`, `middleName`, `lastName`, `gender`,"
                            + " `dob`, `designation`, `qualification`, `nationality`,"
                            + " `identity`, `postalAddress`, `physicalAddress`, `officePhone`, `mobilePhone`, "
                            + "`email`, `enrollDate`, `picture`) VALUES (0,'"+employee.getEmployeeID()+"','"+employee.getTitle()+"',"
                            + "'"+employee.getFirstName()+"','"+employee.getMiddleName()+"','"+employee.getLastName()+"','"+employee.getGender()+"',"
                            + "'"+employee.getDob()+"','"+employee.getDesignation()+"','"+employee.getQualification()+"','"+employee.getNationality()+"',"
                            + "'"+employee.getIdentity()+"','"+employee.getPostalAddress()+"',"
                            + "'"+employee.getPhysicalAddress()+"','"+employee.getTelephone()+"','"+employee.getCellPhone()+"','"+employee.getEmail()+"',"
                            + "'"+employee.getEnrollDate()+"','"+employee.getProfilePsicture()+"')";
                   
                    STATEMENT.executeUpdate(employee_query);
                    
                        
                        String kin_query = "INSERT INTO `next_of_kin`(`id`, `firstname`, `lastname`, `omang`, `employeeID`, `telephone`, `cellphone`, "
                                + "`relationship`, `postal`, `physical`, `email`)"
                                + " VALUES (0,'"+kin.getFirstName()+"','"+kin.getSurname()+"','"+kin.getOmang()+"',"
                                + "'"+kin.getEmployeeId()+"','"+kin.getTelephone()+"','"+kin.getCellphone()+"'"
                                + ",'"+kin.getRelationship()+"','"+kin.getPostalAddress()+"',"
                                + "'"+kin.getPhysicalAddress()+"','"+kin.getEmail()+"')";
                        
                    STATEMENT.executeUpdate(kin_query);    
                    

                    return true;
                }
                catch(Exception e){
                    System.out.println(e);
                    return false;
                }
            }
    }
            
            
}
