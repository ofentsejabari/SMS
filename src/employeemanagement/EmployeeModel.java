package employeemanagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ofentse
 */
public class EmployeeModel {
    SimpleStringProperty ID, employeeID, firstName, lastName, middleName, title, dob,
                        designation, qualification, nationality, identity, postalAddress,
                        physicalAddress, cellPhone,telephone, email, gender, 
                        enrollDate, profilePicture;
    ObservableList<NextOfKin> nextOfKin;

    public EmployeeModel() {
        ID = new SimpleStringProperty("");
        employeeID = new SimpleStringProperty("");
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        middleName = new SimpleStringProperty("");
        title = new SimpleStringProperty("");
        dob = new SimpleStringProperty("");
        designation = new SimpleStringProperty("");
        qualification = new SimpleStringProperty("");
        nationality = new SimpleStringProperty("");
        identity = new SimpleStringProperty("");
        postalAddress = new SimpleStringProperty("");
        physicalAddress = new SimpleStringProperty("");
        cellPhone = new SimpleStringProperty("");
        telephone = new SimpleStringProperty("");
        gender = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        enrollDate = new SimpleStringProperty("");
        profilePicture = new SimpleStringProperty("");
        nextOfKin = FXCollections.observableArrayList();
    }
    
    public EmployeeModel(String ID, String employeeID,String fName, String lName, String mName, String title, String dob,
            String jobTitle,String qualification,String nationality, String omang, 
            String postalAddress, String physicalAddress, String mphone,String ophone, String gender, String  email,
            String enrollDate, String profilePicture){
        
       this.ID = new SimpleStringProperty(ID);
       this.employeeID = new SimpleStringProperty(employeeID);
       this.firstName = new SimpleStringProperty(fName);
       this.lastName = new SimpleStringProperty(lName);
       this.middleName = new SimpleStringProperty(mName);
       this.title = new SimpleStringProperty(title);
       this.dob = new SimpleStringProperty(dob);
       this.designation = new SimpleStringProperty(jobTitle);
       this.qualification = new SimpleStringProperty(qualification);
       this.nationality = new SimpleStringProperty(nationality);
       this.identity = new SimpleStringProperty(omang);
       this.postalAddress = new SimpleStringProperty(postalAddress);
       this.physicalAddress = new SimpleStringProperty(physicalAddress);
       this.cellPhone = new SimpleStringProperty(mphone);
       this.telephone = new SimpleStringProperty(ophone);
       this.gender = new SimpleStringProperty(gender);
       this.email = new SimpleStringProperty(email);
       this.enrollDate = new SimpleStringProperty(enrollDate);
       this.profilePicture = new SimpleStringProperty(profilePicture);
    }
    public ObservableList<NextOfKin> getNextOfKin(){return nextOfKin;}
    public void setNextOfKin(ObservableList<NextOfKin> id){nextOfKin=id;}
    
    public String getEmployeeID(){return employeeID.get();}
    public void setEmployeeID(String id){employeeID.set(id);}
    
    public String getID(){return ID.get();}
    public void setID(String id){ID.set(id);}
    public String getFirstName(){return firstName.get();}
    public void setFirstName(String titl){firstName.set(titl);}
    
    public String getLastName(){return lastName.get();}
    public void setLastName(String fnam){lastName.set(fnam);}
    
    public String getDob(){return dob.get();}
    public void setDob(String lnam){dob.set(lnam);}
    
    public String getMiddleName(){return middleName.get();}
    public void setMiddleName(String mnam){middleName.set(mnam);}
    
    public String getTitle(){  return title.get();}
    public void   setTitle(String id){title.set(id);}
    
    public String getDesignation(){    return designation.get();}
    public void   setDesignation(String titl){designation.set(titl);}
    
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
    
    public String getCellPhone(){    return cellPhone.get();}
    public void   setCellPhone(String mnam){cellPhone.set(mnam);}
    
    public String getTelephone(){    return telephone.get();}
    public void   setTelephone(String mnam){telephone.set(mnam);}
    
    public String getNationality(){    return nationality.get();}
    public void   setNationality(String mnam){nationality.set(mnam);}
    
    public String getEnrollDate(){    return enrollDate.get();}
    public void   setEnrollDate(String mnam){enrollDate.set(mnam);}
    
    
    public String getProfilePsicture(){    return profilePicture.get();}
    public void   setProfilePicture(String mnam){profilePicture.set(mnam);}
    
    public String getFullName(){
        if(middleName.get()!= null && !middleName.get().equalsIgnoreCase("")){
            return firstName.get()+" "+lastName.get();
        }
        return firstName.get()+" "+lastName.get();
    }
    
    public String getFullNameWithInitials(){
        try {
            if (middleName.get() != null && !middleName.get().equalsIgnoreCase("")) {
                return title.get()+" "+firstName.get().substring(0, 1) + " ." + middleName.get().substring(0, 1) + ". " + lastName.get();
            }
            return title.get()+" "+firstName.get();
        } catch (Exception e) {
            return "";
        }
    }
}
