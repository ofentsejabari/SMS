/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import inventorymanagement.PolicyDocument;
import inventorymanagement.PolicyDocumentManagement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class PolicyDocumentController implements Initializable {

    @FXML
    private Tab policyDocTab;
    @FXML
    private Tab policyTypesTab;

    public static ObservableList<PolicyDocument> policyList = FXCollections.observableArrayList();
    
     PolicyDocumentManagement policyItem= null; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        policyItem=  new PolicyDocumentManagement();
        policyDocTab.setContent(policyItem);
      
    }    
    
}
