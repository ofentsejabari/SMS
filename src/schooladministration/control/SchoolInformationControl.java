package schooladministration.control;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import schooladministration.SchoolBasicInfo;
import schooladministration.SchoolBasicInfo;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class SchoolInformationControl implements Initializable {
    
    @FXML
    private Tab rolesTab, usersTab;
    
    public SchoolBasicInfo basicInfo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        basicInfo = new SchoolBasicInfo();
        
        usersTab.setContent(basicInfo);
        usersTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_NETWORK, "icon-secondary", 20));
        
        rolesTab.setContent(null);
        rolesTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.MAP_MARKER_RADIUS, "icon-secondary", 20));
        
    }   
    
}
