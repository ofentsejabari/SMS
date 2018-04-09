package studentmanagement;

import com.jfoenix.controls.JFXListView;
import entry.SMS;
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
public class StudentManagement extends BorderPane{
    
    private JFXListView<Label> mainMenu;
    private AnchorPane dashboardUI, enrolmentUI;
    public static StackPane STUDENT_MAN_STACK;
    
    
    
    public StudentManagement() {
        
        getStyleClass().add("container");
        
        //-- Student Management Menu --
        mainMenu = new JFXListView<>();
        mainMenu.getStyleClass().add("main_menu");
        
        //-- Menu Items --
        Label dashboard = new Label("Dashboard", getIcon("system_task_100px.png", 22));
        Label enrolment = new Label("Student Enrolment", getIcon("1_students.png", 30));
        Label attendance = new Label("Attendance", getIcon("inspection_100px.png", 22));
        Label hostel = new Label("Hostel Management", getIcon("bunk_bed.png", 22));
        Label extraCurriculum = new Label("Extra Curriculum Activities", getIcon("ratings_100px.png", 22));
        Label assessment = new Label("Assessment", getIcon("ratings_100px.png", 22));

        mainMenu.getItems().addAll(dashboard, enrolment, attendance,
                                   hostel, assessment, extraCurriculum);
        
        //-- set the first item selected --
        mainMenu.getSelectionModel().select(0);
        
        mainMenu.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            switch (newValue.intValue()){
                case 0:
                    SMS.setNode(STUDENT_MAN_STACK, dashboardUI);
                    break;
                case 1:
                    SMS.setNode(STUDENT_MAN_STACK, enrolmentUI);
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
            dashboardUI = FXMLLoader.load(getClass().getResource("/studentmanagement/view/dashboard.fxml"));
            
            enrolmentUI = FXMLLoader.load(getClass().getResource("/studentmanagement/view/studentEnrolment.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        STUDENT_MAN_STACK = new StackPane(enrolmentUI, dashboardUI);
        
        setCenter(STUDENT_MAN_STACK);
        
    }
    
}
