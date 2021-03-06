/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schooladministration;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import employeemanagement.EmployeeModel;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.dbHandler;
import static entry.SMS.setDataNotAvailablePlaceholder;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;
import studentmanagement.ExportMenu;
import static entry.SMS.getGraphics;
import mysqldriver.EmployeeQuery;
import static entry.SMS.getGraphics;
import javafx.scene.control.Tooltip;

/**
 *
 * @author ofentse
 */
public class UserDirectory extends BorderPane{
    
    public static CustomTableView<User> usersTable;
    public static ObservableList<User> usersList = FXCollections.observableArrayList();
    
    public static UsersListWorkService usersListWork;
    
    public JFXButton btn_add, btn_export, btn_refresh;

    public UserDirectory() {
        
        setPadding(new Insets(10, 5, 5, 5));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        
        CustomTextField search = new CustomTextField();
        search.getStyleClass().add("search-field");
        
        usersListWork = new UsersListWorkService();
        
        JFXButton clear = new JFXButton("",getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "text-error", 20));
        clear.setStyle("-fx-background-radius:0 20 20 0; -fx-border-radius:0 20 20 0; -fx-cursor: hand;");
        clear.setOnAction((ActionEvent event) -> {
            search.clear();
        });
        
        JFXButton src = new JFXButton("",getGraphics(MaterialDesignIcon.ACCOUNT_SEARCH, "text-bluegray", 20));
        src.setStyle("-fx-background-radius:0 20 20 0; -fx-border-radius:0 20 20 0; -fx-cursor: hand;");
      
        search.setRight(clear);
        
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String str = search.getText().trim(); 
            
            if(usersTable.getTableView().getItems() != null){
                ObservableList<User>  studentList  = dbHandler.getUsers();
                usersTable.getTableView().getItems().clear();
            
                if(str != null && str.length() > 0){
                    
                    for(User user : studentList) {
                        
                        if(user.getUserID().toLowerCase().contains(str.toLowerCase())){
                            usersTable.getTableView().getItems().add(user);
                        }
                    }
                    
                }else{
                    usersListWork.restart();
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
        
        btn_add = new JFXButton("Add User");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_KEY, "text-bluegray", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_export = new JFXButton("Export");
        btn_export.getStyleClass().add("jfx-tool-button");
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "text-bluegray", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            new ExportMenu(btn_export);
        });
        
        btn_refresh = new JFXButton("Refresh");
        btn_refresh.getStyleClass().add("jfx-tool-button");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "text-bluegray", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            usersListWork.restart();
            search.clear();
        });
        
        
        toolbar.getChildren().addAll(search, new HSpacer(), btn_add, btn_export, btn_refresh);
        
        //-------------------Search bar and table-------------------------------
        usersTable = new CustomTableView<>();
        
        CustomTableColumn cnt = new CustomTableColumn("");
        cnt.setPercentWidth(5);
        cnt.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cnt.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn userName = new CustomTableColumn("User Name");
        userName.setPercentWidth(20);
        userName.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        Hyperlink description = new Hyperlink(ID);
                        description.getStyleClass().add("tableLink");
                        
                        if(!empty){
                            
                            //description.setText(employee.getFullName());
                            if(ID.equalsIgnoreCase("administrator")){//-- root account
                                
                                description.setText("Administrator");
                                description.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_SETTINGS_VARIANT, "text-bluegray", 18));
                            }else{
                                
                                EmployeeModel employee = EmployeeQuery.getEmployeeByID(ID);
                                description.setText(employee.getFullName());
                                description.setOnAction((ActionEvent event) -> {
                                });
                            }
                            setGraphic(description);
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });

        CustomTableColumn lastLogin = new CustomTableColumn("Last Login");
        lastLogin.setPercentWidth(15);
        lastLogin.setCellValueFactory(new PropertyValueFactory<>("lastLogin"));
        lastLogin.setCellFactory(TextFieldTableCell.forTableColumn());
        lastLogin.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            Label approved = new Label(ID);
                            setGraphic(approved);
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });
        
        CustomTableColumn status = new CustomTableColumn("Status");
        status.setPercentWidth(10);
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        status.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String status, boolean empty) {
                        super.updateItem(status, empty);
                        HBox actions = new HBox();
                        actions.setStyle("-fx-padding:0");
                        actions.setSpacing(5);
                        
                        if(!empty){
                            
                            if(status.equals("1")){
                                Label approved = new Label("Active", SMS.getGraphics(MaterialDesignIcon.ACCOUNT_CHECK, "text-success", 20));
                                actions.getChildren().addAll(approved);
                                
                            }else{
                                Label approved = new Label("Inactice", SMS.getGraphics(MaterialDesignIcon.ACCOUNT_OFF, "text-error", 20));
                                actions.getChildren().addAll(approved);
                            }
                            setGraphic(actions);
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn ctrl = new CustomTableColumn("");
        ctrl.setPercentWidth(49.9);
        ctrl.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ctrl.setCellFactory(TextFieldTableCell.forTableColumn());
        ctrl.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String status, boolean empty) {
                        super.updateItem(status, empty);
                        HBox actions = new HBox();
                        actions.setStyle("-fx-padding:0");
                        actions.setSpacing(5);
                        
                        if(!empty){
                            
                            JFXButton close = new JFXButton("",SMS.getGraphics(MaterialDesignIcon.DELETE_SWEEP, "text-error", 24));
                            close.setTooltip(new Tooltip("Remove Account"));
                            close.getStyleClass().add("jfx-close-button");
                            close.setOnAction((ActionEvent event) -> {
                              
                            });
                            
                            actions.getChildren().add(close);
                            setGraphic(actions);
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        
        
        
        
        usersTable.getTableView().getColumns().addAll(cnt, userName, lastLogin, status, ctrl);
        
        VBox ph = setDataNotAvailablePlaceholder();
        usersTable.getTableView().setPlaceholder(ph);
        
        
        ProgressIndicator pi = new ProgressIndicator("Loading users data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(usersListWork.runningProperty());
        usersTable.getTableView().itemsProperty().bind(usersListWork.valueProperty());
        
        
        setCenter(new StackPane(usersTable, pi));
        
        usersListWork.start();
        usersListWork.restart();
        
        
    }
    
    public class UsersListWork extends Task<ObservableList<User>> {       
        @Override 
        protected ObservableList<User> call() throws Exception {
            
            Platform.runLater(() -> {               
                usersTable.getTableView().setPlaceholder(new VBox());
            });
            usersList  = dbHandler.getUsers();
            for(int i=0;i<usersList.size();i++){
                usersList.get(i).setID(i+1+"");
            }
                        
            Platform.runLater(() -> {  
                //count.setText(usersList.size()+" Users(s)");
                usersTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return usersList;
        } 
    }

    public class UsersListWorkService extends Service<ObservableList<User>> {

        @Override
        protected Task createTask() {
            return new UsersListWork();
        }
    }
}
