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
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.setDataNotAvailablePlaceholder;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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

/**
 * @author MOILE
 */
public class BookItem extends BorderPane{

    public static CustomTableView<BookCopies> bookDetailsTable;
    public static BookWorkService bookWork;
    private final StackPane stackPane;
    public static String filter = "";
    public static Label lab;
    
    public static ObservableList<BookCopies> bookList = FXCollections.observableArrayList();
    
    public BookItem() {
        
        bookWork = new BookWorkService();
        getStyleClass().add("container");
        stackPane = new StackPane();
        lab = new Label();
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
                
        JFXButton btn_refresh = new JFXButton("Refresh");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            bookWork.restart();
        });
        
        JFXButton btn_add = new JFXButton("Add");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
             //add book copy
             new AddBookCopy(filter);
        });
        
        btn_refresh.getStyleClass().add("jfx-tool-button");
        btn_add.getStyleClass().add("jfx-tool-button");
        toolbar.getChildren().addAll(new HSpacer(),lab,new HSpacer(), btn_refresh, btn_add);
        
        /*
            CREATE facilityType TABLE
        */
        bookDetailsTable = new CustomTableView<>();
        
        CustomTableColumn copyNo = new CustomTableColumn("#");
        copyNo.setPercentWidth(4.9);
        copyNo.setCellValueFactory(new PropertyValueFactory<>("copyNo"));
        copyNo.setCellFactory(TextFieldTableCell.forTableColumn());
        copyNo.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn serialNo = new CustomTableColumn("Serial Number");
        serialNo.setCellValueFactory(new PropertyValueFactory<>("serialNo"));
        serialNo.setPercentWidth(20);
        serialNo.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNo.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
        
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
        
        CustomTableColumn bookLocation = new CustomTableColumn("Status");
        bookLocation.setPercentWidth(15);
        bookLocation.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
        bookLocation.setCellFactory(TextFieldTableCell.forTableColumn());
        bookLocation.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn edition = new CustomTableColumn("Edition");
        edition.setPercentWidth(10);
        edition.setCellValueFactory(new PropertyValueFactory<>("edition"));
        edition.setCellFactory(TextFieldTableCell.forTableColumn());
        edition.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn supplierName = new CustomTableColumn("Supplier Name");
        supplierName.setPercentWidth(20);
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
        
        CustomTableColumn cost = new CustomTableColumn("Cost");
        cost.setPercentWidth(15);
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        cost.setCellFactory(TextFieldTableCell.forTableColumn());
        cost.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn invoiceNo = new CustomTableColumn("Invoice No.");
        invoiceNo.setPercentWidth(15);
        invoiceNo.setCellValueFactory(new PropertyValueFactory<>("cost"));
        invoiceNo.setCellFactory(TextFieldTableCell.forTableColumn());
        invoiceNo.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        bookDetailsTable.getTableView().getColumns().addAll(copyNo
                ,serialNo,bookLocation,edition,invoiceNo,supplierName,cost);
        
        VBox.setVgrow(bookDetailsTable, Priority.ALWAYS);
        
       
        VBox ph = setDataNotAvailablePlaceholder();
        bookDetailsTable.getTableView().setPlaceholder(ph);
        
        ProgressIndicator pi = new ProgressIndicator("Loading users data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(bookWork.runningProperty());
        bookDetailsTable.getTableView().itemsProperty().bind(bookWork.valueProperty());
        
        stackPane.getChildren().addAll(pi,bookDetailsTable);
        setCenter(stackPane);
        
        bookWork.start();
        bookWork.restart();
        
    }
    
    
    public class BookWork extends Task<ObservableList<BookCopies>> {       
        @Override 
        protected ObservableList<BookCopies> call() throws Exception {
            
            Platform.runLater(() -> {               
                bookDetailsTable.getTableView().setPlaceholder(new VBox());
            });
            bookList  =  InventoryQuery.getBookCopiesByBookId(filter);
                       
            Platform.runLater(() -> {  
                bookDetailsTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return bookList;
        } 
    }

    public class BookWorkService extends Service<ObservableList<BookCopies>> {

        @Override
        protected Task createTask() {
            return new BookWork();
        }
    }
    
}
