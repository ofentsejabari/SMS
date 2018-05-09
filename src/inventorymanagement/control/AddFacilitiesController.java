/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import inventorymanagement.Facilities;
import inventorymanagement.Success;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import mysqldriver.AdminQuery;
import mysqldriver.InventoryQuery;
import static mysqldriver.InventoryQuery.updateFacilityItem;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class AddFacilitiesController implements Initializable {

    @FXML
    private VBox background_process;
    @FXML
    private JFXButton btn_toolbar_close;
    @FXML
    private JFXCheckBox enable_editing;
    @FXML
    private Circle profile_picture;
    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton btn_cancel;
    @FXML
    private JFXButton btn_update;
    @FXML
    private VBox personalDetails;
    @FXML
    private JFXTextField fname,fcapacity;
    
    @FXML
    private JFXComboBox fType,fDept,fCondition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "New",
            "Excellent",
            "Moderate",
            "Below Avarage",
            "Poor"
        );
        fType.setItems(InventoryQuery.getFacilitiesTypeList("ALL"));
        fDept.setItems(AdminQuery.getDepartmentNames());
        fCondition.setItems(options);
        btn_update.setOnAction((ActionEvent event) -> {
            String hold;
             if(fname.getText().equals("")||InventoryQuery.getFacilitiesTypeId(fType.getValue().toString()).get(0).equals("") ||
             AdminQuery.getDepartmentByName(fDept.getValue().toString()).getID().equals("") || fCondition.getValue().equals("") || fcapacity.getText().equals("")){
                 new Success("failure",true).show();
             }
             else{
                 Facilities facility  = new Facilities("0",fname.getText(),InventoryQuery.getFacilitiesTypeId(fType.getValue().toString()).get(0)
                 ,AdminQuery.getDepartmentByName(fDept.getValue().toString()).getID(),fCondition.getValue().toString(),fcapacity.getText(),"");
                
                hold=updateFacilityItem(facility, false);
                 if(hold.equals("")){
                    btn_update.setDisable(true);
                    btn_cancel.setDisable(true);
                    fType.setDisable(true);
                    fDept.setDisable(true);
                    fCondition.setDisable(true);
                    fname.setDisable(true);
                    fcapacity.setDisable(true);
                    new Success("success",true).show();
                }
                 
                else{
                    new Success("failure",true).show();
                }
             }
        });        
       
    }   
    
     public void setEventHandler(EventHandler event){btn_toolbar_close.setOnAction(event);}
    
}
