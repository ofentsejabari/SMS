package schooladministration;

import entry.CustomTableView;
import entry.HSpacer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import mysqldriver.AdminQuery;
import org.controlsfx.control.ListSelectionView;
import static schooladministration.SchoolAdministartion.departmentsController;

/**
 *
 * @author ofentse
 */
public class SubjectTeachers extends BorderPane{
    
    public SubjectWorkService subjectWorkService = null;
    public static CustomTableView<Subject> table;

    public SubjectTeachers() {
        
        getStyleClass().add("container");
        subjectWorkService = new SubjectWorkService();
        
        setPadding(new Insets(10));
        
        
        ListSelectionView<String> view = new ListSelectionView<>();
        view.setTargetHeader(new HBox(new Label("Selected"), new HSpacer(), new Button("Hellooooo")));
        view.getSourceItems().addAll("One", "Two", "Three");
        view.getTargetItems().addAll("Four", "Five");
        
        
        setCenter(view);
        
        subjectWorkService.start();
        
    }
    
    
    public class SubjectListWork extends Task<ObservableList<Subject>> {       
        @Override 
        protected ObservableList<Subject> call() throws Exception {
            Platform.runLater(() -> {
                
            });
            
            ObservableList<Subject> data; 
            
            if(departmentsController.selectedDepartment != null){
                data = AdminQuery.getSubjectListFor(departmentsController.selectedDepartment.getID());
            }else{ 
                data = FXCollections.observableArrayList();
            
            }
            
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setSubjectID(i+1+"");
                
            }
            
            
            Platform.runLater(() -> {
                
            });
            
            return data;
        }
       
    }

    public class SubjectWorkService extends Service<ObservableList<Subject>> {

        @Override
        protected Task createTask() {
            return new SubjectListWork();
        }
    }
    
}
