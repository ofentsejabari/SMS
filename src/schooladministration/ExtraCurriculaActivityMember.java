
package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class ExtraCurriculaActivityMember {
    SimpleStringProperty id, memberID, ecActivityID, 
            type; //-- student or teacher, other

    public ExtraCurriculaActivityMember(String id, String memberID, String activityID, String type) {
        this.id = new SimpleStringProperty(id);
        this.memberID = new SimpleStringProperty(memberID);
        this.ecActivityID = new SimpleStringProperty(activityID);
        this.type = new SimpleStringProperty(type);
    }

    public ExtraCurriculaActivityMember() {
        this.id = new SimpleStringProperty("");
        this.memberID = new SimpleStringProperty("");
        this.ecActivityID = new SimpleStringProperty("");
        this.type = new SimpleStringProperty("");
    }

    public String getId() {
        return id.get();
    }

    public String getMemberID() {
        return memberID.get();
    }

    public String getECActivityID() {
        return ecActivityID.get();
    }

    public String getType() {
        return type.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }

    public void setMemberID(String name) {
        this.memberID.set(name);
    }

    public void setECActivityID(String coach) {
        this.ecActivityID.set(coach);
    }

    public void setType(String type) {
        this.type.set(type);
    }
    
    
    
    
    
}
