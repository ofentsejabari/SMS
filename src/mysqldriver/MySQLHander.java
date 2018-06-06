package mysqldriver;

import employeemanagement.Employee;
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
    
    
    
    public ObservableList<Student> getStudentList(String filter){
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
                      + " CONCAT_WS(',  ',`parent`.`email`, `cellphone`, `telephone`) AS `contacts`, `className`,"
                      + " `gender`"
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
                st.setGender(result.getString("gender"));
                
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
     * Get students details
     * @param classID
     * @param filter
     * @return 
     */
    public ObservableList<Student> getStudentListFor(String classID, String  filter){
        ObservableList<Student> students = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `studentID`, `lastName`, `firstName`, `middleName`, `dob`, `classID`, `gender`,"
                    + " `specialNeed`, `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`, `physicalAddress`, "
                    + " `schoolID`, `enrollDate`,`socialWelfare`, `parentID`, `club`, `sportCode`, `picture`, `psle`"
                    + " FROM `student`, `class` WHERE `student`.`classID`= `class`.`classID` "
                    + " AND `status` = '"+filter+"' AND `classID`= '"+classID+"'";
                    
            
            if(filter.equalsIgnoreCase("ALL")){
                
                query = "SELECT `id`, `studentID`, `lastName`, `firstName`, `middleName`, `dob`, `classID`, `gender`,"
                    + " `specialNeed`, `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`, `physicalAddress`, "
                    + " `schoolID`, `enrollDate`,`socialWelfare`, `parentID`, `club`, `sportCode`, `picture`, `psle`"
                    + " FROM `student`, `class` WHERE `student`.`classID`= `class`.`classID` "
                    + " `classID`= '"+classID+"'";
                
                query = "SELECT `students`.`ID`, `students`.`studentID`, `lastName`, `firstName`, `middleName`, `dob`, `classID`, `gender`, `language`,"
                         + "`place_of_birth`,`nationality`, `email`, `phone`, `healthIssues`, `physicalAddress`, `studentID`,"
                         + " `students`.`schoolID`, `enrollDate`,`graduateDate`, `statusChangedDate`, `status`, `statusChangeReason`, `picture`, `oncampus`"
                         + " FROM `students`, `class` WHERE `classID` = `class`"
                         + " AND `classID`= '"+classID+"'";
            }
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                students.add(new Student(result.getString("id"),result.getString("studentID"), result.getString("firstName"), 
                    result.getString("lastName"), result.getString("middleName"), result.getString("dob"), 
                    result.getString("classID"), result.getString("gender"), result.getString("lastSchoolAttended"), 
                    result.getString("psle"), result.getString("citizenship"), result.getString("email"),
                    result.getString("specialNeed"), result.getString("socialWelfare"), result.getString("postalAddress"),
                    result.getString("physicalAddress"), result.getString("parentID"), result.getString("enrollDate"),
                    result.getString("club"), result.getString("sportCode"), result.getString("picture"),
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
            String query = "SELECT `id`, `studentID`, `lastName`, `firstName`, `middleName`, `dob`, `class`.`classID`, `gender`,"
                    + " `specialNeed`, `lastSchoolAttended`, `citizenship`, `email`, `postalAddress`, `physicalAddress`, "
                    + " `class`.`schoolID`, `enrollDate`,`socialWelfare`, `parentID`, `club`, `sportCode`, `picture`, `psle`"
                    + " FROM `student`, `class` WHERE `student`.`classID` = `class`.`classID` "
                    + " AND `class`.`classID` = '"+classID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                students.add(new Student(result.getString("id"),result.getString("studentID"), result.getString("firstName"), 
                    result.getString("lastName"), result.getString("middleName"), result.getString("dob"), 
                    result.getString("classID"), result.getString("gender"), result.getString("lastSchoolAttended"), 
                    result.getString("psle"), result.getString("citizenship"), result.getString("email"),
                    result.getString("specialNeed"), result.getString("socialWelfare"), result.getString("postalAddress"),
                    result.getString("physicalAddress"), result.getString("parentID"), result.getString("enrollDate"),
                    result.getString("club"), result.getString("sportCode"), result.getString("picture"),
                    result.getString("schoolID")));
            }
            return students;
        } 
        catch(Exception ex){
             ex.printStackTrace();
             return students;
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
     * @param ID
     * @return 
     */
    public Employee getEmployeeByID(String ID){
        
        try{
            String query = "SELECT `ID`, `employeeID`, `lName`, `fName`, `mName`, `title`, `dob`,"
                    + " `departmentID`, `employeePosition`, `qualification`, `status`,`nationality`,"
                    + " `identity`,`postalAddress`, `physicalAddress`, `mobilePhone`,"
                    + " `officePhone`, `email`,`gender`, `enrollDate`, `picture`"
                    + " FROM `employee` WHERE `employeeID` = '"+ID+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Employee(result.getString("ID"),result.getString("employeeID"), result.getString("fName"),
                        result.getString("lName"), result.getString("mName"), result.getString("title"),
                        result.getString("dob"), result.getString("departmentID"), result.getString("employeePosition"), 
                        result.getString("qualification"), result.getString("status"),
                        result.getString("nationality"),result.getString("identity"),result.getString("postalAddress"),
                        result.getString("physicalAddress"), result.getString("mobilePhone"),
                        result.getString("officePhone"), result.getString("gender"), 
                        result.getString("email"),result.getString("enrollDate"), result.getString("picture"));
            }
            return new Employee();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Employee();
        }
    }
    
    
    public Employee getEmployeeByName(String employeeName){
        
        try{
            String query = "SELECT `ID`, `employeeID`, `lName`, `fName`, `mName`, `title`, `dob`, `departmentID`, `employeePosition`,"
                    + " `qualification`, `status`,`nationality`, `identity`,`postalAddress`, `physicalAddress`,"
                    + " `picture`, `mobilePhone`, `officePhone`, `email`,`gender`, `enrollDate` FROM `employee` "
                    + "WHERE CONCAT_WS(' ',`fName`,`lName`) = '"+employeeName+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Employee(result.getString("ID"),result.getString("employeeID"), result.getString("fName"),
                        result.getString("lName"), result.getString("mName"), result.getString("title"),
                        result.getString("dob"), result.getString("departmentID"), result.getString("employeePosition"), 
                        result.getString("qualification"), result.getString("status"),
                        result.getString("nationality"),result.getString("identity"),result.getString("postalAddress"),
                        result.getString("physicalAddress"), result.getString("mobilePhone"),
                        result.getString("officePhone"), result.getString("gender"), 
                        result.getString("email"), result.getString("enrollDate"), result.getString("picture"));
            }
            return new Employee();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Employee();
        }
    }
    
    /**
     * 
     * @return 
     */
    public ObservableList<String> getEmployeeNameList(){
        ObservableList<String> employees = FXCollections.observableArrayList();
        try{
            String query = "SELECT `lName`, `fName`, `mName` FROM `employee`";
            
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
    
    public ObservableList<String> getEmployeeIDList(){
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
    
    
    
    /**
     * Get students details
     * @param showAll
     * @param departmentID
     * @return 
     */
    public ObservableList<Employee> getEmployeeList(boolean showAll, String departmentID){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try{
            String query;
            if(departmentID.equalsIgnoreCase("")){
                if(showAll){
                    query = "SELECT `ID`, `employeeID`, `lName`, `fName`, `mName`, `title`, `dob`, `departmentID`, `employeePosition`,"
                        + " `qualification`, `status`, `nationality`, `identity`, `postalAddress`, `physicalAddress`,"
                        + " `picture`, `mobilePhone`, `officePhone`, `email`,`gender`, `enrollDate` FROM `employee`";
                }else{
                
                    query = "SELECT `ID`, `employeeID`, `lName`, `fName`, `mName`, `title`, `dob`, `departmentID`, `employeePosition`,"
                        + " `qualification`, `status`, `nationality`, `identity`, `employeePosition`, `postalAddress`, `physicalAddress`,"
                        + " `picture`, `mobilePhone`, `officePhone`, `email`,`gender`, `enrollDate` "
                            + "FROM `employee` "
                            + "WHERE `status` = 'PRESENT'";
                }
                
            }else{
                if(showAll){
                    query = "SELECT `ID`, `employeeID`, `lName`, `fName`, `mName`, `title`, `dob`, `departmentID`, `employeePosition`,"
                        + " `qualification`, `status`, `nationality`, `identity`, `postalAddress`, `physicalAddress`,"
                        + " `picture`, `mobilePhone`, `officePhone`, `email`,`gender`, `enrollDate` "
                        + " FROM `employee` WHERE `departmentID`='"+departmentID+"'";
                }else{
                
                    query = "SELECT `ID`, `employeeID`, `lName`, `fName`, `mName`, `title`, `dob`, `departmentID`, `employeePosition`,"
                        + " `qualification`, `status`, `nationality`, `identity`, `employeePosition`, `postalAddress`, `physicalAddress`,"
                        + " `picture`, `mobilePhone`, `officePhone`, `email`,`gender`, `enrollDate` "
                            + " FROM `employee` "
                            + " WHERE `status` = 'PRESENT' AND `departmentID`='"+departmentID+"'";
                }
                
            }
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                employees.add(new Employee(result.getString("ID"),result.getString("employeeID"), result.getString("fName"),
                        result.getString("lName"), result.getString("mName"), result.getString("title"),
                        result.getString("dob"), result.getString("departmentID"), result.getString("employeePosition"), 
                        result.getString("qualification"), result.getString("status"),
                        result.getString("nationality"),result.getString("identity"),result.getString("postalAddress"),
                        result.getString("physicalAddress"), result.getString("mobilePhone"),
                        result.getString("officePhone"), result.getString("gender"), 
                        result.getString("email"), result.getString("enrollDate"), result.getString("picture")));
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
    public String updateSchoolDetails(School school){
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
            
            return (STATEMENT.executeUpdate(query) > 0)?"":"Exception occured while trying to add school details";
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return ex.getMessage();
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
     * Get domain schools
     * @param schoolID
     * @return 
     */
    public String getSchoolNameByID(String schoolID){
        try{
            String query = "SELECT `name`  FROM `school` WHERE `id` = '"+schoolID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return (result.getString("name"));
            }
            return "";
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return "";
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
    
    
    
}
