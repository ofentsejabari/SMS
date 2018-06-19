
package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.Notification;
import entry.SMS;
import entry.ToolTip;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;
import schooladministration.StreamClassesList;
import schooladministration.Stream;
import schooladministration.StreamGrading;
import schooladministration.StreamSubjectList;
import schooladministration.UpdateStreamDialog;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class StreamClassesController implements Initializable {

    @FXML
    private JFXButton btn_add, btn_export, btn_refresh, btn_edit;
    @FXML
    private JFXListView<Label> stream_ListView;
    @FXML
    private Label streamName;
    @FXML
    private Tab subjectsTab, classTab, gradingTab;
    @FXML
    private Label totalStreams;
    @FXML
    private VBox streamClasses;
    
    public  Stream selectedStream = null;
    
    public StreamClassesList classesList = null;
    public StreamSubjectList subjectList = null;
    public StreamGrading streamGrading = null;
    
    public int selectedIndex = 0;
    
    public StreamWorkService sws = new StreamWorkService();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        classesList = new StreamClassesList();
        classesList.classWorkService.setOnSucceeded((WorkerStateEvent event) -> {
            subjectList.subjectWorkService.restart();
        });
        
        subjectList = new StreamSubjectList();
        subjectList.subjectWorkService.setOnSucceeded((WorkerStateEvent event) -> {
            streamGrading.gradeSchemeWorkService.restart();
        });
        
        streamGrading = new StreamGrading();
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setTooltip(new ToolTip("Add new stream"));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateStreamDialog(null).show();
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setTooltip(new ToolTip("Export all streams information to a file", 250, 30));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setTooltip(new ToolTip("Refresh stream list", 180, 30));
        btn_refresh.setOnAction((ActionEvent event) -> {
            sws.restart();
            
        });
        
        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        btn_edit.setTooltip(new ToolTip("Edit Stream information", 200, 30));
        btn_edit.setOnAction((ActionEvent event) -> {
            new UpdateStreamDialog(selectedStream);
        });
        
        stream_ListView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
               
            try {
                selectedStream = AdminQuery.getStreamByName(stream_ListView.getItems().get(newValue.intValue()).getText());
                selectedIndex = newValue.intValue(); 
                streamName.setText(selectedStream.getDescription());

                classesList.classWorkService.restart();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        
        
        //-- Set Tab --
        
        classTab.setContent(classesList);
        //classTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.HUMAN_MALE_FEMALE, "icon-secondary", 20));
        
        gradingTab.setContent(streamGrading);
        //gradingTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.PRESENTATION, "icon-secondary", 20));
        
        subjectsTab.setContent(subjectList);
        //subjectsTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.BOOK_OPEN_PAGE_VARIANT, "icon-secondary", 20));
        
        //-- 
        sws.start();
        sws.restart();
    }
    
    
    
    public class StreamListWork extends Task<ObservableList<Label>> {       
        @Override 
        protected ObservableList<Label> call() throws Exception {
            ObservableList<Label> data = FXCollections.observableArrayList();
            Platform.runLater(() -> {
            
            });
            
            ObservableList<Stream> streams = AdminQuery.getStreams();
        
            
        
            for (int i = 0; i < streams.size(); i++) {
                data.add(new Label(streams.get(i).getDescription()));
            }
                        
            Platform.runLater(() -> {
                try {
                    stream_ListView.setItems(data);
                
                    totalStreams.setText(""+streams.size());
                    if(selectedStream != null){
                        stream_ListView.getSelectionModel().select(selectedIndex);
                    }
                } catch (Exception e) {
                }
                
            });
            
            return data;
        }
       
    }

    public class StreamWorkService extends Service<ObservableList<Label>> {

        @Override
        protected Task createTask() {
            return new StreamListWork();
        }
    }

    


    
}
