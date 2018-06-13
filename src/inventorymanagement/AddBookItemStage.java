/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXDialog;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import static inventorymanagement.InventoryManagement.INVENTORY_MAN_STACK;
import inventorymanagement.control.AddBookInventoryController;
import inventorymanagement.control.AddSupplierController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author MOILE
 */
public class AddBookItemStage  extends JFXDialog{
    
    public AddBookInventoryController DIALOG_CONTROLLER;
    
    public AddBookItemStage(){
        try{
            FXMLLoader ui = new FXMLLoader(getClass().getResource("/inventorymanagement/view/addBookInventory.fxml"));
            AnchorPane pane = ui.load();
            
            DIALOG_CONTROLLER = ui.getController();
            DIALOG_CONTROLLER.setEventHandler((EventHandler) (Event event) -> {
                close();
            });
                        
            setDialogContainer(PARENT_STACK_PANE);
            setContent(pane);
            setOverlayClose(false);
            show();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

