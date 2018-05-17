
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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;
import schooladministration.Stream;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class AllocationTableController implements Initializable {
    
    private JFXButton btn_toolbar_close;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btn_export;
    @FXML
    private JFXButton btn_refresh;
    @FXML
    private VBox listview;
    @FXML
    private Label totalStreams;
    @FXML
    private JFXListView<String> stream_ListView;
    @FXML
    private VBox streamClasses;
    @FXML
    private Label streamName;
    @FXML
    private JFXButton btn_info;
    @FXML
    private JFXButton btn_edit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
        });
        
         btn_info.setGraphic(SMS.getGraphics(FontAwesomeIcon.INFO, "icon-default", 20));
        btn_info.setOnAction((ActionEvent event) -> {
            //new AddSupplierStage().show();
        });
        
        btn_edit.setGraphic(SMS.getGraphics(FontAwesomeIcon.EDIT, "icon-default", 24));
        btn_edit.setOnAction((ActionEvent event) -> {
           // activateButtons(flag);
            //new AddSupplierStage().show();
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.REFRESH, "icon-default", 24));
                 
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        
        ObservableList<String> items =AdminQuery.getStreamNames();
        totalStreams.setText(""+items.size());
        stream_ListView.setItems(items);
    }    
    
     public void setEventHandler(EventHandler event){btn_toolbar_close.setOnAction(event);}
    
}
