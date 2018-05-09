/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entry.AutoCompleteComboBoxListener;
import inventorymanagement.Success;
import inventorymanagement.Supplier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import mysqldriver.InventoryQuery;


/**
 *
 * @author MOILE
 */
public class AddSupplierController  implements Initializable {

    @FXML
    private JFXButton btn_close,save;
    
    @FXML
    private JFXTextField companyName,companyTel,companyCell,companyFax,companyEmail;
   
    @FXML
    private JFXTextArea companyPostal,companyPhysical;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        save.setOnAction((ActionEvent event) -> {
                Supplier item=new Supplier("0", companyName.getText(), companyEmail.getText(), companyTel.getText(), companyCell.getText(), 
                            companyPhysical.getText(),companyPostal.getText(), companyFax.getText());
                
                if(InventoryQuery.updateSupplierItem(item,false).equals("")){
                    new Success("success",true).show();
                    save.setDisable(true);
                }
                
                else{
                     new Success("failure",true).show();
                }
        });
          
        
    }  
    public void setEventHandler(EventHandler event){btn_close.setOnAction(event);}
    
   
}

