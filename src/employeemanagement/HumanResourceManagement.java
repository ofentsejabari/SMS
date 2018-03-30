package employeemanagement;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import static entry.SMS.getIcon;

/**
 *
 * @author ofentse
 */
public class HumanResourceManagement extends BorderPane{
    
    private JFXListView<Label> mainMenu;
    private AnchorPane dashboardUI;
    public static StackPane HR_MAN_STACK;

    public HumanResourceManagement() {
        
        getStyleClass().add("container");
        
        //-- Student Management Menu --
        mainMenu = new JFXListView<>();
        mainMenu.getStyleClass().add("main_menu");
        
        //-- Menu Items --
        Label dashboard = new Label("Dashboard", getIcon("system_task_100px.png", 22));
        Label employee = new Label("Employee Management", getIcon("applicant_100px.png", 22));
        Label leaves = new Label("Leave Managegement", getIcon("inspection_100px.png", 22));
        Label appraisal = new Label("Employee Appraisal", getIcon("bunk_bed.png", 22));

        mainMenu.getItems().addAll(dashboard, employee, leaves,
                                   appraisal);
        
        //-- set the first item selected --
        mainMenu.getSelectionModel().select(0);
        
        mainMenu.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            switch (newValue.intValue()){
                case 0:
                    dashboardUI.toFront();
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        });
        
        setLeft(mainMenu);
        
        try {
            //-- Student Management Views
            dashboardUI = FXMLLoader.load(getClass().getResource("/employeemanagement/view/dashboard.fxml"));
        } catch (IOException ex) {
        }
        
        HR_MAN_STACK = new StackPane(dashboardUI);
        
        setCenter(HR_MAN_STACK);
    }
    
}
