package schooladministration;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.DialogUI;
import entry.JFXAlert;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mysqldriver.AdminQuery;
import mysqldriver.EmployeeQuery;

/**
 *
 * @author ofentse
 */
public class ExtraCurriculaMembers extends BorderPane{
    
    public ActivityMembersService ams = null;
    public static CustomTableView<ActivityMember> table;

    public ExtraCurriculaMembers() {
        
        getStyleClass().add("container");
        ams = new ActivityMembersService();
        
        table = new CustomTableView<>();

        CustomTableColumn subjectName = new CustomTableColumn("FULLNAME");
        subjectName.setPercentWidth(24.9);
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
                            setGraphic(description);                           
                            description.setOnAction((ActionEvent event) -> {
                                
                            });
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn type = new CustomTableColumn("TYPE");
        type.setPercentWidth(15);
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
        ctrl.setPercentWidth(60);
        ctrl.setCellValueFactory(new PropertyValueFactory<>("id"));
        ctrl.setCellFactory(TextFieldTableCell.forTableColumn());
        ctrl.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String type, boolean empty) {
                        super.updateItem(type, empty);
                        
                        if(!empty){
                            
                            JFXButton close = new JFXButton("",SMS.getGraphics(MaterialDesignIcon.DELETE_SWEEP, "text-error", 24));
                            close.setTooltip(new Tooltip("Close notification"));
                            close.getStyleClass().add("jfx-close-button");
                            close.setOnAction((ActionEvent event) -> {
                                
                                if(AdminQuery.deleteActivityMember(AdminQuery.getActivityMemberByID(type))){
                                        new DialogUI("Team member has been deregistered successfully",
                                                    DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, null);
                                        ams.restart();
                                }else{
                                    new DialogUI("Exception occurred while trying to remove team member.",
                                                DialogUI.ERROR_NOTIF, PARENT_STACK_PANE, null).show();
                                }
                            });
                            
                            setGraphic(close);
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        table.getTableView().getColumns().addAll(subjectName, type, ctrl);
        VBox.setVgrow(table, Priority.ALWAYS);
        
        
        ProgressIndicator pi = new ProgressIndicator("Loading members data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(ams.runningProperty());
        table.getTableView().itemsProperty().bind(ams.valueProperty());
        
        StackPane stackPane = new StackPane(table, pi);
        stackPane.setPadding(new Insets(10, 5, 0, 5));
        
        setCenter(stackPane);
        
        ams.start();
        
    }
    
    
    
    
    public class ActivityMembersWork extends Task<ObservableList<ActivityMember>> {       
        @Override 
        protected ObservableList<ActivityMember> call() throws Exception {
            
            ObservableList<ActivityMember> members = FXCollections.observableArrayList();
            
            ObservableList<ActivityMember> data = AdminQuery.getExtraCurriculaActivitiesMembers(SchoolAdministartion.extraCurriculaController.selectedActivity.getId()); 
            
            for(ActivityMember ac: data){
                if(ac.getType().equalsIgnoreCase("student")){
                    ac.setMemberID(SMS.dbHandler.getStudentByID(ac.getMemberID()).getFullName());
                }else{
                    ac.setMemberID(EmployeeQuery.getEmployeeByID(ac.getMemberID()).getFullName());
                }
                
                members.add(ac);
            }
            
            return members;
        }
       
    }

    public class ActivityMembersService extends Service<ObservableList<ActivityMember>> {

        @Override
        protected Task createTask() {
            return new ActivityMembersWork();
        }
    }
    
}
