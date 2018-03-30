package entry;



import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author jabari
 */
public class HSpacer extends Region{

     public HSpacer() {
        HBox.setHgrow(this, Priority.ALWAYS);
            
    }
    
    
}