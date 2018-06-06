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
import mysqldriver.AdminQuery;
import mysqldriver.InventoryQuery;
import schooladministration.Stream;

/**
 * @author MOILE
 */
public class AssetAllocationList extends BorderPane{

    public static CustomTableView<StudentResourceList> studentAllocationTable;
    public StudentAllocationWorkService studentAllocationWork;
    private final StackPane stackPane;
    public static String filter = "",stream_ID="ALL";
    
    
    public static ObservableList<StudentResourceList> studentAllocationList = FXCollections.observableArrayList();
    
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
        
        btn_refresh.getStyleClass().add("jfx-tool-button");
        toolbar.getChildren().addAll(new HSpacer(), btn_refresh);
       // 
        
        /*
            CREATE facilityType TABLE
        */
        studentAllocationTable = new CustomTableView<>();
        
        CustomTableColumn studentID = new CustomTableColumn("#");
        studentID.setPercentWidth(20);
        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        studentID.setCellFactory(TextFieldTableCell.forTableColumn());
        studentID.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
                            status.setTooltip(new Tooltip("resource allocations"));
                            setGraphic(status);
                            
                            status.setOnAction((ActionEvent event) -> {
                                new StudentAllocatedResourceDialog(ID);
                            });
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn studentName = new CustomTableColumn("STUDENT NAME");
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        
        studentName.setPercentWidth(34.8);
        studentName.setCellFactory(TextFieldTableCell.forTableColumn());
        studentName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
        
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
        
        
        
        CustomTableColumn className = new CustomTableColumn("CLASS");
        className.setPercentWidth(30);
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
        
        CustomTableColumn resourceNo = new CustomTableColumn("NO. ALLOCATED");
        resourceNo.setPercentWidth(15);
        resourceNo.setCellFactory(TextFieldTableCell.forTableColumn());
        resourceNo.setCellValueFactory(new PropertyValueFactory<>("resourceNo"));
        resourceNo.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        
        
        studentAllocationTable.getTableView().getColumns().addAll(studentID
                ,studentName,className,resourceNo);
        VBox.setVgrow(studentAllocationTable, Priority.ALWAYS);
        
        //-- SET DATA
       // studentAllocationTable.getTableView().setItems(InventoryQuery.getStudentResourceList());
        
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
    
    
    public class StudentAllocationWork extends Task<ObservableList<StudentResourceList>> {       
        @Override 
        protected ObservableList<StudentResourceList> call() throws Exception {
            
            Platform.runLater(() -> {               
                studentAllocationTable.getTableView().setPlaceholder(new VBox());
            });
            
            if(!stream_ID.equals("ALL")){
                Stream stream=AdminQuery.getStreamByName(stream_ID);
                studentAllocationList  =  InventoryQuery.getStudentResourceList(stream.getStreamID());
            }
            else{
                 studentAllocationList  =  InventoryQuery.getStudentResourceList(stream_ID);
           
            }
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

    public class StudentAllocationWorkService extends Service<ObservableList<StudentResourceList>> {

        @Override
        protected Task createTask() {
            return new StudentAllocationWork();
        }
    }
    
}
