/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class SuccessController implements Initializable {

    @FXML
    private JFXButton close,successImg;
    
    @FXML
    private Pane stack;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        successImg.setGraphic(SMS.getGraphics(FontAwesomeIcon.CHECK, "icon-default", 19));
    }    
    public void setEventHandler(EventHandler event){
        close.setOnAction(event);
            
    }
    
}
