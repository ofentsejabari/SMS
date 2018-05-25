package mysqldriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author jabari
 * This class use writeObject and readObject to encode
 * custom data format. When the persistent data is unwieldy, it is suitable
 * to store it in a more convenient, condensed format.
 *
 */
public class DBConfig implements Serializable {
    
    //--database server configuration data--
    String db_host,
           db_name,
           db_username,
           db_password;

    public DBConfig(){
        db_host = "192.168.53.73";
        db_name = "sms";
        db_username = "ischool";
        db_password = "ischool";  
    }

    public static void main(String args[]){
        
        serializeObject(new DBConfig());
        DBConfig cnew = deserialiseObject();
  
        System.out.println();
        System.out.println("Printing object properties...");
        System.out.println(cnew.getDBHost());
        System.out.println(cnew.getDBPassword());
        System.out.println(cnew.getDBUserName());
        System.out.println(cnew.getDBName());
        
        cnew.serializeObject(cnew);
        cnew = deserialiseObject();
        
        System.out.println("Printing object properties...");
        System.out.println(cnew.getDBHost());
        System.out.println(cnew.getDBPassword());
        System.out.println(cnew.getDBUserName());
    }

    //--get and set methods--
    public void setDBPassword(String password){
        db_password = password;
    }
    
    public String getDBPassword(){
        return db_password;
    }
    
    public void setDBUserName(String usrname){
        db_username = usrname;
    }
    
    public String getDBUserName(){
        return db_username;
    }

    public void setDBHost(String host){
        db_host = host;
    }
    
    public String getDBHost(){
        return db_host;
    }
    
    public void setDBName(String name){
        db_name = name;
    }
    
    public String getDBName(){
        return db_name;
    }
    
    
    
    
    /***************************************************************************
    * @return 
    */
    public static DBConfig deserialiseObject() {
        DBConfig cnew;
        //--Deserialize in to new class object--
        
        File file = new File("dbconf.dcf");
        if(!file.exists()){
            serializeObject(new DBConfig());
        }
        
        try {
            FileInputStream fi = new FileInputStream("dbconf.dcf");
            ObjectInputStream si = new ObjectInputStream(fi);
            cnew = (DBConfig) si.readObject();
            si.close();
            
            return cnew;
        } catch(IOException | ClassNotFoundException e){
            
            System.out.println("DB configuration file created........");
            //System.exit(1); 
        }
        return null;
    }

    
    
    
   /****************************************************************************
    * Serialize and save an object to a file
    * @param corg 
    */
    public static void serializeObject(DBConfig corg) {
        //--Serialize the original class object--
        try {
            FileOutputStream fo = new FileOutputStream("dbconf.dcf");
            ObjectOutputStream so = new ObjectOutputStream(fo);
            
            so.writeObject(corg);
            so.flush();
            so.close();
            
        } catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

}

