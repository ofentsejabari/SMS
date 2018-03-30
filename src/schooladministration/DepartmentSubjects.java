package schooladministration;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mysqldriver.AdminQuery;
import static schooladministration.control.DepartmentsController.selectedDepartment;
/**
 *
 * @author ofentse
 */
public class DepartmentSubjects extends BorderPane{
    
    public SubjectWorkService subjectWorkService = null;
    public static CustomTableView<Subject> table;

    public DepartmentSubjects() {
        
        getStyleClass().add("container");
        subjectWorkService = new SubjectWorkService();
        
        setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Add Subject");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new AddDepartmentStage(null).show();
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
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        
        CustomTableColumn subjectName = new CustomTableColumn("SUBJECT NAME");
        subjectName.setPercentWidth(55);
        subjectName.setCellValueFactory(new PropertyValueFactory<>("description"));
        subjectName.setCellFactory(TextFieldTableCell.forTableColumn());
        subjectName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
                                   
//                                selectedSubject = AdminQuery.getSubjectByName(ID);
//                                edit.setDisable(false);
//                                subjectTeachersList.setItems(AdminQuery.getSubjectTeachersNames(selectedSubject.getSubjectID()));
//                                
//                                subjectLink.setVisible(true);
//                                subjectLink.setText(selectedSubject.getDescription());
                            });
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn type = new CustomTableColumn("TYPE");
        type.setPercentWidth(40);
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String type, boolean empty) {
                        super.updateItem(type, empty);
                        Label subjectType = new Label();
                        
                        if(!empty){
                            if(type.equalsIgnoreCase("0")){
                                subjectType.setText("Optional");
                            }else{
                                subjectType.setText("Core");
                            }
                            
                            setGraphic(subjectType);
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        table.getTableView().getColumns().addAll(cn, subjectName, type);
        VBox.setVgrow(table, Priority.ALWAYS);
        
        
        ProgressIndicator pi = new ProgressIndicator("Loading subjects data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(subjectWorkService.runningProperty());
        table.getTableView().itemsProperty().bind(subjectWorkService.valueProperty());
        
        StackPane stackPane = new StackPane(table, pi);
        
        setCenter(stackPane);
        
        subjectWorkService.start();
        
    }
    
    
    
    
    public class SubjectListWork extends Task<ObservableList<Subject>> {       
        @Override 
        protected ObservableList<Subject> call() throws Exception {
            Platform.runLater(() -> {
                
            });
            
            ObservableList<Subject> data; 
            
            if(selectedDepartment != null){
                data = AdminQuery.getSubjectListFor(selectedDepartment.getID());
            }else{ 
                data = FXCollections.observableArrayList();
            
            }
            
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setSubjectID(i+1+"");
                
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
