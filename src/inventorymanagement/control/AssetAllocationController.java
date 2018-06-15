/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import inventorymanagement.AssetAllocationList;
import static inventorymanagement.StudentAllocatedResourceDialog.studentAllocationWork;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class AssetAllocationController implements Initializable {

    @FXML
    private BorderPane borderPane;
   
    @FXML
    private JFXButton btn_export,buttonRefresh;
    @FXML
    private VBox listview;
    @FXML
    private Label totalStreams;
    @FXML
    private JFXListView<String> stream_ListView;
    @FXML
    private VBox streamClasses;
    
    @FXML
    private HBox streamToolBar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> items = AdminQuery.getStreamNames();
        
        totalStreams.setText(""+items.size());
        
        stream_ListView.setItems(items);
        AssetAllocationList asset = new  AssetAllocationList();
        streamClasses.getChildren().add(asset);
        stream_ListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                AssetAllocationList.stream_ID=newValue;
                asset.studentAllocationWork.restart();
         });
        
        JFXButton btn_refresh = new JFXButton();
        btn_refresh.getStyleClass().add("jfx-tool-button");
        streamToolBar.getChildren().add(1, btn_refresh);
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
                ObservableList<String> items1 = AdminQuery.getStreamNames();
                totalStreams.setText(""+items1.size());
                stream_ListView.setItems(items1);
                AssetAllocationList.stream_ID="ALL";
                try{
                    studentAllocationWork.restart();
                }
                catch(Exception e){
                    System.out.println(e);

                }
        
        });
        
    }    
}    
    
