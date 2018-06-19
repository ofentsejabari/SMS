package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;
import mysqldriver.EmployeeQuery;
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
    private JFXButton btn_add, btn_export, btn_refresh, btn_edit;
    @FXML
    private Label totalHouses, hod;
    @FXML
    private JFXListView<Label> house_ListView;
    @FXML
    private VBox houseClasses;
    @FXML
    private Tab housesTab, subjectsTab;
    
    
    private HouseClassesList classesList;
    
    public  House selectedHouse = null;
    public int selectedIndex = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        classesList = new HouseClassesList();
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setTooltip(new Tooltip("Add new stream"));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateHouseDialog(null);
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setTooltip(new Tooltip("Export all streams information to a file"));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setTooltip(new Tooltip("Refresh stream list"));
        btn_refresh.setOnAction((ActionEvent event) -> {
            updateHouseList();
        });
        
        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        btn_edit.setTooltip(new Tooltip("Edit Stream information"));
        btn_edit.setOnAction((ActionEvent event) -> {
            new UpdateHouseDialog(selectedHouse);
        });
        
        house_ListView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            try {
                selectedIndex = newValue.intValue();
                
                selectedHouse = AdminQuery.getHouseByName(house_ListView.getItems().get(newValue.intValue()).getText());
                hod.setText(EmployeeQuery.getEmployeeByID(selectedHouse.getHOH()).getFullNameWithInitials());

                classesList.hcws.restart();
                classesList.hsws.restart();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        
        //-- Set Tab --
        housesTab.setContent(classesList);
        housesTab.setText("House Classes");
        
        subjectsTab.setContent(null);
        subjectsTab.setDisable(true);
        subjectsTab.setText("");
        
        updateHouseList();
        
    }  
    
    
    
    public void updateHouseList(){       
        
        ObservableList<Label> data = FXCollections.observableArrayList();
        ObservableList<House> house = AdminQuery.getHouses();
            
        for(House h: house){
            data.add(new Label(h.getHouseName()));
        }
                        
        house_ListView.setItems(data);
        totalHouses.setText(""+house.size());
        house_ListView.getSelectionModel().select(selectedIndex);
               
    }
    
}
