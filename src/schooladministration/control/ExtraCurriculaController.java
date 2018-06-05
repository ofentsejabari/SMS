package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
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
import schooladministration.ExtraCurriculaActivity;
import schooladministration.ExtraCurriculaMembers;
import schooladministration.UpdateExtraCurriculaActivityDialog;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class ExtraCurriculaController implements Initializable {

    @FXML
    private JFXButton btn_add, btn_export, btn_refresh;//, btn_edit, btn_delete;
    @FXML
    private JFXListView<Label> activity_ListView;
    @FXML
    private Label activityName, total;
    @FXML
    private Tab membersTab; //I, subjectsTab;
    
    
    public ExtraCurriculaActivity selectedActivity = null;
    
    public int selectedIndex = 0;
    
    ExtraCurriculaMembers extraCurriculaMembers = null;
    
    public ExtraCurriculaWorkService eca = new ExtraCurriculaWorkService();
        
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        activityName.setText("");
        total.setText("");
        
        extraCurriculaMembers = new ExtraCurriculaMembers();
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateExtraCurriculaActivityDialog(null);
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            eca.restart();
        });
        
//        btn_edit.setGraphic(SMS.getGraphics(FontAwesomeIcon.EDIT, "icon-default", 24));
//        btn_edit.setOnAction((ActionEvent event) -> {
//            new UpdateExtraCurriculaActivityDialog(selectedActivity);
//        });
//        
//        
//        btn_delete.setGraphic(SMS.getGraphics(MaterialDesignIcon.DELETE_FOREVER, "icon-default", 24));
//        btn_delete.setOnAction((ActionEvent event) -> {
//            //new UpdateDepartmentDialog(selectedDepartment);
//        });
        
        activity_ListView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            
            try{
                selectedActivity = AdminQuery.getActivityByName(activity_ListView.getItems().get(newValue.intValue()).getText());
                selectedIndex = newValue.intValue(); 
                
                activityName.setText("COACH | "+SMS.dbHandler.getEmployeeByID(selectedActivity.getCoach()).getFullName());
                
                extraCurriculaMembers.ams.restart();
            }catch(Exception ex){}
                
        });
        
                
        membersTab.setContent(extraCurriculaMembers);
        //membersTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.SERVER_NETWORK, "icon-secondary", 20));
        membersTab.setText("Sport/ Club Members");
        
        //subjectsTab.setContent(null);
        //subjectsTab.setDisable(true);
        //subjectsTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.SERVER_NETWORK, "icon-secondary", 20));
        
        //-- 
        eca.start();
        eca.restart();
    }
    
    
    public class ExtraCurriculaWorker extends Task<ObservableList<Label>> {       
        @Override 
        protected ObservableList<Label> call() throws Exception {
            ObservableList<Label> data = FXCollections.observableArrayList();
            
            ObservableList<String> dt = AdminQuery.getActivityNames();
            
            for(String activ: dt){
                data.add(new Label(activ));
            }
                        
            Platform.runLater(() -> {
                try {
                    activity_ListView.setItems(data);
                    total.setText(""+data.size());                    
                    if(selectedActivity != null){
                        activity_ListView.getSelectionModel().select(selectedIndex);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            });
            
            return data;
        }
       
    }

    public class ExtraCurriculaWorkService extends Service<ObservableList<Label>> {

        @Override
        protected Task createTask() {
            return new ExtraCurriculaWorker();
        }
    }
   
}
