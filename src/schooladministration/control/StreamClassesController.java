
package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.Notification;
import entry.SMS;
import entry.ToolTip;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;
import schooladministration.UpdateDepartmentDialog;
import schooladministration.StreamClassesList;
import schooladministration.Stream;
import schooladministration.StreamGrading;
import schooladministration.StreamSubjectList;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class StreamClassesController implements Initializable {

    @FXML
    private JFXButton btn_add, btn_export, btn_refresh, btn_edit, btn_info;
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
    
    public static Stream selectedStream = null;
    
    public StreamClassesList classesList = null;
    public StreamSubjectList subjectList = null;
    public StreamGrading streamGrading = null;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Notification nt = new Notification("Add stream classes, associate subjects to the"
                                         + " stream and define stream grading scheme ", 0, streamClasses);
                
                
        streamClasses.getChildren().add(1, nt);
        
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
            new UpdateDepartmentDialog(null).show();
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setTooltip(new ToolTip("Export all streams information to a file", 250, 30));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setTooltip(new ToolTip("Refresh stream list", 180, 30));
        btn_refresh.setOnAction((ActionEvent event) -> {
            stream_ListView.setItems(updateStreams());
            stream_ListView.getSelectionModel().select(0);
            
        });
        
        btn_info.setGraphic(SMS.getGraphics(MaterialDesignIcon.INFORMATION_VARIANT, "icon-default", 24));
        btn_info.setTooltip(new ToolTip("View or hide notification area", 200, 30));
        btn_info.setOnAction((ActionEvent event) -> {
            if(!streamClasses.getChildren().contains(nt)){
                streamClasses.getChildren().add(1, nt);
            }else{
                streamClasses.getChildren().remove(nt);
            }
        });
        
        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        btn_edit.setTooltip(new ToolTip("Edit Stream information", 200, 30));
        btn_edit.setOnAction((ActionEvent event) -> {
            
        });
        
        stream_ListView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
               
            try {
                selectedStream = AdminQuery.getStreamByName(stream_ListView.getItems().get(newValue.intValue()).getText());
                streamName.setText(selectedStream.getDescription()+" Stream");

                classesList.classWorkService.restart();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
           
        stream_ListView.setItems(updateStreams());
        
        
        //-- Set Tab --
        
        classTab.setContent(classesList);
        classTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.HUMAN_MALE_FEMALE, "icon-secondary", 20));
        
        gradingTab.setContent(streamGrading);
        gradingTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.PRESENTATION, "icon-secondary", 20));
        
        subjectsTab.setContent(subjectList);
        subjectsTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.BOOK_OPEN_PAGE_VARIANT, "icon-secondary", 20));
        
    }
    
    

    private ObservableList<Label> updateStreams() {
        
        ObservableList<Label> data = FXCollections.observableArrayList();
        ObservableList<Stream> streams = AdminQuery.getStreams();
        
        totalStreams.setText(""+streams.size());
        
        for (int i = 0; i < streams.size(); i++) {
            data.add(new Label(streams.get(i).getDescription()));
        }
        
        
        return data;
    }


    
}
