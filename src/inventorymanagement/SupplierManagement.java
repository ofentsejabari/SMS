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
public class SupplierManagement extends BorderPane{

    public static CustomTableView<Supplier> supplierTable;
    
    public SupplierManagement() {
    
        getStyleClass().add("container");
        
        setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        toolbar.getChildren().addAll(new HSpacer());
        
        /*
            CREATE SUPPLIER TABLE
        */
        supplierTable = new CustomTableView<>();
        
        CustomTableColumn supplierID = new CustomTableColumn("#");
        supplierID.setPercentWidth(4.9);
        supplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        supplierID.setCellFactory(TextFieldTableCell.forTableColumn());
        supplierID.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn supplierName = new CustomTableColumn("SUPPLIER NAME");
        supplierName.setPercentWidth(15);
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierName.setCellFactory(TextFieldTableCell.forTableColumn());
        supplierName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn supplierEmail = new CustomTableColumn("EMAIL");
        supplierEmail.setPercentWidth(15);
        supplierEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmail"));
        supplierEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        supplierEmail.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
                
        CustomTableColumn supplierPhone = new CustomTableColumn("TEL");
        supplierPhone.setPercentWidth(10);
        supplierPhone.setCellValueFactory(new PropertyValueFactory<>("supplierPhone"));
        supplierPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        supplierPhone.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn supplierCell = new CustomTableColumn("CELL");
        supplierCell.setPercentWidth(10);
        supplierCell.setCellValueFactory(new PropertyValueFactory<>("supplierCell"));
        supplierCell.setCellFactory(TextFieldTableCell.forTableColumn());
        supplierCell.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn supplierPhysical = new CustomTableColumn("PHYSICAL ADD");
        supplierPhysical.setPercentWidth(20);
        supplierPhysical.setCellValueFactory(new PropertyValueFactory<>("supplierPhysical"));
        supplierPhysical.setCellFactory(TextFieldTableCell.forTableColumn());
        supplierPhysical.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn supplierPostal = new CustomTableColumn("POSTAL");
        supplierPostal.setPercentWidth(20);
        supplierPostal.setCellValueFactory(new PropertyValueFactory<>("supplierPostal"));
        supplierPostal.setCellFactory(TextFieldTableCell.forTableColumn());
        supplierPostal.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        CustomTableColumn supplierFax = new CustomTableColumn("FAX");
        supplierFax.setPercentWidth(10);
        supplierFax.setCellValueFactory(new PropertyValueFactory<>("supplierFax"));
        supplierFax.setCellFactory(TextFieldTableCell.forTableColumn());
        supplierFax.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        supplierTable.getTableView().getColumns().addAll(supplierID, supplierName,supplierEmail, supplierPhone,
            supplierCell, supplierPhysical,supplierPostal, supplierFax);
        VBox.setVgrow(supplierTable, Priority.ALWAYS);
        
        //-- SET DATA
        
        ObservableList<Supplier> sp = FXCollections.observableArrayList(
                new Supplier("10", "Jabari", "jabari@gmail.com", "72177941", "72177941", "Address", "Postal", "fax"),
                new Supplier("11", "Jawfi", "jabari@gmail.com", "72177941", "72177941", "Address", "Postal", "fax"),
                new Supplier("11", "Jabwef w", "jabari@gmail.com", "72177941", "72177941", "Address", "Postal", "fax")
        );
        
        supplierTable.getTableView().setItems(sp);
        
        StackPane stackPane = new StackPane(supplierTable);
        
        setCenter(stackPane);
        
    }
    
    
    
}
