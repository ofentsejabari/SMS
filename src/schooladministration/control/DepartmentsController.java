package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableView;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import mysqldriver.AdminQuery;
import schooladministration.UpdateDepartmentDialog;
import schooladministration.Department;
import schooladministration.DepartmentDetails;
import schooladministration.DepartmentSubjects;
import schooladministration.Subject;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class DepartmentsController implements Initializable {

    @FXML
    private JFXButton btn_add, btn_export, btn_refresh;
    @FXML
    private JFXListView<Label> depart_ListView;
    @FXML
    private Label departmentName, totalDepartments;
    @FXML
    private Tab departmentDetailsTab, subjectsTab, subjectTeachersTab;
    
    
    public static Department selectedDepartment = null;
    public static CustomTableView<Subject> table;
    
    DepartmentDetails departmentDetails = null;
    DepartmentSubjects departmentSubjects = null;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        departmentDetails = new DepartmentDetails();
        departmentSubjects = new DepartmentSubjects();
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateDepartmentDialog(null).show();
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            depart_ListView.setItems(updateDepartments());
            depart_ListView.getSelectionModel().select(0);
        });
        
        
        depart_ListView.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
               
            selectedDepartment = AdminQuery.getDepartmentByName(depart_ListView.getItems().get(newValue.intValue()).getText());
                
            departmentName.setText(selectedDepartment.getDepartmentName());
            departmentSubjects.subjectWorkService.restart();
                
        });
        
        
        
        depart_ListView.setItems(updateDepartments());
                
        departmentDetailsTab.setContent(departmentDetails);
        departmentDetailsTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.SERVER_NETWORK, "icon-secondary", 20));
        
        subjectsTab.setContent(departmentSubjects);
        subjectsTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.SERVER_NETWORK, "icon-secondary", 20));
    }

    private ObservableList<Label> updateDepartments() {
        
        ObservableList<Label> data = FXCollections.observableArrayList();
        return data;
    }


}
