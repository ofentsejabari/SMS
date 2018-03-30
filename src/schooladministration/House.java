package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class House {
    SimpleStringProperty ID, houseName, HOH;

    public House() {
        ID = new SimpleStringProperty("");
        houseName = new SimpleStringProperty("");
        HOH = new SimpleStringProperty("");
    }
    
    public House(String ID, String categoryName, String hoh){
        
       this.ID = new SimpleStringProperty(ID);
       this.HOH = new SimpleStringProperty(hoh);
       this.houseName = new SimpleStringProperty(categoryName);
    }
    
    
    public String getID(){return ID.get();}
    public void setID(String id){ID.set(id);}
    
    public String getHOH(){return HOH.get();}
    public void setHOH(String id){HOH.set(id);}
    
    public String getHouseName(){return houseName.get();}
    public void setHouseName(String id){houseName.set(id);}

}
