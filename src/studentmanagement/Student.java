package studentmanagement;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Student {
    SimpleStringProperty id, studentID, firstName, lastName, middleName, dob,
            classID, gender, lastSchoolAttended, pslegrade, citizenship, email, specialNeed,
            parentID, physicalAddress, postalAddress, schoolID, enrollDate, picture, socialWalfare, 
            club, sportCode;

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
        
        specialNeed = new SimpleStringProperty("");
        socialWalfare = new SimpleStringProperty("");
        
        postalAddress = new SimpleStringProperty("");
        physicalAddress = new SimpleStringProperty("");
        
        schoolID = new SimpleStringProperty("");
        enrollDate = new SimpleStringProperty("");
        parentID = new SimpleStringProperty("");
        picture = new SimpleStringProperty("");
    }
    
    public Student(String id, String studentID, String firstName, String lastName, String middleName, 
            String dob, String classID, String gender, String lastSchool, String psl, String citizenship,
            String email, String specialNeed, String socialWelfare, String postalAddress, String physicalAddress,
            String parentID, String enrollDate, String club, String sportCode, String picture, String schoolID){
        
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
        this.specialNeed = new SimpleStringProperty(specialNeed);
        this.socialWalfare = new SimpleStringProperty(socialWelfare);
        
        this.postalAddress = new SimpleStringProperty(postalAddress);
        this.physicalAddress = new SimpleStringProperty(physicalAddress);
        this.parentID = new SimpleStringProperty(parentID);
        
        this.schoolID = new SimpleStringProperty(schoolID);
        this.enrollDate = new SimpleStringProperty(enrollDate);
        this.picture = new SimpleStringProperty(picture);
        
        this.club = new SimpleStringProperty(club);
        this.sportCode = new SimpleStringProperty(sportCode);
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
    
    public String getParentID(){return parentID.get();}
    public void setParentID(String lnam){parentID.set(lnam);}
        
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
    
    public String getSocialWalfare(){    return socialWalfare.get();}
    public void   setSocialWalfare(String mnam){socialWalfare.set(mnam);}
    
    public String getSchoolID(){    return schoolID.get();}
    public void   setSchoolID(String mnam){schoolID.set(mnam);}
    
    public String getSpecialNeed(){    return specialNeed.get();}
    public void   setSpecialNeed(String mnam){specialNeed.set(mnam);}
    
    public String getEnrollDate(){    return enrollDate.get();}
    public void   setEnrollDate(String mnam){enrollDate.set(mnam);}
    
    public String getPicture(){    return picture.get();}
    public void   setPicture(String mnam){picture.set(mnam);}
    
    
    public String getClub(){    return club.get();}
    public void   setClub(String mnam){club.set(mnam);}
    
    public String getSportCode(){    return sportCode.get();}
    public void   setSportCode(String mnam){sportCode.set(mnam);}
    
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
