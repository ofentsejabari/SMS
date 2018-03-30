package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Stream {
    SimpleStringProperty streamID, description;

    public Stream() {
        streamID = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
    }
    
    public Stream(String clusterID, String description){
        
        this.streamID = new SimpleStringProperty(clusterID);
        this.description = new SimpleStringProperty(description);
    }
    
    public String getStreamID(){return streamID.get();}
    public void setStreamID(String id){streamID.set(id);}
    
    public String getDescription(){return description.get();}
    public void setDescrption(String id){description.set(id);}
    
}
