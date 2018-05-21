package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entry.AutoCompleteComboBoxListener;
import entry.DialogUI;
import entry.HSpacer;
import entry.SMS;
import entry.ToolTip;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import mysqldriver.AdminQuery;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CCValidator;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import javafx.scene.layout.GridPane;
import static entry.SMS.getGraphics;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import static schooladministration.SchoolAdministartion.departmentsController;

/**
 *
 * @author jabari
 */
public class UpdateSubjectDialog extends JFXDialog{

    private JFXTextField name;
    private JFXComboBox<String> department;
    
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UpdateSubjectDialog(Subject subject) {
                    
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
        
        name = new JFXTextField();
        name.setPromptText("Subject Name");
        name.setPrefWidth(360);
        name.setLabelFloat(true);
        contentGrid.add(name, 0, 0);
        
        CCValidator.setFieldValidator(name, "Subject name required.");
                
        department = new JFXComboBox<>(AdminQuery.getDepartmentNames());
        department.setPromptText("Department");
        department.setLabelFloat(true);
        department.setPrefWidth(360);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(department);
        new AutoCompleteComboBoxListener(department);
        contentGrid.add(department, 0, 1);
        if(departmentsController.selectedDepartment != null){
            department.setValue(departmentsController.selectedDepartment.getDepartmentName());
        }   
        
        HBox con = new HBox(10);
        con.setAlignment(Pos.CENTER);
        
        ToggleGroup tg = new ToggleGroup();
        
        JFXRadioButton core = new JFXRadioButton("Core");
        RadioButton option = new JFXRadioButton("Optional");
        
        core.setToggleGroup(tg);
        option.setToggleGroup(tg);
        
        tg.selectToggle(core);
        
        con.getChildren().addAll(core, option);
        
        contentGrid.add(con, 0, 2);
        
        
        container.setCenter(SMS.setBorderContainer(contentGrid, null));
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Add Subject");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //-- Update form entries  ----------------------------------------------
        
        if(subject != null){
            
            name.setText(subject.getDescription());
            core.setSelected(subject.getType().equalsIgnoreCase("1"));
            option.setSelected(subject.getType().equalsIgnoreCase("0"));
            department.setValue(AdminQuery.getDepartmentByID(subject.getDepartmentID()).getDepartmentName());
            
            title.setText("Update Subject");
        }
        
        
        //-- Validate and save the form  ---------------------------------------
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save Subject"));
        save.setOnAction((ActionEvent event) -> {
            
            if(!"".equals(name.getText().trim())){
                
                if(subject == null){
                    
                    Subject subj = new Subject();
                    subj.setSubjectID(SMS.generateDBID());
                    subj.setDescrption(name.getText().trim());
                    subj.setType(((core.isSelected())?"1":"0"));
                    subj.setDepartmentID(AdminQuery.getDepartmentByName(department.getValue().toString()).getID());
                    //subj.setSchoolID("20000");

                    if(AdminQuery.updateSubject(subject, false).equalsIgnoreCase("")){
                        new DialogUI("Subject has been added successfully",
                                    DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, this).show();
                        departmentsController.dws.restart();
                        close();
                    }else{
                        new DialogUI("Exception occurred while trying to add subject details.",
                                DialogUI.ERROR_NOTIF, stackPane, null).show();
                  }
                    
                }else{
                    subject.setDescrption(name.getText().toString());
                    subject.setType(((core.isSelected())?"1":"0"));
                    subject.setDepartmentID(AdminQuery.getDepartmentByName(department.getValue().toString()).getID());
                    
                    if(AdminQuery.updateSubject(subject, true).equalsIgnoreCase("")){
                        new DialogUI("Subject details has been updated successfully",
                                    DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, this).show();
                        departmentsController.dws.restart();
                        close();
                    }else{
                       new DialogUI("Exception occurred while trying to update subject details.",
                                DialogUI.ERROR_NOTIF, stackPane, null).show(); 
                  }
                }
            
            }else{
                name.validate();

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
        stackPane.setPrefSize(400, 200);
        show();
        
    }
    
    
}
