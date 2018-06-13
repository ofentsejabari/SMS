package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.Notification;
import entry.SMS;
import entry.ToolTip;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;
import schooladministration.House;
import schooladministration.HouseClassesList;
import schooladministration.UpdateHouseDialog;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class HousesCategoriesController implements Initializable {

    @FXML
    private JFXButton btn_add, btn_info;
    @FXML
    private JFXButton btn_export;
    @FXML
    private JFXButton btn_refresh;
    @FXML
    private Label totalHouses;
    @FXML
    private JFXListView<Label> house_ListView;
    @FXML
    private VBox houseClasses;
    @FXML
    private Label hod;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private Tab housesTab;
    @FXML
    private Tab subjectsTab;
    
    
    private HouseClassesList classesList;
    
    public  House selectedHouse = null;
    public int selectedIndex = 0;
    public HouseWorkService hws = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        hws = new HouseWorkService();
        
        Notification nt = new Notification("Add stream classes, associate subjects to the"
                                         + " stream and define stream grading scheme ", 0, houseClasses);
        classesList = new HouseClassesList();
        
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setTooltip(new ToolTip("Add new stream"));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateHouseDialog(null);
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setTooltip(new ToolTip("Export all streams information to a file", 250, 30));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setTooltip(new ToolTip("Refresh stream list", 180, 30));
        btn_refresh.setOnAction((ActionEvent event) -> {
            hws.restart();
        });
        
        btn_info.setGraphic(SMS.getGraphics(MaterialDesignIcon.INFORMATION_VARIANT, "icon-default", 24));
        btn_info.setTooltip(new ToolTip("View or hide notification area", 200, 30));
        btn_info.setOnAction((ActionEvent event) -> {
            if(!houseClasses.getChildren().contains(nt)){
                houseClasses.getChildren().add(1, nt);
            }else{
                houseClasses.getChildren().remove(nt);
            }
        });
        
        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        btn_edit.setTooltip(new ToolTip("Edit Stream information", 200, 30));
        btn_edit.setOnAction((ActionEvent event) -> {
            new UpdateHouseDialog(selectedHouse);
        });
        
        house_ListView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            try {
                selectedIndex = newValue.intValue();
                selectedHouse = AdminQuery.getHouseByName(house_ListView.getItems().get(newValue.intValue()).getText());
                hod.setText(SMS.dbHandler.getEmployeeByID(selectedHouse.getHOH()).getFullNameWithInitials());

                classesList.hcws.restart();
                classesList.hsws.restart();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
           
        
        //-- Set Tab --
        housesTab.setContent(classesList);
        //housesTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.HUMAN_MALE_FEMALE, "icon-secondary", 20));
        
        subjectsTab.setContent(null);
        subjectsTab.setDisable(true);
        subjectsTab.setText("");
        //subjectsTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.BOOK_OPEN_PAGE_VARIANT, "icon-secondary", 20));
        
        hws.start();
        hws.restart();
        
    }  
    
    
    
    public class HouseListWork extends Task<Integer> {       
        @Override 
        protected Integer call() throws Exception {
            
            ObservableList<Label> data = FXCollections.observableArrayList();
            ObservableList<House> house = AdminQuery.getHouses();
            
            for(House h: house){
                data.add(new Label(h.getHouseName()));
            }
                        
            Platform.runLater(() -> {
                try {
                    house_ListView.setItems(data);
                    
                    totalHouses.setText(""+house.size());
                    if(selectedHouse != null){
                        house_ListView.getSelectionModel().select(selectedIndex);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            });
            
            return 1;
        }
       
    }

    public class HouseWorkService extends Service<Integer> {

        @Override
        protected Task createTask() {
            return new HouseListWork();
        }
    }
    
}
