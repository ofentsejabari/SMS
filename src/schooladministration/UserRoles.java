package schooladministration;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ofentse
 */
public class UserRoles {
    SimpleStringProperty ID, description, priviledges;
    
    public static ObservableList<String> DEFAULT_SYSTEM_ROLES = FXCollections.observableArrayList("Manage Students",
                "Approve Leave","General Settings", "Manage Student Records", "Approve Assessments",
                "Create Assessment", "Manage Timetables", "Manage Employees", "Manage Leaves",
                "Financial Control", "Reporting", "Manage Users");

    public UserRoles() {
        ID = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        priviledges = new SimpleStringProperty("");
    }
    
    public UserRoles(String ID, String description, String priviledges){
        
       this.ID = new SimpleStringProperty(ID);
       this.description = new SimpleStringProperty(description);
       this.priviledges = new SimpleStringProperty(priviledges);
    }
    
    public String getDescription(){return description.get();}
    public void setDescription(String id){description.set(id);}
    
    public String getID(){return ID.get();}
    public void setID(String id){ID.set(id);}
    
    public String getPriviledges(){return priviledges.get();}
    public void setPriviledges(String id){priviledges.set(id);}
    
    
    public boolean hasManageUsersPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Manage Users")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasReportingPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Reporting")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasManageLeavesPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Manage Leaves")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasFinancialControlPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Financial Control")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasGeneralSettingsPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("General Settings")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasManageStudentRecordsPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Manage Student Records")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasManageEmployeesPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Manage Employees")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasManageTimetablesPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Manage Timetables")){
                return true;
            }
        }
        return false;
    }
    
    
    public boolean hasCreateAssessmentPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Create Assessment")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasApproveAssessmentPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Approve Assessments")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasManageStudentsPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Manage Students")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasApproveLeavePriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Approve Leave")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasManageLibraryPriviledge(){
        String []prv = getPriviledges().split(",");
        for(String str: prv){
            if(str.trim().equalsIgnoreCase("Library Management")){
                return true;
            }
        }
        return false;
    }
  
}
