/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entry.ToolTip;
import inventorymanagement.FacilitiesStatus;
import inventorymanagement.Success;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import mysqldriver.InventoryQuery;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class AddFacilityResourcesController implements Initializable {

    @FXML
    private VBox background_process;
    @FXML
    private JFXButton btn_toolbar_close;
    @FXML
    private JFXButton btn_cancel;
    @FXML
    private JFXButton btn_update;
    @FXML
    private VBox personalDetails;
    @FXML
    private JFXComboBox<String> resource;
    @FXML
    private JFXTextField rquantity;
    @FXML
    private JFXTextField rdamaged;
    
    public String ID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        resource.setItems(InventoryQuery.getResourceNames());
         btn_update.getStyleClass().add("dark-blue");
        btn_update.setTooltip(new ToolTip("Save Facility"));
        btn_update.setOnAction((ActionEvent event) -> {
            
            FacilitiesStatus fs = new FacilitiesStatus("0",InventoryQuery.getFacilitiesId(ID).get(0),resource.getValue(),rquantity.getText(),
                    rdamaged.getText());
              
            if(InventoryQuery.addFacilitiesStatus(fs,false).equals("")){
                    btn_cancel.setDisable(true);
                    btn_update.setDisable(true);
                    rquantity.setDisable(true);
                    rdamaged.setDisable(true);
                    resource.setDisable(true);
                    new Success("success",false).show();
            }
            else{
                 new Success("failure",false).show();
            }
               
        });
    }    
     
    public void setEventHandler(EventHandler event){btn_toolbar_close.setOnAction(event);}
    
    public void setId(String ID)
    {
        this.ID=ID;
    }
}
