package studentmanagement.control;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.dbHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;
import studentmanagement.ExportMenu;
import studentmanagement.Student;
import studentmanagement.StudentProfileStage;
import static entry.SMS.setDataNotAvailablePlaceholder;
import static entry.SMS.getGraphics;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class StudentEnrolmentController implements Initializable {

    @FXML
    private JFXButton btn_add, btn_refresh, btn_export;
    
    @FXML
    private CustomTextField search;
    
    @FXML
    private Label count;
    
    @FXML
    private StackPane stackpane;
    
    
    public static String filter = "ALL";
    
    public static StudentProfileStage profileStage;
    public static CustomTableView<Student> studentTable;
    public static ObservableList<Student> studentList = FXCollections.observableArrayList();
    
    public static StudentListWorkService studentListWork;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        count.setText("");
        
        studentListWork = new StudentListWorkService();
        
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
            
            if(studentTable.getTableView().getItems() != null){
                ObservableList<Student>  studentList  = dbHandler.studentList(filter);
                studentTable.getTableView().getItems().clear();
            
                if(str != null && str.length() > 0){
                    
                    for(Student student : studentList) {
                        
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
            count.setText(studentList.size()+" Student(s)");
        });
        
        search.setRight(src);
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.trim().equalsIgnoreCase("")){
                search.setRight(clear);
            }else{
                search.setRight(src);
            }
        });
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            profileStage = new StudentProfileStage(new Student("Ofente", "ofentse", "jabari", "thato", "oabona", "kgopolo",
                                                                filter, filter, filter, filter, filter, filter, filter, filter,
                                                                filter, filter, filter, filter, filter, filter, filter, filter,
                                                                filter, filter));
            profileStage.show();
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            new ExportMenu(btn_export);
        });
        
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            studentListWork.restart();
            search.clear();
        });
        
        //-------------------Search bar and table-------------------------------
        studentTable = new CustomTableView<>();
        
        CustomTableColumn id = new CustomTableColumn("");
        id.setPercentWidth(4.9);
        id.setCellValueFactory(new PropertyValueFactory<>("enrollID"));
        id.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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

        
        CustomTableColumn admissionNumber = new CustomTableColumn("Enrolment Code");
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
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });

        CustomTableColumn fname = new CustomTableColumn("Fullname");
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
                        
                        final Hyperlink fullname = new Hyperlink("");
                        if(!empty){
                            fullname.getStyleClass().add("tableLink");
                            fullname.setText(ID);
                            fullname.setContentDisplay(ContentDisplay.LEFT);
                            fullname.setTooltip(new Tooltip("Student profile"));
                            setGraphic(fullname);
                           
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });
        
        CustomTableColumn contacts = new CustomTableColumn("Class");
        contacts.setPercentWidth(20);
        contacts.setCellValueFactory(new PropertyValueFactory<>("classID"));
        contacts.setCellFactory(TextFieldTableCell.forTableColumn());
        contacts.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            HBox contacts = new HBox(new Label(ID));
                            contacts.setStyle("-fx-padding:0");
                            contacts.setSpacing(5);
                            
                            setGraphic(contacts);
                           
                        }else{ setGraphic(null); }
                      
                    }
                };
            }
        });
        
        CustomTableColumn status = new CustomTableColumn("Parent Contacts");
        status.setPercentWidth(30);
        status.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        status.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        studentTable.getTableView().getColumns().addAll(id, admissionNumber, fname, contacts, status);
        
        VBox ph = setDataNotAvailablePlaceholder();
        studentTable.getTableView().setPlaceholder(ph);
        
        
        ProgressIndicator pi = new ProgressIndicator("Loading student data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(studentListWork.runningProperty());
        studentTable.getTableView().itemsProperty().bind(studentListWork.valueProperty());
        
        
        stackpane.getChildren().addAll(studentTable, pi);
        
        studentListWork.start();
        studentListWork.restart();
    }   
    
    
    
    
    
    public class StudentListWork extends Task<ObservableList<Student>> {       
        @Override 
        protected ObservableList<Student> call() throws Exception {
            
            Platform.runLater(() -> {               
                studentTable.getTableView().setPlaceholder(new VBox());
            });
            studentList  = dbHandler.studentList(filter);
            for(int i=0;i<studentList.size();i++){
                studentList.get(i).setEnrollID(i+1+"");
            }
                        
            Platform.runLater(() -> {  
                count.setText(studentList.size()+" Student(s)");
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
