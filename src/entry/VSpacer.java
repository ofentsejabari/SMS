package entry;

import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author jabari
 */
public class VSpacer extends Region{

    public VSpacer() {
        VBox.setVgrow(this, Priority.ALWAYS);
    }  
}