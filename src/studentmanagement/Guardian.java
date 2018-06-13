package studentmanagement;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Guardian {
    SimpleStringProperty id, studentID, firstName, middleName, lastName, identity, relation, education,
            occupation, telephone, email, cellphone, postalAddress, physicalAddress;

    public Guardian(){
        
        id = new SimpleStringProperty("");
        studentID = new SimpleStringProperty("");
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        middleName = new SimpleStringProperty("");
        identity = new SimpleStringProperty("");
        relation = new SimpleStringProperty("");
        education = new SimpleStringProperty("");
        occupation = new SimpleStringProperty("");
        telephone = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        cellphone = new SimpleStringProperty("");
        postalAddress = new SimpleStringProperty("");
        physicalAddress = new SimpleStringProperty("");
        
    }
    
    public Guardian(String id, String studentID, String firstName, String middleName, String lastName, String identity,
            String relation, String education, String occupation, String email, String cellphone, String telephone, 
             String postalAddress, String physicalAddress){
        
        this.id = new SimpleStringProperty(id);
        //this.parentID = new SimpleStringProperty(parentID);
        this.studentID = new SimpleStringProperty(studentID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        this.identity = new SimpleStringProperty(identity);
        this.relation = new SimpleStringProperty(relation);
        this.education = new SimpleStringProperty(education);
        this.occupation = new SimpleStringProperty(occupation);
        this.telephone = new SimpleStringProperty(telephone);
        this.email = new SimpleStringProperty(email);
        this.cellphone = new SimpleStringProperty(cellphone);
        this.postalAddress = new SimpleStringProperty(postalAddress);
        this.physicalAddress = new SimpleStringProperty(physicalAddress);
        
    }
    
    public String getID(){    return id.get();}
    public void   setID(String mnam){id.set(mnam);}
    
    public String getStudentID(){    return studentID.get();}
    public void   setStudentID(String mnam){studentID.set(mnam);}
    
//    public String getParentID(){    return parentID.get();}
//    public void   setParentID(String mnam){parentID.set(mnam);}
//    
    public String getFirstName(){return firstName.get();}
    public void setFirstName(String titl){firstName.set(titl);}
    
    public String getLastName(){return lastName.get();}
    public void setLastName(String fnam){lastName.set(fnam);}
    
    public String getMiddleName(){return middleName.get();}
    public void setMiddleName(String fnam){middleName.set(fnam);}
    
    public String getIdentity(){return identity.get();}
    public void setIdentity(String lnam){identity.set(lnam);}
        
    public String getRelation(){return relation.get();}
    public void   setRelation(String mnam){relation.set(mnam);}
    
    public String getEducation(){  return education.get();}
    public void   setEducation(String id){education.set(id);}
    
    public String getOccupation(){    return occupation.get();}
    public void   setOccupation(String fnam){occupation.set(fnam);}
    
    public String getEmail(){    return email.get();}
    public void   setEmail(String lnam){email.set(lnam);}
    
    public String getPostalAddress(){    return postalAddress.get();}
    public void   setPostalAddress(String mnam){postalAddress.set(mnam);}
    
    public String getPhysicalAddress(){    return physicalAddress.get();}
    public void   setPhysicalAddress(String mnam){physicalAddress.set(mnam);}
    
    public String getCellphone(){    return cellphone.get();}
    public void   setCellphone(String mnam){cellphone.set(mnam);}
    
    public String getTelephone(){    return telephone.get();}
    public void   setTelephone(String mnam){telephone.set(mnam);}
    
    public String getFullName(){
       
        return firstName.get()+" "+lastName.get();
    }
    
    public String getFullNameWithInitials(){
       
        return firstName.get().substring(0, 1)+". "+lastName.get();
    }

}
