package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import entry.AutoCompleteComboBoxListener;
import entry.DialogUI;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.getGraphics;
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
import static schooladministration.SchoolAdministartion.streamClassesController;

/**
 *
 * @author jabari
 */
public class UpdateStreamDialog extends JFXDialog{

    private JFXTextField name;
    private JFXComboBox<String> hod;
    
    //private final ValidationSupport vSupport;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UpdateStreamDialog(Stream stream) {
                    
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
        name.setPromptText("Stream Name");
        name.setPrefWidth(360);
        name.setLabelFloat(true);
        contentGrid.add(name, 0, 0);
        
        CCValidator.setFieldValidator(name, "Stream required.");
                
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
        
        Label title = new Label("Add Stream");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //-- Update form entries  ----------------------------------------------
        
        if(stream != null){
            name.setText(stream.getDescription());
            title.setText("Update Stream");
        }
        
        
        //-- Validate and save the form  ---------------------------------------
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save Stream"));
        save.setOnAction((ActionEvent event) -> {
            
            if(!"".equals(name.getText().trim())){
                
                if(stream != null){
                    stream.setDescrption(name.getText().trim());
                    
                    if(AdminQuery.updateStream(stream, true)){
                        new DialogUI("Stream details has been updated successfully",
                        DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, this).show();
                        streamClassesController.sws.restart();
                        close();
                    }else{
                        new DialogUI("Exception occurred while trying to update stream details",
                        DialogUI.ERROR_NOTIF, stackPane, null).show();
                    }
                }else{
                
                    Stream strm = new Stream("0", name.getText().trim());
                    
                    if(!AdminQuery.isStreamExists(strm)){
                        
                        if(AdminQuery.updateStream(strm, false)){
                            new DialogUI("New stream has been added successfully",
                            DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, this).show();

                            streamClassesController.sws.restart();
                            close();
                        }else{
                            new DialogUI("Exception occurred while trying to add new stream",
                            DialogUI.ERROR_NOTIF, stackPane, null).show();
                        }
                    }else{
                           new DialogUI("Stream already exists...",
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
