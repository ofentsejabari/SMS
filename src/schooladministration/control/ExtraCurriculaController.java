package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
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
import mysqldriver.EmployeeQuery;
import schooladministration.ExtraCurriculaActivity;
import schooladministration.ExtraCurriculaMembers;
import schooladministration.UpdateExtraCurriculaActivityDialog;
import schooladministration.AddMemberDialog;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class ExtraCurriculaController implements Initializable {
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btn_export, btn_refresh, btn_edit, btn_add_member;
    @FXML
    private JFXListView<Label> activity_ListView;
    @FXML
    private Label activityName;
    @FXML
    private Label total;
    @FXML
    private Tab membersTab;
    
    public ExtraCurriculaActivity selectedActivity = null;
    
    public int selectedIndex = 0;
    
    public ExtraCurriculaMembers extraCurriculaMembers = null;
    
    //public ExtraCurriculaWorkService eca = new ExtraCurriculaWorkService();

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
        
        btn_add_member.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add_member.setOnAction((ActionEvent event) -> {
            new AddMemberDialog(selectedActivity.getId());
        });
        
        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        btn_edit.setOnAction((ActionEvent event) -> {
            new UpdateExtraCurriculaActivityDialog(selectedActivity);
        });
        
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            updateActivityListView();
        });
        
        activity_ListView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            
            try{
                selectedActivity = AdminQuery.getActivityByName(activity_ListView.getItems().get(newValue.intValue()).getText());
                selectedIndex = newValue.intValue(); 
                
                activityName.setText("COACH | "+EmployeeQuery.getEmployeeByID(selectedActivity.getCoach()).getFullName());
                
                extraCurriculaMembers.ams.restart();
            }catch(Exception ex){}
                
        });
        
                
        membersTab.setContent(extraCurriculaMembers);
        membersTab.setText("Sport/ Club Members");
        
        updateActivityListView();
    }
    
    
    public void updateActivityListView(){
        ObservableList<Label> data = FXCollections.observableArrayList();
            
        ObservableList<String> dt = AdminQuery.getActivityNames();
            
        for(String activ: dt){
            data.add(new Label(activ));
        }
        activity_ListView.setItems(data);
        activity_ListView.getSelectionModel().select(selectedIndex);
    }
    
    
  /*  public class ExtraCurriculaWorker extends Task<ObservableList<Label>> {       
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
   */
}
