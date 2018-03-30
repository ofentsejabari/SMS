
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import inventorymanagement.AddInventoryItemStage;
import inventorymanagement.InventoryItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class InventoryListController implements Initializable {

    @FXML
    JFXButton btn_add,btn_export,btn_refresh,btn_edit;
    
    @FXML
    private Tab inventoryList, sold;
    
    
    InventoryItem inventoryItem= null; 
    /**
     * Initializes the dcontroller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    inventoryItem= new InventoryItem();

// TODO
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new AddInventoryItemStage().show();
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
        
        inventoryList.setContent(inventoryItem);
        
        
    } 
}
