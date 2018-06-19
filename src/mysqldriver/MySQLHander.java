package mysqldriver;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schooladministration.GradeScheme;
import schooladministration.School;
import schooladministration.Term;
import schooladministration.User;
import studentmanagement.Aid;
import studentmanagement.Guardian;
import studentmanagement.SocialWelfare;
import studentmanagement.SpecialNeed;
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
        }catch(ClassNotFoundException | SQLException error ){
            System.out.println(error.getMessage());
            error.printStackTrace();
            return false;
        }
    }
    
    
    /**
     * 
     * @return 
     */
    public ObservableList<String> getStudentsNameList(){
        ObservableList<String> students = FXCollections.observableArrayList();
        try{
            String query = " SELECT CONCAT_WS(' ',`firstName`, `lastName`) AS `fullname`"
                         + " FROM `student`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                students.add(result.getString("fullname"));
            }
            return students;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
             
            return students;
        }
    }
    
    
    /**
     * 
     * @param filter
     * @return 
     */
    public ObservableList<Student> getStudentList(String filter){
        ObservableList<Student> students = FXCollections.observableArrayList();
        try{
            String query = " SELECT `student`.`studentID`, CONCAT_WS(' ',`student`.`firstName`,`student`.`lastName`) AS `fullname`,"
                         + " `email`, `className`, `gender`"
                         + " FROM `student`, `class`"
                         + " WHERE `class`.`classID` = `student`.`classID`"
                         + " AND `status` = '"+filter+"'";
            
            if("ALL".equalsIgnoreCase(filter)){
                query = " SELECT `student`.`studentID`, CONCAT_WS(' ',`student`.`firstName`,`student`.`lastName`) AS `fullname`,"
                         + " `email`, `gender`"
                         + " FROM `student`";
            }
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                Student st = new Student();
                st.setFirstName(result.getString("fullname"));
                st.setStudentID(result.getString("studentID"));
                st.setEmail(result.getString("email"));
                st.setGender(result.getString("gender"));
                
                students.add(st);
            }
            return students;
            
        }catch(Exception ex){
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
                         + " `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`, `physicalAddress`,"
                         + " `schoolID`, `enrollDate`, `status`, `psle`, `picture`, `schoolID`"
                         + " FROM `student` WHERE `studentID` = '"+studentID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Student(result.getString("id"),result.getString("studentID"), 
                    result.getString("firstName"), result.getString("lastName"), result.getString("middleName"),
                    result.getString("dob"), result.getString("classID"), result.getString("gender"), 
                    result.getString("lastSchoolAttended"), result.getString("psle"), result.getString("citizenship"),
                    result.getString("email"), result.getString("postalAddress"), result.getString("physicalAddress"),
                    result.getString("enrollDate"), result.getString("status"), result.getString("picture"),
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
                    + " `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`, `physicalAddress`, "
                    + " `schoolID`, `enrollDate`, `status`, `picture`, `psle`"
                    + " FROM `student` WHERE CONCAT_WS(' ',`firstName`,`lastName`) = '"+studentN+"'";
            
            System.out.println(query);
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Student(result.getString("id"),result.getString("studentID"), 
                    result.getString("firstName"), result.getString("lastName"), result.getString("middleName"),
                    result.getString("dob"), result.getString("classID"), result.getString("gender"), 
                    result.getString("lastSchoolAttended"), result.getString("psle"), result.getString("citizenship"),
                    result.getString("email"), result.getString("postalAddress"), result.getString("physicalAddress"),
                    result.getString("enrollDate"), result.getString("status"), result.getString("picture"),
                    result.getString("schoolID"));
            }
            return new Student();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return new Student();
        }
    }
    
    
    /**
     * Get students details
     * @param classID
     * @param filter
     * @return 
     */
    public ObservableList<Student> getStudentListFor(String classID, String  filter){
        ObservableList<Student> students = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `studentID`, `lastName`, `firstName`, `middleName`, `dob`, "
                    + "`classID`, `gender`, `lastSchoolAttended`, `citizenship`,"
                    + " `email`, `postalAddress`, `physicalAddress`, "
                    + " `schoolID`, `enrollDate`, `club`, `status`, `picture`, `psle`"
                    + " FROM `student`, `class` WHERE `student`.`classID`= `class`.`classID` "
                    + " AND `status` = '"+filter+"' AND `classID`= '"+classID+"'";
                    
            
            if(filter.equalsIgnoreCase("ALL")){
                
                query = "SELECT `id`, `studentID`, `lastName`, `firstName`, `middleName`, `dob`, `classID`, `gender`,"
                    + " `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`, `physicalAddress`, "
                    + " `schoolID`, `enrollDate`, `status`, `picture`, `psle`"
                    + " FROM `student`, `class` WHERE `student`.`classID`= `class`.`classID` "
                    + " `classID`= '"+classID+"'";
            }
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                students.add(new Student(result.getString("id"),result.getString("studentID"), 
                    result.getString("firstName"), result.getString("lastName"), result.getString("middleName"),
                    result.getString("dob"), result.getString("classID"), result.getString("gender"), 
                    result.getString("lastSchoolAttended"), result.getString("psle"), result.getString("citizenship"),
                    result.getString("email"), result.getString("postalAddress"), result.getString("physicalAddress"),
                    result.getString("enrollDate"), result.getString("status"), result.getString("picture"),
                    result.getString("schoolID")));
            }
            return students;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return students;
        }
    }
    
    public ObservableList<Student> getStudentListFor(String classID){
        ObservableList<Student> students = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `studentID`, `lastName`, `firstName`, `middleName`, `dob`, `class`.`classID`, "
                    + "`gender`, `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`,"
                    + " `physicalAddress`, `class`.`schoolID`, `enrollDate`, `status`, `picture`, `psle`"
                    + " FROM `student`, `class` WHERE `student`.`classID` = `class`.`classID` "
                    + " AND `class`.`classID` = '"+classID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                students.add(new Student(result.getString("id"),result.getString("studentID"), result.getString("firstName"), 
                    result.getString("lastName"), result.getString("middleName"), result.getString("dob"), 
                    result.getString("classID"), result.getString("gender"), result.getString("lastSchoolAttended"), 
                    result.getString("psle"), result.getString("citizenship"), result.getString("email"),
                    result.getString("postalAddress"), result.getString("physicalAddress"),
                    result.getString("enrollDate"), result.getString("status"), result.getString("picture"),
                    result.getString("schoolID")));
            }
            return students;
        } 
        catch(Exception ex){
             
             return students;
        }
    }
    
    
    /**
     * 
     * @param student
     * @param parent
     * @param update
     * @return 
     */
    public boolean updateStudentProfile(Student student, Guardian parent, boolean update){
        
        try{
            
            String studentQuery = "INSERT INTO `student` (`id`, `studentID`, `firstName`, `middleName`,`lastName`, `dob`, "
                    + "`classID`, `gender`, `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`, `physicalAddress`, "
                    + " `schoolID`, `enrollDate`, `status`, `picture`, `psle`)"
                    + " VALUES ('0', '"+student.getStudentID()+"','"+student.getFirstName()+"', '"+student.getMiddleName()+"',"
                    + "'"+student.getLastName()+"','"+student.getDob()+"', '"+student.getClassID()+"', '"+student.getGender()+"',"
                    + "'"+student.getLastSchoolAttended()+"', '"+student.getCitizenship()+"', '"+student.getEmail()+"',"
                    + "'"+student.getPostalAddress()+"', '"+student.getPhysicalAddress()+"', '"+student.getSchoolID()+"',"
                    + "'"+student.getEnrollDate()+"', '"+student.getStatus()+"', '"+student.getPicture()+"',"
                    + "'"+student.getPslegrade()+"')";
                        
            String parentQuery = "INSERT INTO `parent` (`id`, `studentID`, `firstName`, `middleName`, `lastName`, `relationship`, `identity`,"
                + "`education`, `occupation`, `email`, `cellphone`, `telephone`, `postalAddress`, `physicalAddress`)"
                + " VALUES ('0', '"+parent.getStudentID()+"','"+parent.getFirstName()+"', '"+parent.getMiddleName()+"', "
                + "'"+parent.getLastName()+"', '"+parent.getRelation()+"','"+parent.getIdentity()+"', '"+parent.getEducation()+"', "
                + "'"+parent.getOccupation()+"', '"+parent.getEmail()+"', '"+parent.getCellphone()+"',"
                + "'"+parent.getTelephone()+"', '"+parent.getPostalAddress()+"', '"+parent.getPhysicalAddress()+"')";
            
            
            if(update){
                
                parentQuery = "UPDATE `parent` SET `firstName`='"+parent.getFirstName()+"', `studentID`='"+parent.getStudentID()+"',"
                    + " `lastName`='"+parent.getLastName()+"', `identity`='"+parent.getIdentity()+"',`middleName`='"+parent.getMiddleName()+"',"
                    + " `relationship`='"+parent.getRelation()+"', `education`='"+parent.getEducation()+"',"
                    + " `occupation`='"+parent.getOccupation()+"', `cellphone`='"+parent.getCellphone()+"',"
                    + " `email`='"+parent.getEmail()+"', `telephone`='"+parent.getTelephone()+"',"
                    + " `postalAddress`='"+parent.getPostalAddress()+"', `physicalAddress`='"+parent.getPhysicalAddress()+"'"
                    + " WHERE `id`='"+parent.getID()+"'";
                
                studentQuery = "UPDATE `student` SET `firstName`='"+student.getFirstName()+"',"
                        + " `lastName`='"+student.getLastName()+"', `middleName`='"+student.getMiddleName()+"',"
                        + " `dob`='"+student.getDob()+"', `classID`= '"+student.getClassID()+"', `gender`='"+student.getGender()+"',"
                        + " `lastSchoolAttended`= '"+student.getLastSchoolAttended()+"', `citizenship`='"+student.getStudentID()+"',"
                        + " `email`= '"+student.getEmail()+"', `postalAddress`= '"+student.getPostalAddress()+"', "
                        + " `physicalAddress`= '"+student.getPhysicalAddress()+"', `schoolID`='"+student.getSchoolID()+"',"
                        + " `enrollDate`='"+student.getEnrollDate()+"', `status`='"+student.getEnrollDate()+"',"
                        + " `picture`='"+student.getPicture()+"', `psle`='"+student.getPslegrade()+"' "
                        + "  WHERE `studentID`= '"+student.getStudentID()+"'";
            }
            
            STATEMENT.addBatch(studentQuery);
            STATEMENT.addBatch(parentQuery);
            
            STATEMENT.executeBatch();
            
            return true;
            
        }catch(SQLException ex){
            System.out.println("<updateStudentProfile>"+ex.getMessage());
            return false;
        }
    }
    
    
    
    /**
     * 
     * @param parent
     * @param update
     * @return 
     */
    public boolean updateGaurdianProfile(Guardian parent, boolean update){
        
        try{
            String parentQuery = "INSERT INTO `parent` (`id`, `studentID`, `firstName`, `middleName`, `lastName`, `relationship`, `identity`,"
                + "`education`, `occupation`, `email`, `cellphone`, `telephone`, `postalAddress`, `physicalAddress`)"
                + " VALUES ('0', '"+parent.getStudentID()+"','"+parent.getFirstName()+"', '"+parent.getMiddleName()+"', "
                + "'"+parent.getLastName()+"', '"+parent.getRelation()+"','"+parent.getIdentity()+"', '"+parent.getEducation()+"', "
                + "'"+parent.getOccupation()+"', '"+parent.getEmail()+"', '"+parent.getCellphone()+"',"
                + "'"+parent.getTelephone()+"', '"+parent.getPostalAddress()+"', '"+parent.getPhysicalAddress()+"')";
            
            if(update){
                parentQuery = "UPDATE `parent` SET `firstName`='"+parent.getFirstName()+"', `studentID`='"+parent.getStudentID()+"',"
                    + " `lastName`='"+parent.getLastName()+"', `identity`='"+parent.getIdentity()+"',`middleName`='"+parent.getMiddleName()+"',"
                    + " `relationship`='"+parent.getRelation()+"', `education`='"+parent.getEducation()+"',"
                    + " `occupation`='"+parent.getOccupation()+"', `cellphone`='"+parent.getCellphone()+"',"
                    + " `email`='"+parent.getEmail()+"', `telephone`='"+parent.getTelephone()+"',"
                    + " `postalAddress`='"+parent.getPostalAddress()+"', `physicalAddress`='"+parent.getPhysicalAddress()+"'"
                    + " WHERE `id`='"+parent.getID()+"'";
            }
            
            STATEMENT.addBatch(parentQuery);
            
            STATEMENT.executeBatch();
            
            return true;
            
        }catch(SQLException ex){
            System.out.println("<updateParentProfile>"+ex.getMessage());
            return false;
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
     * @return 
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
    
    public Term getTermByID(String id){
        try{
            String query = "SELECT `id`, `description`, `from`, `to`, `year`, `currentTerm`"
                         + "FROM `term` WHERE `id` = '"+id+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Term(result.getString("id"),result.getString("description"), result.getString("from"),
                          result.getString("to"), result.getString("year"), result.getString("currentTerm"));
            }
            return new Term();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
             
            return new Term();
        }
    }
    
    
    
    public Term getTermByName(String name){
        try{
            String query = "SELECT `id`, `description`, `from`, `to`, `year`, `currentTerm`"
                         + "FROM `term` WHERE `description` = '"+name+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Term(result.getString("id"),result.getString("description"), result.getString("from"),
                          result.getString("to"), result.getString("year"), result.getString("currentTerm"));
            }
            return new Term();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
             
            return new Term();
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
    public Guardian getGaurdianByID(String id){
        
        try{
            String query = "SELECT `id`, `studentID`, `firstName`, `middleName`, `lastName`, `relationship`, `identity`, `education`, "
                    + " `occupation`, `email`, `cellphone`, `telephone`, `postalAddress`, `physicalAddress`"
                    + " FROM `parent` WHERE `id` = '"+id+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Guardian(result.getString("id"),result.getString("studentID"),
                        result.getString("firstName"),result.getString("middleName"), 
                        result.getString("lastName"),result.getString("identity"),
                        result.getString("relationship"), result.getString("education"),
                        result.getString("occupation"), result.getString("email"),
                        result.getString("cellphone"), result.getString("telephone"), 
                        result.getString("postalAddress"), result.getString("physicalAddress"));
            }
            return new Guardian();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return new Guardian();
        }
    }
    
    /**
     * Get a list of guardians associated with the student
     * @param studentID
     * @return 
     */
    public ObservableList<String> getStudentGaurdians(String studentID){
        ObservableList<String> gaurdians = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `studentID`, CONCAT_WS(' ',`firstName`,`lastName`) AS `fullname`, `relationship`, `identity`, `education`, "
                         + " `occupation`, `email`, `cellphone`, `telephone`, `postalAddress`, `physicalAddress`"
                         + " FROM `parent` WHERE `studentID` = '"+studentID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                gaurdians.add(result.getString("fullname"));
            }
            return gaurdians;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return gaurdians;
        }
    }
    
    /**
     * 
     * @param fullname
     * @return 
     */
    public Guardian getGaurdianByName(String fullname){
        
        try{
            String query = "SELECT `id`, `studentID`, `firstName`, `middleName`, `lastName`, `relationship`, `identity`, `education`, "
                    + " `occupation`, `email`, `cellphone`, `telephone`, `postalAddress`, `physicalAddress`"
                    + " FROM `parent`  WHERE CONCAT_WS(' ',`firstName`,`lastName`) = '"+fullname+"'";
            System.err.println(query);
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Guardian(result.getString("id"),result.getString("studentID"),
                        result.getString("firstName"),result.getString("middleName"), 
                        result.getString("lastName"),result.getString("identity"),
                        result.getString("relationship"), result.getString("education"),
                        result.getString("occupation"), result.getString("email"),
                        result.getString("cellphone"), result.getString("telephone"), 
                        result.getString("postalAddress"), result.getString("physicalAddress"));
            }
            return new Guardian();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return new Guardian();
        }
    }
    
    /**
     * Return the first instance of parent
     * @param studentID
     * @return 
     */
    public Guardian getGaurdianByStudentID(String studentID){
        
        try{
            String query = "SELECT `id`, `studentID`, `firstName`, `middleName`, `lastName`, `relationship`, `identity`, `education`, "
                    + " `occupation`, `email`, `cellphone`, `telephone`, `postalAddress`, `physicalAddress`"
                    + " FROM `parent` WHERE `studentID` = '"+studentID+"' ORDER BY `id` DESC";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Guardian(result.getString("id"),result.getString("studentID"),
                        result.getString("firstName"),result.getString("middleName"), 
                        result.getString("lastName"),result.getString("identity"),
                        result.getString("relationship"), result.getString("education"),
                        result.getString("occupation"), result.getString("email"),
                        result.getString("cellphone"), result.getString("telephone"), 
                        result.getString("postalAddress"), result.getString("physicalAddress"));
            }
            return new Guardian();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return new Guardian();
        }
    }
    
    
    
    
    /**
     * Get students details
     * @param departmentID
     * @return 
     */
    public ObservableList<String> getDepartmentEmployeeNames(String departmentID){
        ObservableList<String> employees = FXCollections.observableArrayList();
        try{
            String query = "SELECT `lName`, `fName`, `mName` FROM `employee`";
            
            if(departmentID != null){
                query = " SELECT `lName`, `fName`, `mName` "
                      + " FROM `employee` "
                      + " WHERE `departmentID` = '"+departmentID+"'";
            }
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                employees.add(result.getString("fName")+" "+result.getString("lName"));
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
    public School getSchoolByID(){
        try{
            String query = "SELECT `id`, `name`, `tel`, `fax`, `website`, `email`,"
                         + "`postalAddress`, `physicalAddress`, `showName`, `logo`"
                         + " FROM `school`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new School(result.getString("id"),result.getString("name")
                ,result.getString("tel"),result.getString("fax")
                ,result.getString("website"),result.getString("email")
                ,result.getString("postalAddress"),result.getString("physicalAddress")
                ,result.getString("logo"), result.getString("showName"));
            }
            return new School();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return new School();
        }
    }
    
    /**
     * 
     * @param school
     * @return 
     */
    public boolean updateSchoolDetails(School school){
        try{
            String query = "INSERT INTO `school` (`id`, `name`, `tel`,`fax`, `email`,`website`, "
                    + "`postalAddress`, `physicalAddress`, `logo`, `showName`)"
                    + " VALUES('0', '"+school.getSchoolName()+"', '"+school.getTel()+"',"
                    + " '"+school.getFax()+"' , '"+school.getEmail()+"', "
                    + " '"+school.getWebsite()+"', '"+school.getPostalAddress()+"',"
                    + " '"+school.getPhysicalAddress()+"', '"+school.getLogo()+"',"
                    + " '"+school.getShowName()+"')";
            
            if(!school.getSchoolID().equalsIgnoreCase("")){
                query = "UPDATE `school` SET "
                    + " `name`='"+school.getSchoolName()+"', `tel`='"+school.getTel()+"',"
                    + " `fax`='"+school.getFax()+"', `website`='"+school.getWebsite()+"',"
                    + " `email`='"+school.getEmail()+"',`postalAddress`='"+school.getPostalAddress()+"',"
                    + " `physicalAddress`='"+school.getPhysicalAddress()+"',"
                    + " `showName`='"+school.getShowName()+"'"
                    + " WHERE `id`='"+school.getSchoolID()+"'";
            }
            
            return (STATEMENT.executeUpdate(query) > 0);
            
        }catch(SQLException ex){
            return false;
        }
    }
    
    
    
    public boolean updateSchoolLogo(String logoName){
        try{
            String query = "UPDATE `school` SET  `logo`='"+logoName+"'";
            
            return STATEMENT.executeUpdate(query) > 0;
            
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return false;
        }
    }
    
    
    /**
     * @param schoolID
     * @return 
     */
    public School getSchoolByID(String schoolID){
        try{
            String query = "SELECT `id`, `name`, `tel`, `fax`, `website`, `email`, `postalAddress`, `physicalAddress`,"
                    + " `logo`, `showName` FROM `school` WHERE `id` = '"+schoolID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new School(result.getString("id"), result.getString("name"), result.getString("tel"), result.getString("fax"),
                result.getString("website"), result.getString("email"), result.getString("postalAddress"), result.getString("physicalAddress"),
                result.getString("logo"), result.getString("showName"));
            }
            return new School();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new School();
        }
    }
    
    
    /**
     * @param s_name
     * @return 
     */
    public School getSchoolByName(String s_name){
        try{
            String query = "SELECT `id`, `name`, `tel`, `fax`, `website`, `email`, `postalAddress`, `physicalAddress`,"
                    + " `logo`, `showName` FROM `school` WHERE `name` = '"+s_name+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new School(result.getString("id"), result.getString("name"), result.getString("tel"), result.getString("fax"),
                    result.getString("website"), result.getString("email"), result.getString("postalAddress"), result.getString("physicalAddress"),
                    result.getString("logo"), result.getString("showName"));
            }
            return new School();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new School();
        }
    }
    
    
    
    /**
     * Get domain schools
     * @param schoolID
     * @return 
     */
    public ObservableList<String> getSchoolNames(){
        ObservableList<String> names = FXCollections.observableArrayList();
        
        try{
            String query = "SELECT `name`  FROM `school`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                names.add(result.getString("name"));
            }
            return names;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return names;
        }
    }
    
   
    
    
    /**
     * Get domain names
     * @param schoolName
     * @return 
     */
    public String getSchoolIDByName(String schoolName){
        
        try{
            String query = "SELECT `id` FROM `school` WHERE `name` = '"+schoolName+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return result.getString("id");
            }
            return "";
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return "";
        }
    }
    
    
    
    /**
     * 
     * @param sn
     * @return 
     */
    public SpecialNeed getSpecialNeedByName(String sn){
        try{
            String query = "SELECT `id`, `name`, `description`"
                         + "FROM `special_needs` WHERE `name`='"+sn+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new SpecialNeed(result.getString("id"),result.getString("name"), result.getString("description"));
            }
            return new SpecialNeed();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return new SpecialNeed();
        }
    }
    
    /**
     * 
     * @param sn
     * @return 
     */
    public ObservableList<String> getSpecialNeedNames(){
        ObservableList<String> ssn = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `name`, `description`"
                         + "FROM `special_needs`";
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                ssn.add(result.getString("name"));
            }
            return ssn;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return ssn;
        }
    }
    
    
    /**
     * 
     * @param ssn
     * @param update
     * @return 
     */
    public boolean updateSSN(SpecialNeed ssn, boolean update){
        
        try{
            String query = "INSERT INTO `special_needs` (`id`, `name`, `description`)"
                    + " VALUES ('"+ssn.getId()+"', '"+ssn.getName()+"','"+ssn.getDescription()+"')";
            
            if(update){
                query = "UPDATE `special_needs` SET `description`='"+ssn.getDescription()+"', "
                    + "`name` = '"+ssn.getName()+"'"
                    + " WHERE `id`= '"+ssn.getId()+"'";
            }
            
            return STATEMENT.executeUpdate(query) > 0;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    
    /**
     * 
     * @param snID
     * @return 
     */
    public ObservableList<Student> getStudentsWithSpecialNeed(String snID){
        ObservableList<Student> ssn = FXCollections.observableArrayList();
        try{
            String query = " SELECT `student`.`studentID`, CONCAT_WS(' ',`student`.`firstName`,`student`.`lastName`) AS `fullname`,"
                         + " `email`, `className`, `gender`"
                         + " FROM `student`, `student_sn`, `class`"
                         + " WHERE `student`.`studentID` = `student_sn`.`studentID` "
                         + " AND `class`.`classID` = `student`.`classID` AND `student_sn`.`id` = '"+snID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                Student st = new Student();
                st.setFirstName(result.getString("fullname"));
                st.setStudentID(result.getString("studentID"));
                st.setClassID(result.getString("className"));
                st.setGender(result.getString("gender"));
                
                ssn.add(st);
            }
            return ssn;
            
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return ssn;
        }
    }
    
    //--------------------------------------------------------------------------
    
    
    /**
     * 
     * @param sn
     * @return 
     */
    public SocialWelfare getSocialWelfareByName(String sn){
        try{
            String query = "SELECT `id`, `name`, `description`"
                         + "FROM `social_welfare` WHERE `name`='"+sn+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new SocialWelfare(result.getString("id"),result.getString("name"), result.getString("description"));
            }
            return new SocialWelfare();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return new SocialWelfare();
        }
    }
    
        /**
     * 
     * @param sn
     * @return 
     */
    public SocialWelfare getSocialWelfareByID(String sn){
        try{
            String query = "SELECT `id`, `name`, `description`"
                         + "FROM `social_welfare` WHERE `id`='"+sn+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new SocialWelfare(result.getString("id"),result.getString("name"), result.getString("description"));
            }
            return new SocialWelfare();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return new SocialWelfare();
        }
    }
    
    /**
     * 
     * @param sn
     * @return 
     */
    public ObservableList<String> getSocialWelfareNames(){
        ObservableList<String> sws = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `name`, `description`"
                         + "FROM `social_welfare`";
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                sws.add(result.getString("name"));
            }
            return sws;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return sws;
        }
    }
    
    
    /**
     * 
     * @param sws
     * @param update
     * @return 
     */
    public boolean updateSWS(SocialWelfare sws, boolean update){
        
        try{
            String query = "INSERT INTO `social_welfare` (`id`, `name`, `description`)"
                    + " VALUES ('"+sws.getId()+"', '"+sws.getName()+"','"+sws.getDescription()+"')";
            
            if(update){
                query = "UPDATE `social_welfare` SET `description`='"+sws.getDescription()+"', "
                    + "`name` = '"+sws.getName()+"'"
                    + " WHERE `id`= '"+sws.getId()+"'";
            }
            
            return STATEMENT.executeUpdate(query) > 0;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    
    /**
     * 
     * @param snID
     * @return 
     */
    public ObservableList<Student> getStudentsWithSocialWelfareSupport(String snID){
        ObservableList<Student> ssn = FXCollections.observableArrayList();
        try{
            String query = " SELECT `student`.`studentID`, CONCAT_WS(' ',`student`.`firstName`,`student`.`lastName`) AS `fullname`,"
                         + " `email`, `className`, `gender`"
                         + " FROM `student`, `student_sw`, `class`"
                         + " WHERE `student`.`studentID` = `student_sw`.`studentID` "
                         + " AND `class`.`classID` = `student`.`classID` AND `student_sw`.`id` = '"+snID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                
                Student st = new Student();
                st.setFirstName(result.getString("fullname"));
                st.setStudentID(result.getString("studentID"));
                st.setClassID(result.getString("className"));
                st.setGender(result.getString("gender"));
                
                ssn.add(st);
            }
            return ssn;
            
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return ssn;
        }
    }
    
    
    
    //--------------------------------------------------------------------------
    
    
    /**
     * 
     * @param sn
     * @return 
     */
    public Aid getSocialWelfareAidByName(String sn){
        try{
            String query = "SELECT `id`, `name`, `cooperation`, `swID`, `description`"
                         + "FROM `sw_aid` WHERE `name`='"+sn+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Aid(result.getString("id"),result.getString("swID"), result.getString("name"),
                        result.getString("cooperation"), result.getString("description"));
            }
            return new Aid();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return new Aid();
        }
    }
    
        /**
     * 
     * @param sn
     * @return 
     */
    public Aid getSocialWelfareAidByID(String sn){
        try{
            String query = "SELECT `id`, `name`, `cooperation`, `swID`, `description`"
                         + "FROM `sw_aid` WHERE `id`='"+sn+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Aid(result.getString("id"),result.getString("swID"), result.getString("name"),
                        result.getString("cooperation"), result.getString("description"));
            }
            return new Aid();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return new Aid();
        }
    }
    
    /**
     * 
     * @param sn
     * @return 
     */
    public ObservableList<String> getAidNames(){
        ObservableList<String> sws = FXCollections.observableArrayList();
        try{
            String query = "SELECT `name`"
                         + "FROM `sw_aid`";
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                sws.add(result.getString("name"));
            }
            return sws;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return sws;
        }
    }
    
    
    /**
     * 
     * @param sws
     * @param update
     * @return 
     */
    public boolean updateAid(Aid sws, boolean update){
        
        try{
            String query = "INSERT INTO `sw_aid` (`id`, `name`, `cooperation`, `swID`, `description`)"
                    + " VALUES ('"+sws.getId()+"', '"+sws.getName()+"','"+sws.getCooperation()+"',"
                    + "'"+sws.getSocialWelfareID()+"','"+sws.getDescription()+"')";
            
            if(update){
                query = "UPDATE `sw_aid` SET `name`='"+sws.getName()+"', "
                    + "`cooperation` = '"+sws.getCooperation()+"',"
                    + "`swID` = '"+sws.getSocialWelfareID()+"',"
                    + "`description` = '"+sws.getDescription()+"'"
                    + " WHERE `id`= '"+sws.getId()+"'";
            }
            
            return STATEMENT.executeUpdate(query) > 0;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    
}
