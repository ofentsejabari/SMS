package employeemanagement;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Employee {
    SimpleStringProperty ID, employeeID, fName, lName, mName, title, dob, departmentID,
                        position, qualification, status, nationality, identity, postalAddress,
                        physicalAddress, mobilePhone,ophone, email, gender, 
                        enrollDate, profilePicture;

    public Employee() {
        ID = new SimpleStringProperty("");
        employeeID = new SimpleStringProperty("");
        fName = new SimpleStringProperty("");
        lName = new SimpleStringProperty("");
        mName = new SimpleStringProperty("");
        title = new SimpleStringProperty("");
        dob = new SimpleStringProperty("");
        departmentID = new SimpleStringProperty("");//
        position = new SimpleStringProperty("");
        qualification = new SimpleStringProperty("");
        status = new SimpleStringProperty("");
        nationality = new SimpleStringProperty("");
        identity = new SimpleStringProperty("");
        postalAddress = new SimpleStringProperty("");
        physicalAddress = new SimpleStringProperty("");
        mobilePhone = new SimpleStringProperty("");
        ophone = new SimpleStringProperty("");
        gender = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        enrollDate = new SimpleStringProperty("");
        profilePicture = new SimpleStringProperty("");
    }
    
    public Employee(String ID, String employeeID,String fName, String lName, String mName, String title, String dob,
            String departmentID, String jobTitle,String qualification, String status, String nationality, String omang, 
            String postalAddress, String physicalAddress, String mphone,String ophone, String gender, String  email,
            String enrollDate, String profilePicture){
        
       this.ID = new SimpleStringProperty(ID);
       this.employeeID = new SimpleStringProperty(employeeID);
       this.fName = new SimpleStringProperty(fName);
       this.lName = new SimpleStringProperty(lName);
       this.mName = new SimpleStringProperty(mName);
       this.title = new SimpleStringProperty(title);
       this.dob = new SimpleStringProperty(dob);
       this.departmentID = new SimpleStringProperty(departmentID);
       this.position = new SimpleStringProperty(jobTitle);
       this.qualification = new SimpleStringProperty(qualification);
       this.status = new SimpleStringProperty(status);
       this.nationality = new SimpleStringProperty(nationality);
       this.identity = new SimpleStringProperty(omang);
       this.postalAddress = new SimpleStringProperty(postalAddress);
       this.physicalAddress = new SimpleStringProperty(physicalAddress);
       this.mobilePhone = new SimpleStringProperty(mphone);
       this.ophone = new SimpleStringProperty(ophone);
       this.gender = new SimpleStringProperty(gender);
       this.email = new SimpleStringProperty(email);
       this.enrollDate = new SimpleStringProperty(enrollDate);
       this.profilePicture = new SimpleStringProperty(profilePicture);
    }
    
    
    public String getEmployeeID(){return employeeID.get();}
    public void setEmployeeID(String id){employeeID.set(id);}
    
    public String getID(){return ID.get();}
    public void setID(String id){ID.set(id);}
    
    public String getStatus(){return status.get();}
    public void setStatus(String id){status.set(id);}
    
    public String getFirstName(){return fName.get();}
    public void setFirstName(String titl){fName.set(titl);}
    
    public String getLastName(){return lName.get();}
    public void setLastName(String fnam){lName.set(fnam);}
    
    public String getDob(){return dob.get();}
    public void setDob(String lnam){dob.set(lnam);}
    
    public String getMiddleName(){return mName.get();}
    public void setMiddleName(String mnam){mName.set(mnam);}
    
    public String getDepartmentID(){return departmentID.get();}
    public void setDepartmentID(String mnam){departmentID.set(mnam);}
    
    public String getTitle(){  return title.get();}
    public void   setTitle(String id){title.set(id);}
    
    public String getPosition(){    return position.get();}
    public void   setPosition(String titl){position.set(titl);}
    
    public String getQualification(){    return qualification.get();}
    public void   setQualification(String fnam){qualification.set(fnam);}
    
    public String getEmail(){    return email.get();}
    public void   setEmail(String lnam){email.set(lnam);}
    
    public String getIdentity(){    return identity.get();}
    public void   setIdentity(String lnam){identity.set(lnam);}
    
    public String getPostalAddress(){    return postalAddress.get();}
    public void   setPostalAddress(String mnam){postalAddress.set(mnam);}
    
    public String getPhysicalAddress(){    return physicalAddress.get();}
    public void   setPhysicalAddress(String mnam){physicalAddress.set(mnam);}
    
    public String getGender(){    return gender.get();}
    public void   setGender(String mnam){gender.set(mnam);}
    
    public String getMobilePhone(){    return mobilePhone.get();}
    public void   setMobilePhone(String mnam){mobilePhone.set(mnam);}
    
    public String getOfficePhone(){    return ophone.get();}
    public void   setOfficePhone(String mnam){ophone.set(mnam);}
    
    public String getNationality(){    return nationality.get();}
    public void   setNationality(String mnam){nationality.set(mnam);}
    
    public String getEnrollDate(){    return enrollDate.get();}
    public void   setEnrollDate(String mnam){enrollDate.set(mnam);}
    
    
    public String getProfilePicture(){    return profilePicture.get();}
    public void   setProfilePicture(String mnam){profilePicture.set(mnam);}
    
    public String getFullName(){
        if(mName.get()!= null && !mName.get().equalsIgnoreCase("")){
            return fName.get()+" "+lName.get();
        }
        return fName.get()+" "+lName.get();
    }
    
    public String getFullNameWithInitials(){
        try {
            if (mName.get() != null && !mName.get().equalsIgnoreCase("")) {
                return title.get()+" "+fName.get().substring(0, 1) + " ." + mName.get().substring(0, 1) + ". " + lName.get();
            }
            return title.get()+" "+fName.get().substring(0, 1) + ". " + lName.get();
        } catch (Exception e) {
            return "";
        }
    }
}
