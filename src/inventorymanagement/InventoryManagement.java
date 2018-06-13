package inventorymanagement;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import static entry.SMS.getIcon;


public class InventoryManagement extends BorderPane{
    
    private JFXListView<Label> mainMenu;
    private BorderPane facilitiesUI,inventoryListUI,invoicesUI;
    private AnchorPane dashboardUI,assetAllocationUI,purchaseOrderUI,policyDocumentsUI,supplierUI;
    public static StackPane INVENTORY_MAN_STACK;

    public InventoryManagement() {
        
        getStyleClass().add("container");
        
        //-- Inventory Management Menu --
        mainMenu = new JFXListView<>();
        mainMenu.getStyleClass().add("main_menu");
        
        //-- Menu Items --
        Label dashboard = new Label("Dashboard", getIcon("system_task_100px.png", 22));
        Label inventory = new Label("Inventory List", getIcon("applicant_100px.png", 22));
        Label supplier = new Label("Supplier Management", getIcon("inspection_100px.png", 22));
        Label invoices = new Label("Invoice Management", getIcon("inspection_100px.png", 22));
        Label purchaseOrder = new Label("Purchase Order", getIcon("inspection_100px.png", 22));
        Label assets = new Label("Student Asset Allocation", getIcon("bunk_bed.png", 22));
        Label policyDocuments = new Label("Policy Document Manager", getIcon("bunk_bed.png", 22));
        Label facilities = new Label("Facilities Management", getIcon("bunk_bed.png", 22));

        mainMenu.getItems().addAll(dashboard, inventory, supplier,invoices,
                                   assets, purchaseOrder, policyDocuments,facilities);
        
        //-- set the first item selected --
        mainMenu.getSelectionModel().select(0);
        
        mainMenu.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            switch (newValue.intValue()){
                case 0:
                    dashboardUI.toFront();
                    break;
                case 1:
                    inventoryListUI.toFront();
                    break;
                case 2:
                    supplierUI.toFront();
                    break;
                case 3:
                    invoicesUI.toFront();
                    break;
                case 4:
                    assetAllocationUI.toFront();
                    break;
                case 5:
                    purchaseOrderUI.toFront();
                    break;
                case 6:
                    policyDocumentsUI.toFront();
                    break;
                    
                default:
                    facilitiesUI.toFront();
                    break;
            }
        });
        
        setLeft(mainMenu);
        
            try {
                    dashboardUI = FXMLLoader.load(getClass().getResource("/inventorymanagement/view/dashboard.fxml"));
                    inventoryListUI = new InventoryItem();
                    supplierUI = FXMLLoader.load(getClass().getResource("/inventorymanagement/view/supplierManagement.fxml"));
                    invoicesUI = new InvoiceManagement();
                    assetAllocationUI=FXMLLoader.load(getClass().getResource("/inventorymanagement/view/assetAllocation.fxml"));
                    policyDocumentsUI=FXMLLoader.load(getClass().getResource("/inventorymanagement/view/policyDocuments.fxml"));
                    purchaseOrderUI=FXMLLoader.load(getClass().getResource("/inventorymanagement/view/purchaseOrder.fxml"));
                    facilitiesUI=FXMLLoader.load(getClass().getResource("/inventorymanagement/view/facilities.fxml"));
            } 
            catch (IOException ex) {
        }
        
        INVENTORY_MAN_STACK = new StackPane(facilitiesUI,policyDocumentsUI,purchaseOrderUI,invoicesUI,
                supplierUI,assetAllocationUI,inventoryListUI ,dashboardUI);
        
        setCenter(INVENTORY_MAN_STACK);
    }
    
}
