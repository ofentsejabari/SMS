package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class School {
    SimpleStringProperty schoolID, schoolName, tel, fax, website, email, 
            postalAddress, physicalAddress, showName, logo;

    public School() {
        schoolID = new SimpleStringProperty("");
        schoolName = new SimpleStringProperty("");
        tel = new SimpleStringProperty("");
        fax = new SimpleStringProperty("");
        website = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        postalAddress = new SimpleStringProperty("");
        physicalAddress = new SimpleStringProperty("");
        logo = new SimpleStringProperty("");
        showName = new SimpleStringProperty("");
    }
    
    public School(String schoolID, String schoolName, String tel, String fax, 
                  String website, String email, String postalAddress, 
                  String physicalAddress, String logo, String showName){
        
        this.schoolID = new SimpleStringProperty(schoolID);
        this.schoolName = new SimpleStringProperty(schoolName);
        this.tel = new SimpleStringProperty(tel);
        this.fax = new SimpleStringProperty(fax);
        this.website = new SimpleStringProperty(website);
        this.email = new SimpleStringProperty(email);
        this.postalAddress = new SimpleStringProperty(postalAddress);
        this.physicalAddress = new SimpleStringProperty(physicalAddress);
        this.logo = new SimpleStringProperty(logo);
        this.showName = new SimpleStringProperty(showName);
        
    }
    
    public String getSchoolID(){return schoolID.get();}
    public void setSchoolID(String id){schoolID.set(id);}
    
    public String getSchoolName(){return schoolName.get();}
    public void setSchoolName(String titl){schoolName.set(titl);}
    
    public String getFax(){return fax.get();}
    public void setFax(String lnam){fax.set(lnam);}
    
    public String getTel(){return tel.get();}
    public void setTel(String mnam){tel.set(mnam);}
    
    public String getWebsite(){return website.get();}
    public void setWebsite(String titl){website.set(titl);}
    
    public String getEmail(){return email.get();}
    public void setEmail(String fnam){email.set(fnam);}
    
    public String getLogo(){return logo.get();}
    public void setLogo(String fnam){logo.set(fnam);}
    
    public String getPostalAddress(){return postalAddress.get();}
    public void setPostalAddress(String lnam){postalAddress.set(lnam);}
    
    public String getPhysicalAddress(){return physicalAddress.get();}
    public void setPhysicalAddress(String mnam){physicalAddress.set(mnam);}

    public String getShowName(){return showName.get();}
    public void setShowName(String lnam){showName.set(lnam);}
    
    
}
