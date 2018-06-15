/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.DialogUI;
import entry.SMS;
import static entry.SMS.getGraphics;
import entry.ToolTip;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import inventorymanagement.Inventory;
import static inventorymanagement.FurnitureTab.inventoryListWork;
import inventorymanagement.Success;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;
import mysqldriver.InventoryQuery;
import static schooladministration.SchoolAdministartion.departmentsController;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class AddBookInventoryController implements Initializable {
 @FXML
    private JFXComboBox<?> itemDept;

    @FXML
    private VBox background_process;

    @FXML
    private JFXTextField itemYears;

    @FXML
    private VBox personalDetails;

    @FXML
    private JFXTextField itemGov;

    @FXML
    private JFXTextField itemNo;

    @FXML
    private JFXButton btn_toolbar_close;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXTextField iname;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //setEditing(false);
        
        
        btn_toolbar_close.setGraphic(getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        
        btn_update.getStyleClass().add("dark-blue");
        btn_update.setTooltip(new ToolTip("Save Inventory Entry"));
        
        
        
        btn_update.setOnAction((ActionEvent event) -> {
        
          
//                if(){
//                        new DialogUI("Inventory Item has been added successfully",
//                                    DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE,null).show();
//                        
//                        inventoryListWork.restart();
//                        btn_update.setDisable(true);
//                }
//                else{
//                       new DialogUI("Exception occurred while trying to add Inventory Item.",
//                                DialogUI.ERROR_NOTIF, PARENT_STACK_PANE, null).show();
//                 }
        });
    }  
    
    
    public void setEditing(boolean val){
        
        
        btn_update.setVisible(val);
        
        iname.setEditable(val);              
        itemNo.setEditable(val);
    }
    public void setEventHandler(EventHandler event){btn_toolbar_close.setOnAction(event);}
}
