package studentmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
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
import schooladministration.UpdateSubjectDialog;

/**
 *
 * @author ofentse
 */
public class StudentSpecialNeeds extends BorderPane{

    public static JFXListView<Label> ssn_listView;
    public static SSNWorkService SSNWorkService = null;
    public static CustomTableView<Student> table;
    
    SpecialNeed selectedSpecialNeed = null;
    public static int selectedIndex = 0;
    //private final Label total;
    
    public StudentSpecialNeeds() {
        
        setPadding(new Insets(15, 5, 5, 5));
        SSNWorkService = new SSNWorkService();
        
        BorderPane ssn_center = new BorderPane();
        ssn_center.setPadding(new Insets(0, 0, 0, 10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        ssn_center.setTop(toolbar);
        
        
        JFXButton refresh = new JFXButton("Refresh");
        refresh.getStyleClass().add("jfx-tool-button");
        refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        refresh.setOnAction((ActionEvent event) -> {
            updateSSNListView();
        });
        
        JFXButton edit = new JFXButton("Edit Record");
        edit.getStyleClass().add("jfx-tool-button");
        edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        edit.setOnAction((ActionEvent event) -> {
            new UpdateSSNDialog(selectedSpecialNeed);
        });
        
        JFXButton btn_add = new JFXButton("Add Special Need");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateSSNDialog(null);
        });
        
        Label title = new Label();
        title.getStyleClass().add("title-label");
        
        toolbar.getChildren().addAll(title, new HSpacer(), refresh, edit, btn_add);
        
        setCenter(ssn_center);
        
        ssn_listView = new JFXListView<>();
        ssn_listView.getStyleClass().add("jfx-custom-list");
        ssn_listView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            try {
                selectedSpecialNeed = SMS.dbHandler.getSpecialNeedByName(ssn_listView.getSelectionModel().getSelectedItem().getText());
                title.setText(ssn_listView.getSelectionModel().getSelectedItem().getText());
                title.setTooltip(new Tooltip(selectedSpecialNeed.getDescription()));
                
                selectedIndex = newValue.intValue();
                SSNWorkService.restart();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
          
        setLeft(ssn_listView);
        //----------------------------------------------------------------------
        table = new CustomTableView<>();
        
        CustomTableColumn cn = new CustomTableColumn("Student Name");
        cn.setPercentWidth(24.9);
        cn.setCellValueFactory(new PropertyValueFactory<>("fname"));
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
        
        CustomTableColumn gender = new CustomTableColumn("Gender");
        gender.setPercentWidth(15);
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        gender.setCellFactory(TextFieldTableCell.forTableColumn());
        gender.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
                                
                                new UpdateSubjectDialog(AdminQuery.getSubjectByName(ID));
                            });
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn type = new CustomTableColumn("Class");
        type.setPercentWidth(60);
        type.setCellValueFactory(new PropertyValueFactory<>("classID"));
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
        
        table.getTableView().getColumns().addAll(cn, gender, type);
        VBox.setVgrow(table, Priority.ALWAYS);
                
        ProgressIndicator pi = new ProgressIndicator("Loading data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(SSNWorkService.runningProperty());
        table.getTableView().itemsProperty().bind(SSNWorkService.valueProperty());
        
        StackPane stackPane = new StackPane(table, pi);
        
        ssn_center.setCenter(stackPane);
        SSNWorkService.start();
        
        updateSSNListView();
    }
    
    
    public static void updateSSNListView(){
        ObservableList<String> ssn = SMS.dbHandler.getSpecialNeedNames();
        ObservableList<Label> data = FXCollections.observableArrayList();
        
        for(String dt: ssn){
            data.add(new Label(dt));
        }
        ssn_listView.setItems(data);
        ssn_listView.getSelectionModel().select(selectedIndex);
    }
    
    
    
    public class SSNListWork extends Task<ObservableList<Student>> {       
        @Override 
        protected ObservableList<Student> call() throws Exception {
            
            
            ObservableList<Student> data = null; 
            
            if(selectedSpecialNeed != null){
                data = SMS.dbHandler.getStudentsWithSpecialNeed(selectedSpecialNeed.getId());
            }else{ 
                data = FXCollections.observableArrayList();
            }
            
            Platform.runLater(() -> {
                //total.setText("( "+data.size()+" )");
            });
            
            return data;
        }
       
    }

    public class SSNWorkService extends Service<ObservableList<Student>> {

        @Override
        protected Task createTask() {
            return new SSNListWork();
        }
    }
    
}
