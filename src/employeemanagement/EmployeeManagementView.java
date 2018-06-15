/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagement;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.dbHandler;
import static entry.SMS.getGraphics;
import static entry.SMS.setDataNotAvailablePlaceholder;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mysqldriver.EmployeeQuery;
import org.controlsfx.control.textfield.CustomTextField;
import studentmanagement.Student;

/**
 *
 * @author MOILE
 */
public class EmployeeManagementView extends BorderPane{

    JFXButton btn_add, btn_refresh, btn_export;
    CustomTextField search;
    Label count;
    StackPane stackpane;
    
    public static String filter = "ALL";
    
  //  public static UpdateEmployeeProfile profileStage;
    public static CustomTableView<EmployeeModel> employeeTable;
    public static ObservableList<EmployeeModel> employeeList = FXCollections.observableArrayList();
    public EmployeeListWorkService employeeListWork;
    
   public EmployeeManagementView() {
        
        setPadding(new Insets(10));
        
        count = new Label("");
        
        stackpane = new StackPane();
        
        employeeListWork = new EmployeeListWorkService();
        
        JFXButton clear = new JFXButton("",getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "text-error", 20));
        clear.setStyle("-fx-background-radius:0 20 20 0; -fx-border-radius:0 20 20 0; -fx-cursor: hand;");
        clear.setOnAction((ActionEvent event) -> {
            search.clear();
        });
        
        JFXButton src = new JFXButton("",getGraphics(MaterialDesignIcon.ACCOUNT_SEARCH, "text-bluegray", 20));
        src.setStyle("-fx-background-radius:0 20 20 0; -fx-border-radius:0 20 20 0; -fx-cursor: hand;");
        
        search = new CustomTextField();
        search.getStyleClass().add("search-field");
        search.setRight(clear);
        
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String str = search.getText().trim(); 
            
