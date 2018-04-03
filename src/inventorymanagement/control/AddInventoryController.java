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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

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
    private JFXTextField iname,itemNo,itemCost,itemStaff,itemOrder;
           
    @FXML
    private JFXTextArea itemDesc;
    
     @FXML
     private JFXComboBox<String> itemDept,itemRoom,itemSupplier;
    
    @FXML
    private JFXCheckBox enable_editing;
    
    @FXML
    private VBox purchaseDetails;
    
    public JFXDatePicker itemPurchase;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setEditing(false);
        
        itemPurchase = new JFXDatePicker();
        itemPurchase.setPromptText("Purchase Delivery Date");
        purchaseDetails.getChildren().add(3,itemPurchase);
        
        enable_editing.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(newValue){
                setEditing(true);
            }else{
                setEditing(false);
            }
        });
        
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
