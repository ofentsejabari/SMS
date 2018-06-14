package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import entry.AutoCompleteComboBoxListener;
import entry.DialogUI;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.dbHandler;
import entry.ToolTip;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import mysqldriver.AdminQuery;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import employeemanagement.Employee;
import entry.CCValidator;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import javafx.scene.layout.GridPane;
import static schooladministration.SchoolAdministartion.departmentsController;
import static entry.SMS.getGraphics;
import mysqldriver.EmployeeQuery;

/**
 *
 * @author jabari
 */
public class UpdateDepartmentDialog extends JFXDialog{

    private JFXTextField departTextField;
    private JFXComboBox<String> hod;
    
    //private final ValidationSupport vSupport;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UpdateDepartmentDialog(Department department) {
                    
        //-- Parent Container --
        StackPane stackPane = new StackPane();
        BorderPane container = new BorderPane();
        stackPane.getChildren().add(container);
        
        //-- Create form fields and add to content container ------------------- 
        GridPane contentGrid = new GridPane();
        contentGrid.getStyleClass().add("container");
        contentGrid.setStyle("-fx-padding:25 5 15 20;");
        contentGrid.setVgap(20);
        contentGrid.setHgap(2);
        
        departTextField = new JFXTextField();
        departTextField.setPromptText("Department Name");
        departTextField.setPrefWidth(360);
        departTextField.setLabelFloat(true);
        contentGrid.add(departTextField, 0, 0);
        
        CCValidator.setFieldValidator(departTextField, "Department name required.");
                
        hod = new JFXComboBox<>();
        hod.setPromptText("Head Of Department");
        hod.setLabelFloat(true);
        hod.setPrefWidth(360);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(hod);
        new AutoCompleteComboBoxListener(hod);
        contentGrid.add(hod, 0, 1);
        
        container.setCenter(SMS.setBorderContainer(contentGrid, null));
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Add Department");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //-- Update form entries  ----------------------------------------------
        
        if(department != null){
            Employee employee = EmployeeQuery.getEmployeeByID(department.getHod());
            hod.setItems(dbHandler.getDepartmentEmployeeNames(department.getID()));
            
            departTextField.setText(department.getDepartmentName());
            hod.setValue(employee.getFullName());
            title.setText("Update Department");
        }
        
        
        //-- Validate and save the form  ---------------------------------------
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save Department"));
        save.setOnAction((ActionEvent event) -> {
            
        if(!"".equals(departTextField.getText().trim())){
                
                if(department != null){
                    department.setDepartmentName(departTextField.getText().trim());
                    department.setHod((hod.getValue() == null)? "":EmployeeQuery.getEmployeeByName(hod.getValue()).getEmployeeID());
                    
                    if(AdminQuery.updateDepartment(department, true)){
                        
                        new DialogUI("Department details has been updated successfully",
                        DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, this).show();
                        departmentsController.dws.restart();
                        close();
                    }else{
                        new DialogUI("Exception occurred while trying to update department details",
                        DialogUI.ERROR_NOTIF, stackPane, null).show();
                    }
                    
                }else{
                
                    Department newDepartment = new Department("0", departTextField.getText().trim(), 
                            (hod.getValue() == null)? "":EmployeeQuery.getEmployeeByName(hod.getValue().toString()).getID());
                    
                    if(AdminQuery.updateDepartment(newDepartment, false)){
                        
                        new DialogUI("Department details has been added successfully",
                        DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, null).show();
                        departmentsController.dws.restart();
                        close();
                       
                    }else{
                        new DialogUI("Exception occurred while trying to add department details.",
                        DialogUI.ERROR_NOTIF, stackPane, null).show();
                    }
                }
                
            }else{
                departTextField.validate();
                new DialogUI( "Ensure that mandatory field are filled up... ",
                    DialogUI.ERROR_NOTIF, stackPane, null).show();
            }
            
        });
        
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.getStyleClass().add("secondary-toolbar");
        container.setBottom(footer);

        //-- Set jfxdialog view  -----------------------------------------------
        setDialogContainer(SchoolAdministartion.ADMIN_MAN_STACK);
        setContent(stackPane);
        setOverlayClose(false);
        stackPane.setPrefSize(400, 180);
        show();
        
    }
}
