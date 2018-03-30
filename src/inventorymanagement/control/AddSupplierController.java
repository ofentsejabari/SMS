/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entry.AutoCompleteComboBoxListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
 *
 * @author MOILE
 */
public class AddSupplierController  implements Initializable {

    @FXML
    private JFXButton btn_close;
    @FXML
    private JFXButton save;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXComboBox<String> hod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }  
    public void setEventHandler(EventHandler event){btn_close.setOnAction(event);}
    
   
}

