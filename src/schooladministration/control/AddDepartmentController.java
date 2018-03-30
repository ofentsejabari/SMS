
package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entry.AutoCompleteComboBoxListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class AddDepartmentController implements Initializable {

    @FXML
    private JFXButton btn_close;
    @FXML
    private JFXButton save;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXComboBox<String> hod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //-- Initialize hod with a list of employee names
        hod.setItems(FXCollections.observableArrayList("Ofentse Jabari", "Karabo Stern"));
        
        new AutoCompleteComboBoxListener(hod);
    }  
    
    public void setName(String nm){name.setText(nm);}
    public void setHOD(String nm){hod.setValue(nm);}
    public void setEventHandler(EventHandler event){btn_close.setOnAction(event);}
 
}
