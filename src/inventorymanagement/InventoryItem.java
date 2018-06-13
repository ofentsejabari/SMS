/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXTabPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author MOILE
 */
public class InventoryItem extends BorderPane{

    public InventoryItem() {
        
        setPadding(new Insets(10));
        
        JFXTabPane inventoryTAb = new JFXTabPane();
        inventoryTAb.getStyleClass().add("jfx-tab-flatpane");
        Tab furniture = new Tab("Furniture");
        furniture.setClosable(false);
        Tab vehicles = new Tab("Vehicles");
        vehicles.setClosable(false);
        Tab stationary = new Tab("Stationary");
        stationary.setClosable(false);
        inventoryTAb.prefHeightProperty().bind(this.prefHeightProperty());
        
        Tab books = new Tab("Books");
        books.setClosable(false);
        books.setContent(new BookTab());
        inventoryTAb.getTabs().addAll(books,furniture,vehicles,stationary);
        setCenter(inventoryTAb);
     }
}
