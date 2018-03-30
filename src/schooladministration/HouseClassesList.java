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
import static schooladministration.control.HousesCategoriesController.selectedHouse;
import static schooladministration.control.StreamClassesController.selectedStream;

/**
 *
 * @author ofentse
 */
public class HouseClassesList extends BorderPane{
    
    public static CustomTableView<ISchoolClass> table;
    public HouseClassWorkService houseClassWorkService;

    public HouseClassesList() {
        
        setPadding(new Insets(10));
        getStyleClass().add("container");
        houseClassWorkService = new HouseClassWorkService();
        
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
        className.setPercentWidth(94.9);
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

        
        table.getTableView().getColumns().addAll(cn, className);
        VBox.setVgrow(table, Priority.ALWAYS);
        
        ProgressIndicator pi = new ProgressIndicator("Loading class data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(houseClassWorkService.runningProperty());
        table.getTableView().itemsProperty().bind(houseClassWorkService.valueProperty());
        
        StackPane stackPane = new StackPane(table, pi);
        
        setCenter(stackPane);
    }
    
    
    
    
    public class HouseClassListWork extends Task<ObservableList<ISchoolClass>> {       
        @Override 
        protected ObservableList<ISchoolClass> call() throws Exception {
            
            Platform.runLater(() -> {
                
            });
            
            ObservableList<ISchoolClass> data; 
          
            if(selectedHouse != null){
                data = AdminQuery.getHouseClassList(selectedHouse.getID());
            }else{ 
                data = FXCollections.observableArrayList();
            }
            
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setSchoolID(i+1+"");
                //data.get(i).setHouse(AdminQuery.getHouseByID(data.get(i).getHouse()).getHouseName());
            }
            
            
            Platform.runLater(() -> {
                
            });
            
            return data;
        }
       
    }

    public class HouseClassWorkService extends Service<ObservableList<ISchoolClass>> {

        @Override
        protected Task createTask() {
            return new HouseClassListWork();
        }
    }
   
    
}
