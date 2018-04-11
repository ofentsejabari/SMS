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
import static schooladministration.control.StreamClassesController.selectedStream;

/**
 *
 * @author ofentse
 */
public class StreamClassesList extends BorderPane{
    
    public static CustomTableView<ISchoolClass> table;
    public ClassWorkService classWorkService;

    public StreamClassesList() {
        
        setPadding(new Insets(10));
        getStyleClass().add("container");
        classWorkService = new ClassWorkService();
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Add Class");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new AddDepartmentStage(null).show();
        });
        
        toolbar.getChildren().addAll(new HSpacer(), btn_add);
        
        table = new CustomTableView<>();
        
        CustomTableColumn cn = new CustomTableColumn("");
        cn.setPercentWidth(5);
        cn.setCellValueFactory(new PropertyValueFactory<>("schoolID"));
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
        
        CustomTableColumn className = new CustomTableColumn("Class Name");
        className.setPercentWidth(25);
        className.setCellValueFactory(new PropertyValueFactory<>("name"));
        className.setCellFactory(TextFieldTableCell.forTableColumn());
        className.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn classTeacher = new CustomTableColumn("Class Teacher");
        classTeacher.setPercentWidth(25);
        classTeacher.setCellValueFactory(new PropertyValueFactory<>("classTeacherID"));
        classTeacher.setCellFactory(TextFieldTableCell.forTableColumn());
        classTeacher.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String type, boolean empty) {
                        super.updateItem(type, empty);
                        Label subjectType = new Label(type);
                        
                        if(!empty){
//                            if(type.equalsIgnoreCase("0")){
//                                subjectType.setText("Optional");
//                            }else{
//                                subjectType.setText("Core");
//                            }
                            
                            setGraphic(subjectType);
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn house = new CustomTableColumn("House");
        house.setPercentWidth(30);
        house.setCellValueFactory(new PropertyValueFactory<>("house"));
        house.setCellFactory(TextFieldTableCell.forTableColumn());
        house.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String cont, boolean empty) {
                        super.updateItem(cont, empty);
                        
                        if(!empty){
                            
                            setGraphic(new Label(cont));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn totalStudents = new CustomTableColumn("Class Capacity");
        totalStudents.setPercentWidth(19.9);
        totalStudents.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalStudents.setCellFactory(TextFieldTableCell.forTableColumn());
        totalStudents.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String cont, boolean empty) {
                        super.updateItem(cont, empty);
                        
                        if(!empty){
                            
                            setGraphic(new Label(cont));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        table.getTableView().getColumns().addAll(cn, className, classTeacher, house, totalStudents);
        VBox.setVgrow(table, Priority.ALWAYS);
        
        ProgressIndicator pi = new ProgressIndicator("Loading class data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(classWorkService.runningProperty());
        table.getTableView().itemsProperty().bind(classWorkService.valueProperty());
        
        StackPane stackPane = new StackPane(table, pi);
        
        setCenter(stackPane);
    }
    
    
    public class ClassListWork extends Task<ObservableList<ISchoolClass>> {       
        @Override 
        protected ObservableList<ISchoolClass> call() throws Exception {
            
            Platform.runLater(() -> {
                
            });
            
            ObservableList<ISchoolClass> data; 
          
            if(selectedStream != null){
                data = AdminQuery.getStreamClassList(selectedStream.getStreamID());
            }else{ 
                data = FXCollections.observableArrayList();
            }
            
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setSchoolID((i+1)+"");
                data.get(i).setHouseID(AdminQuery.getHouseByID(data.get(i).getHouseID()).getHouseName());
            }
            
            
            Platform.runLater(() -> {
                
            });
            
            return data;
        }
       
    }

    
    
    public class ClassWorkService extends Service<ObservableList<ISchoolClass>> {

        @Override
        protected Task createTask() {
            return new ClassListWork();
        }
    }
   
    
}
