package studentmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.dbHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CCValidator;
import entry.JFXAlert;
import javafx.scene.layout.GridPane;
import static entry.SMS.getGraphics;
import javafx.scene.control.Tooltip;

/**
 *
 * @author jabari
 */
public class UpdateSWSDialog extends JFXDialog{

    private JFXTextField name;
    private JFXTextArea description;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UpdateSWSDialog(SocialWelfare specialWelfare) {
                    
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
        name.setPromptText("Name");
        name.setPrefWidth(360);
        name.setLabelFloat(true);
        contentGrid.add(name, 0, 0);
        
        CCValidator.setFieldValidator(name, "Name required");
                
        description = new JFXTextArea();
        description.setPromptText("Description");
        description.setLabelFloat(true);
        description.setPrefWidth(360);
        contentGrid.add(description, 0, 1);
        
        container.setCenter(SMS.setBorderContainer(contentGrid, null));
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Add Social Welfare");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //-- Update form entries  ----------------------------------------------
        
        if(specialWelfare != null){
            
            name.setText(specialWelfare.getName());
            description.setText(specialWelfare.getDescription());
            title.setText("Update Social Welfare");
        }
        
        
        //-- Validate and save the form  ---------------------------------------
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new Tooltip("Save"));
        save.setOnAction((ActionEvent event) -> {
            
        if(!"".equals(name.getText().trim())){
                
                if(specialWelfare != null){
                    
                    specialWelfare.setName(name.getText().trim());
                    specialWelfare.setDescriptions((description.getText().trim()));
                    
                    if(dbHandler.updateSWS(specialWelfare, true)){
                        
                        new JFXAlert(JFXAlert.SUCCESS, "Update Successful", "Record has been updated successfully");
                        SocialWelfareSupport.SWSWorkService.restart();
                        SocialWelfareSupport.updateSWSListView();
                        close();
                    }else{
                        new JFXAlert(JFXAlert.ERROR, "Update failed", "Error encountered while trying to update record");
                    }
                    
                }else{
                
                    SocialWelfare sws = new SocialWelfare("0", name.getText().trim(), description.getText().trim());
                    
                    if(dbHandler.updateSWS(sws, false)){
                        
                        new JFXAlert(JFXAlert.SUCCESS, "Update Successful", "New record has been added successfully");
                        SocialWelfareSupport.SWSWorkService.restart();
                        SocialWelfareSupport.updateSWSListView();
                        close();
                       
                    }else{
                        new JFXAlert(JFXAlert.ERROR, "Update failed", "Error encountered while trying to add new record");
                    }
                }
                
            }else{
                name.validate();
                description.validate();
                new JFXAlert(JFXAlert.WARNING, "Input Error", "Ensure that required fields are captured before saving changes.");
            }
            
        });
        
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.getStyleClass().add("secondary-toolbar");
        container.setBottom(footer);

        //-- Set jfxdialog view  -----------------------------------------------
        setDialogContainer(StudentManagement.STUDENT_MAN_STACK);
        setContent(stackPane);
        setOverlayClose(false);
        stackPane.setPrefSize(400, 220);
        show();
        
    }
}
