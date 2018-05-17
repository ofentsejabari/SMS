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
public class AssetAllocationList extends BorderPane{

    public static CustomTableView<StudentAllocationModel> studentAllocationTable;
    public StudentAllocationWorkService studentAllocationWork;
    private final StackPane stackPane;
    public String filter = "";
    
    public static ObservableList<StudentAllocationModel> studentAllocationList = FXCollections.observableArrayList();
    
    public AssetAllocationList() {
        
        studentAllocationWork = new StudentAllocationWorkService();
    
        getStyleClass().add("container");
        stackPane = new StackPane();
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_refresh = new JFXButton("Refresh");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
           studentAllocationWork.restart();
        });
        JFXButton btn_add = new JFXButton("Add");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
             new AddFacilityResources(filter).show();
        });
        
        btn_refresh.getStyleClass().add("jfx-tool-button");
        btn_add.getStyleClass().add("jfx-tool-button");
        toolbar.getChildren().addAll(new HSpacer(), btn_refresh, btn_add);
        
        /*
            CREATE facilityType TABLE
        */
        studentAllocationTable = new CustomTableView<>();
        
        CustomTableColumn assetID = new CustomTableColumn("#");
        assetID.setPercentWidth(4.9);
        assetID.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        assetID.setCellFactory(TextFieldTableCell.forTableColumn());
        assetID.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn studentName = new CustomTableColumn("STUDENT NAME");
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        
        studentName.setPercentWidth(40);
        studentName.setCellFactory(TextFieldTableCell.forTableColumn());
        studentName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
        
        @Override 
        public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            //setGraphic(new Label(InventoryQuery.getResourceName(ID).get(0)));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn assetName = new CustomTableColumn("RESOURCE NAME");
        assetName.setPercentWidth(20);
        assetName.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        assetName.setCellFactory(TextFieldTableCell.forTableColumn());
        assetName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(InventoryQuery.getFacilitiesName(ID).get(0)));
                        }else{ setGraphic(null); }
                    }
                };
            }
        }); 
        
        CustomTableColumn className = new CustomTableColumn("CLASS");
        className.setPercentWidth(20);
        className.setCellValueFactory(new PropertyValueFactory<>("className"));
        className.setCellFactory(TextFieldTableCell.forTableColumn());
        className.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn manufactureSN = new CustomTableColumn("SERIAL NO.");
        manufactureSN.setPercentWidth(15);
        manufactureSN.setCellFactory(TextFieldTableCell.forTableColumn());
        manufactureSN.setCellValueFactory(new PropertyValueFactory<>("manufactureSN"));
        manufactureSN.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        
        
        studentAllocationTable.getTableView().getColumns().addAll(assetID
                ,studentName,assetName,className,manufactureSN);
        VBox.setVgrow(studentAllocationTable, Priority.ALWAYS);
        
        //-- SET DATA
        studentAllocationTable.getTableView().setItems(InventoryQuery.getStudentAllocation("ALL"));
        
        VBox ph = setDataNotAvailablePlaceholder();
        studentAllocationTable.getTableView().setPlaceholder(ph);
        
        
        ProgressIndicator pi = new ProgressIndicator("Loading users data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(studentAllocationWork.runningProperty());
        studentAllocationTable.getTableView().itemsProperty().bind(studentAllocationWork.valueProperty());
        
        stackPane.getChildren().addAll(pi,studentAllocationTable);
        setCenter(stackPane);
        
        studentAllocationWork.start();
        studentAllocationWork.restart();
        
    }
    
    
    public class StudentAllocationWork extends Task<ObservableList<StudentAllocationModel>> {       
        @Override 
        protected ObservableList<StudentAllocationModel> call() throws Exception {
            
            Platform.runLater(() -> {               
                studentAllocationTable.getTableView().setPlaceholder(new VBox());
            });
            studentAllocationList  =  InventoryQuery.getStudentAllocation(filter);
            for(int i=0;i<studentAllocationList.size();i++){
                //studentAllocationList.get(i).setFacilitiesStatusID(i+1+"");
            }
                        
            Platform.runLater(() -> {  
                //count.setText(studentList.size()+" Student(s)");
                studentAllocationTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return studentAllocationList;
        } 
    }

    public class StudentAllocationWorkService extends Service<ObservableList<StudentAllocationModel>> {

        @Override
        protected Task createTask() {
            return new StudentAllocationWork();
        }
    }
    
}
