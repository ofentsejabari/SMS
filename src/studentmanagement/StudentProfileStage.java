package studentmanagement;

import com.jfoenix.controls.JFXDialog;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import studentmanagement.control.StudentProfileController;

/**
 *
 * @author ofentse
 */
public class StudentProfileStage extends JFXDialog{
    
    public Student student = null;
    StudentProfileController spc;

    public StudentProfileStage(Student stdnt){
        this.student = stdnt;
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentmanagement/view/studentProfile2.fxml"));
            AnchorPane pane = loader.load();
            spc = loader.getController();
            
            setDialogContainer(PARENT_STACK_PANE);
            setContent(pane);
            setOverlayClose(false);
            
            
            if(student !=null){
                spc.updateStudentDetails(student);
            }
            
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
