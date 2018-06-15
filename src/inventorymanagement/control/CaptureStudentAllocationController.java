/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entry.ToolTip;
import inventorymanagement.StudentAllocatedResourceDialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class CaptureStudentAllocationController implements Initializable {

    @FXML
    private VBox background_process;
    @FXML
    private JFXButton btn_toolbar_close;
    @FXML
    private JFXButton btn_save;
    @FXML
    private VBox personalDetails;
    @FXML
    private JFXRadioButton filter2;
    @FXML
    private JFXRadioButton filter1;
    @FXML
    private JFXRadioButton filter;
    @FXML
    private JFXTextField rquantity;
    @FXML
    private JFXTextField rdamaged;
    @FXML
    private JFXComboBox<?> resource;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        btn_save.getStyleClass().add("d1ark-blue");
        btn_save.setTooltip(new ToolTip("Save Student Resource Allocation"));
    }    
    
    public void setEventHandler(EventHandler event){btn_toolbar_close.setOnAction(event);}
}
