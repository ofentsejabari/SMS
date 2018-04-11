package entry.control;

import com.jfoenix.controls.JFXButton;
import static entry.control.MainUIFXMLController.employeeManagement;
import static entry.control.MainUIFXMLController.studentManagement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import static entry.control.MainUIFXMLController.admin;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class SystemModulesController implements Initializable {

    @FXML
    private BorderPane parentContainer;
    @FXML
    private JFXButton btn_student_management;
    @FXML
    private JFXButton btn_inventory_management;
    @FXML
    private JFXButton btn_employee_management;
    @FXML
    private JFXButton btn_library_management;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if(event.getSource()== btn_student_management){
            studentManagement.toFront();
        }else if(event.getSource()== btn_employee_management){
            employeeManagement.toFront();
        }else if(event.getSource()== btn_inventory_management){
            //inventoryManagement.toFront();
        }else if(event.getSource()== btn_library_management){
            admin.toFront();
        }
        
    }
    
}
