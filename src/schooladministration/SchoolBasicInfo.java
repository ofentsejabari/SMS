package schooladministration;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author ofentse
 */
public class SchoolBasicInfo extends BorderPane{
    
    BorderPane details;
    public SchoolBasicInfo() {
        
        setPadding(new Insets(10));
        
        try{
            //-- 
            details = FXMLLoader.load(getClass().getResource("/schooladministration/view/basicInfo.fxml"));
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        setCenter(details);
    }
    
   
}
