    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXDialog;
import entry.SMS;
import inventorymanagement.control.FacilityStatusController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author MOILE
 */
public class FacilityStatus  extends JFXDialog{
    
    public FacilityStatusController DIALOG_CONTROLLER;
    public FacilityStatus(String facility_name){
        try{
            FXMLLoader ui = new FXMLLoader(getClass().getResource("/inventorymanagement/view/facilityStatus.fxml"));
            AnchorPane pane = ui.load();
            DIALOG_CONTROLLER = ui.getController();
            
            DIALOG_CONTROLLER.setEventHandler((EventHandler) (Event event) -> {
                close();
            });
            
            DIALOG_CONTROLLER.setFilter(facility_name);
                        
            setDialogContainer(SMS.MAIN_UI);
            setContent(pane);
            setOverlayClose(false);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

