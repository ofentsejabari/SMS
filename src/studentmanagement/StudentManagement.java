package studentmanagement;

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

/**
 *
 * @author ofentse
 */
public class StudentManagement extends BorderPane{
    
    private JFXListView<Label> mainMenu;
    private AnchorPane dashboardUI;
    public static StackPane STUDENT_MAN_STACK;
    private BorderPane studentWelfareUI;
    
    public static StudentEnrolment enrollUI;
    
    public StudentManagement() {
        
        getStyleClass().add("container");
        
        //-- Student Management Menu --
        mainMenu = new JFXListView<>();
        mainMenu.getStyleClass().add("main_menu");
        
        //-- Menu Items --
        Label dashboard = new Label("Dashboard", getIcon("14_System_Task.png", 26));
        Label enrolment = new Label("Student Enrolment", getIcon("students.png", 26));
        Label attendance = new Label("Attendance", getIcon("report.png", 26));
        Label welfare = new Label("Student Welfare", getIcon("welfare.png", 26));
        Label assessment = new Label("Assessment", getIcon("1_students.png", 26));

        mainMenu.getItems().addAll(dashboard, enrolment, attendance, assessment, welfare);
        
        //-- set the first item selected --
        mainMenu.getSelectionModel().select(0);
        
        mainMenu.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            switch (newValue.intValue()){
                case 0:
                    dashboardUI.toFront();
                    break;
                case 1:
                    enrollUI.toFront();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    studentWelfareUI.toFront();
                    break;
                default:
                    break;
            }
        });
        
        setLeft(mainMenu);
        
        try {
            //-- Student Management Views --
            dashboardUI = FXMLLoader.load(getClass().getResource("/studentmanagement/view/dashboard.fxml"));
            
            enrollUI = new StudentEnrolment();
            
            studentWelfareUI = new StudentWelfare();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        STUDENT_MAN_STACK = new StackPane(studentWelfareUI, enrollUI, dashboardUI);
        STUDENT_MAN_STACK.setPadding(new Insets(5));
        
        setCenter(STUDENT_MAN_STACK);
        
    }
    
}
