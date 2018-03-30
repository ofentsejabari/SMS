package messanger.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class ListItemController implements Initializable {

    @FXML
    private Circle profile_picture;
    @FXML
    private Label name;

    
    public void setText(String txt){
        name.setText(txt);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
