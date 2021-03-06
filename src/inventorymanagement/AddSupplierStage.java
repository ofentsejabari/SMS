/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXDialog;
import inventorymanagement.control.AddSupplierController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author MOILE
 */
public class AddSupplierStage  extends JFXDialog{
    
    public AddSupplierController DIALOG_CONTROLLER;
    
    public AddSupplierStage(){
        try{
            FXMLLoader ui = new FXMLLoader(getClass().getResource("/inventorymanagement/view/addSupplier.fxml"));
            AnchorPane pane = ui.load();
            
            DIALOG_CONTROLLER = ui.getController();
            
            DIALOG_CONTROLLER.setEventHandler((EventHandler) (Event event) -> {
                close();
            });
                        
            setDialogContainer(InventoryManagement.INVENTORY_MAN_STACK);
            setContent(pane);
            setOverlayClose(false);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

