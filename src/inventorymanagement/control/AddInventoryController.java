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
import entry.DialogUI;
import entry.SMS;
import entry.ToolTip;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import inventorymanagement.Inventory;
import static inventorymanagement.InventoryItem.inventoryListWork;
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
public class AddInventoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btn_toolbar_close,btn_cancel,btn_update;
    
    @FXML
    private JFXTextField iname,itemNo,itemCost,itemStaff,itemOrder,itemYears,itemQuantity,itemGov,itemBatch;
           
    @FXML
    private JFXTextArea itemDesc;
    
     @FXML
     private JFXComboBox<String> itemDept,itemRoom,itemSupplier;
    
    @FXML
    private JFXCheckBox enable_editing;
    
    @FXML
    private VBox purchaseDetails,personalDetails;
    
    public JFXDatePicker itemPurchase;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setEditing(false);
        
        itemPurchase = new JFXDatePicker();
        itemPurchase.setPromptText("Date Procured");
        purchaseDetails.getChildren().add(3,itemPurchase);
        
        //SMS.setBorderContainer(personalDetails, "Personal Details");
        
        enable_editing.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(newValue){
                setEditing(true);
            }else{
                setEditing(false);
            }
        });
        
        btn_cancel.getStyleClass().add("dark-blue");
        btn_update.getStyleClass().add("dark-blue");
        btn_update.setTooltip(new ToolTip("Save Inventory Entry"));
        
        
        
        btn_update.setOnAction((ActionEvent event) -> {
            
                
                Inventory item = new  Inventory("0",iname.getText(),itemDesc.getText(),itemNo.getText(),itemGov.getText(),
                    itemYears.getText(),itemCost.getText(),itemOrder.getText()
                    ,itemRoom.getValue(),itemBatch.getText(),itemPurchase.getValue().toString()
                    ,itemDept.getValue(),"",itemQuantity.getText(),itemSupplier.getValue()
                    ,"",itemStaff.getText(),"100");
          
                if(InventoryQuery.updateInventoryItem(item, false).equals("")){
                        new DialogUI("Inventory Item has been added successfully",
                                    DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE,null).show();
                        
                        inventoryListWork.restart();
                        btn_update.setDisable(true);
                        btn_cancel.setDisable(true);
                }
                else{
                       new DialogUI("Exception occurred while trying to add Inventory Item.",
                                DialogUI.ERROR_NOTIF, PARENT_STACK_PANE, null).show();
                 }
        });
         itemDept.setItems(AdminQuery.getDepartmentNames()); 
         itemRoom.setItems(InventoryQuery.getFacilitiesNames()); 
         itemSupplier.setItems(InventoryQuery.getSupplierNames());
    }  
    
    
    public void setEditing(boolean val){
        
        
        btn_cancel.setVisible(val);
        btn_update.setVisible(val);
        
        iname.setEditable(val);              
        itemNo.setEditable(val);
        itemCost.setEditable(val);
        itemDesc.setEditable(val);
        itemStaff.setEditable(val);
        itemOrder.setEditable(val);
        /*
        itemSupplier.setEditable(val);
        itemRoom.setEditable(val);
        itemDept.setEditable(val);
        */
    }
    public void setEventHandler(EventHandler event){btn_toolbar_close.setOnAction(event);}
}
