package mysqldriver;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static mysqldriver.MySQLHander.STATEMENT;
import schooladministration.Department;
import schooladministration.ExtraCurriculaActivity;
import schooladministration.GradeScheme;
import schooladministration.Stream;
import schooladministration.House;
import schooladministration.ISchoolClass;
import schooladministration.ActivityMember;
import schooladministration.Subject;
import schooladministration.UserRoles;

/**
 *
 * @author JABARI
 */
public class AdminQuery {
    
    /**
     * 
     * @param all
     * @param type
     * @return 
     */
    public static ObservableList<String> getSubjectNameList(boolean all, int type){
        ObservableList<String> terms = FXCollections.observableArrayList();
        try{
            String query;
            
            if(all){
                query = "SELECT `description`"
                         + "FROM `subject`";
            }else{
                query = "SELECT `description`"
                         + "FROM `subject` WHERE `type` = '"+type+"'";
            }
            
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
    public static Subject getSubjectByID(String id){
        try{
            String query = "SELECT `subjectID`, `departmentID`, `description`, `type`, `schoolID`"
                         + "FROM `subject` WHERE `subjectID`='"+id+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Subject(result.getString("subjectID"),result.getString("departmentID"),
                        result.getString("description"), result.getString("type"), result.getString("schoolID"));
            }
            return new Subject();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new Subject();
        }
    }
    
    /**
     * 
     * @return 
     */
    public static ObservableList<Subject> getSubjectList(){
        ObservableList<Subject> terms = FXCollections.observableArrayList();
        try{
            String query = "SELECT `subjectID`, `departmentID`, `description`, `type`, `schoolID`"
                         + "FROM `subject`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                terms.add(new Subject(result.getString("subjectID"),result.getString("departmentID"),
                        result.getString("description"),result.getString("type"), result.getString("schoolID")));
            }
            return terms;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return terms;
        }
    }
    
    /**
     * @param departmentID
     * @return 
     */
    public static ObservableList<Subject> getSubjectListFor(String departmentID){
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        try{
            String query = "SELECT `subjectID`, `departmentID`, `description`, `type`, `schoolID`"
                         + "FROM `subject` WHERE `departmentID` = '"+departmentID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                subjects.add(new Subject(result.getString("subjectID"),result.getString("departmentID"),
                        result.getString("description"),result.getString("type"), result.getString("schoolID")));
            }
            return subjects;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return subjects;
        }
    }
    
    /**
     * 
     * @param subject
     * @return 
     */
    public static Subject getSubjectByName(String subject){
        try{
            String query = "SELECT `subjectID`, `departmentID`, `description`, `type`, `schoolID`"
                         + "FROM `subject` WHERE `description`='"+subject+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Subject(result.getString("subjectID"),result.getString("departmentID"),
                        result.getString("description"),result.getString("type"), result.getString("schoolID"));
            }
            return null;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return null;
        }
    }
    
    /**
     * 
     * @param subject
     * @param update
     * @return 
     */
    public static String updateSubject(Subject subject, boolean update){
        
        try{
            String insertQuery = "INSERT INTO `subject` (`subjectID`, `departmentID`, `description`, `type`, `schoolID`)"
                    + " VALUES ('"+subject.getSubjectID()+"', '"+subject.getDepartmentID()+"','"+subject.getDescription()+"',"
                    + "'"+subject.getType()+"','"+subject.getSchoolID()+"')";
            
            
            String updateQuery = "UPDATE `subject` SET `description`='"+subject.getDescription()+"', "
                    + "`departmentID` = '"+subject.getDepartmentID()+"',`type` = '"+subject.getType()+"' "
                    + " WHERE `subjectID`= '"+subject.getSubjectID()+"'";
            
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
    
    
    public static boolean deleteSubject(String subjectID){
    
        try{
            String updateQuery = "DELETE FROM `subject` WHERE `subjectID`= '"+subjectID+"'";
            return STATEMENT.executeUpdate(updateQuery) > 0;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return false;
        }
    }
    
    
    
    
    public static ObservableList<UserRoles> getUserRoles(){
        ObservableList<UserRoles> roles = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `description`, `priviledges`"
                          + " FROM `userrole`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                roles.add(new UserRoles(result.getString("id"),result.getString("description"), result.getString("priviledges")));
            }
            return roles;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return roles;
        }
    }
    
    public ObservableList<String> getUserRoleNames(){
        ObservableList<String> leave = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `description`"
                          + " FROM `userrole`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                leave.add(result.getString("description"));
            }
            return leave;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return leave;
        }
    }

    
    /**
     * 
     * @param region
     * @param update
     * @return 
     *
    public static boolean updateRegion(SchoolRegion region, boolean update){

        try{
            String query;
            if(!update){
                query = "INSERT INTO `region` (`id`, `name`, `headID`)"
                        + " VALUES ('0', '"+region.getName()+"', '"+region.getRegionHead()+"')";
                
            }else{
                query = "UPDATE `region` SET `name`='"+region.getName()+"', "
                      + "`headID`='"+region.getRegionHead()+"',"
                      + " WHERE `id`= '"+region.getRegionID()+"'";
                
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
     * @param regionName
     * @return 
     *
    public static SchoolRegion getRegionByName(String regionName){
        try{
            String query = "SELECT `id`, `name`, `headID`"
                         + "FROM `region` WHERE `name`='"+regionName+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new SchoolRegion(result.getString("id"),result.getString("name"),
                        result.getString("headID"));
            }
            return null;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return null;
        }
    }
    
     /**
     * 
     * @param regionID
     * @return 
     *
    public static SchoolRegion getRegionByID(String regionID){
        try{
            String query = "SELECT `id`, `name`, `headID`"
                         + "FROM `region` WHERE `id`='"+regionID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new SchoolRegion(result.getString("id"),result.getString("name"),
                        result.getString("headID"));
            }
            return null;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return null;
        }
    }
    
    /**
     * 
     * @param region
     * @return 
     *
    public static boolean isRegionExists(SchoolRegion region){
        try{
            return STATEMENT.executeQuery("SELECT * FROM `region` "
                            + " WHERE `name`= '"+region.getName()+"'").first();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     * 
     * @param school
     * @return 
     *
    public static boolean isSchoolExists(School school){
        try{
            return STATEMENT.executeQuery("SELECT * FROM `school` "
                            + " WHERE `name`= '"+school.getSchoolName()+"' AND `domain` = '"+school.getDomain()+"'").first();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }


    /**
     * 
     * @param cls
     * @param update
     * @return 
     */
    public static boolean updateClass(ISchoolClass cls, boolean update){

        try{
            String query;
            if(!update){
                query = "INSERT INTO `class` (`classID`, `className`, `classTeacher`, `house`, `stream`, `schoolID`)"
                        + " VALUES ('"+cls.getClassID()+"', '"+cls.getName()+"', '"+cls.getClassTeacherID()+"',"
                        + " '"+cls.getHouse()+"', '"+cls.getStreamID()+"', '"+cls.getSchoolID()+"')";
                STATEMENT.addBatch("INSERT INTO `timetable` (`id`, `classID`, `time`, `monday`, `tuesday`, `wednesday`, `thursday`, `friday`,"
                    + " `saturday`, `sunday`) "
                        + " VALUES (NULL, '"+cls.getClassID()+"', '07:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '08:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '09:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '10:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '11:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '12:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '13:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '14:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '15:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '16:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '17:00', '', '', '', '', '', '', ''),"
                        + " (NULL, '"+cls.getClassID()+"', '18:00', '', '', '', '', '', '', '');");
                STATEMENT.addBatch(query);
                STATEMENT.executeBatch();
                return true;

            }else{
                query = "UPDATE `class` SET `className`='"+cls.getName()+"', `classTeacher`='"+cls.getClassTeacherID()+"',"
                        + " `stream`='"+cls.getStreamID()+"' , `house`='"+cls.getHouse()+"'"
                    + "WHERE `classID`= '"+cls.getClassID()+"'";
                return STATEMENT.executeUpdate(query) > 0;
            }
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return false;
        }
    }
    
    public static boolean isClassExists(ISchoolClass cls){
        try{
            return STATEMENT.executeQuery("SELECT * FROM `class` WHERE `name`= '"+cls.getName()+"'").first();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return false;
        }
    }
    
    /**********************************DEPARTMENTS*****************************/
    public static boolean updateDepartment(Department department, boolean update){
        
        try{
            String query;
            if(!update){
                query = "INSERT INTO `department` (`id`, `departmentName`, `hod`)"
                        + " VALUES ('0', '"+department.getDepartmentName()+"', '"+department.getHod()+"')";
                
                return STATEMENT.executeUpdate(query) > 0;
                
            }else{
                query = "UPDATE `department` SET `departmentName`='"+department.getDepartmentName()+"',"
                        + "`hod`='"+department.getHod()+"'"
                        + " WHERE `id`= '"+department.getID()+"'";
                
                return STATEMENT.executeUpdate(query) > 0;
            }
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return false;
        }
    }
    
    public static Department getDepartmentByID(String id){
        try{
            String query = "SELECT `id`, `departmentName`, `hod`"
                          + " FROM `department` WHERE `id`= '"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            if(result.next()){
                return new Department(result.getString("id"), result.getString("departmentName"),
                        result.getString("hod"));
            }
            return new Department();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new Department();
        }
    }
    
    public static Department getDepartmentByName(String id){
        try{
            String query = "SELECT `id`, `departmentName`, `hod`"
                          + " FROM `department` WHERE `departmentName`= '"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Department(result.getString("id"), result.getString("departmentName"), result.getString("hod"));
            }
            return new Department();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new Department();
        }
    }
    
    public static ObservableList<String> getDepartmentNames(){
        ObservableList<String> departmentNames = FXCollections.observableArrayList();
        try{
            String query = "SELECT `departmentName`"
                          + " FROM `department`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                departmentNames.add((result.getString("departmentName")));
            }
            return departmentNames;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return departmentNames;
        }
    }
    
    
    
    /**
     * 
     * @return 
     */
    public static ObservableList<Department> getDepartments(){
        ObservableList<Department> departments = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `departmentName`, `hod`"
                          +" FROM `department`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                departments.add(new Department(result.getString("id"),
                                               result.getString("departmentName"),
                                               result.getString("hod")));
            }
            return departments;
        } 
        catch(Exception ex){
             
             
             return departments;
        }
    }
    
    
    
    
    
    
    
    
    
    //************************ Extra curricula Activities **********************
    
    public static boolean addActivityMember(ActivityMember activity){
        
        try{
            String  query = "INSERT INTO `ec_activity_members` (`id`, `memberID`, `ec_activityID`, `type`)"
                        + " VALUES ('0', '"+activity.getMemberID()+"', '"+activity.getActivityID()+"',"
                        + " '"+activity.getType()+"')";
                
            return STATEMENT.executeUpdate(query) > 0;
             
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
    public static boolean isActivityMemberExist(ActivityMember member){
        
        try{
            String  query = " SELECT `memberID`, `ec_activityID` "
                          + " FROM `ec_activity_members`"
                          + " WHERE `memberID` = '"+member.getMemberID()+"' "
                          + " AND `ec_activityID` = '"+member.getActivityID()+"'";
            
            return STATEMENT.executeQuery(query).first();
             
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
    public static boolean deleteActivityMember(ActivityMember activity){
        
        try{
            String  query = "DELETE FROM `ec_activity_members` "
                          + " WHERE `id` = '"+activity.getId()+"'";
                
            return STATEMENT.executeUpdate(query) > 0;
             
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
   
    
    public static ActivityMember getActivityMemberByID(String id){
        
        try{
            String query = " SELECT `id`, `memberID`, `ec_activityID`, `type`"
                          +" FROM `ec_activity_members` WHERE `id`= '"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            if(result.next()){
                return new ActivityMember(result.getString("id"),
                                               result.getString("memberID"),
                                               result.getString("ec_activityID"),
                                               result.getString("type"));
            }
            return new ActivityMember();
            
        }catch(Exception ex){
            
            System.out.println(ex.getMessage());
            return new ActivityMember();
        }
        
    }
    
    
    /**
     * 
     * @param activityID
     * @return 
     */
    public static ObservableList<ActivityMember> getExtraCurriculaActivitiesMembers(String activityID){
        ObservableList<ActivityMember> activities = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `memberID`, `ec_activityID`, `type`"
                          +" FROM `ec_activity_members` WHERE `ec_activityID` = '"+activityID+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                activities.add(new ActivityMember(result.getString("id"),
                                               result.getString("memberID"),
                                               result.getString("ec_activityID"),
                                               result.getString("type")));
            }
            return activities;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return activities;
        }
    }
    
    
    
    
    
    
    
    
    
     /************************ Extra curricula Activities *********************/
    
    public static boolean updateActivity(ExtraCurriculaActivity activity, boolean update){
        
        try{
            String query;
            if(!update){
                query = "INSERT INTO `ec_activities` (`id`, `name`, `coach`, `type`)"
                        + " VALUES ('0', '"+activity.getName()+"', '"+activity.getCoach()+"',"
                        + " '"+activity.getType()+"')";
                
                return STATEMENT.executeUpdate(query) > 0;
                
            }else{
                query = "UPDATE `ec_activities` SET `name`='"+activity.getName()+"',"
                        + "`coach` = '"+activity.getCoach()+"', `type` = '"+activity.getType()+"'"
                        + " WHERE `id`= '"+activity.getId()+"'";
                
                return STATEMENT.executeUpdate(query) > 0;
            }
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return false;
        }
    }
    
    public static ExtraCurriculaActivity getActitityByID(String id){
        try{
            String query = "SELECT `id`, `name`, `coach`, `type`"
                          + " FROM `ec_activities` WHERE `id`= '"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            if(result.next()){
                return new ExtraCurriculaActivity(result.getString("id"), result.getString("name"),
                        result.getString("coach"), result.getString("type"));
            }
            return new ExtraCurriculaActivity();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new ExtraCurriculaActivity();
        }
    }
    
    
    public static ExtraCurriculaActivity getActivityByName(String name){
        try{
            String query = "SELECT `id`, `name`, `coach`, `type`"
                          + " FROM `ec_activities` WHERE `name`= '"+name+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            if(result.next()){
                return new ExtraCurriculaActivity(result.getString("id"), result.getString("name"),
                        result.getString("coach"), result.getString("type"));
            }
            return new ExtraCurriculaActivity();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new ExtraCurriculaActivity();
        }
    }
    
    public static ObservableList<String> getActivityNames(){
        ObservableList<String> departmentNames = FXCollections.observableArrayList();
        try{
            String query = "SELECT `name`"
                          + " FROM `ec_activities`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                departmentNames.add((result.getString("name")));
            }
            return departmentNames;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return departmentNames;
        }
    }
    
    
    
    /**
     * 
     * @return 
     */
    public static ObservableList<ExtraCurriculaActivity> getExtraCurriculaActivities(){
        ObservableList<ExtraCurriculaActivity> activities = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `name`, `hod`"
                          +" FROM `ec_activities`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                activities.add(new ExtraCurriculaActivity(result.getString("id"),
                                               result.getString("name"),
                                               result.getString("coach"),
                                               result.getString("type")));
            }
            return activities;
        } 
        catch(Exception ex){
             
             return activities;
        }
    }
    
    
    
    
     /********************************School Houses****************************/
    public static boolean updateHouse(House category, boolean update){
        
        try{
            String query;
            if(!update){
                query = "INSERT INTO `house` (`id`, `houseName`, `hoh`)"
                        + " VALUES ('0', '"+category.getHouseName()+"', '"+category.getHOH()+"')";
                
                return STATEMENT.executeUpdate(query) > 0;
                
            }else{
                query = "UPDATE `house` SET `houseName`='"+category.getHouseName()+"',"
                        + "`hoh`='"+category.getHOH()+"'"
                        + " WHERE `id`= '"+category.getID()+"'";
                
                return STATEMENT.executeUpdate(query) > 0;
            }
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return false;
        }
    }
    
    public static House getHouseByID(String id){
        try{
            String query = "SELECT `id`, `houseName`, `hoh`"
                          + " FROM `house` WHERE `id`= '"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            if(result.next()){
                return new House(result.getString("id"), result.getString("houseName"),
                        result.getString("hoh"));
            }
            return new House();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new House();
        }
    }
    
    public static House getHouseByName(String id){
        try{
            String query = "SELECT `id`, `houseName`, `hoh`"
                          + " FROM `house` WHERE `houseName`= '"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new House(result.getString("id"), result.getString("houseName"), result.getString("hoh"));
            }
            return new House();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new House();
        }
    }
    
    public static ObservableList<String> getHouseNames(){
        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        try{
            String query = "SELECT `houseName`"
                          + " FROM `house`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                categoryNames.add((result.getString("houseName")));
            }
            return categoryNames;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return categoryNames;
        }
    }
    
    public static ObservableList<House> getHouses(){
        ObservableList<House> categories = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `houseName`, `hoh`"
                          + " FROM `house`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                categories.add(new House(result.getString("id"),result.getString("houseName"),
                                result.getString("hoh")));
            }
            return categories;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return categories;
        }
    }
    
    public static ObservableList<String> getHouseClassNames(String categoryID){
        
        ObservableList<String> classNames = FXCollections.observableArrayList();
        try{
            String query = "SELECT `className`" +
                            " FROM `class`" +
                            " WHERE `house` = '"+categoryID+"'";
            
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                classNames.add(result.getString("name"));
            }
            
            return classNames;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return classNames;
        }
    }
    
    
    public static ObservableList<ISchoolClass> getHouseClassList(String houseID){
        ObservableList<ISchoolClass> ischoolclass = FXCollections.observableArrayList();
        try{
            String query = "SELECT `classID`, `className`, `classTeacher`, `stream`,`house`,`schoolID`"
                         + "FROM `class` WHERE `house` = '"+houseID+"'";
            
            System.out.println(query);
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                ischoolclass.add(new ISchoolClass(result.getString("classID"),result.getString("className"),
                        result.getString("classTeacher"), result.getString("house"),
                        result.getString("stream"),result.getString("schoolID")));
            }
            return ischoolclass;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return ischoolclass;
        }
    }
    
    /**
     * Get classess belonging to this stream under this house
     * @param houseID
     * @param streamID
     * @return 
     */
    public static ObservableList<ISchoolClass> getHouseClassList(String houseID, String streamID){
        ObservableList<ISchoolClass> ischoolclass = FXCollections.observableArrayList();
        try{
            String query = "SELECT `classID`, `className`, `classTeacher`, `stream`,`house`,`schoolID`"
                         + "FROM `class` WHERE `house` = '"+houseID+"' AND `stream` = '"+streamID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                ischoolclass.add(new ISchoolClass(result.getString("classID"),result.getString("className"),
                        result.getString("classTeacher"), result.getString("house"),
                        result.getString("stream"),result.getString("schoolID")));
            }
            return ischoolclass;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return ischoolclass;
        }
    }
    
    /************************************** Stream ***************************/
    public static boolean updateStream(Stream stream, boolean update){
        
        try{
            String query;
            if(!update){
                query = "INSERT INTO `stream` (`id`, `name`)"
                        + " VALUES ('0', '"+stream.getDescription()+"')";
                
                return STATEMENT.executeUpdate(query) > 0;
                
            }else{
                query = "UPDATE `stream` SET `name`='"+stream.getDescription()+"'"
                        + " WHERE `id`= '"+stream.getStreamID()+"'";
                
                return STATEMENT.executeUpdate(query) > 0;
            }
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return false;
        }
    }
    
    
    public static ObservableList<Stream> getStreams(){
        ObservableList<Stream> stream = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `name`"
                          + " FROM `stream`";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                stream.add(new Stream(result.getString("id"),result.getString("name")));
            }
            return stream;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return stream;
        }
    }
    
    public static Stream getStreamByID(String id){
        try{
            String query = "SELECT `id`, `name`"
                         + " FROM `stream` WHERE `id`= '"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Stream(result.getString("id"), result.getString("name"));
            }
            return new Stream();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new Stream();
        }
    }
    
    public static Stream getStreamByName(String name){
        try{
            String query = "SELECT `id`, `name`"
                         + " FROM `stream` WHERE `name`= '"+name+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Stream(result.getString("id"), result.getString("name"));
            }
            return new Stream();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new Stream();
        }
    }
    
    
    public static boolean isStreamExists(Stream cls){
        try{
            return STATEMENT.executeQuery("SELECT * FROM `stream` WHERE `name`= '"+cls.getDescription()+"'").first();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            
            return false;
        }
    }
    
    
    public static ObservableList<String> getRegionNames(){
        ObservableList<String> region = FXCollections.observableArrayList();
        try{
            String query = "SELECT `name` FROM `region` ORDER BY `name` ASC";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                region.add(result.getString("name"));
            }
            return region;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return region;
        }
    }
    
    public static ObservableList<String> getStreamNames(){
        ObservableList<String> streams = FXCollections.observableArrayList();
        try{
            String query = "SELECT `name` FROM `stream` ORDER BY `name` ASC";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                streams.add(result.getString("name"));
            }
            return streams;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return streams;
        }
    }
    
    public static ObservableList<String> getStreamClasses(String streamID){
        ObservableList<String> stream = FXCollections.observableArrayList();
        try{
            String query = "SELECT `name` FROM `class` WHERE `stream` = '"+streamID+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                stream.add(result.getString("name"));
            }
            return stream;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return stream;
        }
    }
    
    
    public static ObservableList<ISchoolClass> getStreamClassList(String streamID){
        ObservableList<ISchoolClass> ischoolclass = FXCollections.observableArrayList();
        try{
            String query = "SELECT `classID`, `className`, `classTeacher`, `stream`,`house`,`schoolID`"
                         + "FROM `class` WHERE `stream` = '"+streamID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                ischoolclass.add(new ISchoolClass(result.getString("classID"),result.getString("className"),
                        result.getString("classTeacher"), result.getString("house"),
                        result.getString("stream"),result.getString("schoolID")));
            }
            return ischoolclass;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return ischoolclass;
        }
    }
    
    public static ObservableList<Subject> getStreamSubjects(String streamID, int type){
        ObservableList<Subject> cluster = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `subject`.`subjectID`, `description`, `streamID`, `departmentID`, `type`, `schoolID`"
                         + " FROM `stream_subjects`, `subject` "
                         + " WHERE `streamID`= '"+streamID+"'"
                         + " AND `subject`.`subjectID` = `stream_subjects`.`subjectID`"
                         + " AND `type` = '"+type+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            System.out.println(query);
            
            while(result.next()){
                cluster.add(new Subject(result.getString("subjectID"), result.getString("departmentID"),
                                        result.getString("description"), result.getString("type"),
                                        result.getString("streamID")));
            }
            return cluster;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return cluster;
        }
    }
    
    
    /*public static ObservableList<Subject> getStreamSubjects(String streamID, int type){
        ObservableList<Subject> cluster = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `subject`.`subjectID`, `description`, `streamID`, `departmentID`, `type`, `schoolID`"
                         + " FROM `stream_subjects`, `subject` "
                         + " WHERE `streamID`= '"+streamID+"'"
                         + " AND `subject`.`subjectID` = `stream_subjects`.`subjectID`"
                         + " AND `type` = '"+type+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            System.out.println(query);
            
            while(result.next()){
                cluster.add(new Subject(result.getString("subjectID"), result.getString("departmentID"),
                                        result.getString("description"), result.getString("type"),
                                        result.getString("streamID")));
            }
            return cluster;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return cluster;
        }
    }*/
   
    
    public static boolean deleteSubjectStream(String streamID){
        ObservableList<String> cluster = FXCollections.observableArrayList();
        try{
            String query = "DELETE FROM `stream_subjects` WHERE `streamID` = '"+streamID+"'";
            
            return STATEMENT.executeUpdate(query) > 0;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean addStreamSubject(ObservableList<String> subjectIDs, String streamID){
        
        try{
            for(String subj: subjectIDs){
                String query = "INSERT INTO `stream_subjects` (`id`, `subjectID`, `streamID`)"
                            + " VALUES ('0', '"+subj+"', '"+streamID+"')";
                STATEMENT.addBatch(query);
            }
                
            STATEMENT.executeBatch();
            return true;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
    }
    
    
    public static ObservableList<String> getStreamSubjectNameList(String streamID, int type){
        ObservableList<String> cluster = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `description`, `streamID`"
                         + " FROM `stream_subjects`, `subject` "
                         + " WHERE `streamID`= '"+streamID+"'"
                         + " AND `subject`.`subjectID` = `stream_subjects`.`subjectID`"
                         + " AND `type` = '"+type+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                cluster.add(result.getString("description"));
            }
            return cluster;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             //
             return cluster;
        }
    }
    
    public static ObservableList<Subject> getOptionalSubjectFor(String studentID){
        ObservableList<Subject> optionalSubjects = FXCollections.observableArrayList();
        
        try{
            String query = "SELECT `description`, `subject`.`subjectID`, `departmentID`, `type`, `schoolID`"
                         + " FROM `student_optional_subjects`, `subject` "
                         + " WHERE `studentID` = '"+studentID+"'"
                         + " AND `student_optional_subjects`.`subjectID` = `subject`.`subjectID`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                optionalSubjects.add(new Subject(result.getString("subjectID"), result.getString("departmentID"), 
                        result.getString("description"), result.getString("type"), result.getString("schoolID")));
            }
            return optionalSubjects;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return optionalSubjects;
        }
    }
    
    public static ObservableList<String> getSubjectsList(int type){
        ObservableList<String> cluster = FXCollections.observableArrayList();
        try{
            String query = "SELECT `description`"
                         + " FROM `subject` "
                         + " WHERE  `type` = '"+type+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                cluster.add(result.getString("description"));
            }
            return cluster;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
            
             return cluster;
        }
    }
    
    
    
    public static ObservableList<Subject> getStreamSubjectsList(String streamID){
        
        ObservableList<Subject> cluster = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `description`, `streamID`, `departmentID`"
                         + " FROM `stream_subjects`, `subject` "
                         + " WHERE `streamID`= '"+streamID+"'"
                         + " AND `subject`.`subjectID` = `stream_subjects`.`subjectID`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                cluster.add(new Subject(result.getString("id"), result.getString("departmentID"),
                        result.getString("description"), "", ""));
            }
            return cluster;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             //
             return cluster;
        }
    }
    
    
    
    public static ObservableList<String> getStreamSubjectIDs(String streamID, int type){
        ObservableList<String> cluster = FXCollections.observableArrayList();
        try{
            String query = " SELECT `subject`.`subjectID`, `description`, `streamID`"
                         + " FROM `stream_subjects`, `subject` "
                         + " WHERE `streamID`= '"+streamID+"'"
                         + " AND `subject`.`subjectID` = `stream_subjects`.`subjectID`"
                         + " AND `type` = '"+type+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            System.out.println(query);
            
            while(result.next()){
                cluster.add(result.getString("subjectID"));
            }
            return cluster;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             //
             return cluster;
        }
    }
    
    /*
    public static boolean addClusterSubject(ObservableList<HouseSubject> csb){
        
        try{
            for(HouseSubject subj: csb){
                String checkExistance = "SELECT * FROM `stream_subjects` "
                        + " WHERE `subjectID`= '"+subj.getSubjectID()+"' "
                        + " AND `clusterID`= '"+subj.getClusterID()+"'";
                
                if(!STATEMENT.executeQuery(checkExistance).first()){
                    
                    String query = "INSERT INTO `stream_subjects` (`id`, `subjectID`, `clusterID`)"
                            + " VALUES ('0', '"+subj.getSubjectID()+"', '"+subj.getClusterID()+"')";
                    STATEMENT.addBatch(query);
                }
            }
                
            STATEMENT.executeBatch();
            return true;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
    }
    
    public static boolean removeClusterSubject(ObservableList<HouseSubject> csb){
        
        try{
            for(HouseSubject subj: csb){
                String query = "DELETE FROM `stream_subjects` "
                            + " WHERE `subjectID` = '"+subj.getSubjectID()+"' "
                            + " AND `clusterID` = '"+subj.getClusterID()+"'";
                STATEMENT.addBatch(query);
            }
                
            STATEMENT.executeBatch();
            return true;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
    }*/
    
    
    
 
     /*-***************************** SUBJECTS TEACHERS ***********************/
    
    public static ObservableList<String> getSubjectTeachersNames(String subjectID){
        
        ObservableList<String> teacherNames = FXCollections.observableArrayList();
        try{
            String query = "SELECT `firstName`, `middleName`, `lastName`" +
                            " FROM `subjectteachers`, `employee`" +
                            " WHERE `subjectID` = '"+subjectID+"'" +
                            " AND `employeeID` = `teacherID`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                teacherNames.add(result.getString("firstName")+" "+result.getString("lastName"));
            }
            
            return teacherNames;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return teacherNames;
        }
    }
    
    /*
    public static ObservableList<SubjectTeacher> getSubjectTeachers(String subjectID){
        
        ObservableList<SubjectTeacher> teacherNames = FXCollections.observableArrayList();
        try{
            String query = "SELECT `subjectsTeachers`.`id`, `subjectID`, `fname`, `mName`, `lName`" +
                            " FROM `subjectsTeachers`, `employee`" +
                            " WHERE `subjectID` = '"+subjectID+"'" +
                            " AND `employeeID` = `teacherID`";
            
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                teacherNames.add(new SubjectTeacher(result.getString("id"), result.getString("subjectID"), 
                        result.getString("fname")+" "+result.getString("lname")));
            }
            
            return teacherNames;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return teacherNames;
        }
    }*
    
    public static boolean addSubjectTeachers(ObservableList<SubjectTeacher> subjectTeachers){
        
        try{
            for(SubjectTeacher subjTeacher: subjectTeachers){
                String checkExistance = "SELECT * FROM `subjectsTeachers` "
                        + " WHERE `teacherID`= '"+subjTeacher.getTeacherID()+"' "
                        + " AND `subjectID`= '"+subjTeacher.getSubjectID()+"'";
                
                if(!STATEMENT.executeQuery(checkExistance).first()){
                    
                    String query = "INSERT INTO `subjectsTeachers` (`id`, `teacherID`, `subjectID`)"
                            + " VALUES ('0', '"+subjTeacher.getTeacherID()+"', '"+subjTeacher.getSubjectID()+"')";
                    System.out.println(query);
                    STATEMENT.addBatch(query);
                }
            }
                
            STATEMENT.executeBatch();
            return true;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
    }
    
    public static boolean removeSubjectTeachers(ObservableList<SubjectTeacher> subjectsTeachers){
        
        try{
            for(SubjectTeacher teacher: subjectsTeachers){
                String query = "DELETE FROM `subjectsTeachers` "
                            + " WHERE `subjectID` = '"+teacher.getSubjectID()+"' "
                            + " AND `teacherID` = '"+teacher.getTeacherID()+"'";
                STATEMENT.addBatch(query);
            }
                
            STATEMENT.executeBatch();
            return true;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
    }
    
    /*-***************************** SUBJECTS TEACHERS ***********************/
    
    public static ObservableList<String> getDepartmentSubjectNames(String departmentID){
        
        ObservableList<String> subjects = FXCollections.observableArrayList();
        try{
            String query = "SELECT `description`" +
                            " FROM `subject`" +
                            " WHERE `departmentID` = '"+departmentID+"'";
            
            //System.out.println(query);
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                subjects.add(result.getString("description"));
            }
            
            return subjects;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return subjects;
        }
    }
   
    
     /**************************************************************************
     * 
     * @return 
     */
    public static ObservableList<ISchoolClass> getClassList(){
        ObservableList<ISchoolClass> ischoolclass = FXCollections.observableArrayList();
        try{
            String query = "SELECT `classID`, `className`, `classTeacher`, `house`, `stream`,`schoolID`"
                         + "FROM `class`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                ischoolclass.add(new ISchoolClass(result.getString("classID"),result.getString("className"),
                        result.getString("classTeacher"), result.getString("house"),
                        result.getString("stream"),result.getString("schoolID")));
            }
            return ischoolclass;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return ischoolclass;
        }
    }
    
    /**
     * 
     * @param descr
     * @return 
     */
    public static ISchoolClass getClassByName(String descr){
        
        try{
            String query = "SELECT `classID`, `className`, `classTeacher`, `house`, `stream`,`schoolID`"
                         + "FROM `class` WHERE `className` = '"+descr+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new ISchoolClass(result.getString("classID"),result.getString("className"),
                        result.getString("classTeacher"), result.getString("house"),
                        result.getString("stream"),result.getString("schoolID"));
            }
            return new ISchoolClass();
            
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new ISchoolClass();
        }
    }
    
    
     /**
     * 
     * @param id
     * @return 
     */
    public static ISchoolClass getClassByID(String id){
        
        try{
            String query = "SELECT `classID`, `className`, `classTeacher`, `house`, `stream`, `schoolID`"
                         + " FROM `class` WHERE `classID` = '"+id+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new ISchoolClass(result.getString("classID"),result.getString("className"),
                        result.getString("classTeacher"), result.getString("house"),
                        result.getString("stream"),result.getString("schoolID"));
            }
            return new ISchoolClass();
            
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return new ISchoolClass();
        }
    }
    
    
   /* 
    public static ISchoolClass getMyClass(String employeeID){
        
        try{
            String query = "SELECT `classID`, `name`, `classTeacher`, `cluster`, `category`, `schoolID`"
                         + "FROM `class` WHERE `classTeacher` = '"+employeeID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                new ISchoolClass(result.getString("classID"),result.getString("name"),
                        result.getString("classTeacher"), result.getString("category"),
                        result.getString("cluster"),result.getString("schoolID"));
            }
            return new ISchoolClass();
            
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             return new ISchoolClass();
        }
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public static boolean removeClass(String id){
        
        try{
            String query = "DELETE FROM `class` WHERE `classID` = '"+id+"'";
            STATEMENT.addBatch(query);
            STATEMENT.addBatch("DELETE FROM `timetable` WHERE `classID` = '"+id+"'");
            
            STATEMENT.executeBatch();
            return true;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return false;
        }
    }
    
    /**
     * 
     * @return 
     */
    public static ObservableList<String> getClassNames(){
        ObservableList<String> classNames = FXCollections.observableArrayList();
        try{
            String query = "SELECT `className` FROM `class` ORDER BY `className` ASC";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                classNames.add(result.getString("className"));
            }
            return classNames;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             
             return classNames;
        }
    }
    
    
    
    public static ObservableList<GradeScheme> getGrades(){
        ObservableList<GradeScheme> grades = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `symbol`, `lowerBound`, `upperBound`, `points`"
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
                            + " `upperBound`='"+grade.getUpperBound()+"',"
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
    
}
