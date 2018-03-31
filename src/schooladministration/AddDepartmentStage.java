
package schooladministration;

import com.jfoenix.controls.JFXDialog;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import schooladministration.control.AddDepartmentController;

/**
 *
 * @author ofentse
 */
public class AddDepartmentStage extends JFXDialog{
    
    public AddDepartmentController DIALOG_CONTROLLER;
    
    public AddDepartmentStage(Department department){
        try{
            FXMLLoader ui = new FXMLLoader(getClass().getResource("/schooladministration/view/addDepartment.fxml"));
            AnchorPane pane = ui.load();
            
            DIALOG_CONTROLLER = ui.getController();
            if(department != null){
                DIALOG_CONTROLLER.setName(department.getDepartmentName());
                DIALOG_CONTROLLER.setHOD(department.getHod());
            }
            
            DIALOG_CONTROLLER.setEventHandler((EventHandler) (Event event) -> {
                close();
            });
                        
            setDialogContainer(SchoolAdministartion.ADMIN_MAN_STACK);
            setContent(pane);
            setOverlayClose(false);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
