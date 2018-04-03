
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import static entry.SMS.getGraphics;
import static entry.SMS.setDataNotAvailablePlaceholder;
import inventorymanagement.AddInventoryItemStage;
import inventorymanagement.InventoryItem;
import inventorymanagement.Inventory;
import static inventorymanagement.InventoryItem.inventoryTable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import mysqldriver.InventoryQuery;
import org.controlsfx.control.textfield.CustomTextField;
import static studentmanagement.control.StudentEnrolmentController.studentTable;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class InventoryListController implements Initializable {

    @FXML
    JFXButton btn_add,btn_export,btn_refresh,btn_edit;
    
     public static String filter = "ALL";
    
    @FXML
    private CustomTextField search;
    
    @FXML
    private Tab inventoryList, sold;
    
    public static ObservableList<Inventory> inventList = FXCollections.observableArrayList();
    
    
    
    InventoryItem inventoryItem= null; 
    
    /**
     * Initializes the dcontroller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
    
    inventoryItem = new InventoryItem();
      /*
      
      JFXButton clear = new JFXButton("",getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "text-error", 20));
        clear.setStyle("-fx-background-radius:0 20 20 0; -fx-border-radius:0 20 20 0; -fx-cursor: hand;");
        clear.setOnAction((ActionEvent event) -> {
            search.clear();
        });
        
        JFXButton src = new JFXButton("",getGraphics(MaterialDesignIcon.ACCOUNT_SEARCH, "text-bluegray", 20));
        src.setStyle("-fx-background-radius:0 20 20 0; -fx-border-radius:0 20 20 0; -fx-cursor: hand;");
      
       search.setRight(clear);
        
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String str = search.getText().trim(); 
           
            if(inventoryTable.getTableView().getItems() != null){
                ObservableList<Inventory>  inventList  = InventoryQuery.inventoryList(filter);
                inventoryTable.getTableView().getItems().clear();
            
                if(str != null && str.length() > 0){
                    
                    for(Inventory inventory : inventList) {
                        
                        if(inventory.getInventoryID().toLowerCase().contains(str.toLowerCase())){
                            inventoryTable.getTableView().getItems().add(inventory);
                        }

                        if(inventory.getInventoryName().toLowerCase().contains(str.toLowerCase())){
                            inventoryTable.getTableView().getItems().add(inventory);
                        }
                        
                        if(inventory.getInventoryLocation().toLowerCase().contains(str.toLowerCase())){
                            inventoryTable.getTableView().getItems().add(inventory);
                        } 
                    }
                    
                }else{
                    inventoryListWork.restart();
                }
            }
        });
// TODO
*/
        
        
        inventoryList.setContent(inventoryItem);
        
        
    } 
    
    
    
 
}
