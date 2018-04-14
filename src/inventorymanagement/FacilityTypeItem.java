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
import static inventorymanagement.control.InventoryListController.filter;
import static inventorymanagement.control.InventoryListController.inventList;
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
import static studentmanagement.control.StudentEnrolmentController.studentTable;

/**
 * @author MOILE
 */
public class FacilityTypeItem extends BorderPane{

    public static CustomTableView<FacilitiesType> facilityTypeTable;
    public static FacilityTypeListWorkService facilityTypeWork;
    private final StackPane stackPane;
    
    public static ObservableList<FacilitiesType> facilityTypeList = FXCollections.observableArrayList();
    
    public FacilityTypeItem() {
        
        facilityTypeWork = new FacilityTypeListWorkService();
    
        getStyleClass().add("container");
        stackPane = new StackPane();
        
        setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Add");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            
        });
                
        JFXButton btn_refresh = new JFXButton("Refresh");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            facilityTypeWork.restart();
        });
   
        toolbar.getChildren().addAll(new HSpacer(), btn_refresh, btn_add);
        
        /*
            CREATE facilityType TABLE
        */
        facilityTypeTable = new CustomTableView<>();
        
        CustomTableColumn facilityTypeID = new CustomTableColumn("#");
        facilityTypeID.setPercentWidth(10);
        facilityTypeID.setCellValueFactory(new PropertyValueFactory<>("facilitiesTypeID"));
        facilityTypeID.setCellFactory(TextFieldTableCell.forTableColumn());
        facilityTypeID.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn facilityTypeName = new CustomTableColumn("FACILITY NAME");
        facilityTypeName.setPercentWidth(45);
        facilityTypeName.setCellValueFactory(new PropertyValueFactory<>("facilitiesTypeName"));
        facilityTypeName.setCellFactory(TextFieldTableCell.forTableColumn());
        facilityTypeName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn facilityTypeQuantity = new CustomTableColumn("QUANTITY");
        facilityTypeQuantity.setPercentWidth(45);
        facilityTypeQuantity.setCellValueFactory(new PropertyValueFactory<>("facilitiesTypeQuantity"));
        facilityTypeQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
        facilityTypeQuantity.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
                        
        facilityTypeTable.getTableView().getColumns().addAll(facilityTypeID, facilityTypeName,facilityTypeQuantity);
        VBox.setVgrow(facilityTypeTable, Priority.ALWAYS);
        
        //-- SET DATA
        facilityTypeTable.getTableView().setItems(InventoryQuery.facilitiesTypeList("ALL"));
        
        VBox ph = setDataNotAvailablePlaceholder();
        facilityTypeTable.getTableView().setPlaceholder(ph);
        
        
        ProgressIndicator pi = new ProgressIndicator("Loading users data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(facilityTypeWork.runningProperty());
        facilityTypeTable.getTableView().itemsProperty().bind(facilityTypeWork.valueProperty());
        HBox typeHolder = new HBox(facilityTypeTable,new VBox());
        stackPane.getChildren().addAll(pi,typeHolder);
        setCenter(stackPane);
        
        facilityTypeWork.start();
        facilityTypeWork.restart();
        
    }
    
    public class FacilityTypeListWork extends Task<ObservableList<FacilitiesType>> {       
        @Override 
        protected ObservableList<FacilitiesType> call() throws Exception {
            
            Platform.runLater(() -> {               
                facilityTypeTable.getTableView().setPlaceholder(new VBox());
            });
            facilityTypeList  =  InventoryQuery.facilitiesTypeList(filter);
            for(int i=0;i<facilityTypeList.size();i++){
                facilityTypeList.get(i).setFacilitiesTypeID(i+1+"");
            }
                        
            Platform.runLater(() -> {  
                //count.setText(studentList.size()+" Student(s)");
                facilityTypeTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return facilityTypeList;
        } 
    }

    public class FacilityTypeListWorkService extends Service<ObservableList<FacilitiesType>> {

        @Override
        protected Task createTask() {
            return new FacilityTypeListWork();
        }
    }
    
}
