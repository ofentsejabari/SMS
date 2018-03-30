/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schooladministration.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.Notification;
import entry.SMS;
import entry.ToolTip;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;
import schooladministration.AddDepartmentStage;
import schooladministration.House;
import schooladministration.HouseClassesList;

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
    
    public static House selectedHouse = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Notification nt = new Notification("Add stream classes, associate subjects to the"
                                         + " stream and define stream grading scheme ", 0, houseClasses);
                
        houseClasses.getChildren().add(1, nt);
        
        classesList = new HouseClassesList();
        classesList.houseClassWorkService.setOnSucceeded((WorkerStateEvent event) -> {
           //subjectList.subjectWorkService.restart();
        });
        
        
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setTooltip(new ToolTip("Add new stream"));
        btn_add.setOnAction((ActionEvent event) -> {
            new AddDepartmentStage(null).show();
        });
        
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setTooltip(new ToolTip("Export all streams information to a file", 250, 30));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setTooltip(new ToolTip("Refresh stream list", 180, 30));
        btn_refresh.setOnAction((ActionEvent event) -> {
            house_ListView.setItems(updateHouseList());
            house_ListView.getSelectionModel().select(0);
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
            
        });
        
        house_ListView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
               
            selectedHouse = AdminQuery.getHouseByName(house_ListView.getItems().get(newValue.intValue()).getText());
            hod.setText("HOD > "+selectedHouse.getHOH());
            
            classesList.houseClassWorkService.restart();
            
        });
           
        house_ListView.setItems(updateHouseList());
        
        //-- Set Tab --
        housesTab.setContent(classesList);
        housesTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.HUMAN_MALE_FEMALE, "icon-secondary", 20));
        
        subjectsTab.setContent(null);
        subjectsTab.setGraphic(SMS.getGraphics(MaterialDesignIcon.BOOK_OPEN_PAGE_VARIANT, "icon-secondary", 20));
        
    }  
    
    
    
    /**
     * 
     * @return 
     */
    private ObservableList<Label> updateHouseList() {
        
        ObservableList<Label> data = FXCollections.observableArrayList();
        ObservableList<House> house = AdminQuery.getHouses();
        
        totalHouses.setText(""+house.size());
        
        for (int i = 0; i < house.size(); i++) {
            data.add(new Label(house.get(i).getHouseName()));
        }
        
        totalHouses.setText(""+house.size());
        
        return data;
    }
    
}
