package entry.control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class DialogController implements Initializable {

    @FXML
    private Label dialogIcon;
    @FXML
    private Label dialogMessage;
    @FXML
    private JFXButton btn_no;
    @FXML
    private JFXButton btn_yes;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dialogMessage.setWrapText(true);
    }    
    
    public void setIcon(ImageView icon){
        dialogIcon.setGraphic(icon);
    }
    
    public void setText(String str){
        dialogMessage.setText(str);
    }
    
    public void setBTNNOControl(EventHandler<ActionEvent> eventHandler, String label){
        btn_no.setText(label);
        btn_no.setOnAction(eventHandler);
    }
    
    public void setBTNYESControl(EventHandler<ActionEvent> eventHandler, String label){
        btn_yes.setText(label);
        btn_yes.setOnAction(eventHandler);
    }
    
    
    
}
