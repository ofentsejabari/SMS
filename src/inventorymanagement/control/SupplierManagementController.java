/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import inventorymanagement.AddSupplierStage;
import inventorymanagement.SupplierInfo;
import inventorymanagement.SupplierManagement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class SupplierManagementController implements Initializable {
    
    
    
    @FXML
    private JFXButton btn_add, btn_export ;
    
    @FXML
    private BorderPane borderPane;
    
    BorderPane details ;
    
    public SupplierInfo supplierInfo;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      try{
            //-- 
            details = FXMLLoader.load(getClass().getResource("/inventorymanagement/view/supplierInformation.fxml"));
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        borderPane.setCenter(details);
        
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new AddSupplierStage().show();
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        
        
        //supplierDetails.setContent(supplierManagement);
    }    
    
   
}
