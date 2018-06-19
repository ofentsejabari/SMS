package studentmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.dbHandler;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ofentse
 */
public class SocialWelfareAid extends BorderPane{

    public static JFXListView<Label> swsAid_listView;
    
    Aid selectedAid = null;
    public static int selectedIndex = 0;
    private final JFXTextField name;
    private final JFXTextArea description;
    private final JFXTextField sws;
    private final JFXTextField cooperation;
    
    public SocialWelfareAid() {
        
        setPadding(new Insets(15, 5, 5, 5));
        
        BorderPane ssn_center = new BorderPane();
        ssn_center.setPadding(new Insets(0, 0, 0, 10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        ssn_center.setTop(toolbar);
        
        
        JFXButton refresh = new JFXButton("Refresh");
        refresh.getStyleClass().add("jfx-tool-button");
        refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        refresh.setOnAction((ActionEvent event) -> {
            updateSWSAidListView();
        });
        
        JFXButton edit = new JFXButton("Edit Record");
        edit.getStyleClass().add("jfx-tool-button");
        edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        edit.setOnAction((ActionEvent event) -> {
            new UpdateSWAidDialog(selectedAid);
        });
        
        JFXButton btn_add = new JFXButton("Add Aid");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateSWAidDialog(null);
        });
        
        Label title = new Label();
        title.getStyleClass().add("title-label");
        
        toolbar.getChildren().addAll(title, new HSpacer(), refresh, edit, btn_add);
        
        setCenter(ssn_center);
        
        swsAid_listView = new JFXListView<>();
        swsAid_listView.getStyleClass().add("jfx-custom-list");
        
        setLeft(swsAid_listView);
        
        GridPane contentGrid = new GridPane();
        contentGrid.getStyleClass().add("container");
        contentGrid.setAlignment(Pos.CENTER);
        contentGrid.setStyle("-fx-padding:25 5");
        contentGrid.setVgap(20);
        contentGrid.setHgap(2);
        
        name = new JFXTextField();
        name.prefWidthProperty().bind(contentGrid.widthProperty().subtract(25));
        name.setPromptText("Aid Name");
        name.setLabelFloat(true);
        name.setDisable(true);
        contentGrid.add(name, 0, 0);
        
        sws = new JFXTextField();
        sws.setPromptText("Social Welfare Support");
        sws.setLabelFloat(true);
        sws.setDisable(true);
        contentGrid.add(sws, 0, 1);
        
        cooperation = new JFXTextField();
        cooperation.setPromptText("Aid Offered by");
        cooperation.setLabelFloat(true);
        cooperation.setDisable(true);
        contentGrid.add(cooperation, 0, 2);
        
        description = new JFXTextArea();
        description.setPrefRowCount(4);
        description.setPromptText("Aid Description");
        description.setLabelFloat(true);
        description.setDisable(true);
        contentGrid.add(description, 0, 3);
                
        VBox cont = new VBox(SMS.setBorderContainer(contentGrid, "Social Welfare Aid Details "));
        cont.setPadding(new Insets(10, 0, 0, 0));
        
        ssn_center.setCenter(cont);
        
        swsAid_listView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            try {
                selectedAid = SMS.dbHandler.getSocialWelfareAidByName(swsAid_listView.getSelectionModel().getSelectedItem().getText());
                title.setText(swsAid_listView.getSelectionModel().getSelectedItem().getText());
                title.setTooltip(new Tooltip(selectedAid.getName()+"\n"+ selectedAid.getCooperation()));
                
                name.setText(selectedAid.getName());
                sws.setText(dbHandler.getSocialWelfareByID(selectedAid.getSocialWelfareID()).getName());
                cooperation.setText(selectedAid.getCooperation());
                description.setText(selectedAid.getDescription());
                
                selectedIndex = newValue.intValue();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        
        updateSWSAidListView();
    }
    
    
    public static void updateSWSAidListView(){
        ObservableList<String> ssn = SMS.dbHandler.getAidNames();
        ObservableList<Label> data = FXCollections.observableArrayList();
        
        for(String dt: ssn){
            data.add(new Label(dt));
        }
        swsAid_listView.setItems(data);
        swsAid_listView.getSelectionModel().select(selectedIndex);
    }
    
    
    
    public class SWSListWork extends Task<ObservableList<Student>> {       
        @Override 
        protected ObservableList<Student> call() throws Exception {
            
            
            ObservableList<Student> data = null; 
            
            if(selectedAid != null){
                data = SMS.dbHandler.getStudentsWithSocialWelfareSupport(selectedAid.getId());
            }else{ 
                data = FXCollections.observableArrayList();
            }
            
            Platform.runLater(() -> {
                //total.setText("( "+data.size()+" )");
            });
            
            return data;
        }
       
    }

    public class SWSAidWorkService extends Service<ObservableList<Student>> {

        @Override
        protected Task createTask() {
            return new SWSListWork();
        }
    }
    
}
