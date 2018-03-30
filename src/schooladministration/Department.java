package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Department {
    SimpleStringProperty ID, departmentName, hod;

    public Department() {
        ID = new SimpleStringProperty("");
        departmentName = new SimpleStringProperty("");
        hod = new SimpleStringProperty("");
    }
    
    public Department(String ID, String departmentName, String hod){
        
       this.ID = new SimpleStringProperty(ID);
       this.departmentName = new SimpleStringProperty(departmentName);
       this.hod = new SimpleStringProperty(hod);
    }
    
    
    
    public String getDepartmentName(){return departmentName.get();}
    public void setDepartmentName(String id){departmentName.set(id);}
    
    public String getID(){return ID.get();}
    public void setID(String id){ID.set(id);}
    
    public String getHod(){return hod.get();}
    public void setHod(String id){hod.set(id);}

}
