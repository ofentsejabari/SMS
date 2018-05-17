
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXDialog;
import entry.SMS;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import inventorymanagement.control.FailedController;
import inventorymanagement.control.SuccessController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
public class Success extends JFXDialog{
    
    public SuccessController DIALOG_CONTROLLER;
    public FailedController DIALOG_CONTROLLER1;
    
    public Success(String status,boolean value){
        
        StackPane stack= SMS.MAIN_UI;
        try{
            FXMLLoader ui;
            AnchorPane pane;
            if(status.equals("success")){
                 ui= new FXMLLoader(getClass().getResource("/inventorymanagement/view/success.fxml"));
                 
                pane = ui.load();
                DIALOG_CONTROLLER = ui.getController();
            
                DIALOG_CONTROLLER.setEventHandler((EventHandler) (Event event) -> {
                    close();
                });
                        
            
            }
            else{
                ui= new FXMLLoader(getClass().getResource("/inventorymanagement/view/failed.fxml"));
                pane = ui.load();
                DIALOG_CONTROLLER1 = ui.getController();
            
                DIALOG_CONTROLLER1.setEventHandler((EventHandler) (Event event) -> {
                    close();
                });
            }
            if(value==true)
                stack= InventoryManagement.INVENTORY_MAN_STACK;
              
            setDialogContainer(stack);
            setContent(pane);
            setAlignment(Pos.CENTER);
            setOverlayClose(false);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

