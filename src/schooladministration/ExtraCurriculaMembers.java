package schooladministration;

import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.ProgressIndicator;
import entry.SMS;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mysqldriver.AdminQuery;

/**
 *
 * @author ofentse
 */
public class ExtraCurriculaMembers extends BorderPane{
    
    public ActivityMembersService ams = null;
    public static CustomTableView<Subject> table;

    public ExtraCurriculaMembers() {
        
        getStyleClass().add("container");
        ams = new ActivityMembersService();
        
        setPadding(new Insets(10));
//        
//        HBox toolbar = new HBox();
//        toolbar.getStyleClass().add("secondary-toolbar");
//        setTop(toolbar);
//        
//        JFXButton btn_add = new JFXButton("Add Subject");
//        btn_add.getStyleClass().add("jfx-tool-button");
//        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
//        btn_add.setOnAction((ActionEvent event) -> {
//            new UpdateSubjectDialog(null).show();
//        });
//        
//        toolbar.getChildren().addAll(new HSpacer(), btn_add);
        
        table = new CustomTableView<>();
        
        CustomTableColumn cn = new CustomTableColumn("");
        cn.setPercentWidth(4.9);
        cn.setCellValueFactory(new PropertyValueFactory<>("type"));
        cn.setCellFactory(TextFieldTableCell.forTableColumn());
        cn.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setAlignment(Pos.CENTER);
                            if(ID.equals("student")){
                                setGraphic(new Label("", SMS.getIcon("u10.png", 22)));
                            }else{
                                setGraphic(new Label("", SMS.getIcon("u5.png", 22)));
                            }
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn subjectName = new CustomTableColumn("FULLNAME");
        subjectName.setPercentWidth(59.9);
        subjectName.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        subjectName.setCellFactory(TextFieldTableCell.forTableColumn());
        subjectName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        Hyperlink description = new Hyperlink(ID);
                        description.getStyleClass().add("tableLink");
                        
                        if(!empty){
                            
                            if(ID.equals("student")){
                                description.setGraphic(new Label("", SMS.getIcon("u10.png", 18)));
                            }else{
                                description.setGraphic(new Label("", SMS.getIcon("u5.png", 18)));
                            }
                            
                            
                            setGraphic(description);                           
                            description.setOnAction((ActionEvent event) -> {
                                new UpdateSubjectDialog(AdminQuery.getSubjectByName(ID));
                            });
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn type = new CustomTableColumn("TYPE");
        type.setPercentWidth(20);
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        
        CustomTableColumn ctrl = new CustomTableColumn("");
        ctrl.setPercentWidth(20);
        ctrl.setCellValueFactory(new PropertyValueFactory<>("type"));
        ctrl.setCellFactory(TextFieldTableCell.forTableColumn());
        ctrl.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String type, boolean empty) {
                        super.updateItem(type, empty);
                        
                        if(!empty){
                            
                            setGraphic(new Label("", SMS.getIcon("bin.png", 25)));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        
        table.getTableView().getColumns().addAll(subjectName, type, ctrl);
        VBox.setVgrow(table, Priority.ALWAYS);
        
        
        ProgressIndicator pi = new ProgressIndicator("Loading subjects data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(ams.runningProperty());
        table.getTableView().itemsProperty().bind(ams.valueProperty());
        
        StackPane stackPane = new StackPane(table, pi);
        
        setCenter(stackPane);
        
        ams.start();
        
    }
    
    
    
    
    public class ActivityMembersWork extends Task<ObservableList<ExtraCurriculaActivityMember>> {       
        @Override 
        protected ObservableList<ExtraCurriculaActivityMember> call() throws Exception {
            Platform.runLater(() -> {
                
            });
            
            ObservableList<ExtraCurriculaActivityMember> data = FXCollections.observableArrayList(
                    new ExtraCurriculaActivityMember("0", "Ofentse Jabari", "23", "student"),
                    new ExtraCurriculaActivityMember("1", "4431413", "53", "teacher"),
                    new ExtraCurriculaActivityMember("2", "5446413", "63", "teacher")); 
            
            
            
//            for (int i = 0; i < data.size(); i++) {
//                data.get(i).setECActivityID(i+1+"");
//            }
            
            
            return data;
        }
       
    }

    public class ActivityMembersService extends Service<ObservableList<Subject>> {

        @Override
        protected Task createTask() {
            return new ActivityMembersWork();
        }
    }
    
}
