/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 *
 * @author MOILE
 */
public class InventoryItem extends BorderPane{

    public static CustomTableView<Inventory> inventoryTable;
    
    public InventoryItem() {
    
        getStyleClass().add("container");
        
        setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        toolbar.getChildren().addAll(new HSpacer());
        
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
        
        CustomTableColumn inventoryStaff = new CustomTableColumn("POSTAL");
        inventoryStaff.setPercentWidth(20);
        inventoryStaff.setCellValueFactory(new PropertyValueFactory<>("inventoryStaff"));
        inventoryStaff.setCellFactory(TextFieldTableCell.forTableColumn());
        inventoryStaff.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        CustomTableColumn inventoryQuantity = new CustomTableColumn("FAX");
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
            inventoryBatch,inventoryDate, inventoryStaff,inventoryQuantity);
        VBox.setVgrow(inventoryTable, Priority.ALWAYS);
        
        //-- SET DATA
        
        ObservableList<Inventory> sp = FXCollections.observableArrayList(
                new Inventory("10", "Jabari", "jabari@gmail.com", "72177941", "72177941", "Address", "Postal", "fax"),
                new Inventory("11", "Jawfi", "jabari@gmail.com", "72177941", "72177941", "Address", "Postal", "fax"),
                new Inventory("11", "Jabwef w", "jabari@gmail.com", "72177941", "72177941", "Address", "Postal", "fax")
        );
        
        inventoryTable.getTableView().setItems(sp);
        
        StackPane stackPane = new StackPane(inventoryTable);
        
        setCenter(stackPane);
        
    }
    
    
    
}
