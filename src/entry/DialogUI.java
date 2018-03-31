package entry;

import com.jfoenix.controls.JFXDialog;
import entry.control.DialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author ofentse
 */
public class DialogUI extends JFXDialog{

    public DialogController DIALOG_CONTROLLER;
    
    //-- Nitification types --
    public static int ERROR_NOTIF = 1, SUCCESS_NOTIF = 2, INFORMATION_NOTIF = 3;
    
    public DialogUI(String label, int type, StackPane dialogContainer){
        try{
            FXMLLoader ui = new FXMLLoader(getClass().getResource("/entry/view/Dialog.fxml"));
            AnchorPane pane = ui.load();
            DIALOG_CONTROLLER = ui.getController();
            DIALOG_CONTROLLER.setText(label);
            
            switch(type){
                case 1:
                    DIALOG_CONTROLLER.setIcon(SMS.getIcon("high_priority_96.png", 64));
                    pane.getStyleClass().add("dialog_error_notif");
                    setOverlayClose(false);
                    break;
                    
                case 2:
                    DIALOG_CONTROLLER.setIcon(SMS.getIcon("ok_96.png", 64));
                    pane.getStyleClass().add("dialog_success_notif");
                    setOverlayClose(true);
                    break;    
                
                case 3:
                    DIALOG_CONTROLLER.setIcon(SMS.getIcon("info_96.png", 64));
                    pane.getStyleClass().add("dialog_information_notif");
                    setOverlayClose(true);
                    break;
            }
            
            setDialogContainer(dialogContainer);
            setContent(pane);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
