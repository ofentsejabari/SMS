/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.getGraphics;
import static entry.SMS.setDataNotAvailablePlaceholder;
import entry.ToolTip;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
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
 *
 * @author MOILE
 */
public class StudentAllocatedResourceDialog extends JFXDialog{

    public static CustomTableView<StudentAllocationModel> studentAllocationTable;
    public static StudentAllocationWorkService studentAllocationWork;
   
    private final StackPane stackPane;
    public String studentID = "",filter2 = "";
    
    
    public StudentAllocatedResourceDialog(String studentID) {
        
        
        //-- Parent Container --------------------------------------------------
        StackPane stack = new StackPane();
        BorderPane container = new BorderPane();
        stack.getChildren().add(container);
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        //Label title = new Label("Database Configuration", getGraphics(MaterialDesignIcon.SERVER_NETWORK, "", 32));
        //title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //---------------------- END SCREEN DECORATION -------------------------
        
        BorderPane bp = new BorderPane();
        this.studentID = studentID;
        
        
        studentAllocationWork = new StudentAllocationWorkService();
        bp.getStyleClass().add("container");
        stackPane = new StackPane();
        stackPane.setStyle("-fx-padding: 10 5 0 5;");
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        bp.setTop(toolbar);
        
        JFXComboBox<String> resource = new  JFXComboBox<>(InventoryQuery.getInventoryType());
        resource.setPromptText("Asset Type");
        resource.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter2 = newValue;
            studentAllocationWork.restart();
              
        });
        
        JFXButton btn_add = new JFXButton("Add");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new AllocateResourceDialog(studentID);
        });
        
        JFXButton btn_refresh = new JFXButton("Refresh");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            studentAllocationWork.restart();
        });
        btn_refresh.getStyleClass().add("jfx-tool-button");
        btn_add.getStyleClass().add("jfx-tool-button");
        toolbar.getChildren().addAll(resource, new HSpacer(), btn_refresh, btn_add);
        
        /*
            CREATE SUPPLIER TABLE
        */
        studentAllocationTable = new CustomTableView<>();
        
        CustomTableColumn assetID = new CustomTableColumn("#");
        assetID.setPercentWidth(7.9);
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
        
        CustomTableColumn assetName = new CustomTableColumn("ASSET NAME");
        assetName.setPercentWidth(70);
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
                            setGraphic(new Label(ID));
                            
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
                
        CustomTableColumn manufactureSN = new CustomTableColumn("SERIAL NO.");
        manufactureSN.setPercentWidth(25);
        manufactureSN.setCellValueFactory(new PropertyValueFactory<>("manufactureSN"));
        manufactureSN.setCellFactory(TextFieldTableCell.forTableColumn());
        manufactureSN.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() 
        {
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
        
        studentAllocationTable.getTableView().getColumns().addAll(assetID,assetName,manufactureSN);
        VBox.setVgrow(studentAllocationTable, Priority.ALWAYS);
        
        
        VBox ph = setDataNotAvailablePlaceholder();
        studentAllocationTable.getTableView().setPlaceholder(ph);
        
        ProgressIndicator pi = new ProgressIndicator("Loading users data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(studentAllocationWork.runningProperty());
        studentAllocationTable.getTableView().itemsProperty().bind(studentAllocationWork.valueProperty());
        
        
        stackPane.getChildren().addAll(pi,studentAllocationTable);
        bp.setCenter(stackPane);
        container.setCenter(bp);
        
        studentAllocationWork.start();
        studentAllocationWork.restart();
        
          //-- Validate and save the form  ---------------------------------------
//        JFXButton save = new JFXButton("Update Configuration");
//        save.getStyleClass().add("dark-blue");
//        save.setTooltip(new ToolTip("Save Department"));
//        save.setOnAction((ActionEvent event) -> {
//            
//            if(!dbHost.getText().trim().equals("")& 
//                    !dbName.getText().trim().equals("")&
//                    !username.getText().trim().equals("")){
//
//                        config.setDBHost(dbHost.getText().trim());
//                        config.setDBName(dbName.getText().trim());
//                        config.setDBUserName(username.getText().trim());
//
//                        //-- Save configuration file with update details --
//                        DBConfig.serializeObject(config);
//
//                        new DialogUI("Database configuration details has been updated successfully. "
//                                + "Restart the application for changes to effect.",
//                            DialogUI.SUCCESS_NOTIF, SMS.MAIN_UI, this).show(); 
//            }else{
//                dbHost.validate();
//                dbName.validate();
//                username.validate();
//
//                new DialogUI( "Ensure that mandatory field are filled up... ",
//                    DialogUI.ERROR_NOTIF, stackPane, null).show();
//            }
//            
//        });
        

        //-- footer ------------------------------------------------------------
//        HBox footer = new HBox(save);
//        footer.getStyleClass().add("secondary-toolbar");
//        container.setBottom(footer);

        //-- Set JFXDialog view  -----------------------------------------------
        setDialogContainer(SMS.MAIN_UI);
        setTransitionType(JFXDialog.DialogTransition.CENTER);
        setContent(stack);
        setOverlayClose(true);
        stack.setPrefSize(600, 300);
        show();
        
    }
    
    
    
    public class StudentAllocationWork extends Task<ObservableList<StudentAllocationModel>> {       
        @Override 
        protected ObservableList<StudentAllocationModel> call() throws Exception {
            ObservableList<StudentAllocationModel> studentAllocationList = FXCollections.observableArrayList();
            Platform.runLater(() -> {               
                studentAllocationTable.getTableView().setPlaceholder(new VBox());
            });
           studentAllocationList  =  InventoryQuery.getStudentAllocation(studentID,filter2);
            
            for(int i=0;i<studentAllocationList.size();i++){
                studentAllocationList.get(i).setAssetID(i+1+"");
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
