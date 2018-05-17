package entry.control;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import static entry.control.MainUIFXMLController.drawerStack;
import static entry.control.MainUIFXMLController.jFXDrawer;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import static entry.SMS.getIcon;
import schooladministration.SystemConfigDialog;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class NavigationDrawerController implements Initializable {
    
    @FXML
    private Circle profile_picture;

    @FXML
    private JFXButton closeDrawer, exit, inbox;
    
    @FXML
    private JFXButton configure;
        
    private Image imageHolder;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //-- Update profile picture --
        imageHolder = getIcon("user.png").getImage();
        profile_picture.setFill(new ImagePattern(imageHolder));
                
        closeDrawer.setText("");
        closeDrawer.setGraphic(SMS.getGraphics(MaterialDesignIcon.ARROW_LEFT_BOLD, "text-white", 20));
        closeDrawer.setOnAction((ActionEvent event) -> {
            drawerStack.toggle(jFXDrawer);
        });
        
        
        //-- Exit --
        exit.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXIT_TO_APP, "text-gray", 24));
        exit.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });
        
        //-- inbox --
        inbox.setGraphic(SMS.getGraphics(MaterialDesignIcon.MESSAGE_OUTLINE, "text-gray", 18));
        inbox.setOnAction((ActionEvent event) -> {
            
        });
        
        configure.setOnAction((ActionEvent event) -> {
            new SystemConfigDialog();
        });
        
        JFXBadge badge = new JFXBadge(SMS.getGraphics(MaterialDesignIcon.MESSAGE_OUTLINE, "text-gray", 18),
                Pos.TOP_RIGHT);
        badge.getStyleClass().add("icons-badge-green");
        badge.setText("445");
        
        inbox.setGraphic(badge);
    }    
    
}
