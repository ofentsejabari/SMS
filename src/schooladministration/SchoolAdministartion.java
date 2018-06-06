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
import schooladministration.control.DepartmentsController;
import schooladministration.control.ExtraCurriculaController;
import schooladministration.control.HousesCategoriesController;
import schooladministration.control.StreamClassesController;

/**
 *
 * @author ofentse
 */
public class SchoolAdministartion extends BorderPane{
    
    private JFXListView<Label> mainMenu;
    private AnchorPane  departments, systemUsers, streamClasses,
            academicTerms ,housesCategories, schoolInfo, extraCurricula;
    
    
    public static StackPane ADMIN_MAN_STACK;
    private BorderPane dashboardUI;
    
    //-- View controllers --
    public static DepartmentsController departmentsController;
    public static StreamClassesController streamClassesController;
    public static HousesCategoriesController houseController;
    
    public static ExtraCurriculaController extraCurriculaController;

    public SchoolAdministartion() {
        
        getStyleClass().add("container");
        
        //-- Library Management Menu --
        mainMenu = new JFXListView<>();
        mainMenu.getStyleClass().add("main_menu");
        
        //-- Menu Items --
        Label dashboard          = new Label("Dashboard",getIcon("14_System_Task.png",   26));
        Label department_subject = new Label("Departments And Subjects",getIcon("13_unit.png", 26));
        Label streams            = new Label("Streams And Classes",getIcon("12_training.png",   26));
        Label schoolHouses       = new Label("Houses And Categories", getIcon("10_settings.png",  26));
        Label terms              = new Label("Academic Terms",getIcon("10_settings.png",   26));
        Label users              = new Label("User Directory", getIcon("14_access.png",   26));
        Label school             = new Label("School Information", getIcon("10_settings.png", 26));
        Label extraCurrActivity  = new Label("Extra Curricula Activity", getIcon("10_settings.png", 26));

        mainMenu.getItems().addAll(dashboard, department_subject, streams,
                                   terms, schoolHouses, users, school, extraCurrActivity);
        
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
                    
                case 7:
                    extraCurricula.toFront();
                    break;
                    
                default:
                    break;
            }
        });
        
        setLeft(mainMenu);
        
        try{
            //-- Student Management Views
            dashboardUI = new DashboardStatistics();
            
            FXMLLoader departmentLoader = new FXMLLoader(getClass().getResource("/schooladministration/view/departments.fxml"));
            departments = departmentLoader.load();
            departmentsController = departmentLoader.getController();
            
            systemUsers = FXMLLoader.load(getClass().getResource("/schooladministration/view/systemUsers.fxml"));
            
            FXMLLoader streamLoader = new FXMLLoader(getClass().getResource("/schooladministration/view/streamClasses.fxml"));
            streamClasses = streamLoader.load();
            streamClassesController = streamLoader.getController();
            
            
            FXMLLoader houseLoader = new FXMLLoader(getClass().getResource("/schooladministration/view/housesCategories.fxml"));
            housesCategories = houseLoader.load();
            houseController = houseLoader.getController();
            
            academicTerms = FXMLLoader.load(getClass().getResource("/schooladministration/view/academicTerms.fxml"));
            schoolInfo = FXMLLoader.load(getClass().getResource("/schooladministration/view/schoolInformation.fxml"));
            
            
            FXMLLoader activity = new FXMLLoader(getClass().getResource("/schooladministration/view/extraCurriculaActivity.fxml"));
            extraCurricula = activity.load();
            extraCurriculaController = activity.getController();
            
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        ADMIN_MAN_STACK = new StackPane(extraCurricula, academicTerms, housesCategories, streamClasses, systemUsers,
                departments, schoolInfo, dashboardUI);
        
        setCenter(ADMIN_MAN_STACK);
    }
    
}
