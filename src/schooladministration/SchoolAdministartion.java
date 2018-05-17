package schooladministration;

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
public class SchoolAdministartion extends BorderPane{
    
    private JFXListView<Label> mainMenu;
    private AnchorPane dashboardUI, departments, systemUsers, streamClasses,
            academicTerms ,housesCategories, schoolInfo;
    public static StackPane ADMIN_MAN_STACK;

    public SchoolAdministartion() {
        
        getStyleClass().add("container");
        
        //-- Library Management Menu --
        mainMenu = new JFXListView<>();
        mainMenu.getStyleClass().add("main_menu");
        
        //-- Menu Items --
        Label dashboard         = new Label("Dashboard",getIcon("14_System_Task.png",   26));
        Label department_subject = new Label("Departments And Subjects",getIcon("13_unit.png", 26));
        Label streams          = new Label("Streams And Classes",getIcon("12_training.png",   26));
        Label schoolHouses     = new Label("Houses And Categories", getIcon("10_settings.png",  26));
        Label terms            = new Label("Academic Terms",getIcon("10_settings.png",   26));
        Label users             = new Label("User Directory", getIcon("14_access.png",   26));
        Label school            = new Label("School Information", getIcon("10_settings.png", 26));

        mainMenu.getItems().addAll(dashboard, department_subject, streams,
                                   terms, schoolHouses, users, school);
        
        //-- set the first item selected --
        mainMenu.getSelectionModel().select(0);
        
        mainMenu.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    
            switch (newValue.intValue()){
                case 0:
                    dashboardUI.toFront();
                    break;
                case 1:
                    departments.toFront();
                    break;
                    
                case 2:
                    streamClasses.toFront();
                    break;
                
                case 3:
                    academicTerms.toFront();
                    break;
                case 4:
                    housesCategories.toFront();
                    break;
                case 5:
                    systemUsers.toFront();
                    break;
                case 6:
                    schoolInfo.toFront();
                    break;
                    
                default:
                    break;
            }
        });
        
        setLeft(mainMenu);
        
        try{
            //-- Student Management Views
            dashboardUI = FXMLLoader.load(getClass().getResource("/schooladministration/view/dashboard.fxml"));
            departments = FXMLLoader.load(getClass().getResource("/schooladministration/view/departments.fxml"));
            systemUsers = FXMLLoader.load(getClass().getResource("/schooladministration/view/systemUsers.fxml"));
            streamClasses = FXMLLoader.load(getClass().getResource("/schooladministration/view/streamClasses.fxml"));
            housesCategories = FXMLLoader.load(getClass().getResource("/schooladministration/view/housesCategories.fxml"));
            academicTerms = FXMLLoader.load(getClass().getResource("/schooladministration/view/academicTerms.fxml"));
            schoolInfo = FXMLLoader.load(getClass().getResource("/schooladministration/view/schoolInformation.fxml"));
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        ADMIN_MAN_STACK = new StackPane(academicTerms, housesCategories, streamClasses, systemUsers,
                departments, schoolInfo, dashboardUI);
        
        setCenter(ADMIN_MAN_STACK);
    }
    
}
