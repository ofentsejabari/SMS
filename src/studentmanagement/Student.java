package studentmanagement;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Student {
    SimpleStringProperty enrollID, studentID, firstName, lastName, middleName, dob, classID, gender, language,
            placeOfBirth, nationality, email, phone, healthIssues, physicalAddress, schoolID, enrollDate,
            graduateDate, statusChangedDate, status, statusChangeReason, picture, oncampus;

    public Student() {
        enrollID = new SimpleStringProperty("");
        studentID = new SimpleStringProperty("");
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        middleName = new SimpleStringProperty("");
        dob = new SimpleStringProperty("");
        classID = new SimpleStringProperty("");
        gender = new SimpleStringProperty("");
        language = new SimpleStringProperty("");
        placeOfBirth = new SimpleStringProperty("");
        nationality = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        phone = new SimpleStringProperty("");
        healthIssues = new SimpleStringProperty("");
        physicalAddress = new SimpleStringProperty("");
        oncampus = new SimpleStringProperty();
        schoolID = new SimpleStringProperty("");
        enrollDate = new SimpleStringProperty("");
        graduateDate = new SimpleStringProperty("");
        statusChangedDate = new SimpleStringProperty("");
        status = new SimpleStringProperty("");
        statusChangeReason = new SimpleStringProperty("");
        picture = new SimpleStringProperty("");
    }
    
    public Student(String enrollID, String studentID, String firstName, String lastName, String middleName, 
            String dob, String classID, String gender, String language, String placeOfBirth, String nationality,
            String email, String phone, String postalAddress, String physicalAddress, String parentID,
            String schoolID, String enrollDate, String graduateDate, String statusChangedDate,String status,
            String statusChangeReason, String picture, String oncampus){
        
        this.enrollID = new SimpleStringProperty(enrollID);
        this.studentID = new SimpleStringProperty(studentID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        this.dob = new SimpleStringProperty(dob);
        this.classID = new SimpleStringProperty(classID);
        this.gender = new SimpleStringProperty(gender);
        this.language = new SimpleStringProperty(language);
        this.placeOfBirth = new SimpleStringProperty(placeOfBirth);
        this.nationality = new SimpleStringProperty(nationality);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.healthIssues = new SimpleStringProperty(postalAddress);
        this.physicalAddress = new SimpleStringProperty(physicalAddress);
        this.oncampus = new SimpleStringProperty(oncampus);
        this.schoolID = new SimpleStringProperty(schoolID);
        this.enrollDate = new SimpleStringProperty(enrollDate);
        this.graduateDate = new SimpleStringProperty(graduateDate);
        this.statusChangedDate = new SimpleStringProperty(statusChangedDate);
        this.status = new SimpleStringProperty(status);
        this.statusChangeReason = new SimpleStringProperty(statusChangeReason);
        this.picture = new SimpleStringProperty(picture);
    }
    
    
    
    public String getEnrollID(){return enrollID.get();}
    public void setEnrollID(String id){enrollID.set(id);}
    
    public String getStudentID(){return studentID.get();}
    public void setStudentID(String id){studentID.set(id);}
    
    public String getFirstName(){return firstName.get();}
    public void setFirstName(String titl){firstName.set(titl);}
    
    public String getLastName(){return lastName.get();}
    public void setLastName(String fnam){lastName.set(fnam);}
    
    public String getDob(){return dob.get();}
    public void setDob(String lnam){dob.set(lnam);}
    
    public String getMiddleName(){return middleName.get();}
    public void setMiddleName(String mnam){middleName.set(mnam);}
    
    public String getClassID(){return classID.get();}
    public void setClassID(String mnam){classID.set(mnam);}
    
    public String getGender(){  return gender.get();}
    public void   setGender(String id){gender.set(id);}
    
    public String getLanguage(){    return language.get();}
    public void   setLanguage(String titl){language.set(titl);}
    
    public String getPlaceOfBirth(){    return placeOfBirth.get();}
    public void   setPlaceOfBirth(String fnam){placeOfBirth.set(fnam);}
    
    public String getEmail(){    return email.get();}
    public void   setEmail(String lnam){email.set(lnam);}
    
    public String getHealthIssues(){    return healthIssues.get();}
    public void   setHealthIssues(String mnam){healthIssues.set(mnam);}
    
    public String getPhysicalAddress(){    return physicalAddress.get();}
    public void   setPhysicalAddress(String mnam){physicalAddress.set(mnam);}
    
    public String getOncampus(){    return oncampus.get();}
    public void   setOncampus(String mnam){oncampus.set(mnam);}
    
    public String getSchoolID(){    return schoolID.get();}
    public void   setSchoolID(String mnam){schoolID.set(mnam);}
    
    public String getPhone(){    return phone.get();}
    public void   setPhone(String mnam){phone.set(mnam);}
    
    public String getNationality(){    return nationality.get();}
    public void   setNationality(String mnam){nationality.set(mnam);}
    
    public String getEnrollDate(){    return enrollDate.get();}
    public void   setEnrollDate(String mnam){enrollDate.set(mnam);}
    
    public String getGraduateDate(){    return graduateDate.get();}
    public void   setGraduateDate(String mnam){graduateDate.set(mnam);}
    
    public String getStatusChangedDate(){    return statusChangedDate.get();}
    public void   setStatusChangeDate(String mnam){statusChangedDate.set(mnam);}
    
    public String getStatusChangeReason(){    return statusChangeReason.get();}
    public void   setStatusChangeReason(String mnam){statusChangeReason.set(mnam);}
    
    public String getStatus(){    return status.get();}
    public void   setStatus(String mnam){status.set(mnam);}
    
    public String getPicture(){    return picture.get();}
    public void   setPicture(String mnam){picture.set(mnam);}
    
    public String getFullName(){
        return firstName.get()+" "+lastName.get();
    }
    
    public String getFullNameWithInitials(){
        try {
            if (middleName.get() != null && !middleName.get().equalsIgnoreCase("")) {
                return firstName.get().substring(0, 1) + " ." + middleName.get().substring(0, 1) + ". " + lastName.get();
            }
            return firstName.get().substring(0, 1) + ". " + lastName.get();
        } catch (Exception e) {
            return "";
        }
    }
    
}
