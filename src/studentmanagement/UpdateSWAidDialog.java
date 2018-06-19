package studentmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import entry.AutoCompleteComboBoxListener;
import entry.CCValidator;
import entry.JFXAlert;
import javafx.scene.layout.GridPane;
import static entry.SMS.getGraphics;
import javafx.scene.control.Tooltip;

/**
 *
 * @author jabari
 */
public class UpdateSWAidDialog extends JFXDialog{

    private JFXTextField name;
    private final JFXComboBox<String> sw_name;
    private final JFXComboBox<String> coorporation;
    private final JFXTextArea description;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UpdateSWAidDialog(Aid aid) {
                    
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
        name.setPromptText("Aid Name");
        name.setPrefWidth(360);
        name.setLabelFloat(true);
        contentGrid.add(name, 0, 0);
        
        CCValidator.setFieldValidator(name, "Aid name required");
                
        sw_name = new JFXComboBox<>(dbHandler.getSocialWelfareNames());
        sw_name.setPromptText("Social Welfare");
        sw_name.setLabelFloat(true);
        sw_name.setPrefWidth(360);
        new AutoCompleteComboBoxListener(sw_name);
        contentGrid.add(sw_name, 0, 1);
        
        coorporation = new JFXComboBox<>();
        coorporation.setPromptText("Cooperation");
        coorporation.setLabelFloat(true);
        coorporation.setPrefWidth(360);
        new AutoCompleteComboBoxListener(coorporation);
        contentGrid.add(coorporation, 0, 2);
        
        description = new JFXTextArea();
        description.setPrefWidth(360);
        description.setPrefRowCount(4);
        description.setPromptText("Description");
        description.setLabelFloat(true);
        contentGrid.add(description, 0, 3);
        
        container.setCenter(SMS.setBorderContainer(contentGrid, null));
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Add Social Welfare Aid");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //-- Update form entries  ----------------------------------------------
        
        if(aid != null){
            
            name.setText(aid.getName());
            coorporation.setValue(aid.getCooperation());
            sw_name.setValue(dbHandler.getSocialWelfareByID(aid.getSocialWelfareID()).getName());
            description.setText(aid.getDescription());
            title.setText("Update Social Welfare Aid");
        }
        
        
        //-- Validate and save the form  ---------------------------------------
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new Tooltip("Save"));
        save.setOnAction((ActionEvent event) -> {
            
        if(!"".equals(name.getText().trim())){
                
                if(aid != null){
                    
                    aid.setName(name.getText().trim());
                    aid.setSocialWelfare(dbHandler.getSocialWelfareByName(sw_name.getValue()).getId());
                    aid.setCooperation(coorporation.getValue());
                    aid.setDescription(description.getText().trim());
                    
                    if(dbHandler.updateAid(aid, true)){
                        
                        new JFXAlert(JFXAlert.SUCCESS, "Update Successful", "Record has been updated successfully");
                        //SocialWelfareAid.SWSAidWorkService.restart();
                        SocialWelfareAid.updateSWSAidListView();
                        close();
                    }else{
                        new JFXAlert(JFXAlert.ERROR, "Update failed", "Error encountered while trying to update record");
                    }
                    
                }else{
                
                    Aid sws = new Aid("0", dbHandler.getSocialWelfareByName(sw_name.getValue()).getId(),
                                      name.getText().trim(), coorporation.getValue(), description.getText().trim());
                    
                    if(dbHandler.updateAid(sws, false)){
                        
                        new JFXAlert(JFXAlert.SUCCESS, "Update Successful", "New record has been added successfully");
                        //SocialWelfareAid.SWSAidWorkService.restart();
                        SocialWelfareAid.updateSWSAidListView();
                        close();
                       
                    }else{
                        new JFXAlert(JFXAlert.ERROR, "Update failed", "Error encountered while trying to add new record");
                    }
                }
                
            }else{
                name.validate();
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
        //stackPane.setPrefSize(400, 220);
        show();
        
    }
}
