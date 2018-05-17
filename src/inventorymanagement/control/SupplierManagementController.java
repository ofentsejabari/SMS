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
import inventorymanagement.Supplier;
import inventorymanagement.SupplierInfo;
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
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import mysqldriver.InventoryQuery;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class SupplierManagementController implements Initializable {
    
    
    
    @FXML
    private JFXButton btn_add, btn_export ;
    
   @FXML
    private Tab supplierDetails,supplierProfileTab;
    
    public SupplierInfo supplierInfo;
    
    SupplierManagement supplierManagement = null;  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        supplierInfo = new SupplierInfo();
        
        supplierProfileTab.setContent(supplierInfo);
        supplierManagement= new SupplierManagement();
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new AddSupplierStage().show();
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        
        
        supplierDetails.setContent(supplierManagement);
    }    
    
   
}
