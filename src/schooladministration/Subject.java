package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Subject {
    SimpleStringProperty subjectID, departmentID, description, type, schoolID;

    public Subject() {
        subjectID = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        departmentID = new SimpleStringProperty("");
        type = new SimpleStringProperty("");
        schoolID = new SimpleStringProperty("");
    }
    
    public Subject(String subjectID, String departmentID, String description, String type, String schoolID){
        
        this.subjectID = new SimpleStringProperty(subjectID);
        this.departmentID = new SimpleStringProperty(departmentID);
        this.description = new SimpleStringProperty(description);
        this.type = new SimpleStringProperty(type);
        this.schoolID = new SimpleStringProperty(schoolID);
    }

    public String getSubjectID(){return subjectID.get();}
    public void setSubjectID(String id){subjectID.set(id);}
    
    public String getDescription(){return description.get();}
    public void setDescrption(String id){description.set(id);}
    
    public String getDepartmentID(){return departmentID.get();}
    public void setDepartmentID(String id){departmentID.set(id);}
    
    public String getType(){return type.get();}
    public void setType(String id){type.set(id);}
    
    public String getSchoolID(){return schoolID.get();}
    public void setSchoolID(String mnam){schoolID.set(mnam);}
}
