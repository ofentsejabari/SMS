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
public class SocialWelfareSupport extends BorderPane{

    public static JFXListView<Label> sws_listView;
    public static SWSWorkService SWSWorkService = null;
    public static CustomTableView<Student> table;
    
    SocialWelfare selectedSocialWelfare = null;
    public static int selectedIndex = 0;
    
    public SocialWelfareSupport() {
        
        setPadding(new Insets(15, 5, 5, 5));
        SWSWorkService = new SWSWorkService();
        
        BorderPane ssn_center = new BorderPane();
        ssn_center.setPadding(new Insets(0, 0, 0, 10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        ssn_center.setTop(toolbar);
        
        
        JFXButton refresh = new JFXButton("Refresh");
        refresh.getStyleClass().add("jfx-tool-button");
        refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        refresh.setOnAction((ActionEvent event) -> {
            updateSWSListView();
        });
        
        JFXButton edit = new JFXButton("Edit Record");
        edit.getStyleClass().add("jfx-tool-button");
        edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        edit.setOnAction((ActionEvent event) -> {
            new UpdateSWSDialog(selectedSocialWelfare);
        });
        
        JFXButton btn_add = new JFXButton("Add Social Welfare");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateSWSDialog(null);
        });
        
        Label title = new Label();
        title.getStyleClass().add("title-label");
        
        toolbar.getChildren().addAll(title, new HSpacer(), refresh, edit, btn_add);
        
        setCenter(ssn_center);
        
        sws_listView = new JFXListView<>();
        sws_listView.getStyleClass().add("jfx-custom-list");
        sws_listView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            try {
                selectedSocialWelfare = SMS.dbHandler.getSocialWelfareByName(sws_listView.getSelectionModel().getSelectedItem().getText());
                title.setText(sws_listView.getSelectionModel().getSelectedItem().getText());
                title.setTooltip(new Tooltip(selectedSocialWelfare.getDescription()));
                
                selectedIndex = newValue.intValue();
                SWSWorkService.restart();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
          
        setLeft(sws_listView);
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
        
        pi.visibleProperty().bind(SWSWorkService.runningProperty());
        table.getTableView().itemsProperty().bind(SWSWorkService.valueProperty());
        
        StackPane stackPane = new StackPane(table, pi);
        
        ssn_center.setCenter(stackPane);
        SWSWorkService.start();
        
        updateSWSListView();
    }
    
    
    public static void updateSWSListView(){
        ObservableList<String> ssn = SMS.dbHandler.getSocialWelfareNames();
        ObservableList<Label> data = FXCollections.observableArrayList();
        
        for(String dt: ssn){
            data.add(new Label(dt));
        }
        sws_listView.setItems(data);
        sws_listView.getSelectionModel().select(selectedIndex);
    }
    
    
    
    public class SWSListWork extends Task<ObservableList<Student>> {       
        @Override 
        protected ObservableList<Student> call() throws Exception {
            
            
            ObservableList<Student> data = null; 
            
            if(selectedSocialWelfare != null){
                data = SMS.dbHandler.getStudentsWithSocialWelfareSupport(selectedSocialWelfare.getId());
            }else{ 
                data = FXCollections.observableArrayList();
            }
            
            Platform.runLater(() -> {
                //total.setText("( "+data.size()+" )");
            });
            
            return data;
        }
       
    }

    public class SWSWorkService extends Service<ObservableList<Student>> {

        @Override
        protected Task createTask() {
            return new SWSListWork();
        }
    }
    
}
