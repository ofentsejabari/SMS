package schooladministration.control;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import schooladministration.SystemUsers;
import schooladministration.AccessRights;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class SystemUsersController implements Initializable {
    
    @FXML
    private Tab rolesTab, usersTab;
    
    public SystemUsers systemUsers;
    public AccessRights userRoles;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        systemUsers = new SystemUsers();
        userRoles = new AccessRights();
        
        usersTab.setContent(systemUsers);
        usersTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_NETWORK, "icon-secondary", 20));
        
        rolesTab.setContent(userRoles);
        rolesTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_KEY, "icon-secondary", 20));
        
    }   
    

    
}
