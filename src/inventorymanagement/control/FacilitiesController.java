/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import inventorymanagement.Facilities;
import inventorymanagement.FacilitiesManagement;
import inventorymanagement.FacilityTypeItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class FacilitiesController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Tab facilitiesTab,facilitiesTypes;
    

    
    public static ObservableList<Facilities> facilitiesList = FXCollections.observableArrayList();

    FacilitiesManagement facilitiesItem= null; 
    FacilityTypeItem facilitiesTypeItem= null; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        facilitiesTypeItem = new FacilityTypeItem();
        facilitiesItem = new FacilitiesManagement();
        facilitiesTab.setContent(facilitiesItem);
        facilitiesTypes.setContent(facilitiesTypeItem);
    }    
    
}
