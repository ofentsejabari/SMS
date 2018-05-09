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
import static inventorymanagement.control.FacilitiesController.facilitiesList;
import static inventorymanagement.control.InventoryListController.filter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mysqldriver.InventoryQuery;
import static mysqldriver.InventoryQuery.facilitiesList;

/**
 *
 * @author MOILE
 */
public class FacilitiesManagement extends BorderPane{

    public static CustomTableView<Facilities> facilitiesTable;
    public static FacilitiesWorkService facilitiesWork;
    private final StackPane stackPane;
    
    public FacilitiesManagement() {
        
        facilitiesWork = new FacilitiesWorkService();
    
        getStyleClass().add("container");
        stackPane = new StackPane();
        
        setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Add");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new AddFacilities().show();
        });
        
        JFXButton btn_refresh = new JFXButton("Refresh");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            facilitiesWork.restart();
           // new DialogUI("ahgjagjcas as ", DialogUI.ERROR_NOTIF, stackPane).show();
        });
        
        toolbar.getChildren().addAll(new HSpacer(), btn_refresh, btn_add);
        
        /*
            CREATE SUPPLIER TABLE
        */
        facilitiesTable = new CustomTableView<>();
        
        CustomTableColumn facilitiesID = new CustomTableColumn("#");
        facilitiesID.setPercentWidth(4.9);
        facilitiesID.setCellValueFactory(new PropertyValueFactory<>("facilitiesID"));
        facilitiesID.setCellFactory(TextFieldTableCell.forTableColumn());
        facilitiesID.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn facilitiesName = new CustomTableColumn("ROOM NAME");
        facilitiesName.setPercentWidth(20);
        facilitiesName.setCellValueFactory(new PropertyValueFactory<>("facilitiesName"));
       facilitiesName.setCellFactory(TextFieldTableCell.forTableColumn());
        facilitiesName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        final Hyperlink status = new Hyperlink("");
                        if(!empty){
                            status.getStyleClass().add("tableLink");
                            status.setText(ID);
                            status.setContentDisplay(ContentDisplay.LEFT);
                            status.setTooltip(new Tooltip("facility status"));
                            setGraphic(status);
                            
                            status.setOnAction((ActionEvent event) -> {
                               //  System.out.println(facilitiesID.getCellFactory());
                                new FacilityStatus(ID).show();
                            });
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn facilitiesType = new CustomTableColumn("ROOM TYPE");
        facilitiesType.setPercentWidth(20);
        facilitiesType.setCellValueFactory(new PropertyValueFactory<>("facilitiesType"));
        facilitiesType.setCellFactory(TextFieldTableCell.forTableColumn());
        facilitiesType.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
                
        CustomTableColumn facilitiesDept= new CustomTableColumn("DEPARTMENT");
        facilitiesDept.setPercentWidth(20);
        facilitiesDept.setCellValueFactory(new PropertyValueFactory<>("facilitiesDept"));
        facilitiesDept.setCellFactory(TextFieldTableCell.forTableColumn());
        facilitiesDept.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn facilitiesStatus= new CustomTableColumn("FACILITY CONDITION");
        facilitiesStatus.setPercentWidth(20);
        facilitiesStatus.setCellValueFactory(new PropertyValueFactory<>("facilitiesStatus"));
        facilitiesStatus.setCellFactory(TextFieldTableCell.forTableColumn());
        facilitiesStatus.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
      
        
        CustomTableColumn facilitiesCapacity= new CustomTableColumn("CAPACITY");
        facilitiesCapacity.setPercentWidth(20);
        facilitiesCapacity.setCellValueFactory(new PropertyValueFactory<>("facilitiesCapacity"));
        facilitiesCapacity.setCellFactory(TextFieldTableCell.forTableColumn());
        facilitiesCapacity.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        facilitiesTable.getTableView().getColumns().addAll(facilitiesID,facilitiesName,facilitiesType,facilitiesDept,facilitiesStatus,facilitiesCapacity);
        VBox.setVgrow(facilitiesTable, Priority.ALWAYS);
        
        //-- SET DATA
        facilitiesTable.getTableView().setItems(InventoryQuery.facilitiesList("All"));
        
        
        VBox ph = setDataNotAvailablePlaceholder();
        facilitiesTable.getTableView().setPlaceholder(ph);
        
        ProgressIndicator pi = new ProgressIndicator("Loading users data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(facilitiesWork.runningProperty());
        facilitiesTable.getTableView().itemsProperty().bind(facilitiesWork.valueProperty());
        
        VBox vb =new VBox(facilitiesTable);
        
        stackPane.getChildren().addAll(pi,vb);
        setCenter(stackPane);
        
        facilitiesWork.start();
        facilitiesWork.restart();
        
    }
    
    public class FacilitiesWork extends Task<ObservableList<Facilities>> {       
        @Override 
        protected ObservableList<Facilities> call() throws Exception {
            
            Platform.runLater(() -> {               
                facilitiesTable.getTableView().setPlaceholder(new VBox());
            });
            facilitiesList  =  InventoryQuery.facilitiesList(filter);
            
            for(int i=0;i<facilitiesList.size();i++){
                facilitiesList.get(i).setFacilitiesID(i+1+"");
            }
                        
            Platform.runLater(() -> {  
                //count.setText(studentList.size()+" Student(s)");
                facilitiesTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return facilitiesList;
        } 
    }

    public class FacilitiesWorkService extends Service<ObservableList<Facilities>> {

        @Override
        protected Task createTask() {
            return new FacilitiesWork();
        }
    }
    
}
