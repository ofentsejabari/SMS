/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableView;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.setDataNotAvailablePlaceholder;
import static inventorymanagement.FacilitiesManagement.facilitiesTable;
import static inventorymanagement.FacilitiesManagement.facilitiesWork;
import static inventorymanagement.control.FacilitiesController.facilitiesList;
import static inventorymanagement.control.InventoryListController.filter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import mysqldriver.InventoryQuery;

/**
 * @author MOILE
 */
public class InvoiceManagement extends BorderPane{

    public static ObservableList<InvoicesModel> invoicesList = FXCollections.observableArrayList();
    public static CustomTableView<InvoicesModel> invoicesTable;
    public static InvoicesWorkService invoicesWork;
    private final StackPane stackPane;
    
    public InvoiceManagement() {
        
        //setPadding(new Insets(10));
        this.setStyle("-fx-background-color:#fff;");
        invoicesWork = new InvoicesWorkService();
        stackPane = new StackPane();
        
        /*
            set top 
        */
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Add");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            //new AddFacilities().show();
        });
        
        JFXButton btn_refresh = new JFXButton("Refresh");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            //facilitiesWork.restart();
        });
        
        btn_refresh.getStyleClass().add("jfx-tool-button");
        btn_add.getStyleClass().add("jfx-tool-button");
        toolbar.getChildren().addAll(new HSpacer(), btn_refresh, btn_add);
        
        setTop(toolbar);
        
        /*
            set center
        */
        invoicesTable = new CustomTableView<>();
    }
    
    public class InvoicesWork extends Task<ObservableList<InvoicesModel>> {       
        @Override 
        protected ObservableList<InvoicesModel> call() throws Exception {
            
            Platform.runLater(() -> {               
                invoicesTable.getTableView().setPlaceholder(new VBox());
            });
            
            Platform.runLater(() -> {  
                invoicesTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return invoicesList;
        } 
    }

    public class InvoicesWorkService extends Service<ObservableList<InvoicesModel>> 
    {

        @Override
        protected Task createTask()
        {
            return new InvoicesWork();
        }
    }
}
