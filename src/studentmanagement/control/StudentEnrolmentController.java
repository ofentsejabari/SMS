package studentmanagement.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import studentmanagement.StudentEnrolment;

/**
 *
 * @author ofentse
 */
public class StudentEnrolmentController implements Initializable {

    @FXML
    private Tab studentTab;
    
    public StudentEnrolment enrollUI;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //--
        enrollUI = new StudentEnrolment();
        
        studentTab.setContent(enrollUI);
    }    
  
}
