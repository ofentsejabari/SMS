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
import javafx.geometry.Insets;
import schooladministration.control.DepartmentsController;
import schooladministration.control.ExtraCurriculaController;
import schooladministration.control.HousesCategoriesController;
import schooladministration.control.StreamClassesController;
import schooladministration.control.SystemAccessControl;
import static entry.SMS.getIcon;

/**
 *
 * @author ofentse
 */
public class SchoolAdministartion extends BorderPane{
    
    private JFXListView<Label> mainMenu;
    private AnchorPane  departments, streamClasses,
            housesCategories, extraCurricula;
    private BorderPane  systemUsers;
    
    public static StackPane ADMIN_MAN_STACK;
    private BorderPane dashboardUI, academicTerms;
    
    //-- View controllers --
    public static DepartmentsController departmentsController;
    public static StreamClassesController streamClassesController;
    public static HousesCategoriesController houseController;
    public static ExtraCurriculaController extraCurriculaController;
    
    public static SchoolInformation schoolInformation;

    public SchoolAdministartion() {
        
        getStyleClass().add("container");
        //-- Library Management Menu --
        mainMenu = new JFXListView<>();
        mainMenu.getStyleClass().add("main_menu");
        
        //-- Menu Items --
        Label dashboard          = new Label("Dashboard",getIcon("14_System_Task.png",   26));
        Label department_subject = new Label("Departments And Subjects",getIcon("13_unit.png", 26));
        Label streams            = new Label("Streams And Classes",getIcon("12_training.png",   26));
        Label schoolHouses       = new Label("School Houses", getIcon("hierarchy.png",  26));
        Label terms              = new Label("Academic Terms",getIcon("10_settings.png",   26));
        Label users              = new Label("System Access Control", getIcon("14_access.png",   26));
        Label school             = new Label("School Address Book", getIcon("address_book.png", 26));
        Label extraCurrActivity  = new Label("Extra Curricula Activity", getIcon("team.png", 26));

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
                    schoolInformation.toFront();
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
            schoolInformation = new SchoolInformation();
            systemUsers = new SystemAccessControl();
            academicTerms = new SchoolTerms();
            
            FXMLLoader departmentLoader = new FXMLLoader(getClass().getResource("/schooladministration/view/departments.fxml"));
            departments = departmentLoader.load();
            departmentsController = departmentLoader.getController();
                        
            FXMLLoader streamLoader = new FXMLLoader(getClass().getResource("/schooladministration/view/streamClasses.fxml"));
            streamClasses = streamLoader.load();
            streamClassesController = streamLoader.getController();
            
            FXMLLoader houseLoader = new FXMLLoader(getClass().getResource("/schooladministration/view/housesCategories.fxml"));
            housesCategories = houseLoader.load();
            houseController = houseLoader.getController();
            
            FXMLLoader activity = new FXMLLoader(getClass().getResource("/schooladministration/view/extraCurriculaActivity.fxml"));
            extraCurricula = activity.load();
            extraCurriculaController = activity.getController();
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        ADMIN_MAN_STACK = new StackPane(extraCurricula, academicTerms, housesCategories, streamClasses, systemUsers,
                                        departments, schoolInformation, dashboardUI);
        
        ADMIN_MAN_STACK.setPadding(new Insets(5));
        
        setCenter(ADMIN_MAN_STACK);
    }
    
}
