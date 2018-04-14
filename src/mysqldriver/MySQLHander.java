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
import studentmanagement.SParent;
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
            String query = " SELECT `student`.`studentID`, CONCAT_WS(' ',`firstName`,`lastName`) AS `fullname`,"
                         + " CONCAT_WS(',  ',`parent`.`email`, `cellphone`, `telephone`) AS `contacts`, `className`"
                         + " FROM `student`, `parent`, `class`"
                         + " WHERE `student`.`parentID` = `parent`.`id`"
                         + " AND `class`.`classID` = `student`.`classID`"
                         + " AND `status` = '"+filter+"'";
            
            if("ALL".equalsIgnoreCase(filter)){
                query = " SELECT `student`.`studentID`, CONCAT_WS(' ',`firstName`,`lastName`) AS `fullname`,"
                      + " CONCAT_WS(',  ',`parent`.`email`, `cellphone`, `telephone`) AS `contacts`, `className`"
                      + " FROM `student`, `parent`, `class`"
                      + " WHERE `student`.`parentID` = `parent`.`id`"
                      + " AND `class`.`classID` = `student`.`classID`";
            }
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                Student st = new Student();
                st.setFirstName(result.getString("fullname"));
                st.setClassID(result.getString("className"));
                st.setStudentID(result.getString("studentID"));
                st.setParentID(result.getString("contacts"));
                
                students.add(st);
            }
            
            return students;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return students;
        }
    }
    
    
    /***************************************************************************
     * @param studentID
     * @return 
     */
    public Student getStudentByID(String studentID){
        
        try{
            String query = "SELECT `id`, `studentID`, `lastName`, `firstName`, `middleName`, `dob`, `classID`, `gender`,"
                         + " `specialNeed`, `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`, `physicalAddress`,"
                         + " `schoolID`, `enrollDate`,`socialWelfare`, `parentID`, `club`, `sportCode`, `psle`, `picture`"
                         + " FROM `student` WHERE `studentID` = '"+studentID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Student(result.getString("id"),result.getString("studentID"), result.getString("firstName"), 
                    result.getString("lastName"), result.getString("middleName"), result.getString("dob"), 
                    result.getString("classID"), result.getString("gender"), result.getString("lastSchoolAttended"), 
                    result.getString("psle"), result.getString("citizenship"), result.getString("email"),
                    result.getString("specialNeed"), result.getString("socialWelfare"), result.getString("postalAddress"),
                    result.getString("physicalAddress"), result.getString("parentID"), result.getString("enrollDate"),
                    result.getString("club"), result.getString("sportCode"), result.getString("picture"),
                    result.getString("schoolID"));
            }
            return new Student();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Student();
        }
    }
    
    
    /**
     * 
     * @param studentN
     * @return 
     */
    public Student getStudentByName(String studentN){
        
        try{
            
            String query = "SELECT `id`, `studentID`, `lastName`, `firstName`, `middleName`, `dob`, `classID`, `gender`,"
                    + " `specialNeed`, `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`, `physicalAddress`, "
                    + " `schoolID`, `enrollDate`,`socialWelfare`, `parentID`, `club`, `sportCode`, `picture`, `psle`"
                    + " FROM `student` WHERE WHERE CONCAT_WS(' ',`firstName`,`lastName`) = '"+studentN+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Student(result.getString("id"),result.getString("studentID"), result.getString("firstName"), 
                    result.getString("lastName"), result.getString("middleName"), result.getString("dob"), 
                    result.getString("classID"), result.getString("gender"), result.getString("lastSchoolAttended"), 
                    result.getString("psle"), result.getString("citizenship"), result.getString("email"),
                    result.getString("specialNeed"), result.getString("socialWelfare"), result.getString("postalAddress"),
                    result.getString("physicalAddress"), result.getString("parentID"), result.getString("enrollDate"),
                    result.getString("club"), result.getString("sportCode"), result.getString("picture"),
                    result.getString("schoolID"));
            }
            return new Student();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Student();
        }
    }
    
    
    
    /**
     * Get all System users
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
                          result.getString("lastLogin"), result.getString("updatePassword"), result.getString("status")));
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
            String query = " SELECT `id`, `symbol`, `lowerBound`, `upperBound`, `points`"
                         + " FROM `grading`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                grades.add(new GradeScheme(result.getString("id"), result.getString("symbol"), 
                        result.getString("lowerBound"), result.getString("upperBound"), result.getString("points")));
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
                    query = " INSERT INTO `grading` (`id`, `symbol`, `lowerBound`, `upperbound`, `points`)"
                          + " VALUES ('0', '"+grade.getSymbol()+"', '"+grade.getLowerBound()+"', "
                          + " '"+grade.getUpperBound()+"', '"+grade.getPoints()+"')";
                    
                    STATEMENT.addBatch(query);
                }
                STATEMENT.executeBatch();
                return true;
                
            }else{
                for(GradeScheme grade: grades){
                    query = "UPDATE `grading` "
                            + " SET `symbol`='"+grade.getSymbol()+"', `lowerBound`='"+grade.getLowerBound()+"',"
                            + " `upperBound`='"+grade.getUpperBound()+"', `points`='"+grade.getPoints()+"'"
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
                terms.add(new Term(result.getString("id"),result.getString("description"), result.getString("from"),
                          result.getString("to"), result.getString("year"), result.getString("currentTerm")));
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
    
    
    
    /**
     * 
     * @param id
     * @return 
     */
    public SParent getParentByID(String id){
        
        try{
            String query = "SELECT `id`, `fName`, `lName`, `identity`, `relationship`, `education`,"
                    + "`occupation`,`cellphone`, `email`, `telephone`, `postalAddress`, `physicalAddress`"
                    + " FROM `parent` WHERE `id` = '"+id+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new SParent(result.getString("id"),result.getString("fName"),
                        result.getString("lName"), result.getString("identity"), 
                    result.getString("relationship"), result.getString("education"), result.getString("occupation"), 
                    result.getString("telephone"), result.getString("cellphone"), result.getString("email"), 
                    result.getString("postalAddress"), result.getString("physicalAddress"));
            }
            return new SParent();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new SParent();
        }
    }
    
    
    
}
