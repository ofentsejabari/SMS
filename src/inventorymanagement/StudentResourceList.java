/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;


import javafx.beans.property.SimpleStringProperty;
import mysqldriver.AdminQuery;
/**
 *
 * @author MOILE
 */
public class StudentResourceList {
    SimpleStringProperty studentID ,studentName,className,resourceNo;

    public StudentResourceList() {
       studentID= new SimpleStringProperty("");
        studentName= new SimpleStringProperty("");
        className =new SimpleStringProperty("");
       resourceNo = new SimpleStringProperty("");
    }
    public StudentResourceList(String studentID,String studentName,String className,String resourceNo){
        
       this.studentID = new SimpleStringProperty(studentID);
       this.studentName = new SimpleStringProperty(studentName);
       this.className = new SimpleStringProperty(className);
       this.resourceNo = new SimpleStringProperty(resourceNo);
    }
    
    public String getStudentID(){return studentID.get();}
    public void setStudentID(String id){studentID.set(id);}
    
    public String getStudentName(){return studentName.get();}
    public void setStudentName(String id){studentName.set(id);}
    
    public String getResourceNo(){return resourceNo.get();}
    public void setResourceNo(String id) { resourceNo.set(id); }
    
    public String getClassName(){return className.get();}
    public void setClassName(String id) { className.set(id); }
    
}

