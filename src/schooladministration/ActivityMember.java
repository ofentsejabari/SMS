package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class ActivityMember {
    SimpleStringProperty id, memberID, activityID, type;

    public ActivityMember() {
        id = new SimpleStringProperty("");
        activityID = new SimpleStringProperty("");
        memberID = new SimpleStringProperty("");
        type = new SimpleStringProperty("");
    }
    
    public ActivityMember(String id, String memberID, String activityID, String type){
        
        this.id = new SimpleStringProperty(id);
        this.memberID = new SimpleStringProperty(memberID);
        this.activityID = new SimpleStringProperty(activityID);
        this.type = new SimpleStringProperty(type);
    }

    public String getId(){return id.get();}
    public void setId(String id){this.id.set(id);}
    
    public String getActivityID(){return activityID.get();}
    public void setActivityID(String id){activityID.set(id);}
    
    public String getMemberID(){return memberID.get();}
    public void setMemberID(String id){memberID.set(id);}
    
    public String getType(){return type.get();}
    public void setType(String id){type.set(id);}
}
