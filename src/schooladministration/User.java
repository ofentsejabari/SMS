package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class User {
    SimpleStringProperty ID, userID, password, lastLogin, updatePassword, status;

    public User() {
        ID = new SimpleStringProperty("");
        userID = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
        lastLogin = new SimpleStringProperty("");
        updatePassword = new SimpleStringProperty("");
        status = new SimpleStringProperty("");
    }
    
    public User(String ID, String userID, String password, String lastLogin, String updatePassword,
            String status){
        
        this.ID = new SimpleStringProperty(ID);
        this.userID = new SimpleStringProperty(userID);
        this.password = new SimpleStringProperty(password);
        this.lastLogin = new SimpleStringProperty(lastLogin);
        this.updatePassword = new SimpleStringProperty(updatePassword);
        this.status = new SimpleStringProperty(status);
    }
    
    
    
    public String getID(){return ID.get();}
    public void   setID(String id){ID.set(id);}
    
    public String getUserID(){return userID.get();}
    public void   setUserID(String id){userID.set(id);}
    
    public String getPassword(){return password.get();}
    public void   setPassword(String titl){password.set(titl);}
    
    public String getLastLogin(){return lastLogin.get();}
    public void   setLastLogin(String fnam){lastLogin.set(fnam);}
    
    public String getUpdatePassword(){    return updatePassword.get();}
    public void   setUpdatePassword(String mnam){updatePassword.set(mnam);}
    
    public String getStatus(){    return status.get();}
    public void   setStatus(String mnam){status.set(mnam);}
    
}
