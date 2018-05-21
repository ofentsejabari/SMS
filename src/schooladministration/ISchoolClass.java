package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class ISchoolClass {
    
    SimpleStringProperty classID, name, classTeacherID, house, stream, schoolID;

    public ISchoolClass() {
        classID = new SimpleStringProperty("");
        name = new SimpleStringProperty("");
        stream = new SimpleStringProperty("");
        house = new SimpleStringProperty("");
        classTeacherID = new SimpleStringProperty("");
        schoolID = new SimpleStringProperty("");
    }
    
    public ISchoolClass(String classID, String name,String teacherID,String category, String cluster, String schoolID){
        
        this.classID = new SimpleStringProperty(classID);
        this.name = new SimpleStringProperty(name);
        this.stream = new SimpleStringProperty(cluster);
        this.house = new SimpleStringProperty(category);
        this.classTeacherID = new SimpleStringProperty(teacherID);
        this.schoolID = new SimpleStringProperty(schoolID);
    }

    public String getClassID(){return classID.get();}
    public void setClassID(String id){classID.set(id);}
    
    public String getName(){return name.get();}
    public void setName(String id){name.set(id);}
    
    public String getStream(){return stream.get();}
    public void setStream(String id){stream.set(id);}
    
    public String getHouse(){return house.get();}
    public void setHouse(String id){house.set(id);}
    
    public String getClassTeacherID(){return classTeacherID.get();}
    public void setClassTeacherID(String fnam){classTeacherID.set(fnam);}
    
    public String getSchoolID(){return schoolID.get();}
    public void setSchoolID(String mnam){schoolID.set(mnam);}
}
