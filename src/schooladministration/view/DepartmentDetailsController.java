package schooladministration.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import schooladministration.Department;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class DepartmentDetailsController implements Initializable {

    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField hod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        btn_edit.setOnAction((ActionEvent event) -> {
            
        });        
    }  
    
    public void updateDetails(Department department){
        name.setText(department.getDepartmentName());
        hod.setText(department.getHod());
    }
    
}
