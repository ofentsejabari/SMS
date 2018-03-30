/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import inventorymanagement.AddSupplierStage;
import inventorymanagement.SupplierManagement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class SupplierManagementController implements Initializable {
    
    ObservableList<String> items =FXCollections.observableArrayList(); 
    
    @FXML
    private JFXButton btn_add, btn_export, btn_refresh, btn_edit,searchIcon;
    
    @FXML
    private JFXTextField searchField;
    
    @FXML
    private JFXListView<String> supplier_ListView;
    
    @FXML
    private VBox searchArea,listview;

    @FXML
    private Tab supplierDetails;
    
    
    SupplierManagement supplierManagement = null;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
        supplierManagement= new SupplierManagement();
        
        searchIcon.setGraphic(SMS.getGraphics(FontAwesomeIcon.SEARCH, "icon-default", 17));
        listview.getChildren().remove(searchArea);
        searchIcon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if(listview.getChildren().contains(searchArea))
               {
                   listview.getChildren().remove(searchArea);
                   
               }
               else{
                   //hiddenHbox.setVisible(true);
                   listview.getChildren().add(1,searchArea);
               }
            }
        });
        
        items =FXCollections.observableArrayList (
            "Crane Corp pty - 74664426","AXE Accesories - +26773890123","Befound Corporation - 3917287","Botsway Group Of Companies 3102893");
        supplier_ListView.setItems(items);
        searchField.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldVal,
                Object newVal) {
              search((String) oldVal, (String) newVal);
            }
          });
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new AddSupplierStage().show();
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
        });
        
        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        btn_edit.setOnAction((ActionEvent event) -> {
            
        });
        
        supplierDetails.setContent(supplierManagement);
    }    
    
    public void search(String oldVal, String newVal) {
       // System.out.print("here");
        if (oldVal != null && (newVal.length() < oldVal.length())) {
          supplier_ListView.setItems(items);
        }
        String value = newVal.toUpperCase();
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for (Object entry : supplier_ListView.getItems()) {
            boolean match = true;
            String entryText = (String) entry;
            
            if (!entryText.toUpperCase().contains(value)) {
                System.out.println(entryText.toUpperCase()+"--"+value);
              match = false;
            }
            if (match) {
                
              subentries.add(entryText);
            }
        }
        supplier_ListView.setItems(subentries);
  }
    
}
