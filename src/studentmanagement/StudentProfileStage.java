package studentmanagement;

import com.jfoenix.controls.JFXDialog;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author ofentse
 */
public class StudentProfileStage extends JFXDialog{

    public StudentProfileStage(){
        try {
            AnchorPane ui = FXMLLoader.load(getClass().getResource("/studentmanagement/view/studentProfile2.fxml"));
            
            setDialogContainer(PARENT_STACK_PANE);
            setContent(ui);
            setOverlayClose(false);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
