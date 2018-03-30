package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.dbHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import studentmanagement.ExportMenu;
import static entry.SMS.setDataNotAvailablePlaceholder;
import eventcalendar.JBCalendarDate;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import schooladministration.Term;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class AcademicTermController implements Initializable {

    @FXML
    private JFXButton btn_add, btn_refresh, btn_export;
    
    @FXML
    private StackPane stackpane;
    
    public static CustomTableView<Term> table;
    public static TermListWorkService termListWork;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        termListWork = new TermListWorkService();
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            new ExportMenu(btn_export);
        });
        
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            termListWork.restart();
        });
        
        //-------------------Search bar and table-------------------------------
        table = new CustomTableView<>();
        
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

        
        CustomTableColumn termDescr = new CustomTableColumn("TERM DESCRIPTION");
        termDescr.setPercentWidth(25);
        termDescr.setCellValueFactory(new PropertyValueFactory<>("description"));
        termDescr.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        if(!empty){
                            Hyperlink term = new Hyperlink(ID);
                            term.getStyleClass().add("tableLink");
                            
//                            if(LoginWindow.USER_ROLES.hasGeneralSettingsPriviledge()){
//                                term.setGraphic(new CJFXToolbarButton("Edit", CJFXToolbarButton.SUCCESS));
//                                term.setOnAction((ActionEvent event) -> {
//                                    Term tm = dbHandler.getTermByName(ID);
//                                    new UpdateTerm(tm);
//                                });
//                            }
                           setGraphic(term);
                           
                        }else{ setGraphic(null); }
                        
                    }
                };
            }
        });
        
        CustomTableColumn startDate = new CustomTableColumn("START DATE");
        startDate.setPercentWidth(20);
        startDate.setCellValueFactory(new PropertyValueFactory<>("from"));
        startDate.setCellFactory(TextFieldTableCell.forTableColumn());
        startDate.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn endDate = new CustomTableColumn("END DATE");
        endDate.setPercentWidth(20);
        endDate.setCellValueFactory(new PropertyValueFactory<>("to"));
        endDate.setCellFactory(TextFieldTableCell.forTableColumn());
        endDate.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn weeksDays = new CustomTableColumn("PERIOD");
        weeksDays.setPercentWidth(20);
        weeksDays.setCellValueFactory(new PropertyValueFactory<>("currentTerm"));
        weeksDays.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn academicYear = new CustomTableColumn("ACADEMIC YEAR");
        academicYear.setPercentWidth(14.9);
        academicYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        academicYear.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        table.getTableView().getColumns().addAll(id, termDescr, startDate, endDate, weeksDays, academicYear);
        VBox ph = setDataNotAvailablePlaceholder();
        
        ProgressIndicator pi = new ProgressIndicator("Loading student data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(termListWork.runningProperty());
        table.getTableView().itemsProperty().bind(termListWork.valueProperty());
        
        
        stackpane.getChildren().addAll(table, pi);
        
        termListWork.start();
        termListWork.restart();
    }   
    
    
    
    
    
    public class TermListWork extends Task<ObservableList<Term>> {       
        @Override 
        protected ObservableList<Term> call() throws Exception {
            ObservableList<Term> data = FXCollections.observableArrayList();
            
            ObservableList<Term> termList = dbHandler.getTermList();
            
            Platform.runLater(() -> {
                
            });
            for (Term term : termList) {
                
                JBCalendarDate tf1 = new JBCalendarDate(term.getFrom());
                JBCalendarDate tf2 = new JBCalendarDate(term.getTo());
                
                int days = JBCalendarDate.getDatesBetween(tf1, tf2, false, true).size();
                term.setCurrentTerm(days+" Day(s) - " + (days/5)+"."+days%5+" Weeks");
                data.add(term);
                
            }
            //- Restore back the table place holder
            Platform.runLater(() -> {
            
            });

            return data;
        }
       
    }

    public class TermListWorkService extends Service<ObservableList<Term>> {

        @Override
        protected Task createTask() {
            return new TermListWork();
        }
    }
 

    
}