            if(employeeTable.getTableView().getItems() != null){
                ObservableList<Student>  studentList  = dbHandler.getStudentList(filter);
                employeeTable.getTableView().getItems().clear();
            
                if(str != null && str.length() > 0){
                    
                    for(EmployeeModel employee : employeeList) {
                        
                        if(employee.getEmployeeID().toLowerCase().contains(str.toLowerCase())){
                            employeeTable.getTableView().getItems().add(employee);
                        }

                        if(employee.getFirstName().toLowerCase().contains(str.toLowerCase())){
                            employeeTable.getTableView().getItems().add(employee);
                        }
                        
                        if(employee.getDesignation().toLowerCase().contains(str.toLowerCase())){
                            employeeTable.getTableView().getItems().add(employee);
                        } 
                    }
                    
                }else{
                    employeeListWork.restart();
                }
            }
        });
        
        search.setRight(src);
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.trim().equalsIgnoreCase("")){
                search.setRight(clear);
            }else{
                search.setRight(src);
            }
        });
        
        btn_add = new JFXButton("Add Employee");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_export = new JFXButton("Export");
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh = new JFXButton("Refresh");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            employeeListWork.restart();
            search.clear();
        });
        
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        
        /////////////////////////////////////////////////////////////////////
        ToggleButton ref = new ToggleButton("Refresh", 
                SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 20));
        ref.getStyleClass().add("left-pill");
        ref.setOnAction((ActionEvent event) -> {
            employeeListWork.restart();
            search.clear();
        });
        
        ToggleButton ad = new ToggleButton("Add Student",
                SMS.getGraphics(MaterialDesignIcon.ACCOUNT_PLUS, "icon-default", 20));
        ad.getStyleClass().add("center-pill");
        
        
        ToggleButton exp = new ToggleButton("Export", 
                SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 20));
        exp.getStyleClass().add("right-pill");
        
 
        final ToggleGroup group = new ToggleGroup();
        ref.setToggleGroup(group);
        ad.setToggleGroup(group);
        exp.setToggleGroup(group);
        group.selectToggle(ref);
 
        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            if (newValue == null) {
                group.selectToggle(oldValue);
            }
        });
 
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(ref, ad, exp);
 
        /////////////////////////////////////////////////////////////////////
        
        
        toolbar.getChildren().addAll(search, new HSpacer(), btn_add, btn_export, btn_refresh);
      
        //-------------------Search bar and table-------------------------------
        employeeTable = new CustomTableView<>();
        
        CustomTableColumn id = new CustomTableColumn("");
        id.setPercentWidth(4.9);
        id.setCellValueFactory(new PropertyValueFactory<>("gender"));
        id.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        if(!empty){
                            setGraphic(new Label("", SMS.getIcon("u6.png", 24)));
                            if(ID.equalsIgnoreCase("MALE")){
                                setGraphic(new Label("", SMS.getIcon("u8.png", 24)));
                            }
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });
        
        CustomTableColumn staffID = new CustomTableColumn("EMPLOYEE ID");
        staffID.setPercentWidth(20);
        staffID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        staffID.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        if(!empty){
                            
                            final Hyperlink employeeID = new Hyperlink(ID);
                            employeeID.setTooltip(new Tooltip("Edit employee profile"));
                            employeeID.setOnAction((ActionEvent event) -> {
                               // new UpdateStudentProfile(dbHandler.getStudentByID(ID)).show();
                            });
                            
                            setGraphic(employeeID);
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });
        
        CustomTableColumn fname = new CustomTableColumn("EMPLOYEE NAME");
        fname.setPercentWidth(25);
        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        fname.setCellFactory(TextFieldTableCell.forTableColumn());
        fname.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        final Hyperlink fullname = new Hyperlink(ID);
                        if(!empty){
                            fullname.setContentDisplay(ContentDisplay.LEFT);
                            fullname.setTooltip(new Tooltip("View employee profile"));
                            fullname.setOnAction((ActionEvent event) -> {
                                //new StudentProfileStage(dbHandler.getStudentByName(ID)).show();
                            });
                            setGraphic(fullname);
                           
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });
        
        CustomTableColumn designation = new CustomTableColumn("DESIGNATION");
        designation.setPercentWidth(20);
        designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        designation.setCellFactory(TextFieldTableCell.forTableColumn());
        designation.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn contacts = new CustomTableColumn("EMAIL ADDRESS");
        contacts.setPercentWidth(30);
        contacts.setCellValueFactory(new PropertyValueFactory<>("email"));
        contacts.setCellFactory(TextFieldTableCell.forTableColumn());
        contacts.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>(){
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String status, boolean empty) {
                        super.updateItem(status, empty);
                        if(!empty){
                            
                            setGraphic(new Label(status));
                           
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        employeeTable.getTableView().getColumns().addAll(id, staffID, fname, designation, contacts);
        
        VBox ph = setDataNotAvailablePlaceholder();
        employeeTable.getTableView().setPlaceholder(ph);
        
        ProgressIndicator pi = new ProgressIndicator("Loading Student Data", "If network connection is very slow,"
                                           + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(employeeListWork.runningProperty());
        employeeTable.getTableView().itemsProperty().bind(employeeListWork.valueProperty());
        
        stackpane.getChildren().addAll(employeeTable, pi);
        setCenter(stackpane);
        
        employeeListWork.start();
        employeeListWork.restart();
    }   
    
  
 
 
 public class EmployeeListWork extends Task<ObservableList<EmployeeModel>> {       
        @Override 
        protected ObservableList<EmployeeModel> call() throws Exception {
            
            Platform.runLater(() -> {               
                employeeTable.getTableView().setPlaceholder(new VBox());
            });
            
           employeeList  = EmployeeQuery.getEmployeeList(true);
                        
            Platform.runLater(() -> {
                employeeTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return employeeList;
        } 
    }

    
    public class EmployeeListWorkService extends Service<ObservableList<EmployeeModel>> {

        @Override
        protected Task createTask() {
            return new EmployeeListWork();
        }
    }
}
