
package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.AutoCompleteComboBoxListener;
import entry.SMS;
import static entry.SMS.dbHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import schooladministration.Department;
import static entry.SMS.getGraphics;
import mysqldriver.AdminQuery;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class UpdateDepartmentController implements Initializable {

    @FXML
    private JFXButton btn_close;
    @FXML
    private JFXButton save;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXComboBox<String> hod;
    @FXML
    private BorderPane container;
    @FXML
    private VBox content;
    
    public Department department;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //-- Field validator --
        ValidationSupport vSupport = new ValidationSupport();
        
        //-- Window decoration close button icon and style --
        btn_close.setGraphic(getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
            
        //-- Add content to a bordered pane --
        container.setCenter(SMS.setBorderContainer(content, null));
        
        //-- Set field validator --
        vSupport.registerValidator(name, Validator.createEmptyValidator("Department name required"));
        
        //-- Set auto complete to hod combobox --
        AutoCompleteComboBoxListener.setAutoCompleteValidator(hod);
        new AutoCompleteComboBoxListener(hod);
        
        
        save.setOnAction((ActionEvent event) -> {
            
            if(!vSupport.isInvalid()){
                if(department != null){
                    
                    department.setDepartmentName(name.getText().trim());
                    department.setHod((hod.getValue() == null)? "":
                            dbHandler.getEmployeeByName(hod.getValue().toString()).getEmployeeID());
                    
                    if(AdminQuery.updateDepartment(department, true)){
                        System.out.println("Department Updated");
                    }else{
                        System.out.println("Department failed to update");
                    }
                    
                }else{
                }
            }else{
                
            }
        });
    }  

      
    
    /**
     * Get reference to close button
     * @return 
     */
    public JFXButton getCloseButton(){
        return btn_close;
    }
    
    /**
     * Method is invoked from the UpdateDepartmentStage (JFXDialog) to
     * initialize
     */
    public void updateContent(){
        
        hod.setItems(dbHandler.getDepartmentEmployeeNames(department.getID()));
        
        if(department != null){
            name.setText(department.getDepartmentName());
            hod.setValue(department.getHod());
        }
    }
 
}
