package studentmanagement.control;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import static entry.SMS.getGraphics;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class StudentProfileController implements Initializable {

    @FXML
    private Tab assessment;

    @FXML
    private VBox background_process;

    @FXML
    private Tab student_details;

    @FXML
    private Circle profile_picture;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton btn_toolbar_close;

    @FXML
    private Label title;

    @FXML
    private Tab finance;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btn_toolbar_close.setGraphic(getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_toolbar_close.getStyleClass().add("window-close");
        
        student_details.setContent(null);
        student_details.setGraphic(SMS.getGraphics(MaterialDesignIcon.CLIPBOARD_ACCOUNT, "icon-secondary", 20));
        
        assessment.setContent(null);
        assessment.setGraphic(SMS.getGraphics(MaterialDesignIcon.LIBRARY_BOOKS, "icon-secondary", 20));
        
        finance.setContent(null);
        finance.setGraphic(SMS.getGraphics(MaterialDesignIcon.LIBRARY_BOOKS, "icon-secondary", 20));
        
    }  
    
    
    /**
     * Get reference to close button --
     * @return 
     */
    public JFXButton getCloseButton(){
        return btn_toolbar_close;
    }
    
}
