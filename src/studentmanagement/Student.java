package studentmanagement;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Student {
    SimpleStringProperty id, studentID, firstName, lastName, middleName, dob,
            classID, gender, lastSchoolAttended, pslegrade, citizenship, email,
            physicalAddress, postalAddress, schoolID, enrollDate, picture, status;

    public Student() {
        id = new SimpleStringProperty("");
        studentID = new SimpleStringProperty("");
        
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        middleName = new SimpleStringProperty("");
        
        dob = new SimpleStringProperty("");
        classID = new SimpleStringProperty("");
        gender = new SimpleStringProperty("");
        
        lastSchoolAttended = new SimpleStringProperty("");
        pslegrade = new SimpleStringProperty("");
        citizenship = new SimpleStringProperty("");
        
        email = new SimpleStringProperty("");
        
        postalAddress = new SimpleStringProperty("");
        physicalAddress = new SimpleStringProperty("");
        
        schoolID = new SimpleStringProperty("");
        enrollDate = new SimpleStringProperty("");
        picture = new SimpleStringProperty("");
        
        this.status = new SimpleStringProperty("");
    }
    
    public Student(String id, String studentID, String firstName, String lastName, String middleName, 
            String dob, String classID, String gender, String lastSchool, String psl, String citizenship,
            String email, String postalAddress, String physicalAddress, String enrollDate,
            String status, String picture, String schoolID){
        
        this.id = new SimpleStringProperty(id);
        this.studentID = new SimpleStringProperty(studentID);
        
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        
        this.dob = new SimpleStringProperty(dob);
        this.classID = new SimpleStringProperty(classID);
        this.gender = new SimpleStringProperty(gender);
        
        this.lastSchoolAttended = new SimpleStringProperty(lastSchool);
        this.pslegrade = new SimpleStringProperty(psl);
        this.citizenship = new SimpleStringProperty(citizenship);
        
        this.email = new SimpleStringProperty(email);        
        this.postalAddress = new SimpleStringProperty(postalAddress);
        this.physicalAddress = new SimpleStringProperty(physicalAddress);
        
        this.schoolID = new SimpleStringProperty(schoolID);
        this.enrollDate = new SimpleStringProperty(enrollDate);
        this.picture = new SimpleStringProperty(picture);
        
        this.status = new SimpleStringProperty(status);
    }
    
    
    
    public String getId(){return id.get();}
    public void setId(String id){this.id.set(id);}
    
    public String getStudentID(){return studentID.get();}
    public void setStudentID(String id){studentID.set(id);}
    
    public String getFirstName(){return firstName.get();}
    public void setFirstName(String titl){firstName.set(titl);}
    
    public String getLastName(){return lastName.get();}
    public void setLastName(String fnam){lastName.set(fnam);}
    
    public String getMiddleName(){return middleName.get();}
    public void setMiddleName(String mnam){middleName.set(mnam);}
    
    public String getDob(){return dob.get();}
    public void setDob(String lnam){dob.set(lnam);}
        
    public String getClassID(){return classID.get();}
    public void setClassID(String mnam){classID.set(mnam);}
    
    public String getGender(){  return gender.get();}
    public void   setGender(String id){gender.set(id);}
    
    
    public String getLastSchoolAttended(){  return lastSchoolAttended.get();}
    public void   setLastSchoolAttended(String titl){lastSchoolAttended.set(titl);}
    
    public String getPslegrade(){   return pslegrade.get();}
    public void   setPslegrade(String fnam){pslegrade.set(fnam);}
    
    public String getCitizenship(){    return citizenship.get();}
    public void   setCitizenship(String mnam){citizenship.set(mnam);}
    
    
    public String getEmail(){    return email.get();}
    public void   setEmail(String lnam){email.set(lnam);}
    
    public String getPostalAddress(){    return postalAddress.get();}
    public void   setPostalAddress(String mnam){postalAddress.set(mnam);}
    
    public String getPhysicalAddress(){    return physicalAddress.get();}
    public void   setPhysicalAddress(String mnam){physicalAddress.set(mnam);}
    
    public String getSchoolID(){    return schoolID.get();}
    public void   setSchoolID(String mnam){schoolID.set(mnam);}
    
    public String getEnrollDate(){    return enrollDate.get();}
    public void   setEnrollDate(String mnam){enrollDate.set(mnam);}
    
    public String getPicture(){    return picture.get();}
    public void   setPicture(String mnam){picture.set(mnam);}
    
    public String getStatus(){    return status.get();}
    public void   setStatus(String mnam){status.set(mnam);}
    
    public String getFullName(){
        return firstName.get()+" "+lastName.get();
    }
    
    public String getFullNameWithInitials(){
        try {
            if (middleName.get() != null && !middleName.get().equalsIgnoreCase("")) {
                return firstName.get().substring(0, 1) + " ." + middleName.get().substring(0, 1) +
                       ". " + lastName.get();
            }
            return firstName.get().substring(0, 1) + ". " + lastName.get();
        } catch (Exception e) {
            return "";
        }
    }
    
}
