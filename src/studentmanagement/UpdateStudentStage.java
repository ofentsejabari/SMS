package studentmanagement;

import com.jfoenix.controls.JFXDialog;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import studentmanagement.control.UpdateStudentController;

/**
 *
 * @author ofentse
 */
public class UpdateStudentStage extends JFXDialog{
    
    public static Student student = null;
    UpdateStudentController spc;

    public UpdateStudentStage(Student stdnt){
        this.student = stdnt;
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentmanagement/view/updateStudent.fxml"));
            AnchorPane pane = loader.load();
            spc = loader.getController();
            
            setDialogContainer(PARENT_STACK_PANE);
            setContent(pane);
            setOverlayClose(false);
            
            spc.getCloseButton().setOnAction((ActionEvent event) -> {
                close();
            });
            
            if(student != null){
                spc.updateStudentDetails(student);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
