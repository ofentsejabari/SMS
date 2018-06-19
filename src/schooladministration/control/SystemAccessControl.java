package schooladministration.control;

import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import schooladministration.UserDirectory;
import schooladministration.AccessControlRights;

/**
 *
 * @author ofentse
 */
public class SystemAccessControl extends BorderPane{
    
    private Tab rolesTab, usersTab;
    
    public UserDirectory systemUsers;
    public AccessControlRights userRoles;
    
    public SystemAccessControl () {
        
        getStyleClass().add("container");
        
        systemUsers = new UserDirectory();
        userRoles = new AccessControlRights();
        
        usersTab = new Tab("User Directory");
        usersTab.setClosable(false);
        usersTab.setContent(systemUsers);
        usersTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_NETWORK, "icon-secondary", 20));
        
        rolesTab = new Tab("Access Control List");
        rolesTab.setClosable(false);
        rolesTab.setContent(userRoles);
        rolesTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_KEY, "icon-secondary", 20));
        
        JFXTabPane tabPane = new JFXTabPane();
        tabPane.getStyleClass().add("jfx-tab-flatpane");
        tabPane.getTabs().addAll(usersTab, rolesTab);
        
        setCenter(tabPane);
    }   
    
}
