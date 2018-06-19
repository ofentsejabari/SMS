package studentmanagement;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Aid {
    SimpleStringProperty id, socialWelfareID, name, cooperation, description;

    public Aid() {
        id = new SimpleStringProperty("");
        socialWelfareID = new SimpleStringProperty("");
        name = new SimpleStringProperty("");
        cooperation = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
    }
    
    public Aid(String id, String swID, String name, String cooperation, String description){
        
        this.id = new SimpleStringProperty(id);
        this.socialWelfareID = new SimpleStringProperty(swID);
        this.name = new SimpleStringProperty(name);
        this.cooperation = new SimpleStringProperty(cooperation);
        this.description = new SimpleStringProperty(description);
    }
    
    
    public String getId(){return id.get();}
    public void setId(String id){this.id.set(id);}
    
    public String getSocialWelfareID(){return socialWelfareID.get();}
    public void setSocialWelfare(String id){socialWelfareID.set(id);}
    
    public String getName(){return name.get();}
    public void setName(String titl){name.set(titl);}
    
    public String getCooperation(){return cooperation.get();}
    public void setCooperation(String titl){cooperation.set(titl);}
    
    public String getDescription(){return description.get();}
    public void setDescription(String titl){description.set(titl);}

}
