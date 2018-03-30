package mysqldriver;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schooladministration.GradeScheme;
import schooladministration.Term;
import schooladministration.User;
import studentmanagement.Student;

/**
 *
 * @author jabari
 */
public class MySQLHander {

    private final String Host_Address, userName, dbName, password;
    
    private final String DB_DRIVERCLASS = "com.mysql.jdbc.Driver";
    public static Connection CONNECTION = null;
    public static Statement STATEMENT = null;

    public MySQLHander() {
           
        if(!new File("dbconf.dcf").exists()){
            Host_Address = "";
            userName = "";
            dbName = "";
            password = "";
        }else{
            DBConfig dBConfig = DBConfig.deserialiseObject();

            Host_Address = dBConfig.getDBHost();
            userName = dBConfig.getDBUserName();
            dbName = dBConfig.getDBName();
            password = dBConfig.getDBPassword();
        }
        System.out.println("Connecting to: "+userName+"@"+Host_Address);
        if(dbConnect()){
            System.out.println("Connection established...");
        }else{
            System.out.println("Connection failed...");
        }
        
    }
    
    /**
     * Initialize database connect handler
     * @return 
     */
    public boolean dbConnect(){
        
        try{
            //-- Load the database class driver
            Class.forName(DB_DRIVERCLASS);
            
            String dbURL = "jdbc:mysql://"+ Host_Address + ":3306/"+dbName;

            //-- Get database connection handler
            CONNECTION  =  DriverManager.getConnection( dbURL, userName, password );
            if(CONNECTION != null){ 
                //-- create a Statement object for sending SQL statements to the database.  
               STATEMENT = CONNECTION.createStatement();
               
               //-- 
               return true;
            }
            return false;
        }catch(Exception error ){
            System.out.println(error.getMessage());
            return false;
        }
    }
    
    
    
    
    
    
    public ObservableList<Student> studentList(String filter){
        ObservableList<Student> students = FXCollections.observableArrayList();
        try{
            String query = " SELECT `students`.`studentID`, CONCAT_WS(' ',`firstName`,`lastName`) AS `fullname`,`status`,"
                         + " CONCAT_WS(',  ',`studentparent`.`email`, `mobilePhone`, `officePhone`) AS `contacts`, `name`"
                         + " FROM `students`, `studentparent`, `class`"
                         + " WHERE `students`.`studentID` = `studentparent`.`studentID`"
                         + " AND `class`.`classID` = `students`.`class`"
                         + " AND `status` = '"+filter+"'";
            
            if("ALL".equalsIgnoreCase(filter)){
                query = " SELECT `students`.`studentID`, CONCAT_WS(' ',`firstName`,`lastName`) AS `fullname`, `status`,"
                      + " CONCAT_WS(',  ',`studentparent`.`email`, `mobilePhone`, `officePhone`) AS `contacts`, `name`"
                      + " FROM `students`, `studentparent`, `class`"
                      + " WHERE `students`.`studentID` = `studentparent`.`studentID`"
                      + " AND `class`.`classID` = `students`.`class`";
            }
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                students.add(new Student("",result.getString("studentID"), result.getString("fullname"),
                        result.getString("contacts"), "","",result.getString("name"),
                        "","","","","","","","","","","","","","","","",""));
            }
            
            return students;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return students;
        }
    }
    
    
    
    
    
    
    
    
    /**
     * Get al/ System users
     * @return 
     */
    public ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();
        try{
            String query = "SELECT `ID`, `userID`, `password`, `lastLogin`, `updatePassword`, `status`"
                    + "FROM `users`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                users.add(new User(result.getString("ID"),result.getString("userID"), result.getString("password"),
                        result.getString("lastLogin"), result.getString("updatePassword"),
                        result.getString("status")));
            }
            
            return users;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return users;
        }
    }
    
    
    
    /***************************************************************************
     * GRADING SCHEME
     **************************************************************************/
    
    public static ObservableList<GradeScheme> getGrades(){
        ObservableList<GradeScheme> grades = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `symbol`, `lowerBound`, `upperBound`, `points`"
                         + " FROM `grading`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                grades.add(new GradeScheme(result.getString("id"), result.getString("symbol"), 
                        result.getString("lowerBound"), result.getString("upperBound")
                        , result.getString("points")));
            }
            return grades;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return grades;
        }
    }
    
    public static boolean updateGrades(ObservableList<GradeScheme> grades, boolean update){
        try{
            String query;
            if(!update){
                for(GradeScheme grade: grades){
                    query = "INSERT INTO `grading` "
                        + "(`id`, `symbol`, `lowerBound`, `upperbound`, `points`)"
                        + " VALUES ('0', '"+grade.getSymbol()+"',"
                        + " '"+grade.getLowerBound()+"',"
                        + " '"+grade.getUpperBound()+"',"
                        + " '"+grade.getPoints()+"')";
                    
                    STATEMENT.addBatch(query);
                }
                STATEMENT.executeBatch();
                return true;
                
            }else{
                for(GradeScheme grade: grades){
                    query = "UPDATE `grading` "
                            + " SET `symbol`='"+grade.getSymbol()+"',"
                            + " `lowerBound`='"+grade.getLowerBound()+"',"
                            + " `upperBound`='"+grade.getUpperBound()+"'"
                            + " `points`='"+grade.getPoints()+"'"
                            + " WHERE `id`= '"+grade.getId()+"'";

                    STATEMENT.addBatch(query);
                }
                STATEMENT.executeBatch();
                return true;
            }
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
    }
    
    
    
    
    /***************************************************************************
     * GRADING SCHEME
     **************************************************************************/
    
    public ObservableList<Term> getTermList(){
        ObservableList<Term> terms = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `description`, `from`, `to`, `year`, `currentTerm`"
                         + "FROM `term` ORDER BY `id` ASC";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                terms.add(new Term(result.getString("id"),result.getString("description"), result.getString("from"), result.getString("to"),
                result.getString("year"), result.getString("currentTerm")));
            }
            return terms;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return terms;
        }
    }
    
    
    /**
     * 
     * @return 
     */
    public ObservableList<String> getTermNameList(){
        ObservableList<String> terms = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `description`, `from`, `to`, `year`, `currentTerm`"
                         + "FROM `term` ORDER BY `id`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                terms.add(result.getString("description"));
            }
            return terms;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return terms;
        }
    }
    
    
    
}
