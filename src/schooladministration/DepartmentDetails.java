package schooladministration;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author ofentse
 */
public class DepartmentDetails extends BorderPane{

    BorderPane details;
    public DepartmentDetails() {
        
        setPadding(new Insets(10));
        
        try{
            //-- 
            details = FXMLLoader.load(getClass().getResource("/schooladministration/view/departmentDetails.fxml"));
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        setCenter(details);
    }
    
    
    
}
