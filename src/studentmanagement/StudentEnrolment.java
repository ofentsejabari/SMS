package studentmanagement;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.dbHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;
import static entry.SMS.setDataNotAvailablePlaceholder;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import entry.ToolTip;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static entry.SMS.getGraphics;

/**
 * @author ofentse
 */
public class StudentEnrolment extends  BorderPane{

    JFXButton btn_add, btn_refresh, btn_export;
    CustomTextField search;
    Label count;
    StackPane stackpane;
    
    public static String filter = "ALL";
    
    public static UpdateStudentProfile profileStage;
    public static CustomTableView<Student> studentTable;
    public static ObservableList<Student> studentList = FXCollections.observableArrayList();
    public StudentListWorkService studentListWork;
    
    public StudentEnrolment(){
        
        getStyleClass().add("container");
        
        count = new Label("");
        
        stackpane = new StackPane();
        
        studentListWork = new StudentListWorkService();
        
        JFXButton clear = new JFXButton("",getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "text-error", 20));
        clear.setStyle("-fx-background-radius:0 20 20 0; -fx-border-radius:0 20 20 0; -fx-cursor: hand;");
        clear.setOnAction((ActionEvent event) -> {
            search.clear();
        });
        
        JFXButton src = new JFXButton("",getGraphics(MaterialDesignIcon.ACCOUNT_SEARCH, "text-bluegray", 18));
        src.setStyle("-fx-background-radius:0 20 20 0; -fx-border-radius:0 20 20 0; -fx-cursor: hand;");
        
        search = new CustomTextField();
        search.getStyleClass().add("search-field");
        search.setRight(clear);
        
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String str = search.getText().trim(); 
            
            if(studentTable.getTableView().getItems() != null){
                ObservableList<Student>  studentLst  = dbHandler.getStudentList(filter);
                studentTable.getTableView().getItems().clear();
            
                if(str != null && str.length() > 0){
                    
                    for(Student student : studentLst) {
                        
                        if(student.getStudentID().toLowerCase().contains(str.toLowerCase())){
                            studentTable.getTableView().getItems().add(student);
                        }

                        if(student.getFirstName().toLowerCase().contains(str.toLowerCase())){
                            studentTable.getTableView().getItems().add(student);
                        }
                        
                        if(student.getClassID().toLowerCase().contains(str.toLowerCase())){
                            studentTable.getTableView().getItems().add(student);
                        } 
                    }
                    
                }else{
                    studentListWork.restart();
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
        
        btn_add = new JFXButton("Add Student");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            profileStage = new UpdateStudentProfile(null);
            profileStage.show();
        });
        
        btn_export = new JFXButton("Export");
        btn_export.getStyleClass().add("jfx-tool-button");
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh = new JFXButton("Refresh");
        btn_refresh.getStyleClass().add("jfx-tool-button");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            studentListWork.restart();
            search.clear();
        });
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        //----------------------------------------------------------------------
        
        toolbar.getChildren().addAll(search, new HSpacer(), btn_add, btn_export, btn_refresh);
      
        //-------------------Search bar and table-------------------------------
        studentTable = new CustomTableView<>();
        
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
        
        CustomTableColumn admissionNumber = new CustomTableColumn("ENROLLMENT ID");
        admissionNumber.setPercentWidth(20);
        admissionNumber.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        admissionNumber.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        if(!empty){
                            
                            final Hyperlink studentID = new Hyperlink(ID);
                            studentID.setTooltip(new ToolTip("Edit student profile", 300, 100));
                            studentID.setOnAction((ActionEvent event) -> {
                                new UpdateStudentProfile(dbHandler.getStudentByID(ID)).show();
                            });
                            
                            setGraphic(studentID);
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });
        
        CustomTableColumn fname = new CustomTableColumn("STUDENT NAME");
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
                            fullname.setTooltip(new ToolTip("View student profile"));
                            fullname.setOnAction((ActionEvent event) -> {
                                new StudentProfileStage(dbHandler.getStudentByName(ID)).show();
                            });
                            setGraphic(fullname);
                           
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });
        
        CustomTableColumn _class = new CustomTableColumn("CLASS NAME");
        _class.setPercentWidth(20);
        _class.setCellValueFactory(new PropertyValueFactory<>("classID"));
        _class.setCellFactory(TextFieldTableCell.forTableColumn());
        _class.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn contacts = new CustomTableColumn("PARENT/GUARDIAN CONTACTS");
        contacts.setPercentWidth(30);
        contacts.setCellValueFactory(new PropertyValueFactory<>("parentID"));
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
        
        studentTable.getTableView().getColumns().addAll(id, admissionNumber, fname, _class, contacts);
        
        VBox ph = setDataNotAvailablePlaceholder();
        studentTable.getTableView().setPlaceholder(ph);
        
        ProgressIndicator pi = new ProgressIndicator("Loading Student Data", "If network connection is very slow,"
                                           + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(studentListWork.runningProperty());
        studentTable.getTableView().itemsProperty().bind(studentListWork.valueProperty());
        
        stackpane.getChildren().addAll(studentTable, pi);
        setCenter(stackpane);
        
        studentListWork.start();
        studentListWork.restart();
    }   
    
    
    
    
    public class StudentListWork extends Task<ObservableList<Student>> {       
        @Override 
        protected ObservableList<Student> call() throws Exception {
            
            Platform.runLater(() -> {               
                studentTable.getTableView().setPlaceholder(new VBox());
            });
            
            studentList  = dbHandler.getStudentList(filter);
            for(int i=0; i<studentList.size(); i++){
                studentList.get(i).setId(i+1+"");
            }
                        
            Platform.runLater(() -> {
                studentTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return studentList;
        } 
    }

    
    public class StudentListWorkService extends Service<ObservableList<Student>> {

        @Override
        protected Task createTask() {
            return new StudentListWork();
        }
    }
}
