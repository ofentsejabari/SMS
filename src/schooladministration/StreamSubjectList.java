package schooladministration;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
import entry.ToolTip;
import javafx.application.Platform;
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
import static schooladministration.SchoolAdministartion.streamClassesController;

/**
 *
 * @author ofentse
 */
public class StreamSubjectList extends BorderPane{
    
    public static CustomTableView<Subject> table;
    public SubjectWorkService subjectWorkService;

    public StreamSubjectList() {
        
        setPadding(new Insets(10));
        subjectWorkService = new SubjectWorkService();
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Add Subject");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateDepartmentDialog(null).show();
        });
        
        toolbar.getChildren().addAll(new HSpacer(), btn_add);
        table = new CustomTableView<>();
        
        CustomTableColumn cn = new CustomTableColumn("");
        cn.setPercentWidth(4.9);
        cn.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
        cn.setCellFactory(TextFieldTableCell.forTableColumn());
        cn.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            JFXButton delete = new JFXButton("",
                                    SMS.getGraphics(MaterialDesignIcon.DELETE_FOREVER, "text-bluegray", 24));
                            delete.setTooltip(new ToolTip("Close notification"));
                            delete.getStyleClass().add("table-error-button");
                            delete.setOnAction((ActionEvent event) -> {
                                
                            });
                            setGraphic(delete);
                            setStyle("-fx-background-color:#fff;");
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn name = new CustomTableColumn("Subject Name");
        name.setPercentWidth(40);
        name.setCellValueFactory(new PropertyValueFactory<>("description"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        Hyperlink description = new Hyperlink("");
                        description.getStyleClass().add("tableLink");
                        
                        if(!empty){
                            description.setText(ID);
                            setGraphic(description);                           
                            description.setOnAction((ActionEvent event) -> {
                                   
                            });
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn department = new CustomTableColumn("Department");
        department.setPercentWidth(30);
        department.setCellValueFactory(new PropertyValueFactory<>("departmentID"));
        department.setCellFactory(TextFieldTableCell.forTableColumn());
        department.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String type, boolean empty) {
                        super.updateItem(type, empty);
                        Label subjectType = new Label(type);
                        
                        if(!empty){
                            setGraphic(subjectType);
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn totalStudents = new CustomTableColumn("Enrolled Students");
        totalStudents.setPercentWidth(25);
        totalStudents.setCellValueFactory(new PropertyValueFactory<>("schoolID"));
        totalStudents.setCellFactory(TextFieldTableCell.forTableColumn());
        totalStudents.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String type, boolean empty) {
                        super.updateItem(type, empty);
                        Label subjectType = new Label(type);
                        
                        if(!empty){
                            setGraphic(subjectType);
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        table.getTableView().getColumns().addAll(cn, name, department, totalStudents);
        VBox.setVgrow(table, Priority.ALWAYS);
        
        
        ProgressIndicator pi = new ProgressIndicator("Loading subject data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(subjectWorkService.runningProperty());
        table.getTableView().itemsProperty().bind(subjectWorkService.valueProperty());
        
        StackPane stackPane = new StackPane(table, pi);
        
        setCenter(stackPane);
    }
    
    
    
    
    
    public class SubjectListWork extends Task<ObservableList<Subject>> {       
        @Override 
        protected ObservableList<Subject> call() throws Exception {
            
            Platform.runLater(() -> {
                
            });
            
            ObservableList<Subject> data; 
          
            if(streamClassesController.selectedStream != null){
                data = AdminQuery.getStreamSubjectsList(streamClassesController.selectedStream.getStreamID());
            }else{ 
                data = FXCollections.observableArrayList();
            }
            
            for (int i = 0; i < data.size(); i++) {
                //data.get(i).setSubjectID(i+1+"");
                data.get(i).setDepartmentID(AdminQuery.getDepartmentByID(data.get(i).getDepartmentID()).getDepartmentName());
            }
            
            
            Platform.runLater(() -> {
                
            });
            
            return data;
        }
       
    }

    public class SubjectWorkService extends Service<ObservableList<Subject>> {

        @Override
        protected Task createTask() {
            return new SubjectListWork();
        }
    }
   
    
}
