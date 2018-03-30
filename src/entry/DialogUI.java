package entry;

import com.jfoenix.controls.JFXDialog;
import entry.control.DialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author ofentse
 */
public class DialogUI extends JFXDialog{

    public DialogController DIALOG_CONTROLLER;
    
    public DialogUI(String label, ImageView icon, StackPane dialogContainer){
        try{
            FXMLLoader ui = new FXMLLoader(getClass().getResource("/entry/view/Dialog.fxml"));
            AnchorPane pane = ui.load();
            DIALOG_CONTROLLER = ui.getController();
            DIALOG_CONTROLLER.setText(label);
            DIALOG_CONTROLLER.setIcon(icon);
            
            setDialogContainer(dialogContainer);
            setContent(pane);
            setOverlayClose(true);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
