package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableView;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
import schooladministration.SubjectTeachers;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class DepartmentsController implements Initializable {

    @FXML
    private JFXButton btn_add, btn_export, btn_refresh, btn_edit, btn_delete;
    @FXML
    private JFXListView<Label> depart_ListView;
    @FXML
    private Label departmentName, totalDepartments;
    @FXML
    private Tab departmentDetailsTab, subjectsTab, subjectTeachersTab;
    
    
    public Department selectedDepartment = null;
    
    //-- Selected index in the department listview --
    public int selectedIndex = 0;
    public static CustomTableView<Subject> table;
    
    DepartmentDetails departmentDetails = null;
    DepartmentSubjects departmentSubjects = null;
    SubjectTeachers subjectTeachers = null;
    
    public DepartmentWorkService dws = new DepartmentWorkService();
        
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        departmentName.setText("");
        
        departmentDetails = new DepartmentDetails();
        departmentSubjects = new DepartmentSubjects();
        subjectTeachers = new SubjectTeachers();
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateDepartmentDialog(null).show();
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            dws.restart();
        });
        
        btn_edit.setGraphic(SMS.getGraphics(FontAwesomeIcon.EDIT, "icon-default", 24));
        btn_edit.setOnAction((ActionEvent event) -> {
            new UpdateDepartmentDialog(selectedDepartment);
        });
        
        
        btn_delete.setGraphic(SMS.getGraphics(MaterialDesignIcon.DELETE_FOREVER, "icon-default", 24));
        btn_delete.setOnAction((ActionEvent event) -> {
            //new UpdateDepartmentDialog(selectedDepartment);
        });
        
        depart_ListView.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            
            try{
                selectedDepartment = AdminQuery.getDepartmentByName(depart_ListView.getItems().get(newValue.intValue()).getText());
                selectedIndex = newValue.intValue(); 
                departmentName.setText(selectedDepartment.getDepartmentName());
                
                departmentSubjects.subjectWorkService.restart();
                departmentDetails.dds.restart();
            }catch(Exception ex){}
                
        });
        
                
        departmentDetailsTab.setContent(departmentDetails);
        //departmentDetailsTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.SERVER_NETWORK, "icon-secondary", 20));
        
        subjectsTab.setContent(departmentSubjects);
        //subjectsTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.SERVER_NETWORK, "icon-secondary", 20));
        
        subjectTeachersTab.setContent(subjectTeachers);
        //-- 
        dws.start();
        dws.restart();
    }
    
    
    public class DepartmentListWork extends Task<ObservableList<Label>> {       
        @Override 
        protected ObservableList<Label> call() throws Exception {
            ObservableList<Label> data = FXCollections.observableArrayList();
            
            ObservableList<String> dt = AdminQuery.getDepartmentNames();
            
            for(String dept: dt){
                data.add(new Label(dept));
            }
                        
            Platform.runLater(() -> {
                try {
                    depart_ListView.setItems(data);
                
                    totalDepartments.setText(""+data.size());
                    if(selectedDepartment != null){
                        depart_ListView.getSelectionModel().select(selectedIndex);
                    }
                } catch (Exception e) {
                }
                
            });
            
            return data;
        }
       
    }

    public class DepartmentWorkService extends Service<ObservableList<Label>> {

        @Override
        protected Task createTask() {
            return new DepartmentListWork();
        }
    }
   
}
