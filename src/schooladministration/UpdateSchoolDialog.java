package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entry.HSpacer;
import entry.SMS;
import entry.ToolTip;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CCValidator;
import javafx.scene.layout.GridPane;
import static entry.SMS.getGraphics;

/**
 *
 * @author jabari
 */
public class UpdateSchoolDialog extends JFXDialog{

    private School school;
    private JFXTextField email, fax, tel, website, name;
    private JFXTextArea postalAddress, physicalAddress;
    private JFXCheckBox showName;
    
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UpdateSchoolDialog(School school) {
                    
        //-- Parent Container --
        StackPane stackPane = new StackPane();
        
        BorderPane container = new BorderPane();
        stackPane.getChildren().add(container);
        
        //-- Create form fields and add to content container ------------------- 
        GridPane grid = new GridPane();
        grid.getStyleClass().add("container");
        grid.setStyle("-fx-padding:25 5 15 20;");
        grid.setVgap(20);
        grid.setHgap(10);
                 
        name = new JFXTextField();
        name.setPromptText("School Name");
        name.setLabelFloat(true);
        name.setPrefWidth(300);
        CCValidator.setFieldValidator(name, "School name required.");
        grid.add(name, 0, 0);
                
        website = new JFXTextField();
        website.setPrefWidth(300);
        website.setPromptText("Web Site");
        website.setLabelFloat(true);
        grid.add(website, 1, 0);
        
        tel = new JFXTextField();
        tel.setPromptText("Telephone");
        tel.setLabelFloat(true);
        grid.add(tel, 0, 1);
        CCValidator.setFieldValidator(tel, "Telephone required.");
        
        fax = new JFXTextField();
        fax.setLabelFloat(true);
        fax.setPromptText("Fax");
        grid.add(fax, 1, 1);
        CCValidator.setFieldValidator(tel, "fax required.");
        
        email = new JFXTextField();
        email.setLabelFloat(true);
        email.setPromptText("Email");
        grid.add(email, 0, 2);
        CCValidator.setFieldValidator(fax, "Fax required.");
        
        postalAddress = new JFXTextArea();
        postalAddress.setPrefRowCount(3);
        //postalAddress.setPrefColumnCount(8);
        postalAddress.setPromptText("Postal Address");
        postalAddress.setLabelFloat(true);
        
        grid.add(postalAddress, 0, 3);
        
        physicalAddress = new JFXTextArea();
        physicalAddress.setPrefRowCount(3);
        //physicalAddress.setPrefColumnCount(1);
        physicalAddress.setPromptText("Physical Address");
        physicalAddress.setLabelFloat(true);
        
        grid.add(physicalAddress, 1, 3);
        
        container.setCenter(SMS.setBorderContainer(grid, null));
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Add Subject");
        title.setText("Update School Information");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //-- Update form entries  ----------------------------------------------
        
        if(school != null){
            
            tel.setText(school.getTel());
            name.setText(school.getSchoolName());
            website.setText(school.getWebsite());
            postalAddress.setText(school.getPostalAddress());
            physicalAddress.setText(school.getPhysicalAddress());
            fax.setText(school.getFax());
            email.setText(school.getEmail());
        }
        
        
        //-- Validate and save the form  ---------------------------------------
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save Subject"));
        save.setOnAction((ActionEvent event) -> {
            
            if(!"".equals(name.getText().trim())&& 
               !"".equals(tel.getText().trim())&& 
               !"".equals(fax.getText().trim())){
                
                if(school != null){
                    
                    /*Subject subj = new Subject();
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
                  }*/
                    
                }else{
                    
                    /*subject.setDescrption(name.getText().toString());
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
                  }*/
                }
            
            }else{
                name.validate();
                tel.validate();
                fax.validate();

                /*new DialogUI( "Ensure that mandatory field are filled up... ",
                        DialogUI.ERROR_NOTIF, stackPane, null).show();*/
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
        stackPane.setPrefSize(650, 300);
        show();
        
    }
    
    
}
