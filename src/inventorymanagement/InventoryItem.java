/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.DialogUI;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.setDataNotAvailablePlaceholder;
import static inventorymanagement.control.InventoryListController.filter;
import static inventorymanagement.control.InventoryListController.inventList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mysqldriver.InventoryQuery;
import static studentmanagement.control.StudentEnrolmentController.studentTable;

/**
 *
 * @author MOILE
 */
public class InventoryItem extends BorderPane{

    public static CustomTableView<Inventory> inventoryTable;
    public static InventoryListWorkService inventoryListWork;
    private final StackPane stackPane;
    
    public InventoryItem() {
        
        inventoryListWork = new InventoryListWorkService();
    
        getStyleClass().add("container");
        stackPane = new StackPane();
        
        setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Add");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new AddInventoryItemStage().show();
        });
        
//        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
//        btn_export.setOnAction((ActionEvent event) -> {
//            
//        });
        
        JFXButton btn_refresh = new JFXButton("Refresh");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            inventoryListWork.restart();
            new DialogUI("ahgjagjcas as ", DialogUI.ERROR_NOTIF, stackPane).show();
        });
//        
//        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
//        btn_edit.setOnAction((ActionEvent event) -> {
//            
//        });
//        
        
        toolbar.getChildren().addAll(new HSpacer(), btn_refresh, btn_add);
        
        /*
            CREATE SUPPLIER TABLE
        */
        inventoryTable = new CustomTableView<>();
        
        CustomTableColumn inventoryID = new CustomTableColumn("#");
        inventoryID.setPercentWidth(4.9);
        inventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        inventoryID.setCellFactory(TextFieldTableCell.forTableColumn());
        inventoryID.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn inventoryName = new CustomTableColumn("ITEM NAME");
        inventoryName.setPercentWidth(15);
        inventoryName.setCellValueFactory(new PropertyValueFactory<>("inventoryName"));
        inventoryName.setCellFactory(TextFieldTableCell.forTableColumn());
        inventoryName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn inventoryCost = new CustomTableColumn("COST");
        inventoryCost.setPercentWidth(15);
        inventoryCost.setCellValueFactory(new PropertyValueFactory<>("inventoryCost"));
        inventoryCost.setCellFactory(TextFieldTableCell.forTableColumn());
        inventoryCost.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        }); 
                
        CustomTableColumn inventoryLocation= new CustomTableColumn("LOCATION");
        inventoryLocation.setPercentWidth(10);
        inventoryLocation.setCellValueFactory(new PropertyValueFactory<>("inventoryLocation"));
        inventoryLocation.setCellFactory(TextFieldTableCell.forTableColumn());
        inventoryLocation.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        }); 
        
        CustomTableColumn inventoryBatch = new CustomTableColumn("BATCH");
        inventoryBatch.setPercentWidth(10);
        inventoryBatch.setCellValueFactory(new PropertyValueFactory<>("inventoryBatch"));
        inventoryBatch.setCellFactory(TextFieldTableCell.forTableColumn());
        inventoryBatch.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn inventoryDate = new CustomTableColumn("PURCHASE DATE");
        inventoryDate.setPercentWidth(20);
        inventoryDate.setCellValueFactory(new PropertyValueFactory<>("inventoryDate"));
        inventoryDate.setCellFactory(TextFieldTableCell.forTableColumn());
        inventoryDate.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn inventoryStaffID = new CustomTableColumn("STAFF");
        inventoryStaffID.setPercentWidth(20);
        inventoryStaffID.setCellValueFactory(new PropertyValueFactory<>("inventoryStaffID"));
        inventoryStaffID.setCellFactory(TextFieldTableCell.forTableColumn());
        inventoryStaffID.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        CustomTableColumn inventoryQuantity = new CustomTableColumn("QUANTITY");
        inventoryQuantity.setPercentWidth(10);
        inventoryQuantity.setCellValueFactory(new PropertyValueFactory<>("inventoryQuantity"));
        inventoryQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
        inventoryQuantity.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });        
        
        inventoryTable.getTableView().getColumns().addAll(inventoryID, inventoryName,inventoryCost, inventoryLocation,
            inventoryBatch,inventoryDate, inventoryStaffID,inventoryQuantity);
        VBox.setVgrow(inventoryTable, Priority.ALWAYS);
        
        //-- SET DATA
        
        
        
        inventoryTable.getTableView().setItems(InventoryQuery.inventoryList("All"));
        
        
        VBox ph = setDataNotAvailablePlaceholder();
        inventoryTable.getTableView().setPlaceholder(ph);
        
        
        ProgressIndicator pi = new ProgressIndicator("Loading users data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(inventoryListWork.runningProperty());
        inventoryTable.getTableView().itemsProperty().bind(inventoryListWork.valueProperty());
        
        stackPane.getChildren().addAll(pi,inventoryTable);
        setCenter(stackPane);
        
        inventoryListWork.start();
        inventoryListWork.restart();
        
    }
    
    public class InventoryListWork extends Task<ObservableList<Inventory>> {       
        @Override 
        protected ObservableList<Inventory> call() throws Exception {
            
            Platform.runLater(() -> {               
                inventoryTable.getTableView().setPlaceholder(new VBox());
            });
            inventList  =  InventoryQuery.inventoryList(filter);
            for(int i=0;i<inventList.size();i++){
                inventList.get(i).setInventoryID(i+1+"");
            }
                        
            Platform.runLater(() -> {  
                //count.setText(studentList.size()+" Student(s)");
                studentTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return inventList;
        } 
    }

    public class InventoryListWorkService extends Service<ObservableList<Inventory>> {

        @Override
        protected Task createTask() {
            return new InventoryListWork();
        }
    }
    
}
